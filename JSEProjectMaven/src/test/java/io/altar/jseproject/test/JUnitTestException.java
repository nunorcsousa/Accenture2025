package io.altar.jseproject.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;

public class JUnitTestException{
	
		@Test
    public void testIvaProducts() {
		Product p = new Product(null, 0, 0, 33, 0, null);
		p.setIva(33);
		assertThrows(NumberFormatException.class,
	            ()->{ p.getIva(); });
		p.setIva(23);
		assertDoesNotThrow(()->{ p.getIva(); });
	}
	
}

