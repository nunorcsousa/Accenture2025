package io.altar.jseproject.model;

import java.util.HashSet;
import java.util.Set;

public class Store extends MyEntity {
    private String name;
    private Set<Long> userIds = new HashSet<>();
    private Set<Long> shelfIds = new HashSet<>();

    // Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }

    public Set<Long> getShelfIds() {
        return shelfIds;
    }

    public void setShelfIds(Set<Long> shelfIds) {
        this.shelfIds = shelfIds;
    }
}
