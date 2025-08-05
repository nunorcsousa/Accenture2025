package io.altar.jseproject.dtos;

import java.util.Set;

public class StoreDTO {
    public long id;
    public String name;
    public Set<Long> userIds;
    public Set<Long> shelfIds;
}

