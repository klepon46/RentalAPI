package com.rental.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rental.model.User;

@RestController
@RequestMapping("/student")
public class HelloController {

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ResponseEntity<User> welcome() {
		User user = new User(1, "Gani", "gani@gmail.com", 123);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
