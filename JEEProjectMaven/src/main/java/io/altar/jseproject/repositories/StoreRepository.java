package io.altar.jseproject.repositories;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.Store;

@SuppressWarnings("cdi-ambiguous-dependency")
@RequestScoped
public class StoreRepository extends EntityRepository<Store> implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
    
    private StoreRepository() {}
    
}

