package com.api.mocktailstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.mocktailstore.entities.PartyOrder;

public interface PartyOrderRepository extends JpaRepository<PartyOrder, Long> {

	@Query("SELECT MAX(orderId) FROM PartyOrder")
	public long getLastPartyOrderId();

}
