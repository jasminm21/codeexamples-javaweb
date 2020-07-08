package com.vogella.spring.quiz.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vogella.spring.quiz.repositories.UserRepository;

import com.vogella.spring.quiz.entities.User;

@RestController
@RequestMapping("/api/user")   
public class UserRestController {
	private UserRepository userRepository;
	
	public UserRestController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@EventListener
    public void appReady(ApplicationReadyEvent event) {
		int[] courses = {1}; 
		User user = new User("cxu@vogella.com", 1, courses);
        this.userRepository.save(user);
        String e = "cxu@vogella.com";
        int[] courses2 = {1, 2}; 
		User user2 = new User(e, 2, courses2);
        this.userRepository.save(user2);
    }
	
	@GetMapping("/{userId}")
	 public ResponseEntity<User> getUsers(@PathVariable("userId") Optional<User> userOptional) {
		int[] courses = {1}; 
		User user = new User("cxu@vogella.com", 1, courses);
        this.userRepository.save(user);
        String e = "cxu@vogella.com";
        int[] courses2 = {1, 2, 3}; 
		User user2 = new User(e, 2, courses2);
        user = this.userRepository.save(user2);
		
		/*
		if (!userOptional.isPresent() ) {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
		*/
		return new ResponseEntity<>(userRepository.findByUserId(user.getUserId()), HttpStatus.OK);
      }
	
	@PostMapping("/user")
	public User addUser(@Valid @RequestBody User user) {
		return this.userRepository.save(user);
	}
	
	@GetMapping
	public List<User> getAll(){
		return this.userRepository.findAll();
	}
}
