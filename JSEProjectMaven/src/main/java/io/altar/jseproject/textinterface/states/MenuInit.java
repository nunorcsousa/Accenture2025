package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.textinterface.Utils;

@Dependent
public class MenuInit extends State {

	@Override
	public int on() {
		System.out.println("Menu Principal:\n1 - Produtos\n2 - Prateleiras\n3 - Sair");
		return new Utils().readInt("Escolha a opção:",1, 3);
	}

}
