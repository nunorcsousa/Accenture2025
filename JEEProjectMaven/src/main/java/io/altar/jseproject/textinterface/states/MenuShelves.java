package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.textinterface.Utils;

public class MenuShelves extends State {

	@Override
	public int on() {
		System.out.println("Menu Prateleiras:\n1 - Criar\n2 - Editar\n3 - Listar\n4 - Remover\n5 - Voltar");
		return new Utils().readInt("Escolha a opção:",1, 5);

	}

}