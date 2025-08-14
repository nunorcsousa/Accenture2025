package io.altar.jeeproject.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.altar.jeeproject.model.Store;

@ApplicationScoped
public class StoreRepository {
    @PersistenceContext(unitName = "database")
    private EntityManager em;

    @Transactional
    public Long save(Store e) {
        em.persist(e);
        em.flush();
        return e.getId();
    }

    public Store find(Long id) { return em.find(Store.class, id); }

    public List<Store> findAll() {
        return em.createQuery("SELECT e FROM Store e", Store.class).getResultList();
    }

    @Transactional
    public Store update(Store e) { return em.merge(e); }

    @Transactional
    public void delete(Long id) {
        Store e = find(id);
        if (e != null) em.remove(e);
    }
}
