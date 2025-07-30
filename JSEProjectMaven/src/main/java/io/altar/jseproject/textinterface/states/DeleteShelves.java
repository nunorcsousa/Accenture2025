package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class DeleteShelves extends State {
	
	private ProductService ps = new ProductService(); 
	private ShelfService ss = new ShelfService();
	
	@Override
	public int on() {
		new CommonProductShell().listShelves();
        Long id = new Utils().readLong("ID da prateleira a remover:");
        Shelf shelf = ss.getById(id);
        if (shelf == null) {
            System.out.println("Prateleira não encontrada.");
            return 1;
        }
        // Limpar referência no produto
        if (shelf.getProductId() != null) {
            Product product = ps.getById(shelf.getProductId());
            if (product != null) {
                product.removeShelfId(shelf.getId());
                ps.update(product);
            }
        }
        ss.delete(id);
        System.out.println("Prateleira removida.");
		return 1;
	}
}
