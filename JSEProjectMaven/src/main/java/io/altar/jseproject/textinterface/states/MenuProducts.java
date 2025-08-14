package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.textinterface.Utils;

@Dependent
public class MenuProducts extends State {

	@Override
	public int on() {
		System.out.println("Menu Produtos:\n1 - Criar\n2 - Editar\n3 - Listar\n4 - Remover\n5 - Voltar");
		return new Utils().readInt("Escolha a opção:",1, 5);

	}

}