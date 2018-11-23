package com.api.mocktailstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.mocktailstore.entities.OrderMocktail;
import com.api.mocktailstore.entities.PartyOrder;

public interface PartyOrderRepository extends JpaRepository<PartyOrder, Long> {

	@Query("SELECT MAX(orderId) FROM PartyOrder")
	public long getLastPartyOrderId();

	@Query("SELECT o FROM OrderMocktail o WHERE o.order.orderId=?1")
	public List<OrderMocktail> getMocktailOrderDetailsByOrderId(long orderId);

}
