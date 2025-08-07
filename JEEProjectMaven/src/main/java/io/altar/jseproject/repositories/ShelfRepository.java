package io.altar.jseproject.repositories;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Shelf;

@RequestScoped
public class ShelfRepository extends EntityRepository<Shelf>{

    public ShelfRepository() {}

}