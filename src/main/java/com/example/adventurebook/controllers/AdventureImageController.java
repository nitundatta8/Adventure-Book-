package com.example.adventurebook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.AdventureImageDao;

@RestController
@RequestMapping(value="/api/AdventureImage")
public class AdventureImageController {
	
	@Autowired
	private AdventureImageDao adventureImageDao;
	
	@PostMapping
	@ResponseBody
	public void addData(@RequestParam(value="location") String location,@RequestParam(value="describtion")String describtion) {
		adventureImageDao.createAdventureImg(location, describtion);
		
	}
	
	

}
