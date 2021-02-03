package com.example.adventurebook.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.AdventureImageDao;
import com.example.adventurebook.dao.CommentDao;
import com.example.adventurebook.dao.UserDao;
import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.Comment;
import com.example.adventurebook.models.User;

@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CommentDao commentDao;

	@PostMapping(value = "/addComment")
	public void addComment(@RequestBody Comment comment, Principal principal) {
		logger.info("adventureImageId:   ");
		try {
			String userName = principal.getName();
			User user = userDao.findByUserName(userName);
			commentDao.addComment(comment, user);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@GetMapping("/{adventureId}")
	public ResponseEntity<List<Comment>> findCommentById(@PathVariable("adventureId")Long adventureId){
		List<Comment> commentList = new ArrayList<>() ;
		try {
			commentList = commentDao.findCommentById(adventureId );
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	return new ResponseEntity<> (commentList,HttpStatus.OK); 
	}

}
