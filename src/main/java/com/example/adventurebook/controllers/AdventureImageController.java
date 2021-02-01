package com.example.adventurebook.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.adventurebook.dao.AdventureImageDao;
import com.example.adventurebook.dao.UserDao;
import com.example.adventurebook.models.AdventureImage;
import com.example.adventurebook.models.UploadFileResponse;
import com.example.adventurebook.models.User;
import com.example.adventurebook.services.FileStorageService;

@RestController
@RequestMapping(value = "/api/AdventureImage")
public class AdventureImageController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private AdventureImageDao adventureImageDao;
	@Autowired
	private UserDao userdao;

	@Autowired
	private FileStorageService fileStorageService;
	@Autowired
	private AdventureImage adventureImage;

	@PostMapping("/createAdventure")
	public UploadFileResponse addAdventure(@RequestParam(value = "location") String location,
			@RequestParam(value = "describtion") String describtion, Principal principal,
			@RequestParam("file") MultipartFile file) {
		String userName = principal.getName();
		User user = userdao.findByUserName(userName);
		logger.info("Location create" + user.getFirstName());
		String fileName = fileStorageService.storeFile(file);

		adventureImageDao.createAdventureImg(location, describtion, user, fileName);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/")
				.path(fileName).toUriString();
		return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());

	}
	
	@GetMapping("/adventureList")
	public List<AdventureImage> getAdvetureList(){
		List<AdventureImage> adventures = adventureImageDao.getAllAdventure();
		return adventures;
	}

	@GetMapping("/adventure")
	@ResponseBody
	public AdventureImage getAdventure(@RequestParam(value = "id") Long id) {

		AdventureImage adImage = adventureImageDao.findAdventureById(id);
		return adImage;
	}

	@GetMapping(value = "/adventureByLocation/{location}")
	public ResponseEntity<List<AdventureImage>> getAdventureByLocation(
			@PathVariable(value = "location") String location) {
		List<AdventureImage> adventureImageList = adventureImageDao.findAdventureByLocation(location);
		// return adventureImageList;
		return new ResponseEntity<List<AdventureImage>>(adventureImageList, HttpStatus.OK);
	}

	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@PutMapping("/adventureImgUpdate/{id}")
	public ResponseEntity<?> update(@RequestBody AdventureImage adventureImage, @PathVariable Long id) {
		try {
			AdventureImage existAdventureImage = adventureImageDao.findAdventureById(id);
			if (existAdventureImage != null) {
				adventureImage.setId(id);
				adventureImage.setCurrentDate(existAdventureImage.getCurrentDate());

				adventureImage.setUser(existAdventureImage.getUser());
				adventureImageDao.update(adventureImage);
			} else {
				logger.info("Id does not match");
			}

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
