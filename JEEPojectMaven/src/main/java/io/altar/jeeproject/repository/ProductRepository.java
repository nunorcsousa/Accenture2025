package io.altar.jeeproject.repository;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import io.altar.jeeproject.model.Product;

@ApplicationScoped
public class ProductRepository {
    @PersistenceContext(unitName = "database")
    private EntityManager em;

    @Transactional
    public Long save(Product e) {
        em.persist(e);
        em.flush();
        return e.getId();
    }

    public Product find(Long id) { return em.find(Product.class, id); }

    public List<Product> findAll() {
        return em.createQuery("SELECT e FROM Product e", Product.class).getResultList();
    }

    @Transactional
    public Product update(Product e) { return em.merge(e); }

    @Transactional
    public void delete(Long id) {
        Product e = find(id);
        if (e != null) em.remove(e);
    }
}
