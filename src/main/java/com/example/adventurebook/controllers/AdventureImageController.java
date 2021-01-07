package com.example.adventurebook.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.AdventureImageDao;
import com.example.adventurebook.dao.UserDao;
import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.User;


@RestController
@RequestMapping(value="/api/AdventureImage")
public class AdventureImageController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdventureImageDao adventureImageDao;
	
	@Autowired
	private UserDao userdao;
	
	@PostMapping
	@ResponseBody
	public void addAdventure(@RequestParam(value="location") String location,@RequestParam(value="describtion")String describtion,Principal principal) {
		String userName = principal.getName();
		User user = userdao.findByUserName(userName);
		logger.info("Location create"+ user.getFirstName());
		adventureImageDao.createAdventureImg(location, describtion,user);
		
	}
	
	@GetMapping("/adventure")
	@ResponseBody
	public AdventureImage getAdventure(@RequestParam(value = "id") Long id) {
		
		AdventureImage adImage = adventureImageDao.findAdventureById(id);
		return adImage;
	}
	
	@GetMapping(value = "/adventureByLocation/{location}")
	public ResponseEntity<AdventureImage> getAdventureByLocation(@PathVariable(value = "location") String location) {
		AdventureImage adventureImage = adventureImageDao.findAdventureByLocation(location);
		return new ResponseEntity<AdventureImage>(adventureImage, HttpStatus.OK);
	}
	
	/*@GetMapping(value = "/username")
    @ResponseBody
    public String currentUserName(Principal principal) {
		logger.info("principal.getName() "+ principal.getName());
        return principal.getName();
    }*/
	
	

}
