package com.price.calculator.v1.repository;

import com.price.calculator.v1.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    public Optional<Item> findByName(String name);
}
