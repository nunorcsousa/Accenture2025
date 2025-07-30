package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.textinterface.CommonProductShell;

public class ReadProducts extends State {

	@Override
	public int on() {
		new CommonProductShell().listProducts();
		return 1;

	}
	
}