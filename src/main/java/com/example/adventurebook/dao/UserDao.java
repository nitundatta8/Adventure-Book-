package com.example.adventurebook.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.User;


@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void addUser(String firstName,String lastrName,String userName,String password) {
		System.out.print(" firstName: "+ firstName );
		User user = new User(firstName,lastrName,userName,password);
		entityManager.persist(user);
		
	}
	
	public User findByUserName(String username) {
		return (User) entityManager.createQuery("from User where userName= :userName").setParameter("userName", username)
				.getSingleResult();
	}

	

}
