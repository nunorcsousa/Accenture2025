package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class DeleteShelves extends State {
	
	private ShelfRepository shelfRepo = ShelfRepository.getInstance();
	private ProductRepository productRepo = ProductRepository.getInstance();
	
	@Override
	public int on() {
		new CommonProductShell().listShelves();
        Long id = new Utils().readLong("ID da prateleira a remover:");
        Shelf shelf = shelfRepo.getById(id);
        if (shelf == null) {
            System.out.println("Prateleira não encontrada.");
            return 1;
        }
        // Limpar referência no produto
        if (shelf.getProductId() != null) {
            Product product = productRepo.getById(shelf.getProductId());
            if (product != null) {
                product.removeShelfId(shelf.getId());
                productRepo.update(product);
            }
        }
        shelfRepo.delete(id);
        System.out.println("Prateleira removida.");
		return 1;
	}
}
