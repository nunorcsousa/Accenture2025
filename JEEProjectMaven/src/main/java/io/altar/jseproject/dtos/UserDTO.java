package io.altar.jseproject.dtos;

import java.util.Set;

public class UserDTO {
    public long id;
    public String name;
    public String username;
    public String password;
    public Set<Long> storeIds;
}

