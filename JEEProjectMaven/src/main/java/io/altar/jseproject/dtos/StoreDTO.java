package io.altar.jseproject.dtos;

import java.util.Set;

public class StoreDTO {
    private Long id;
    private String name;
    private String location;

    private Set<Long> shelfIds;  // IDs das prateleiras da loja
    private Set<Long> userIds;   // IDs dos utilizadores da loja

    public StoreDTO() {}

    public StoreDTO(Long id, String name, String location, Set<Long> shelfIds, Set<Long> userIds) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.shelfIds = shelfIds;
        this.userIds = userIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Set<Long> getShelfIds() { return shelfIds; }
    public void setShelfIds(Set<Long> shelfIds) { this.shelfIds = shelfIds; }

    public Set<Long> getUserIds() { return userIds; }
    public void setUserIds(Set<Long> userIds) { this.userIds = userIds; }
}
