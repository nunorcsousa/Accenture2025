package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

@Dependent
public class UpdateProducts extends State{
	
	private ProductService ps = new ProductService(); 
	
	@Override
	public int on() {
		Long id = new Utils().readLong("ID do Produto a editar:");
        Product p = ps.getById(id);
        if (p == null) {
            System.out.println("Produto não encontrado.");
            return 1;
        }
        p.setName(new Utils().readLine("Nome atual: " + p.getName() + " → "));
        p.setPrice(new Utils().readDouble("Preço atual: " + p.getPrice() + " → "));
        p.setDiscount(new Utils().readInt("Desconto atual: " + p.getDiscount() + " → ", 0, 100));
        p.setIva(new Utils().readInt("IVA atual: " + p.getIva() + " → ", 0, 100));
        p.setQuantity(new Utils().readInt("Quantidade atual: " + p.getQuantity() + " → ", 1, 9999));
        new CommonProductShell().allocateProductToShelves(id);
        ps.update(p);
        System.out.println("Produto atualizado.");
		return 1;

	}
}
