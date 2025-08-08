package io.altar.jseproject.textinterface.states;

import javax.enterprise.context.Dependent;

import io.altar.jseproject.textinterface.CommonProductShell;

@Dependent
public class ReadShelves extends State {
	
	@Override
	public int on() {
		new CommonProductShell().listShelves();
		return 1;
	}
}
