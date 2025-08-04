package io.altar.jseproject.textinterface;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;

public class CommonProductShell {

	private ProductService ps = new ProductService();
	private ShelfService ss = new ShelfService();

	public void listShelves() {
		System.out.println("\n--- Lista de Prateleiras ---");
		for (Shelf s : ss.getAll()) {
			String prodStr = (s.getProductId() == 0) ? "vazia" : "Produto ID " + s.getProductId();
			System.out.println("ID: " + s.getId() + " | Capacidade: " + s.getCapacity() + " | Ocupado: "
					+ s.getCurrentQuantity() + " | Preço diário: " + s.getDailyPrice() + " | Produto: " + prodStr);
		}
	}

	public void listProducts() {
		System.out.println("\n--- Lista de Produtos ---");
		for (Product p : ps.getAll()) {
			System.out.println("ID: " + p.getId() + " | Nome: " + p.getName() + " | Preço: " + p.getPvp() + " | Qtd: "
					+ p.getQuantity() + " | Prateleiras: " + p.getShelfIds());
		}
	}

	public void allocateProductToShelves(Long productId) {
		Product product = ps.getById(productId);
		System.out.println("Prateleiras disponíveis:");
		ss.getAll().forEach(s -> {
			String estado = s.getProductId() == 0 ? "(vazia)" : "(ocupada por produto " + s.getProductId() + ")";
			System.out.println("ID: " + s.getId() + " Capacidade: " + s.getCapacity() + " " + estado);
		});

		while (true) {
			Long shelfId = new Utils().readLong("ID da prateleira onde alocar o produto (0 para terminar):");
			if (shelfId == 0)
				break;
			Shelf shelf = ss.getById(shelfId);
			if (shelf == null || shelf.getProductId() != 0) {
				System.out.println("Prateleira inválida ou ocupada.");
				return;
			}
			if (shelf.hasSpace(product.getQuantity())) {
				shelf.setProductId(productId);
				shelf.setCurrentQuantity(product.getQuantity());
				ss.update(shelf);
				product.addShelfId(shelfId);
				return;
			} else {
				System.out.println("Capacidade insuficiente nessa prateleira.");
			}
		}
	}
}
