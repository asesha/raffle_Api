package com.alkimi.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.Raffle;


@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Integer> {

	List<Raffle> findByEndDateAfter(LocalDate endDate);
}
