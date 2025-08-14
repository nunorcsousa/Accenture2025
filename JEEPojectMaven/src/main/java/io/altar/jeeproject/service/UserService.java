package io.altar.jeeproject.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.altar.jeeproject.model.User;
import io.altar.jeeproject.repository.UserRepository;

@ApplicationScoped
public class UserService {
    @Inject
    private UserRepository repo;

    public Long create(User e) { return repo.save(e); }
    public User findById(Long id) { return repo.find(id); }
    public List<User> getAll() { return repo.findAll(); }
    public User update(User e) { return repo.update(e); }
    public void delete(Long id) { repo.delete(id); }
}
