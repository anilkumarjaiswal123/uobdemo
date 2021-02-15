package com.uob.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uob.Entity.Token;
import com.uob.dao.ITokenDao;

@Service
public class ReviewService implements IReviewService{
	@Autowired
	private ITokenDao tokenDAO;
	@Override
	public List<Token> getCompletedToken(){
		return tokenDAO.getCompletedToken();
	}
	@Override
	public void updateToken(Token token) {
		tokenDAO.updateToken(token);
	}
	
}
