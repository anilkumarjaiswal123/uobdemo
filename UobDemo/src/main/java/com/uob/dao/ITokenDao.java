package com.uob.dao;

import java.util.List;

import com.uob.Entity.Token;

public interface ITokenDao {
	List<Token> getAllTokens();
    Token getTokenById(int tokenId,String serviceType);
    int updateStatus(String status,String tokenNumber);
    int updateStatusNextToken(String status,String tokenNumber);
    List<Token> getCurrentActiveToken();
    List<Token> getCompletedToken();
    public void updateToken(Token token);
    void generateToken(int acctNo,String serviceTYpe);
}
