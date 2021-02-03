package com.example.adventurebook.dao;

import java.util.List;

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
	
	public void addComment(Comment comment,User user) {
		try {
			AdventureImage adventureImage =entityManager.find(AdventureImage.class, comment.getAdventureImage().getId());
			comment.setUser(user);
			comment.setAdventureImage(adventureImage);
			user.getCommentList().add(comment);
			entityManager.persist(comment);
		}catch(NoResultException e) {
			logger.info("No result forund for... ");
		}
		
	}

	public List<Comment> findCommentById(Long adventureId) {
		@SuppressWarnings("unchecked")
		List<Comment> comments = entityManager.createQuery("from Comment where adventureImage.id= :adventureId")
		.setParameter("adventureId", adventureId).getResultList();
		return comments;
	}

}
