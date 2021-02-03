package com.example.adventurebook.controllers;

import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.AdventureImageDao;
import com.example.adventurebook.dao.CampaignDao;

import com.example.adventurebook.dao.TagProductDao;
import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.TagProduct;

@RestController
@RequestMapping(value = "/api/tagproduct")
public class TagProductController {
	@Autowired
	private TagProductDao tagDao;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@PostMapping(value = "/createTag", consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE }, 
			produces = { MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
	public void createTag(@RequestBody TagProduct tag) {
		
		logger.info("Taggg  x " + tag.getxPos());
		logger.info("Taggg  cam id  " + tag.getCampaign());
		tagDao.createTag(tag);

	}
	
	@GetMapping("/{id}")
	public TagProduct getTagProductById(@PathVariable("id")Long id) {
		TagProduct tagProduct =	tagDao.findTagById(id);
		return tagProduct;
	}
	
	@GetMapping("/getTagProductByAdvId/{adventureId}")
	public List<TagProduct> getTagProductByadventureId(@PathVariable("adventureId")Long adventureId) {
		List<TagProduct> tagProduct =	tagDao.findTagByadventureId(adventureId);
		return tagProduct;
	}

	
}
