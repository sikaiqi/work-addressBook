package com.work.addressBook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AddressBookPageController {

	@RequestMapping(value = "/")
	public String toAddress() {
		return "addressBook";
	}
	
}
