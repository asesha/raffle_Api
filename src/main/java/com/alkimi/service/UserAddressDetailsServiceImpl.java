package com.alkimi.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkimi.dao.UserAddressDetailsRepository;
import com.alkimi.entities.UserAddressDetails;
import com.alkimi.vo.UserAddressDetailsVO;

@Service
public class UserAddressDetailsServiceImpl implements UserAddressDetailsService {
	
	@Autowired
	private UserAddressDetailsRepository repository;

	@Override
	public UserAddressDetailsVO createUserAddressDetails(UserAddressDetailsVO vo) {
		UserAddressDetails userAddressDetails = repository.save(vo.getUserAddressDetails());
		return userAddressDetails.getUserAddressDetailsVO();
	}

	@Override
	public UserAddressDetailsVO getUserAddressDetailsById(int addressDetailsId) {
		Optional<UserAddressDetails> addressDetails = repository.findById(addressDetailsId);
		return addressDetails.get().getUserAddressDetailsVO();
	}

	@Override
	public UserAddressDetailsVO updateUserAddressDetails(int addressDetailsId, UserAddressDetailsVO vo) {
		Optional<UserAddressDetails> address = repository.findById(addressDetailsId);
		UserAddressDetails modifiedUserAddressDetails = null;
		if(address.get() != null) {
			modifiedUserAddressDetails = address.get();
			modifiedUserAddressDetails.setUpdatedOn(LocalDate.now());
			if ( vo.getFirstName()!=null ) {
				modifiedUserAddressDetails.setFirstName(vo.getFirstName());
			}
			modifiedUserAddressDetails = repository.save(modifiedUserAddressDetails);
	    }
		return modifiedUserAddressDetails.getUserAddressDetailsVO();
	}

	@Override
	public UserAddressDetailsVO deleteUserAddressDetails(int addressDetailsId) {
		Optional<UserAddressDetails> address = repository.findById(addressDetailsId);
		if( address.get() != null) {
			repository.delete( address.get());
	    }
		return address.get().getUserAddressDetailsVO();
	}

}
