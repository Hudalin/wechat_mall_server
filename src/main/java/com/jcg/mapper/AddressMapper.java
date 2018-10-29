package com.jcg.mapper;

import java.util.List;

import com.jcg.model.Address;

public interface AddressMapper {

	List<Address> getAddressListByAcc(String account);

	Address getAddressById(int address_id);

	int updateAddress(Address address);

	int deleteAddressById(int address_id);

	int addAddress(Address address);

}
