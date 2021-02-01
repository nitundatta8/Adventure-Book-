package com.example.adventurebook.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "tagProduct")
public class TagProduct implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private Double xPos;

	@NotNull
	private Double yPos;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_advtureId")
	private AdventureImage adventureImage;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_campaignId")
	private Campaign campaign;

	public TagProduct() {
	}

	public TagProduct(Double xPos, Double yPos) {
		super();
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public Double getxPos() {
		return xPos;
	}

	public void setxPos(Double xPos) {
		this.xPos = xPos;
	}

	public Double getyPos() {
		return yPos;
	}

	public void setyPos(Double yPos) {
		this.yPos = yPos;
	}

	public AdventureImage getAdventureImage() {
		return adventureImage;
	}

	public void setAdventureImage(AdventureImage adventureImage) {
		this.adventureImage = adventureImage;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	@Override
	public String toString() {
		return "TagProduct [id=" + id + ", xPos=" + xPos + ", yPos=" + yPos + ", adventureImage=" + adventureImage.getId()
				+ ", campaign=" + campaign.getId() + "]";
	}

}
