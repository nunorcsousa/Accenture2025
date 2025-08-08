package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

@Dependent
public class DeleteProducts extends State {
	
	private ProductService ps = new ProductService(); 
	private ShelfService ss = new ShelfService();
	
	@Override
	public int on() {
		new CommonProductShell().listProducts();
        Long id = new Utils().readLong("ID do produto a remover:");
        Product product = ps.getById(id);
        if (product == null) {
            System.out.println("Produto não encontrado.");
            return 1;
        }
        // Remover de prateleiras
        for (Long shelfId : product.getShelves()) {
            Shelf shelf = ss.getById(shelfId);
            if (shelf != null && id.equals(shelf.getProductId())) {
                shelf.setProductId(0);
                shelf.setCurrentQuantity(0);
                ss.update(shelf);
            }
        }
        ps.delete(id);
        System.out.println("Produto removido.");
		return 1;
	}
}
