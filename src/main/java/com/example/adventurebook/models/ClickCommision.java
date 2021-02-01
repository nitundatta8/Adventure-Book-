package com.example.adventurebook.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clickcommision")
public class ClickCommision {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double commision;

	private Date clickDate = new Date();

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_userId")
	private User user;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_campaignId")
	private Campaign campaign;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_advtureId")
	private AdventureImage adventureImage;

	public ClickCommision() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public Date getClickDate() {
		return clickDate;
	}

	public void setClickDate(Date clickDate) {
		this.clickDate = clickDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public AdventureImage getAdventureImage() {
		return adventureImage;
	}

	public void setAdventureImage(AdventureImage adventureImage) {
		this.adventureImage = adventureImage;
	}

}
