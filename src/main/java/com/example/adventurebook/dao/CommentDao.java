package com.example.adventurebook.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.Comment;
import com.example.adventurebook.models.User;

@Repository
@Transactional
public class CommentDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void addComment(String comment,AdventureImage adventureImage,User user) {
		try {
			Comment curr_comment =new Comment(comment);
			curr_comment.setUser(user);
			curr_comment.setAdventureImage(adventureImage);
			user.getCommentList().add(curr_comment);
			entityManager.persist(curr_comment);
		}catch(NoResultException e) {
			logger.info("No result forund for... ");
		}
		
	}

}
