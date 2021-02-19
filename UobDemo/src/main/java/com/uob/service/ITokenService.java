package com.uob.service;

import java.util.List;

import com.uob.Entity.Token;

public interface ITokenService {
	List<Token> getAllTokens();
    //Token getTokenById(int tokenId,String serviceTYpe);
	String getTokenById(int tokenId,String serviceTYpe);
	void generateToken(int acctNo,String serviceTYpe);
}
