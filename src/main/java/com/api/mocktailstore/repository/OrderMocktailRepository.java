package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mocktailstore.entities.OrderMocktail;
import com.api.mocktailstore.entities.OrderMocktailId;

public interface OrderMocktailRepository extends JpaRepository<OrderMocktail, OrderMocktailId> {

}
