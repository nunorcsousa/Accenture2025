package io.altar.jseproject.textinterface;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;

public class CommonProductShell {
	
	private ProductRepository productRepo = ProductRepository.getInstance();
	private ShelfRepository shelfRepo = ShelfRepository.getInstance();
	
    public void listShelves() {
        System.out.println("\n--- Lista de Prateleiras ---");
        for (Shelf s : shelfRepo.getAll()) {
            String prodStr = (s.getProductId() == null) ? "vazia" : "Produto ID " + s.getProductId();
            System.out.println("ID: " + s.getId() + " | Capacidade: " + s.getCapacity() +
                    " | Ocupado: " + s.getCurrentQuantity() + " | Produto: " + prodStr);
        }
    }
	
    public void listProducts() {
        System.out.println("\n--- Lista de Produtos ---");
        for (Product p : productRepo.getAll()) {
            System.out.println("ID: " + p.getId() + " | Nome: " + p.getName() +
                    " | Preço: " + p.getPvp() + " | Qtd: " + p.getQuantity() +
                    " | Prateleiras: " + p.getShelfIds());
        }
    }
    
    public void allocateProductToShelves(Long productId) {
        Product product = productRepo.getById(productId);
        System.out.println("Prateleiras disponíveis:");
        shelfRepo.getAll().forEach(s -> {
            String estado = s.getProductId() == null ? "(vazia)" : "(ocupada por produto " + s.getProductId() + ")";
            System.out.println("ID: " + s.getId() + " Capacidade: " + s.getCapacity() + " " + estado);
        });

        while (true) {
            Long shelfId = new Utils().readLong("ID da prateleira onde alocar o produto (0 para terminar):");
            if (shelfId == 0) break;
            Shelf shelf = shelfRepo.getById(shelfId);
            if (shelf == null || shelf.getProductId() != null) {
                System.out.println("Prateleira inválida ou ocupada.");
                return;
            }
            if (shelf.hasSpace(product.getQuantity())) {
                shelf.setProductId(productId);
                shelf.setCurrentQuantity(product.getQuantity());
                shelfRepo.update(shelf);
                product.addShelfId(shelfId);
                return;
            } else {
                System.out.println("Capacidade insuficiente nessa prateleira.");
            }
        }
    }
}
