package io.altar.jseproject.textinterface.states;

import java.util.Scanner;

public abstract class State {
	public static final Scanner sc = new Scanner(System.in);

	public abstract int on();
	
}