package com.example.adventurebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.User;




@Repository
@Transactional
public class AdventureImageDao {

	@PersistenceContext
	private EntityManager entityManager;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public void createAdventureImg(String location,String describtion,User user) {
		
		AdventureImage advImg = new AdventureImage(location,describtion);
		advImg.setUser(user);
		user.getAdventureImageList().add(advImg);
		entityManager.persist(advImg);
		
	}
	
//	public Book getById(Long bookId) {
//		return (Book) entityManager.createQuery("from   Book where id= :bookId").setParameter("bookId", bookId)
//				.getSingleResult();
//	}
	
	public AdventureImage findAdventureById(Long id) {
		return (AdventureImage) entityManager.createQuery("from AdventureImage where id= :id").setParameter("id", id).getSingleResult();
	}
	
	public AdventureImage findAdventureByLocation(String location) {
		return (AdventureImage) entityManager.createQuery("from AdventureImage where location= :location").setParameter("location", location).getSingleResult();
	}
}
