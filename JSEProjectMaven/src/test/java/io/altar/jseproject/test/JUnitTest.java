package io.altar.jseproject.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;


public class JUnitTest {
	
	private ProductRepository repositoryP = ProductRepository.getInstance();
	
	@Test
    public void testGetAllProducts() {
        Product p1 = new Product(null, 0, 0, 0, 0);
        Product p2 = new Product(null, 0, 0, 0, 0);
        repositoryP.create(p1);
        repositoryP.create(p2);
        Collection<Product> all = repositoryP.getAll();
        assertEquals(2, all.size());
        assertTrue(p1.getId() == 1);
    }
	
	@Test
    public void testIvaProducts() {
		Product p = new Product(null, 0, 0, 33, 0);;
		assertThrows(NumberFormatException.class,
	            ()->{ p.getIva(); });
		p.setIva(23);
		assertDoesNotThrow(()->{ p.getIva(); });
	}
	
}

