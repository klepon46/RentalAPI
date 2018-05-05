package com.rental.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
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
		List<Item> items = itemRepository.findAll();
		ResponseEntity<List<Item>> response = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

		return response;
	}

	@GetMapping(value = "/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable("itemId") int itemId) {
		Optional<Item> item = itemRepository.findById(itemId);
		ResponseEntity<Item> response = new ResponseEntity<Item>(item.get(), HttpStatus.OK);

		return response;
	}

	@GetMapping(value = "/category/{categoryName}")
	public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable("categoryName") String categoryName) {
		List<Item> items = itemRepository.findByCategory(categoryName);
		ResponseEntity<List<Item>> response = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

		return response;
	}

	@PostMapping(value = "/{userId}/createitem", consumes = "application/json")
	public ResponseEntity<Void> addUserItem(@PathVariable("userId") int userId, @RequestBody Item item) {
		Optional<List<Item>> optItems = itemRepository.findByUserId(userId);
		item.setUserId(userId);

		optItems.ifPresent(x -> itemRepository.save(item));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
