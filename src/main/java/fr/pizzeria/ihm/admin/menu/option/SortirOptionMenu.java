package fr.pizzeria.ihm.admin.menu.option;

import fr.pizzeria.dao.IPizzaDao;

public class SortirOptionMenu extends OptionMenu {

	public SortirOptionMenu(IPizzaDao pizzaDao) {
		super();
	}

	@Override
	public String getLibelle() {
		return "Sortir";
	}

	@Override
	public boolean execute() {
		System.exit(0);
		return false;
	}

}
