package com.example.adventurebook.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name="campaign")
public class Campaign implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String brand;
	@NotNull
	private String category;
	@NotNull
	private String productName;
	@NotNull
	private String productUrl;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Los_Angeles")
	private Date startDate ;
	@NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="America/Los_Angeles")
	private Date endDate ;
	@NotNull
	private Double commision ;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "campaign", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TagProduct> tagProduct = new ArrayList<TagProduct>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "campaign", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ClickCommision> clickCommisionList = new ArrayList<ClickCommision>();
	
	
	public Campaign() {}
	
	public Campaign(String brand, String category, String productName, String productUrl, Date startDate, Date endDate,
			Double commision) {
		super();
		this.brand = brand;
		this.category = category;
		this.productName = productName;
		this.productUrl = productUrl;
		this.startDate = startDate;
		this.endDate = endDate;
		this.commision = commision;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getCommision() {
		return commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	public List<TagProduct> getTagProduct() {
		return tagProduct;
	}

	public void setTagProduct(List<TagProduct> tagProduct) {
		this.tagProduct = tagProduct;
	}

	public List<ClickCommision> getClickCommisionList() {
		return clickCommisionList;
	}

	public void setClickCommisionList(List<ClickCommision> clickCommisionList) {
		this.clickCommisionList = clickCommisionList;
	}

	@Override
	public String toString() {
		return "Campaign [id=" + id + ", brand=" + brand + ", category=" + category + ", productName=" + productName
				+ ", productUrl=" + productUrl + ", startDate=" + startDate + ", endDate=" + endDate + ", commision="
				+ commision + ", tagProduct=" + tagProduct + ", clickCommisionList=" + clickCommisionList + "]";
	}
	
	
}
