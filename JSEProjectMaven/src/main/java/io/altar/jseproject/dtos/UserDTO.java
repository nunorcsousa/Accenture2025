package io.altar.jseproject.dtos;

import java.util.Set;

public class UserDTO {
    private Long id;
    private String name;
    private String email;

    // IDs das lojas associadas ao utilizador
    private Set<Long> storeIds;

    public UserDTO() {}

    public UserDTO(Long id, String name, String email, Set<Long> storeIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.storeIds = storeIds;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Set<Long> getStoreIds() { return storeIds; }
    public void setStoreIds(Set<Long> storeIds) { this.storeIds = storeIds; }
}
