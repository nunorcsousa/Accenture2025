package io.altar.jseproject.textinterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

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
@ApplicationScoped
public class TextInterfaceStateMachine {
	
	@Inject
	MenuInit menuInit;
	@Inject
	MenuProducts menuProducts;
	@Inject
	MenuShelves menuShelves;
	@Inject
	CreateShelves createShelves;
	@Inject
	UpdateShelves updateShelves;
	@Inject
	ReadShelves readShelves;
	@Inject
	DeleteShelves deleteShelves;
	@Inject
	CreateProducts createProducts;
	@Inject
	UpdateProducts updateProducts;
	@Inject
	ReadProducts readProducts;
	@Inject
	DeleteProducts deleteProducts;
	// 2. states
	private State[] states;

	@PostConstruct
	public void init() {

		states = new State[] { menuInit, // State 0
				menuProducts, // State 1
				menuShelves, // State 2
				createShelves, // State 3
				updateShelves, // State 4
				readShelves, // State 5
				deleteShelves, // State 6
				createProducts, // State 7
				updateProducts, // State 8
				readProducts, // State 9
				deleteProducts };// State 10
	}

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