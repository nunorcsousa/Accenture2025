package io.altar.jseproject.textinterface.states;

import java.util.Scanner;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfService;
import io.altar.jseproject.textinterface.CommonProductShell;

@Dependent
public abstract class State {
	
	public Scanner sc = new Scanner(System.in);
	
	@Inject
	public ProductService ps;
	
	@Inject
	public ShelfService ss;
	
	@Inject
	public CommonProductShell cps;
	
	public abstract int on();
	
}