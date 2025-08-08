package io.altar.jseproject.repositories;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Store;

@RequestScoped
public class StoreRepository extends EntityRepository<Store>{
	
    public StoreRepository() {}
    
}

