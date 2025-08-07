package io.altar.jseproject.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.Serializable;
import java.util.Collection;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class JUnitTestRepository implements Serializable{
	
	private static final long serialVersionUID = 1149251039409861914L;
	
	@Inject
	private ProductRepository repositoryP;

	
	public JUnitTestRepository() {};
	
	@Test
    public void testGetAllProducts() {
        Product p1 = new Product(null, 0, 0, 0, 0, null);
        Product p2 = new Product(null, 0, 0, 0, 0, null);
        repositoryP.create(p1);
        repositoryP.create(p2);
        Collection<Product> all = repositoryP.getAll();
        assertEquals(2, all.size());
        assertTrue(p1.getId() == 1);
    }
	
}

