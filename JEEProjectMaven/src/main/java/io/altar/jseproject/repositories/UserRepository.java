package io.altar.jseproject.repositories;

import javax.enterprise.context.RequestScoped;

import io.altar.jseproject.model.User;

@RequestScoped
public class UserRepository extends EntityRepository<User>{
	
    public UserRepository() {}

}

