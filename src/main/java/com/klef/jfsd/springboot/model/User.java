package com.klef.jfsd.springboot.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    private String id;

    private String name;
    private String displayName;
    private String challenge;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Password> credentials = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getChallenge() {
		return challenge;
	}

	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}

	public List<Password> getCredentials() {
		return credentials;
	}

	public void setCredentials(List<Password> credentials) {
		this.credentials = credentials;
	}

    // Getters and setters remain unchanged
}
