package com.vogella.spring.user.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	private long id;
	private String name = "";
	private String email = "";
	private String password = "";
	private List<String> roles = new ArrayList<>();
	private Instant lastLogin = Instant.now();
	private boolean enabled;
	
	public User(long id) {
		this.id = id;
	}

}