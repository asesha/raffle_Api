package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.RafflePrizeShipmentDetails;


@Repository
public interface RafflePrizeShipmentRepository extends JpaRepository<RafflePrizeShipmentDetails, Integer> {

}



