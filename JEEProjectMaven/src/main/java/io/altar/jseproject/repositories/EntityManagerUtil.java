package io.altar.jseproject.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("jseprojectPU");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
