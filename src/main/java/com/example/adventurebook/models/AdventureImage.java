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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

@Entity
@Table(name = "adventureImage")
@ConfigurationProperties(prefix = "file")
public class AdventureImage implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String location;
	@NotNull
	private String describtion;

	private Date currentDate = new Date();

	@Transient
	private transient String uploadDir;

	@NotNull
	private String fileName;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_userId")
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adventureImage", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comment> comments = new ArrayList<Comment>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adventureImage", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<TagProduct> tagProduct = new ArrayList<TagProduct>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "adventureImage", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ClickCommision> clickCommisionList = new ArrayList<ClickCommision>();

	public AdventureImage() {
	}

	public AdventureImage(String location, String describtion) {
		super();
		this.location = location;
		this.describtion = describtion;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Transient
	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
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
		return "AdventureImage [location=" + location + ", describtion=" + describtion + ", currentDate=" + currentDate
				+ "]";
	}

}
