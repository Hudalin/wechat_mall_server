package com.jcg.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcg.mapper.AddressMapper;
import com.jcg.model.Address;
import com.jcg.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<Address> getAddressListByAcc(String account) {
		return addressMapper.getAddressListByAcc(account);
	}

	@Override
	public Address getAddressById(int address_id) {
		return addressMapper.getAddressById(address_id);
	}

	@Override
	public int updateAddress(Address address) {
		return addressMapper.updateAddress(address);
	}

	@Override
	public int deleteAddressById(int address_id) {
		return addressMapper.deleteAddressById(address_id);
	}

	@Override
	public int addAddress(Address address) {
		return addressMapper.addAddress(address);
	}

	
}
