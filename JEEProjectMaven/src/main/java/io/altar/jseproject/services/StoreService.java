package io.altar.jseproject.services;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.model.Store;
import io.altar.jseproject.repositories.StoreRepository;

@RequestScoped
public class StoreService extends EntityService {

	@Inject
	private StoreRepository storeRepo;

	public StoreService() {
	};

	public void create(Store entity) {
		storeRepo.save(entity);
	}

	public Store getById(Long id) {
		return storeRepo.findById(id);
	}

	public Collection<Store> getAll() {
		return storeRepo.findAll();
	}

	public void update(Store entity) {
		storeRepo.update(entity);
	}

	public void delete(Long id) {
		storeRepo.delete(id);
	}
}