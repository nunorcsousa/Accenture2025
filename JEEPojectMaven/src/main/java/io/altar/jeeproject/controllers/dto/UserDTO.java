package io.altar.jeeproject.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import io.altar.jeeproject.model.Store;
import io.altar.jeeproject.model.User;

public class UserDTO {
    public Long id;
    public String name;
    public List<Long> storeIds;

    public static UserDTO from(User u) {
        UserDTO d = new UserDTO();
        d.id = u.getId();
        d.name = u.getName();
        d.storeIds = u.getStores().stream().map(Store::getId).collect(Collectors.toList());
        return d;
    }
}
