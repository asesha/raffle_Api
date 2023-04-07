package com.alkimi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alkimi.entities.UserAddressDetails;


@Repository
public interface UserAddressDetailsRepository extends JpaRepository<UserAddressDetails, Integer> {

}



