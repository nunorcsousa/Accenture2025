package io.altar.jseproject.repositories;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.PersistenceContext;

import io.altar.jseproject.model.User;

@ApplicationScoped
public class UserRepository { // extends EntityRepository<User>{

	@PersistenceContext(unitName = "database")
	EntityManager em;
	
	public UserRepository() {
	}

	 @ElementCollection
	    @CollectionTable(
	        name = "store_users",
	        joinColumns = @JoinColumn(name = "store_id")
	    )
	    @Column(name = "user_id")
	    private Set<Long> userIds = new HashSet<>();

	    @ElementCollection
	    @CollectionTable(
	        name = "store_shelves",
	        joinColumns = @JoinColumn(name = "store_id")
	    )
	    @MapKeyColumn(name = "shelf_id")
	    @Column(name = "quantity")
	    private Map<Long, Integer> shelves = new HashMap<>();
	
	public void save(User user) {
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
		return em.find(User.class, id);
	}

	public List<User> findAll() {
		return em.createQuery("FROM User", User.class).getResultList();
	}

	public void update(User user) {
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
