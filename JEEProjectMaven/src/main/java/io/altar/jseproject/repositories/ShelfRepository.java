package io.altar.jseproject.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.Shelf;

@ApplicationScoped
public class ShelfRepository { // extends EntityRepository<Shelf> {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	public ShelfRepository() {
	}

	public Long save(Shelf shelf) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(shelf);
			em.flush();
			tx.commit();
			return shelf.getId();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public Shelf findById(Long id) {
		return em.find(Shelf.class, id);
	}

	public List<Shelf> findAll() {
		return em.createQuery("FROM Shelf", Shelf.class).getResultList();
	}

	public void update(Shelf shelf) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(shelf);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public void delete(Long id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Shelf s = em.find(Shelf.class, id);
			if (s != null)
				em.remove(s);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}
}