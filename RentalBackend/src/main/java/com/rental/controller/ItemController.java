package com.rental.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.model.Item;
import com.rental.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping(value = "/")
	public ResponseEntity<List<Item>> getAllItems() {
		ResponseEntity<List<Item>> response = null;
		Optional<List<Item>> items = Optional.of(itemRepository.findAll());
		if (items.isPresent()) {
			response = new ResponseEntity<List<Item>>(items.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<Item>>(new ArrayList<Item>(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping(value = "/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable("itemId") int itemId) {
		ResponseEntity<Item> response = null;
		Optional<Item> item = itemRepository.findById(itemId);
		if (item.isPresent()) {
			response = new ResponseEntity<Item>(item.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<Item>(new Item(), HttpStatus.OK);
		}

		return response;
	}

	@GetMapping(value = "/category/{categoryName}")
	public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable("categoryName") String categoryName) {
		ResponseEntity<List<Item>> response = null;

		Optional<List<Item>> items = itemRepository.findByCategory(categoryName);
		if (items.isPresent()) {
			response = new ResponseEntity<List<Item>>(items.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<List<Item>>(new ArrayList<Item>(), HttpStatus.OK);
		}

		return response;
	}

	@PostMapping(value = "/{userId}/createitem", consumes = "application/json")
	public ResponseEntity<String> addUserItem(@PathVariable("userId") int userId, @RequestBody Item item) {
		ResponseEntity<String> response = null;

		Optional<List<Item>> optItems = itemRepository.findByUserId(userId);

		if (optItems.isPresent()) {
			item.setUserId(userId);
			itemRepository.save(item);
			response = new ResponseEntity<String>(HttpStatus.OK);
		} else {
			response = new ResponseEntity<String>("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR	);
		}

		return response;
	}

}
