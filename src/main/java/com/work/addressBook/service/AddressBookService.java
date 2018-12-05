package com.work.addressBook.service;

import java.util.List;

import com.work.addressBook.bean.ContactPO;

public interface AddressBookService {

	public List<ContactPO> findAll();
	
	public List<ContactPO> findByName(String name);
	
	public boolean addAddress(ContactPO po);
	
	public boolean deleteAddressById(Integer id);
	
	public boolean updateAddress(ContactPO po);
    
	
}
