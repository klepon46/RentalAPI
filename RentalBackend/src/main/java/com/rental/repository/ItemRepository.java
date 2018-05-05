package com.rental.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Optional<List<Item>> findByUserId(int userId);
	

	Optional<List<Item>> findByCategory(String category);

}
