package com.uob.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uob.Entity.CustomerDetails;
import com.uob.Entity.Services;
import com.uob.Entity.Token;
@Transactional
@Repository
public class TokenDao implements ITokenDao{
		
	@PersistenceContext	
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public Token getTokenByTokenNoServiceName(int tokenNo,String serviceTypes) {
		String s=String.valueOf(tokenNo);
		Token t=null;
		String hql = "FROM Token as token WHERE token.tokenNumber= ? and token.serviceType=? ";
		List<Token> listToken= entityManager.createQuery(hql).setParameter(1, s)
				    
				    .setParameter(2, serviceTypes)
		            .getResultList();
		if(!(listToken.isEmpty() && listToken==null))
		{ 
			for(Token token:listToken) {
				 t=token;
			 }
		}
			 
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public List<Token> getAllTokens() {
         String hql = "FROM Token as token ORDER BY token.tokenNumber";
		
		return (List<Token>) entityManager.createQuery(hql).getResultList();
	
	
	}	
	
	public int updateStatus(String tokenNumber,String seviceType) {
		 int val = entityManager
	     .createQuery("update Token set tokenStatus = \'complete\' where tokenNumber=\'"+tokenNumber+"\' and serviceType=\'"+seviceType+"\'")
	     .executeUpdate();
		 return val;
	}
	
	public int updateStatusNextToken(String tokenNumber,String serviceType) {
		int val = entityManager
	     .createQuery("update Token set tokenStatus = \'active\' where tokenNumber=\'"+tokenNumber+"\' and serviceType=\'"+serviceType+"\'" )
	     .executeUpdate();
		return val;
	}
	@SuppressWarnings("unchecked")
	public List<Token> getCurrentActiveToken(String serviceType) {
		List<Token> activeTokenList = entityManager
	     .createQuery("FROM Token as token where token.tokenStatus = \'active\' and serviceType=\'"+serviceType+"\'"  )
	     .getResultList();
		return activeTokenList;
	}	
	@SuppressWarnings("unchecked")
	public List<Token> getCompletedToken() {
		List<Token> activeTokenList = entityManager
	     .createQuery("FROM Token as token where token.tokenStatus = \'complete\'" )
	     .getResultList();
		return activeTokenList;
	}	
	
	
	@Override
	public void updateToken(Token token) {
		Token tokens = getTokenByTokenNo(token.getTokenNumber());
		tokens.setFeedback(token.getFeedback());
		
		entityManager.flush();
	}
	
	//@SuppressWarnings("unchecked")
	public Token getTokenByTokenNo(String tokenNumber) {
		Token token = (Token)entityManager
	     .createQuery("FROM Token as token where token.tokenNumber ="+tokenNumber )
	     .getResultList().get(0);
		return token;
	}	
	
	public int getMaxTokenByServiceType(String serviceType) {
		String maxValue= entityManager.createQuery("select max(t.tokenNumber) from Token t where t.serviceType="+"\'"+serviceType+"\'", String.class).getSingleResult();
		if(maxValue==null) {
			return 0;
		}
		return Integer.parseInt(maxValue);
	}
	
	public void generateToken(int acctNo,String serviceTYpe) {
		int tokenNumber=getMaxTokenByServiceType(serviceTYpe);
		String status="";
		if(tokenNumber==0) {
			status="active";
		}
		else {
			status="pending";
		}
		String strTokenNumber=String.valueOf(tokenNumber+1);
		String query = "insert into customer_token(token_no,customer_accno,token_status,service_type,customer_feedback) values(?,?,?,?,?)";
		entityManager.createNativeQuery(query)
		   .setParameter(1, strTokenNumber)
		   .setParameter(2, acctNo)
		   .setParameter(3, status)
		   .setParameter(4, serviceTYpe)
		   .setParameter(5, null)
		   
		   .executeUpdate();
	 }
	
	@SuppressWarnings("unchecked")
	public List<CustomerDetails> isValidAccountNo(int acctNo) {
		String custAcctNo=String.valueOf(acctNo);
		String hql = "FROM CustomerDetails as cust WHERE cust.customerAccNo= ?";
		List<CustomerDetails> listCustomer= entityManager.createQuery(hql).setParameter(1, custAcctNo).getResultList();
		
		return listCustomer;
	}	
	
	@SuppressWarnings("unchecked")
	public List<Services> isValidServiceType(String serviceType) {
		
		String hql = "FROM Services as serv WHERE serv.serviceType= ?";
		List<Services> listService= entityManager.createQuery(hql).setParameter(1, serviceType).getResultList();
		
		return listService;
	}	
	
	@SuppressWarnings("unchecked")
	public Token getTokenByAcctNoServiceType(int acctNo,String serviceType) {
		String actNo=String.valueOf(acctNo);
		String hql = "FROM Token as token WHERE token.customerAcctNo= ? and token.serviceType=? ";
		Token token= (Token)entityManager.createQuery(hql).setParameter(1, actNo)
				    .setParameter(2, serviceType)
		            .getResultList().get(0);
		
		return token;
	}	
	
	@Override
	public boolean tokenExists(String accountNumber, String serviceType) {
		String hql = "FROM Token as token WHERE token.customerAcctNo = ? and token.serviceType = ?";
		int count = entityManager.createQuery(hql).setParameter(1, accountNumber)
		              .setParameter(2, serviceType).getResultList().size();
		return count > 0 ? true : false;
	}
	
	
}
