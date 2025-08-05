package io.altar.jseproject.repositories;

import io.altar.jseproject.model.User;

public class UserRepository extends EntityRepository<User> {
    private static final UserRepository INSTANCE = new UserRepository();
    
    private UserRepository() {}
    
    public static UserRepository getInstance() {
        return INSTANCE;
    }
}

