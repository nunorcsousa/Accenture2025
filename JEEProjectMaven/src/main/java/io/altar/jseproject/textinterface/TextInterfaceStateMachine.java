package io.altar.jseproject.textinterface;

import io.altar.jseproject.textinterface.states.CreateProducts;
import io.altar.jseproject.textinterface.states.CreateShelves;
import io.altar.jseproject.textinterface.states.DeleteProducts;
import io.altar.jseproject.textinterface.states.DeleteShelves;
import io.altar.jseproject.textinterface.states.MenuInit;
import io.altar.jseproject.textinterface.states.MenuProducts;
import io.altar.jseproject.textinterface.states.MenuShelves;
import io.altar.jseproject.textinterface.states.ReadProducts;
import io.altar.jseproject.textinterface.states.ReadShelves;
import io.altar.jseproject.textinterface.states.State;
import io.altar.jseproject.textinterface.states.UpdateProducts;
import io.altar.jseproject.textinterface.states.UpdateShelves;


//1. Create a "wrapper" class that models the state machine
public class TextInterfaceStateMachine {
	// 2. states
	private State[] states = { new MenuInit(), // State 0
			new MenuProducts(), // State 1
			new MenuShelves(), // State 2
			new CreateShelves(), // State 3
			new UpdateShelves(), // State 4
			new ReadShelves(), // State 5
			new DeleteShelves(), // State 6
			new CreateProducts(), // State 7
			new UpdateProducts(), // State 8
			new ReadProducts(), // State 9
			new DeleteProducts() };// State 10
	// 4. transitions
	private int[][] transition = { 
			{ 1, 2 }, 
			{ 7, 8, 9, 10, 0 }, 
			{ 3, 4, 5, 6, 0 }, 
			{ 2 }, 
			{ 2 }, 
			{ 2 }, 
			{ 2 }, 
			{ 1 },
			{ 1 }, 
			{ 1 }, 
			{ 1 } 
			};
	// 3. current
	private int current = 0;

	// 5. All client requests are simply delegated to the current state object
	public void start() {
		
		while(true) {
			int option = states[current].on();
			if (current == 0 && option == 3) {
				System.out.println("Fim de programa.");
				break;
			}
			current = transition[current][option-1];
		}
	}

}

//6. Create a state base class that makes the concrete states interchangeable
//7. The State base class specifies default behavior