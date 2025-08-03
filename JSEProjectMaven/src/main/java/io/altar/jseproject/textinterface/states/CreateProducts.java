package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class CreateProducts extends State {

	private ProductService ps = new ProductService(); 
	
	@Override
	public int on() {
		System.out.println("\nCriar Produto");
        String name = new Utils().readLine("Nome:");
        double price = new Utils().readDouble("Preço base:");
        int discount = new Utils().readInt("Desconto (%):", 0, 100);
        int iva = new Utils().readInt("IVA (%):", 0, 100);
        int quantity = new Utils().readInt("Quantidade:", 1, 9999);
        
        Product p = new Product(name, price, discount, iva, quantity, null);
        Long id = ps.create(p);
        new CommonProductShell().allocateProductToShelves(id);
        System.out.println("Produto criado com ID: " + id);
		return 1;
	}
}
