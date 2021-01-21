package com.example.adventurebook.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.adventurebook.exception.FileStorageException;
import com.example.adventurebook.exception.MyFileNotFoundException;
import com.example.adventurebook.models.AdventureImage;

@Service
public class FileStorageService {
private final Path fileStorageLocation;

@Autowired
public FileStorageService(AdventureImage adventureImage) {
    this.fileStorageLocation = Paths.get(adventureImage.getUploadDir())
            .toAbsolutePath().normalize();

    try {
        Files.createDirectories(this.fileStorageLocation);
    } catch (Exception ex) {
        throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
    }
}

public String storeFile(MultipartFile file) {
    // Normalize file name
	
	System.out.println("FileStorageService.storeFile(): " +file.getOriginalFilename() );
	
	String ext =FilenameUtils.getExtension(file.getOriginalFilename());
	
	
	String fileName = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
	

    try {
        // Check if the file's name contains invalid characters
        if(fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        // Copy file to the target location (Replacing existing file with the same name)
        Path targetLocation = this.fileStorageLocation.resolve(fileName);  // c:/user/adve/asdas.jpeg
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    } catch (IOException ex) {
        throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
    }
}

public Resource loadFileAsResource(String fileName) {
    try {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        if(resource.exists()) {
            return resource;
        } else {
            throw new MyFileNotFoundException("File not found " + fileName);
        }
    } catch (MalformedURLException ex) {
        throw new MyFileNotFoundException("File not found " + fileName, ex);
    }
}

}
