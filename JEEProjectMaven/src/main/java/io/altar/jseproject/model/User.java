package io.altar.jseproject.model;

import java.util.HashSet;
import java.util.Set;

public class User extends MyEntity{
	
    private String name;
    private String username;
    private String password;
    private Set<Long> storeIds = new HashSet<>();

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Long> getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(Set<Long> storeIds) {
        this.storeIds = storeIds;
    }
}

