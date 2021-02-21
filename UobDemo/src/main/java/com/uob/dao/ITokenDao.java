package com.uob.dao;

import java.util.List;

import com.uob.Entity.CustomerDetails;
import com.uob.Entity.Services;
import com.uob.Entity.Token;

public interface ITokenDao {
	List<Token> getAllTokens();
    Token getTokenByTokenNoServiceName(int tokenNo,String serviceType);
    int updateStatus(String status,String tokenNumber);
    int updateStatusNextToken(String status,String tokenNumber);
    List<Token> getCurrentActiveToken(String serviceType);
    List<Token> getCompletedToken();
    public void updateToken(Token token);
    void generateToken(int acctNo,String serviceTYpe);
    List<CustomerDetails> isValidAccountNo(int acctNo);
    List<Services> isValidServiceType(String serviceType);
    public Token getTokenByAcctNoServiceType(int acctNo,String serviceType);
    public boolean tokenExists(String accountNumber, String serviceType);
}
