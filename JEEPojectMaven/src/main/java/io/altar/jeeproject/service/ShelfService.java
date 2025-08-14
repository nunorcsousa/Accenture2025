package io.altar.jeeproject.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jeeproject.model.Shelf;
import io.altar.jeeproject.repository.ShelfRepository;

@ApplicationScoped
public class ShelfService {
    @Inject
    private ShelfRepository repo;

    public Long create(Shelf e) { return repo.save(e); }
    public Shelf findById(Long id) { return repo.find(id); }
    public List<Shelf> getAll() { return repo.findAll(); }
    public Shelf update(Shelf e) { return repo.update(e); }
    public void delete(Long id) { repo.delete(id); }
}
