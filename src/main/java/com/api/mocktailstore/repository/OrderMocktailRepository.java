package com.api.mocktailstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.mocktailstore.entities.OrderMocktail;
import com.api.mocktailstore.entities.OrderMocktailId;

public interface OrderMocktailRepository extends JpaRepository<OrderMocktail, OrderMocktailId> {

	@Query("SELECT o FROM OrderMocktail o WHERE o.order.orderId=?1")
	public List<OrderMocktail> getMocktailOrderDetailsByOrderId(long orderId);

}
