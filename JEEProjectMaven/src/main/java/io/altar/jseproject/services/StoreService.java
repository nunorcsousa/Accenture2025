package io.altar.jseproject.services;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Store;
import io.altar.jseproject.repositories.StoreRepository;

@RequestScoped
public class StoreService extends EntityService  implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	private StoreRepository storeRepo;
	
	public StoreService() {};
	
    public Long create(Store entity) {
        return storeRepo.create(entity);
    }

    public Store getById(Long id) {
        return storeRepo.getById(id);
    }

    public Collection<Store> getAll() {
        return storeRepo.getAll();
    }

    public void update(Store entity) {
        storeRepo.update(entity);
    }

    public void delete(Long id) {
        storeRepo.delete(id);
    }
}