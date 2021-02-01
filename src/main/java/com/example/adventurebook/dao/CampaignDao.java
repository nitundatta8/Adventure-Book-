package com.example.adventurebook.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.Campaign;

@Repository
@Transactional
public class CampaignDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void addCampaign(Campaign campaign) {
		
		//LocalDate date = LocalDate.parse((CharSequence) campaign.getStartDate());
		entityManager.persist(campaign);
	}
	
	public Campaign findCampaignById(Long id) {
		Campaign campaign = new Campaign();
		try {
			campaign = (Campaign) entityManager.createQuery("from Campaign where id=:id").setParameter("id", id).getSingleResult();
			logger.info("cam brand...........   "+campaign.getBrand());
			
		}catch(NoResultException e) {
			
			logger.info("No result forund for... "+e.getMessage());
			e.printStackTrace();
		}
		
		return campaign;
	}
	
	@SuppressWarnings("unchecked")
	public List<Campaign> getcampaignList() {
		return entityManager.createQuery("from Campaign").getResultList();
	}

}
