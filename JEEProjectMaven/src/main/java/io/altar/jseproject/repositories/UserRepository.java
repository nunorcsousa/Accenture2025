package io.altar.jseproject.repositories;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import io.altar.jseproject.model.User;

@ApplicationScoped
public class UserRepository { // extends EntityRepository<User>{

	public UserRepository() {
	}

	public void save(User user) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			throw e;
		} finally {
			em.close();
		}
	}

	public User findById(Long id) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		return em.find(User.class, id);
	}

	public List<User> findAll() {
		EntityManager em = EntityManagerUtil.getEntityManager();
		return em.createQuery("FROM User", User.class).getResultList();
	}

	public void update(User user) {
		EntityManager em = EntityManagerUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(user);
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
			User u = em.find(User.class, id);
			if (u != null)
				em.remove(u);
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
