package io.altar.jseproject.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import io.altar.jseproject.model.Product;

@ApplicationScoped
public class ProductRepository { // extends EntityRepository<Product> {

	public ProductRepository() {
	}

	public Long save(Product product) {
		EntityManager em = EntityManagerUtil.getEntityManager();
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
		EntityManager em = EntityManagerUtil.getEntityManager();
		return em.find(Product.class, id);
	}

	public List<Product> findAll() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		return em.createQuery("FROM Product", Product.class).getResultList();
	}

	public void update(Product product) {
		EntityManager em = EntityManagerUtil.getEntityManager();
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
		EntityManager em = EntityManagerUtil.getEntityManager();
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