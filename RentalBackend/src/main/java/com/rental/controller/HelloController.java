package com.rental.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rental.model.User;
import com.rental.repository.UserRepository;

@RestController
@RequestMapping("/student")
public class HelloController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ResponseEntity<User> welcome() {
		Optional<User> user1 = userRepository.findById(5);
		
		User user = new User(1, "Gani", "gani@gmail.com", "123");
		
		return new ResponseEntity<User>(user1.get(), HttpStatus.OK);
	}
}
