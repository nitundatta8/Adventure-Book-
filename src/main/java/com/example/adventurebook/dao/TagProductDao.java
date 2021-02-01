package com.example.adventurebook.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.Campaign;
import com.example.adventurebook.models.TagProduct;

@Repository
@Transactional
public class TagProductDao {
	@PersistenceContext
	private EntityManager entityManager;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	public void createTag(TagProduct tagProduct) {
		AdventureImage adv =entityManager.find(AdventureImage.class, tagProduct.getAdventureImage().getId());
		Campaign cam = entityManager.find(Campaign.class, tagProduct.getCampaign().getId());
		tagProduct.setAdventureImage(adv);
		tagProduct.setCampaign(cam);
		
		entityManager.persist(tagProduct);
		
	}
	
	public TagProduct findTagById(Long id) {
		TagProduct tag=	entityManager.find(TagProduct.class, id);
		return tag;
	}

	public List<TagProduct> findTagByadventureId(Long adventureId) {
		List<TagProduct> tagList=  entityManager.createQuery("from TagProduct where adventureImage.id= :adventureId").setParameter("adventureId", adventureId).getResultList();
		return tagList;
	}
	
}
