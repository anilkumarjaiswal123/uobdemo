package com.uob.service;

import java.util.List;

import com.uob.Entity.Token;

public interface IReviewService {
	List<Token> getCompletedToken();
	public void updateToken(Token token);
}
