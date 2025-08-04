package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.CommonProductShell;
import io.altar.jseproject.textinterface.Utils;

public class UpdateShelves extends State {
	
	private ShelfService ss = new ShelfService();
	
	@Override
	public int on() {
		new CommonProductShell().listShelves();
        Long id = new Utils().readLong("ID da prateleira a editar:");
        Shelf shelf = ss.getById(id);
        if (shelf == null) {
            System.out.println("Prateleira não encontrada.");
            return 1;
        }
        shelf.setCapacity(new Utils().readInt("Nova capacidade (" + shelf.getCapacity() + "):", 1, Integer.MAX_VALUE));
        shelf.setDailyPrice(new Utils().readDouble("Novo preço diário (atual: " + shelf.getDailyPrice() + "):"));
        System.out.println("Prateleira atualizada.");
		return 1;
	}
}
