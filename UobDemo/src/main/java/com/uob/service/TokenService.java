package com.uob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uob.Entity.Customer;
import com.uob.Entity.Token;
import com.uob.dao.ICustomerDAO;
import com.uob.dao.ITokenDao;

@Service
public class TokenService implements ITokenService{
	@Autowired
	private ITokenDao tokenDAO;
	
	@Override
	public String getTokenById(int tokenNo,String serviceType) {
		String message = "";
		String tokenStatus = "";
		Token obj = tokenDAO.getTokenById(tokenNo,serviceType);
		if(obj!=null)
		{
			tokenStatus = obj.getTokenStatus();
			if(tokenStatus.equals("active")) {
				int val = tokenDAO.updateStatus("active", String.valueOf(tokenNo));
				if(val>0)
				{
					message = "Service done for the token no - "+tokenNo;
					tokenDAO.updateStatusNextToken("active", String.valueOf(tokenNo+1));
				}
			}
			else
			{
				
				List<Token> curentActiveTokenList = tokenDAO.getCurrentActiveToken();
				
				if(curentActiveTokenList.isEmpty() && curentActiveTokenList==null)
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
			message = "This token number "+tokenNo+" is not available. Please provide corect token number";
		return message;
	}
	
	@Override
	public List<Token> getAllTokens(){
		return tokenDAO.getAllTokens();
	}
	
	@Override
	public void generateToken(int acctNo,String serviceType){
		 tokenDAO.generateToken(acctNo, serviceType);
	}
}
