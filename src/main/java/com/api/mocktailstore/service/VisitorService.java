package com.api.mocktailstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.mocktailstore.modals.Visitor;
import com.api.mocktailstore.repository.VisitorRepository;

@Service("visitorService")
public class VisitorService {

	private VisitorRepository visitorRepository;

	@Autowired
	public VisitorService(VisitorRepository visitorRepository) {
		this.visitorRepository = visitorRepository;
	}

	public void saveVisitor(Visitor visitor) {
		visitorRepository.save(visitor);
	}

	public Visitor findByUsername(String username) {
		return visitorRepository.findByUsername(username);
	}
}
