package com.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	List<Item> findByUserId(int userId);

	List<Item> findByCategory(String category);

}
