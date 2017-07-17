package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	private static final Logger LOG = LoggerFactory.getLogger(SupprimerPizzaOptionMenu.class);

	public SupprimerPizzaOptionMenu(PizzaDaoMemoire dao) {
		super(dao);
	}

	@Override
	public String getLibelle() {
		return "4. Supprimer une pizza";
	}

	@Override
	public boolean execute() {

		do {
			LOG.info("Veuillez choisir le code de la pizza à supprimer (en majuscules)");
			userChoice = sc.nextLine();

			trouve = dao.findByCode(userChoice);

			if (!trouve) {
				LOG.info("Le code " + userChoice + " n'existe pas");
			}
		} while (!trouve);

		dao.deletePizza(userChoice);

		return false;
	}
}
