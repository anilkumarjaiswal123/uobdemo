package com.uob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uob.Entity.Token;
import com.uob.response.GenericResponse;
import com.uob.service.ITokenService;

@RestController
@RequestMapping("token")
public class TokenController {
	@Autowired
	private ITokenService tokenService;
	
	/**
	 * 
	 * Service the current Active Token and make it completed
	 * and update the next pending token as active
	 * @param serviceType
	 * @return
	 */
	
	@GetMapping("token/{id}")
	public ResponseEntity<GenericResponse<String>> getTokenById(@PathVariable("id") Integer id,@RequestParam("serviceType") String serviceType) {
		GenericResponse<String> response = tokenService.getTokenById(id,serviceType);
		 return new ResponseEntity<GenericResponse<String>>(response, HttpStatus.OK);
	}
	
	/**
	 * Fetch all tokens
	 * @return
	 */
	@GetMapping("tokens")
	public ResponseEntity<List<Token>> getAllTokens() {
		List<Token> list = tokenService.getAllTokens();
		return new ResponseEntity<List<Token>>(list, HttpStatus.OK);
	}
	
	/**
	 * Generate the new token based on valid account no and service type
	 * @param accountNo
	 * @param serviceType
	 * @return
	 */
	@GetMapping("generateToken/{accountNo}")
	public ResponseEntity<GenericResponse<String>> generateToken(@PathVariable("accountNo") Integer accountNo,@RequestParam("serviceType") String serviceType) {
		GenericResponse<String> response=tokenService.generateToken(accountNo,serviceType );
       return new ResponseEntity<GenericResponse<String>>(response, HttpStatus.OK);
	}
}
