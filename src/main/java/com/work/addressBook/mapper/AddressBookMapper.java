package com.work.addressBook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.work.addressBook.bean.ContactPO;

@Mapper
public interface AddressBookMapper {

    List<ContactPO> findAll();
    
    List<ContactPO> findByName(@Param("name") String name);
    
	int addAddress(ContactPO po);
	
	int deleteAddressById(@Param("id")Integer id);
	
	int updateAddress(ContactPO po);
    
}