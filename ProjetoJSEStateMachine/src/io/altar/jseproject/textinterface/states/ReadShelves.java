package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.textinterface.CommonProductShell;

public class ReadShelves extends State {
	
	@Override
	public int on() {
		new CommonProductShell().listShelves();
		return 1;
	}
}
