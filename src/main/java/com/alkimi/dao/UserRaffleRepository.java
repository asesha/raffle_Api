package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.UserRaffle;

@Repository
public interface UserRaffleRepository extends JpaRepository<UserRaffle, Integer> {

	
}
