package com.uob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.uob.Entity.Token;
import com.uob.service.IReviewService;

@RequestMapping("token")
@RestController
public class ReviewController {
	
	@Autowired
	private IReviewService reviewService;
	@GetMapping("reviews")
	public ResponseEntity<List<Token>> getAllTokens() {
		List<Token> list = reviewService.getCompletedToken();
		return new ResponseEntity<List<Token>>(list, HttpStatus.OK);
	}
	
	@PutMapping("review")
	public ResponseEntity<Token> updateToken(@RequestBody Token token) {
		reviewService.updateToken(token);
		return new ResponseEntity<Token>(token, HttpStatus.OK);
	}
	
}
