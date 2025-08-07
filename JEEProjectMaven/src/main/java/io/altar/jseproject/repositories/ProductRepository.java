package io.altar.jseproject.repositories;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Product;

@RequestScoped
public class ProductRepository extends EntityRepository<Product>{

    public ProductRepository() {}

}