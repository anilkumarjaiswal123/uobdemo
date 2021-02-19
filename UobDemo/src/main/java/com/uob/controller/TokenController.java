package com.uob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uob.Entity.Token;

import com.uob.service.ITokenService;

@RestController
@RequestMapping("token")
public class TokenController {
	@Autowired
	private ITokenService tokenService;
	
	
	@GetMapping("token/{id}")
	public ResponseEntity<String> getTokenById(@PathVariable("id") Integer id,@RequestParam("serviceType") String serviceType) {
		String message = tokenService.getTokenById(id,serviceType);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping("tokens")
	public ResponseEntity<List<Token>> getAllTokens() {
		List<Token> list = tokenService.getAllTokens();
		return new ResponseEntity<List<Token>>(list, HttpStatus.OK);
	}
	
	@GetMapping("generateToken/{accountNo}")
	public ResponseEntity<String> generateToken(@PathVariable("accountNo") Integer accountNo,@RequestParam("serviceType") String serviceType) {
       tokenService.generateToken(accountNo,serviceType );
       return new ResponseEntity<String>("Token is generated", HttpStatus.OK);
	}
}
