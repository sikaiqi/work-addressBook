package com.work.addressBook.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.work.addressBook.bean.ContactPO;
import com.work.addressBook.service.AddressBookService;

@RestController
@RequestMapping("addressBook")
public class AddressBookRestController {

	@Autowired
	private AddressBookService addressBookService;

	private Gson gson = new Gson();

	@RequestMapping(value = "/findAllContacts", method = RequestMethod.GET)
	public Map<String,Object> queryAll(
			@RequestParam(value = "page", defaultValue = "1") String pageNo,
			@RequestParam(value = "rows", defaultValue = "5") String pageSize) {
		
		PageHelper.startPage(Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		List<ContactPO> list = addressBookService.findAll();
		PageInfo<ContactPO> pageInfo = new PageInfo<>(list);
		long total = pageInfo.getTotal();
		Map<String, Object> map = new HashMap<>();
		// total 存放总记录数
		map.put("total", total);
		// rows存放每页记录 ，这里的两个参数名是固定的，必须为 total和 rows
		map.put("rows", list);
		return map;
	}

	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
	public List<ContactPO> queryByName(@PathVariable("name") String name) {
		List<ContactPO> list = addressBookService.findByName(name);
		return list;
	}

	@RequestMapping(value = "/addContact", method = RequestMethod.POST)
	public Map<String, String> addAddress(@RequestBody String json) {
		Map<String, String> map = new HashMap<>();
		try {
			ContactPO po = gson.fromJson(json, ContactPO.class);
			boolean flag = addressBookService.addAddress(po);
			if (flag) {
				map.put("success", "true");
			} else {
				map.put("msg", "添加联系人失败!");
			}
		} catch (Exception e) {
			map.put("msg", "添加联系人失败!");
		}
		return map;
	}

	@RequestMapping(value = "/updateContact", method = RequestMethod.POST)
	public Map<String, String> updateAddress(@RequestBody String json) {
		Map<String, String> map = new HashMap<>();
		try {
			ContactPO po = gson.fromJson(json, ContactPO.class);
			boolean flag = addressBookService.updateAddress(po);
			if (flag) {
				map.put("success", "true");
			} else {
				map.put("msg", "修改联系人失败!");
			}
		} catch (Exception e) {
			map.put("msg", "修改联系人失败!");
		}
		return map;
	}

	@RequestMapping(value = "/deleteContact/{addressId}", method = RequestMethod.GET)
	public Map<String, String> updateAddress(
			@PathVariable("addressId") Integer id) {
		Map<String, String> map = new HashMap<>();
		try {
			boolean flag = addressBookService.deleteAddressById(id);
			if (flag) {
				map.put("success", "true");
			} else {
				map.put("msg", "删除联系人失败!");
			}
		} catch (Exception e) {
			map.put("msg", "删除联系人失败!");
		}
		return map;
	}
}
