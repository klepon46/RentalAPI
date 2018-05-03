package com.rental.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rental.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
