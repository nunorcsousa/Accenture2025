package io.altar.jeeproject.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.altar.jeeproject.model.User;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext(unitName = "database")
    private EntityManager em;

    @Transactional
    public Long save(User e) {
        em.persist(e);
        em.flush();
        return e.getId();
    }

    public User find(Long id) { return em.find(User.class, id); }

    public List<User> findAll() {
        return em.createQuery("SELECT e FROM User e", User.class).getResultList();
    }

    @Transactional
    public User update(User e) { return em.merge(e); }

    @Transactional
    public void delete(Long id) {
        User e = find(id);
        if (e != null) em.remove(e);
    }
}
