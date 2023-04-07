package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.RaffleTicketConfig;


@Repository
public interface RaffleTicketConfigRepository extends JpaRepository<RaffleTicketConfig, Integer> {


}



