package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class UpdateProducts extends State{
	
	private ProductRepository productRepo = ProductRepository.getInstance();
	
	@Override
	public int on() {
		Long id = new Utils().readLong("ID do Produto a editar:");
        Product p = productRepo.getById(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return 1;
        }
        p.setName(new Utils().readLine("Nome atual: " + p.getName() + " → "));
        p.setPrice(new Utils().readDouble("Preço atual: " + p.getPrice() + " → "));
        p.setDiscount(new Utils().readInt("Desconto atual: " + p.getDiscount() + " → ", 0, 100));
        p.setIva(new Utils().readInt("IVA atual: " + p.getIva() + " → ", 0, 100));
        p.setQuantity(new Utils().readInt("Quantidade atual: " + p.getQuantity() + " → ", 1, 9999));
        p.setDailyPrice(new Utils().readLong("Novo preço diário (atual: " + p.getDailyPrice() + "):"));
        new CommonProductShell().allocateProductToShelves(id);
        productRepo.update(p);
        System.out.println("Produto atualizado.");
		return 1;

	}
}
