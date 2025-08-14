package io.altar.jeeproject.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jeeproject.model.Store;
import io.altar.jeeproject.repository.StoreRepository;

@ApplicationScoped
public class StoreService {
    @Inject
    private StoreRepository repo;

    public Long create(Store e) { return repo.save(e); }
    public Store findById(Long id) { return repo.find(id); }
    public List<Store> getAll() { return repo.findAll(); }
    public Store update(Store e) { return repo.update(e); }
    public void delete(Long id) { repo.delete(id); }
}
