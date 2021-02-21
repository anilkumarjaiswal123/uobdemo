package com.uob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.uob.Entity.Customer;
import com.uob.Entity.CustomerDetails;
import com.uob.Entity.Services;
import com.uob.Entity.Token;
import com.uob.dao.ICustomerDAO;
import com.uob.dao.ITokenDao;
import com.uob.response.GenericResponse;

@Service
public class TokenService implements ITokenService{
	@Autowired
	private ITokenDao tokenDAO;
	
	/**
	 * update the current active token to completed
	 * and next pending token to active
	 * and also check whether account number and service type valid or not
	 * maintain the order in which token to be serviced 
	 */
	
	@Override
	public GenericResponse<String> getTokenById(int tokenNo,String serviceType) {
		String message = "";
		String tokenStatus = "";
		Token obj = tokenDAO.getTokenByTokenNoServiceName(tokenNo,serviceType);
		if(obj!=null)
		{
			tokenStatus = obj.getTokenStatus();
			if(tokenStatus.equals("active")) {
				int val = tokenDAO.updateStatus(String.valueOf(tokenNo),serviceType);
				if(val>0)
				{
					message = "Service done for the token no - "+tokenNo+" of service"+serviceType ;
					tokenDAO.updateStatusNextToken(String.valueOf(tokenNo+1),serviceType);
					return new GenericResponse<String>(message);
				}
			}
			else
			{
				List<Token> curentActiveTokenList = tokenDAO.getCurrentActiveToken(serviceType);
				
				if( curentActiveTokenList==null || curentActiveTokenList.isEmpty() )
				{
					message = "This token number is not available. Please provide corect token number";
				}
				else
				{
					int currentActiveTokenNo = Integer.parseInt(curentActiveTokenList.get(0).getTokenNumber());
					if(currentActiveTokenNo > tokenNo)
					{
						message = "The service for this token is already completed "+tokenNo;
					}
					else
						message = "The sequesnce of this token "+ tokenNo + " will come after the service completed for the current active token = "+currentActiveTokenNo;		
				}
			}
		}
		else
			message = "This token number "+tokenNo+" is not available. Please provide correct token number";
		return new GenericResponse<String>(message, 400, null);
	}
	
	/**
	 * Fetch All Tokens
	 */
	@Override
	public List<Token> getAllTokens(){
		return tokenDAO.getAllTokens();
	}
	
	/**
	 * Generate new token based on valid account no and service type
	 */
	@Override
	public GenericResponse<String> generateToken(int acctNo,String serviceType){
		String message="";
		boolean flag=tokenDAO.tokenExists(String.valueOf(acctNo), serviceType);
		if(flag==true) {
			message="Token with Account NUmber "+acctNo+ " and services with "+ serviceType+" Already Exist";
			return new GenericResponse<String>(message, 400, null);
		}
		else {
		List<CustomerDetails> listCustomer=tokenDAO.isValidAccountNo(acctNo);
		List<Services> listServices=tokenDAO.isValidServiceType(serviceType);
		if((listCustomer==null || listCustomer.isEmpty())||(listServices==null||listServices.isEmpty()) ) {
			message="please provide valid Acctount No and services";
			return new GenericResponse<String>(message, 400, null);
		}
		else {
			tokenDAO.generateToken(acctNo, serviceType);
			Token token=tokenDAO.getTokenByAcctNoServiceType(acctNo, serviceType);
			message="Token No- "  +token.getTokenNumber() + " generated for Account No "+acctNo+" service "+serviceType;
			return new GenericResponse<String>(message);
		}
	//	return message;
		}
	}
}
