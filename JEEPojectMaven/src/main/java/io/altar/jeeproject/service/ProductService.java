package io.altar.jeeproject.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jeeproject.model.Product;
import io.altar.jeeproject.repository.ProductRepository;

@ApplicationScoped
public class ProductService {
    @Inject
    private ProductRepository repo;

    public Long create(Product e) { return repo.save(e); }
    public Product findById(Long id) { return repo.find(id); }
    public List<Product> getAll() { return repo.findAll(); }
    public Product update(Product e) { return repo.update(e); }
    public void delete(Long id) { repo.delete(id); }
}
