package com.example.adventurebook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.adventurebook.models.AdventureImage;

@SpringBootApplication
@EnableConfigurationProperties({
	AdventureImage.class
})
public class AdventurebookApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(AdventurebookApplication.class, args);
	}

}
