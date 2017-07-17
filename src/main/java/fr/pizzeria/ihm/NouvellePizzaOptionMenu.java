package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.model.Pizza;

public class NouvellePizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(ListerPizzasOptionMenu.class);

	public NouvellePizzaOptionMenu(PizzaDaoMemoire dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return "2. Ajouter une nouvelle pizza";
	}

	@Override
	public boolean execute() {

		LOG.info("Veuillez saisir le code");
		String code = sc.nextLine();

		LOG.info("Veuillez saisir le nom (sans espace)");
		String nomPizza = sc.nextLine();

		LOG.info("Veuillez saisir le prix");
		Integer prix = Integer.parseInt(sc.nextLine());

		Pizza nouvellePizza = new Pizza(code, nomPizza, prix);
		dao.saveNewPizza(nouvellePizza);

		return false;
	}

}
