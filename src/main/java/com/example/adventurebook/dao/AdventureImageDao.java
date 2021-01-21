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

	public void createAdventureImg(String location, String describtion, User user, String fileName) {

		AdventureImage advImg = new AdventureImage(location, describtion);
		advImg.setUser(user);
		advImg.setFileName(fileName);
		user.getAdventureImageList().add(advImg);
		entityManager.persist(advImg);

	}
	/*edit adventure*/
	public void update(AdventureImage adventureImage) {
		entityManager.merge(adventureImage);
		
	}

	public AdventureImage findAdventureById(Long id) {
		return (AdventureImage) entityManager.createQuery("from AdventureImage where id= :id").setParameter("id", id)
				.getSingleResult();
	}

	public List<AdventureImage> findAdventureByLocation(String location) {

		List<AdventureImage> advImgList = entityManager
				.createQuery("from AdventureImage where location= :location", AdventureImage.class)
				.setParameter("location", location).getResultList();
		// advImgList.forEach(action) ---->>> check it later
		advImgList.forEach(str -> System.out.println("Str:   " + str));
		return advImgList;
	}
}
