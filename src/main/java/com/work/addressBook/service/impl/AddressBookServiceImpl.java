package com.work.addressBook.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.addressBook.bean.ContactPO;
import com.work.addressBook.mapper.AddressBookMapper;
import com.work.addressBook.service.AddressBookService;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	@Override
	public List<ContactPO> findAll() {
		return addressBookMapper.findAll();
	}

	@Override
	public List<ContactPO> findByName(String name) {
		return addressBookMapper.findByName(name);
	}

	@Override
	public boolean addAddress(ContactPO po) {
		return addressBookMapper.addAddress(po) > 0;
	}

	@Override
	public boolean deleteAddressById(Integer id) {
		return addressBookMapper.deleteAddressById(id) > 0;
	}

	@Override
	public boolean updateAddress(ContactPO po) {
		return addressBookMapper.updateAddress(po) > 0;
	}

}
