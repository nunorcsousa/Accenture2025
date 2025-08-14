package io.altar.jseproject.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.Product;

@ApplicationScoped
public class ProductRepository { // extends EntityRepository<Product> {

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	public ProductRepository() {
	}

	public Long save(Product product) {
		
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(product);
			em.flush();
			tx.commit();
			em.persist(product);
	        return product.getId();
			
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public Product findById(Long id) {
		return em.find(Product.class, id);
	}

	public List<Product> findAll() {
		return em.createQuery("FROM Product", Product.class).getResultList();
	}

	public void update(Product product) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(product);
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
			Product p = em.find(Product.class, id);
			if (p != null)
				em.remove(p);
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