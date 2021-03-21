package com.example.adventurebook.dao;

import java.security.Principal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.Campaign;
import com.example.adventurebook.models.ClickCommission;
import com.example.adventurebook.models.User;

@Repository
@Transactional
public class ClickCommissionDao {

	@PersistenceContext
	private EntityManager entityManager;
	@Autowired
	private UserDao userDao;

	public ClickCommission createClickCommision(ClickCommission clickCommision, String userName) {
		AdventureImage advImg = entityManager.find(AdventureImage.class, clickCommision.getAdventureImage().getId());
		Campaign campaign = entityManager.find(Campaign.class, clickCommision.getCampaign().getId());
		User user = userDao.findByUserName(userName);
		clickCommision.setAdventureImage(advImg);
		clickCommision.setCampaign(campaign);
		clickCommision.setUser(user);
		clickCommision.setCommision(campaign.getCommission());
		entityManager.persist(clickCommision);
		return clickCommision;

	}

	public List<ClickCommission> getclickCommissionList() {
		@SuppressWarnings("unchecked")
		List<ClickCommission> list = entityManager.createQuery("from ClickCommission").getResultList();
		return list;

	}

}
