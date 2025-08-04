package io.altar.jseproject.repositories;

import io.altar.jseproject.model.MyEntity;
import io.altar.jseproject.repositories.interfaces.EntityCRUDInterface;

import java.util.*;

public abstract class EntityRepository<T extends MyEntity> implements EntityCRUDInterface<T>{
    protected Map<Long, T> database = new HashMap<>();
    private Long currentId = 0L;

    private Long getNextId() {
        return ++currentId;
    }

    public Long create(T entity) {
        Long id = getNextId();
        entity.setId(id);
        database.put(id, entity);
        return id;
    }

    public T getById(Long id) {
        return database.get(id);
    }

    public Collection<T> getAll() {
        return database.values();
    }

    public void update(T entity) {
        database.put(entity.getId(), entity);
    }

    public void delete(Long id) {
        database.remove(id);
    }
}