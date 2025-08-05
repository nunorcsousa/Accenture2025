package io.altar.jseproject.services;

import java.util.Collection;

import io.altar.jseproject.model.User;
import io.altar.jseproject.repositories.UserRepository;

public class UserService extends EntityService{
	private UserRepository userRepo = UserRepository.getInstance();
	
    public Long create(User entity) {
        return userRepo.create(entity);
    }

    public User getById(Long id) {
        return userRepo.getById(id);
    }

    public Collection<User> getAll() {
        return userRepo.getAll();
    }

    public void update(User entity) {
        userRepo.update(entity);
    }

    public void delete(Long id) {
        userRepo.delete(id);
    }
}

