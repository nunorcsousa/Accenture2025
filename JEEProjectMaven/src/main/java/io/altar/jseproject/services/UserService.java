package io.altar.jseproject.services;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.model.User;
import io.altar.jseproject.repositories.UserRepository;

@RequestScoped
public class UserService extends EntityService implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	@Inject
	private UserRepository userRepo;
	
	public UserService() {};
	
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

