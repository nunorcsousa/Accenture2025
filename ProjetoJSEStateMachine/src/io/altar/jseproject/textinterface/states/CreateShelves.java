package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class CreateShelves extends State {
	private ShelfRepository shelfRepo = ShelfRepository.getInstance();
	private ProductRepository productRepo = ProductRepository.getInstance();
	
	@Override
	public int on() {
		System.out.println("\nCriar Prateleira");
        int capacity = new Utils().readInt("Capacidade máxima:", 1, 9999);

        Shelf shelf = new Shelf(capacity);
        Long shelfId = shelfRepo.create(shelf);

        System.out.println("Deseja associar um produto? (s/n)");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            new CommonProductShell().listProducts();
            Long productId = new Utils().readLong("ID do Produto:");
            Product product = productRepo.getById(productId);
            if (product != null) {
                int quantity = new Utils().readInt("Quantidade a colocar:", 1, capacity);
                if (quantity <= capacity) {
                    shelf.setProductId(productId);
                    shelf.setCurrentQuantity(quantity);
                    product.addShelfId(shelfId);
                    productRepo.update(product);
                }
            }
        }
		return 1;
	}

}
