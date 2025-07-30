package io.altar.jseproject.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;


public class JUnitTest {
	
	private ProductRepository repositoryP;
	
	@Test
    public void testGetAllProducts() {
//		Product(String name, double price, int discount, int iva, int quantity, long dailyprice)
        Product p1 = new Product("Produto A", 100.0, 10, 23, 1, 80.0);
        Product p2 = new Product("Produto B", 50.0, 5, 23, 2, 45.0);
        repositoryP.create(p1);
        repositoryP.create(p2);

        Collection<Product> all = repositoryP.getAll();
        assertEquals(2, all.size());
        assertTrue(p1.getId() == 1);
    }
	
}

