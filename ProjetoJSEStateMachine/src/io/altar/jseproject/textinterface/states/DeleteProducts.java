package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class DeleteProducts extends State {
	private ProductRepository productRepo = ProductRepository.getInstance();
	private ShelfRepository shelfRepo = ShelfRepository.getInstance();
	
	@Override
	public int on() {
		new CommonProductShell().listProducts();
        Long id = new Utils().readLong("ID do produto a remover:");
        Product product = productRepo.getById(id);
        if (product == null) {
            System.out.println("Produto não encontrado.");
            return 1;
        }
        // Remover de prateleiras
        for (Long shelfId : product.getShelfIds()) {
            Shelf shelf = shelfRepo.getById(shelfId);
            if (shelf != null && id.equals(shelf.getProductId())) {
                shelf.setProductId(null);
                shelf.setCurrentQuantity(0);
                shelfRepo.update(shelf);
            }
        }
        productRepo.delete(id);
        System.out.println("Produto removido.");
		return 1;
	}
}
