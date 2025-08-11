package io.altar.jseproject.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.Store;

@ApplicationScoped
public class StoreRepository{ // extends EntityRepository<Store>{
	
	@PersistenceContext(unitName = "database")
	EntityManager em;
	
    public StoreRepository() {}
    
    public void save(Store store) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(store);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Store findById(Long id) {
        return em.find(Store.class, id);
    }

    public List<Store> findAll() {
        return em.createQuery("FROM Store", Store.class).getResultList();
    }

    public void update(Store store) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(store);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Store s = em.find(Store.class, id);
            if (s != null) em.remove(s);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}

