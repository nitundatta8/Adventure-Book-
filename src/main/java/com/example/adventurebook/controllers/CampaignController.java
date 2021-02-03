package com.example.adventurebook.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.CampaignDao;
import com.example.adventurebook.models.Campaign;

@RestController
@RequestMapping(value="/api/campaign")
public class CampaignController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CampaignDao campaignDao;
	
	@RequestMapping(value="/createCampaign")
	public void createCampaign(@RequestBody Campaign campaign ) {
		campaignDao.addCampaign(campaign);
		
	}
	
	@RequestMapping(value="campaignList")
	public List<Campaign> getcampaignList() {
		List<Campaign> list=campaignDao.getcampaignList();
		return list;
	}
	
	
	@GetMapping("/{id}")
	public Campaign getcampaignById(@PathVariable("id") Long id) {
		Campaign campaign=campaignDao.findCampaignById(id);
		return campaign;
	}
	
	@GetMapping("/{brand}/{category}")
	public ResponseEntity<List<Campaign>> findProductNameFRCampaign(@PathVariable String brand,@PathVariable String category){
		List<Campaign> list = new ArrayList<>();
		try {
			list = campaignDao.findProductNameFRCampaign(brand,category);
			
		}catch(Exception e) {
			return new ResponseEntity<List<Campaign>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Campaign>>(list,HttpStatus.OK);
	}
}
