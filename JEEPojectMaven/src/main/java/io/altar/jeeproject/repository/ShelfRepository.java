package io.altar.jeeproject.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.altar.jeeproject.model.Shelf;

@ApplicationScoped
public class ShelfRepository {
    @PersistenceContext(unitName = "database")
    private EntityManager em;

    @Transactional
    public Long save(Shelf e) {
        em.persist(e);
        em.flush();
        return e.getId();
    }

    public Shelf find(Long id) { return em.find(Shelf.class, id); }

    public List<Shelf> findAll() {
        return em.createQuery("SELECT e FROM Shelf e", Shelf.class).getResultList();
    }

    @Transactional
    public Shelf update(Shelf e) { return em.merge(e); }

    @Transactional
    public void delete(Long id) {
        Shelf e = find(id);
        if (e != null) em.remove(e);
    }
}
