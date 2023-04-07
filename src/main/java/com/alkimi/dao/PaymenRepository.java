package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.Payment;

@Repository
public interface PaymenRepository extends JpaRepository<Payment, Integer> {

	
}
