package io.altar.jseproject.services;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;

@RequestScoped
public class ProductService extends EntityService{
	
	@Inject
	private ProductRepository productRepo;
	
	public ProductService(){};
	
    public Long create(Product entity) {
        return productRepo.create(entity);
    }

    public Product getById(Long id) {
        return productRepo.getById(id);
    }

    public Collection<Product> getAll() {
        return productRepo.getAll();
    }

    public void update(Product entity) {
        productRepo.update(entity);
    }

    public void delete(Long id) {
        productRepo.delete(id);
    }
}
