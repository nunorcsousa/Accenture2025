package io.altar.jeeproject.controllers.dto;

import java.util.List;
import java.util.stream.Collectors;

import io.altar.jeeproject.model.Store;
import io.altar.jeeproject.model.User;

public class StoreDTO {
    public Long id;
    public String name;
    public List<Long> userIds;

    public static StoreDTO from(Store s) {
        StoreDTO d = new StoreDTO();
        d.id = s.getId();
        d.name = s.getName();
        d.userIds = s.getUsers().stream().map(User::getId).collect(Collectors.toList());
        return d;
    }
}
