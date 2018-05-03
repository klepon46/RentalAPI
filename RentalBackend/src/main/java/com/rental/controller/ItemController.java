package com.rental.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rental.model.Item;
import com.rental.repository.ItemRepository;

@RestController
@RequestMapping("/items")
public class ItemController {

	@Autowired
	private ItemRepository itemRepository;

	@RequestMapping("/")
	public ResponseEntity<List<Item>> getAllItems() {
		List<Item> items = itemRepository.findAll();
		ResponseEntity<List<Item>> response = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

		return response;
	}

	@RequestMapping("/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable("itemId") int itemId) {
		Optional<Item> item = itemRepository.findById(itemId);
		ResponseEntity<Item> response = new ResponseEntity<Item>(item.get(), HttpStatus.OK);

		return response;
	}

	@RequestMapping("/{userId}/items")
	public ResponseEntity<List<Item>> getItemsByUserId(@PathVariable("userId") int userId) {
		List<Item> items = itemRepository.findByUserId(userId);
		ResponseEntity<List<Item>> response = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

		return response;
	}

	@RequestMapping("/category/{categoryName}")
	public ResponseEntity<List<Item>> getItemsByCategory(@PathVariable("categoryName") String categoryName) {
		List<Item> items = itemRepository.findByCategory(categoryName);
		ResponseEntity<List<Item>> response = new ResponseEntity<List<Item>>(items, HttpStatus.OK);

		return response;
	}

}
