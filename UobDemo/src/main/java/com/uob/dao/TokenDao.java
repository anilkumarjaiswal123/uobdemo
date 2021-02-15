package com.uob.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.uob.Entity.Customer;
import com.uob.Entity.Token;
@Transactional
@Repository
public class TokenDao implements ITokenDao{
	
	 
	
	
	@PersistenceContext	
	private EntityManager entityManager;
	@SuppressWarnings("unchecked")
	@Override
	public Token getTokenById(int tokenNo,String serviceTypes) {
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
	
	public int updateStatus(String status,String tokenNumber) {
		 int val = entityManager
	     .createQuery("update Token set tokenStatus = \'complete\' where tokenNumber="+tokenNumber)
	     .executeUpdate();
		 return val;
	}
	
	public int updateStatusNextToken(String status,String tokenNumber) {
		int val = entityManager
	     .createQuery("update Token set tokenStatus = \'active\' where tokenNumber="+tokenNumber+" and serviceType=\'deposit\'" )
	     .executeUpdate();
		return val;
	}
	@SuppressWarnings("unchecked")
	public List<Token> getCurrentActiveToken() {
		List<Token> activeTokenList = entityManager
	     .createQuery("FROM Token as token where token.tokenStatus = \'active\' and token.serviceType=\'deposit\'" )
	     .getResultList();
		return activeTokenList;
	}	
	@SuppressWarnings("unchecked")
	public List<Token> getCompletedToken() {
		List<Token> activeTokenList = entityManager
	     .createQuery("FROM Token as token where token.tokenStatus = \'complete\' and token.serviceType=\'deposit\'" )
	     .getResultList();
		return activeTokenList;
	}	
	
	
	@Override
	public void updateToken(Token token) {
		Token tokens = getTokenByTokenNo(token.getTokenNumber());
		tokens.setFeedback(token.getFeedback());
		//artcl.setCategory(article.getCategory());
		entityManager.flush();
	}
	
	//@SuppressWarnings("unchecked")
	public Token getTokenByTokenNo(String tokenNumber) {
		Token token = (Token)entityManager
	     .createQuery("FROM Token as token where token.tokenNumber ="+tokenNumber+"  and token.serviceType=\'deposit\'" )
	     .getResultList().get(0);
		return token;
	}	
	
}
