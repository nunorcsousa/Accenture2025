package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Store;

public class StoreRepository extends EntityRepository<Store> {
    private static final StoreRepository INSTANCE = new StoreRepository();
    
    private StoreRepository() {}
    
    public static StoreRepository getInstance() {
        return INSTANCE;
    }
}

