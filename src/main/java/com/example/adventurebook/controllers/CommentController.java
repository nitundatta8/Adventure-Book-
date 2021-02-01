package com.example.adventurebook.controllers;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.AdventureImageDao;
import com.example.adventurebook.dao.CommentDao;
import com.example.adventurebook.dao.UserDao;
import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.User;

@RestController
@RequestMapping(value="/api/comment")
public class CommentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	@Autowired
	private AdventureImageDao adventureImageDao;
	@Autowired
	private CommentDao commentDao;
	
	@PostMapping(value="/addComment")
	public void addComment(@RequestParam(value="comment") String comment,@RequestParam(value="adventureImageId") Long adventureImageId  ,Principal principal ) {
		logger.info("adventureImageId:   "+ adventureImageId);
		try {
			String userName = principal.getName();
			User user = userDao.findByUserName(userName);
			logger.info("user:   "+ user.getFirstName());
			AdventureImage adventureImage = adventureImageDao.findAdventureById(adventureImageId);
			logger.info("Location:   "+ adventureImage.getLocation());
			
			commentDao.addComment(comment,adventureImage, user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
