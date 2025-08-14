package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

@Dependent
public class CreateShelves extends State {

	
	@Override
	public int on() {
		System.out.println("\nCriar Prateleira");
        int capacity = new Utils().readInt("Capacidade máxima:", 1, 9999);
        double dailyPrice = new Utils().readDouble("Preço diário:");
        
        Shelf shelf = new Shelf(capacity, dailyPrice);
        Long shelfId = ss.create(shelf);

        System.out.println("Deseja associar um produto? (s/n)");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            new CommonProductShell().listProducts();
            Long productId = new Utils().readLong("ID do Produto:");
            Product product = ps.getById(productId);
            if (product != null) {
                int quantity = new Utils().readInt("Quantidade a colocar:", 1, capacity);
                if (quantity <= capacity) {
                    shelf.setProductId(productId);
                    shelf.setCurrentQuantity(quantity);
                    product.addShelfId(shelfId);
                    ps.update(product);
                }
            }
            
        }
		return 1;
	}

}
