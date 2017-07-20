package fr.pizzeria.ihm.menu.option;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.dao.exception.SavePizzaException;
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

		boolean saisieCorrecte = false;

		String code = "";
		do {
			LOG.info("Veuillez saisir le code");
			code = sc.nextLine().trim();

			if (!code.matches("^[A-Z]{3}$")) {
				LOG.info("Veuillez saisir un code valide");
			} else {
				saisieCorrecte = true;
			}
		} while (!saisieCorrecte);

		String nom = "";
		do {
			LOG.info("Veuillez saisir le nom (sans espace)");
			nom = sc.nextLine().trim();

			if (nom.isEmpty()) {
				LOG.info("Veuillez saisir un nom valide");
			}

			saisieCorrecte = true;
		} while (!saisieCorrecte);

		double prix = 0;
		do {
			try {
				LOG.info("Veuillez saisir le prix");
				String saisie = sc.nextLine().trim();

				if (saisie.isEmpty()) {
					LOG.info("Veuillez saisir un prix valide");
				}
				prix = Integer.parseInt(saisie);

				saisieCorrecte = true;

			} catch (NumberFormatException e) {
				LOG.info(e.getMessage());
			}
		} while (!saisieCorrecte);

		Pizza nouvellePizza = new Pizza(code, nom, prix);

		try {
			dao.saveNewPizza(nouvellePizza);
		} catch (SavePizzaException e) {
			LOG.info(e.getMessage());
		}

		return false;
	}

}
