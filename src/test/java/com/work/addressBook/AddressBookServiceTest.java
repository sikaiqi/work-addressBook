package com.work.addressBook;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.gson.Gson;
import com.work.addressBook.bean.ContactPO;
import com.work.addressBook.service.AddressBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WorkAddressBookApplication.class)
public class AddressBookServiceTest {

	private Gson gson = new Gson();
	
	@Autowired
	private AddressBookService addressBookService;

	@Test
	public void testFindAll() {
		List<ContactPO> all = addressBookService.findAll();
		System.out.println(gson.toJson(all));
	}

	@Test
	public void testFindByName() {
		List<ContactPO> list = addressBookService.findByName("金文");
		System.out.println(gson.toJson(list));
	}
	
	@Test
	public void testAddAddress(){
		ContactPO po = new ContactPO();
		po.setAddress("北京朝阳区曙光大道");
		po.setBirthday("1988-11-11");
		po.setEmail("3243@dfas");
		po.setGender("男");
//		po.setId(id);
		po.setName("李金文");
		po.setNumber("123456789");
		po.setQq("987654321");
		
		boolean flag = addressBookService.addAddress(po);
		System.out.println(flag);
	}
	
	@Test
	public void testDeleteAddressById(){
		boolean flag = addressBookService.deleteAddressById(1);
		System.out.println(flag);
	}
	
	@Test
	public void testUpdateAddress(){
		ContactPO po = new ContactPO();
		po.setAddress("北京朝阳区曙光大道");
		po.setBirthday("1988-11-11");
		po.setEmail("3243@dfas");
		po.setGender("男");
		po.setId(1);
		po.setName("李金文");
		po.setNumber("123456788");
		po.setQq("987654321");
		boolean flag = addressBookService.updateAddress(po);
		System.out.println(flag);
	}

}
