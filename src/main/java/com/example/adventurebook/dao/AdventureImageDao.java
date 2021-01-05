package com.example.adventurebook.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;

@Repository
@Transactional
public class AdventureImageDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void createAdventureImg(String location,String describtion) {
		AdventureImage advImg = new AdventureImage(location,describtion);
		entityManager.persist(advImg);
		
	}
}
