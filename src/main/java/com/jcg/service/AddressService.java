package com.jcg.service;

import java.util.List;

import com.jcg.model.Address;

public interface AddressService {

	List<Address> getAddressListByAcc(String account);

	Address getAddressById(int address_id);

	int updateAddress(Address address);

	int deleteAddressById(int address_id);

	int addAddress(Address address);

}
