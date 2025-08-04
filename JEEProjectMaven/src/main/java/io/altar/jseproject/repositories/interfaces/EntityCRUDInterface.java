package io.altar.jseproject.repositories.interfaces;

import java.util.Collection;

public interface EntityCRUDInterface<T>{
	
    public Long create(T entity);

    public T getById(Long id);

    public Collection<T> getAll();

    public void update(T entity);

    public void delete(Long id);

}
