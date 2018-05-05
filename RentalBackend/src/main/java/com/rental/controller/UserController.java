package com.rental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.model.Item;
import com.rental.model.User;
import com.rental.repository.ItemRepository;
import com.rental.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping(value = "/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int userId) {

		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			return new ResponseEntity<User>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(new User(), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/{userId}/items")
	public ResponseEntity<List<Item>> getItemsByUserId(@PathVariable("userId") int userId) {

		Optional<List<Item>> items = itemRepository.findByUserId(userId);

		if (items.isPresent()) {
			return new ResponseEntity<List<Item>>(items.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<List<Item>>(new ArrayList<Item>(), HttpStatus.OK);
		}

	}

	@PostMapping(value = "/newuser", consumes = "application/json")
	public ResponseEntity<String> addUser(@RequestBody User user) {
		try {
			userRepository.save(user);
			return new ResponseEntity<String>(HttpStatus.OK);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<String>(e.getRootCause().getMessage(), 
					HttpStatus.BAD_REQUEST);
		}
	}
}
