package com.example.adventurebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.UserDao;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
	@Autowired
	private UserDao userDao;
	

	@PostMapping("/createUser")
	@ResponseBody
	public void createUser(@RequestParam(value = "firstName") String firstName,
			@RequestParam(value = "lastName") String lastName, @RequestParam(value = "userName") String userName,
			@RequestParam(value = "password") String password) {
		System.out.print("firstName$$: "+ firstName);
		userDao.addUser(firstName, lastName, userName, password);

	}

}
