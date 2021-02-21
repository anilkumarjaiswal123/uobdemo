package com.uob.service;

import java.util.List;


import com.uob.Entity.Token;
import com.uob.response.GenericResponse;

public interface ITokenService {
	List<Token> getAllTokens();
    //Token getTokenById(int tokenId,String serviceTYpe);
	GenericResponse<String> getTokenById(int tokenId,String serviceTYpe);
	GenericResponse<String> generateToken(int acctNo,String serviceTYpe);
}
