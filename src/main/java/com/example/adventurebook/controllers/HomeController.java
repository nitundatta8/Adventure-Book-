package com.example.adventurebook.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.adventurebook.dao.UserDao;
import com.example.adventurebook.models.AuthRequest;
import com.example.adventurebook.models.User;
import com.example.adventurebook.util.JwtUtil;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDao userDao;

	@GetMapping("/")
	public String welcome() {
		return "Welcome to Adventure Book";
	}

	@PostMapping("/authenticate")    /*Initially this method return token string*/
    public User generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        
        User user = userDao.findByUserName(authRequest.getUserName());
        user.setPassword(null);
        user.setToken(jwtUtil.generateToken(authRequest.getUserName()));
        
        return user;
    }
}
