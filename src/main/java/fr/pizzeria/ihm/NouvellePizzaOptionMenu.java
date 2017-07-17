package fr.pizzeria.ihm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.exception.SavePizzaException;
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

		String code = "";
		String nom = "";
		double prix = 0;
		
		boolean saisieCorrecte = false;
		
		do {
			try {
				LOG.info("Veuillez saisir le code");
				code = sc.nextLine().trim();
				
				if (code.isEmpty() || code.length() != 3 || !code.matches("^[A-Z]{3}$")) {
					throw new SavePizzaException("Veuillez saisir un code valide");
				}
				
				saisieCorrecte = true;
				
			} catch (SavePizzaException e) {
				LOG.info(e.getMessage());
			}
				
		} while (!saisieCorrecte);
		
		
		do {
			try {
				LOG.info("Veuillez saisir le nom (sans espace)");
				nom = sc.nextLine().trim();
				
				if (nom.isEmpty()) {
					throw new SavePizzaException("Veuillez saisir un nom valide");
				}
				
				saisieCorrecte = true;
				
			} catch (SavePizzaException e) {
				LOG.info(e.getMessage());
			}
				
		} while (!saisieCorrecte);
		
		
		do {
			try {
				LOG.info("Veuillez saisir le prix");
				String saisie = sc.nextLine().trim();
				if (saisie.isEmpty()){
					throw new SavePizzaException("Veuillez saisir un prix valide");
				}
				prix = Integer.parseInt(saisie);
				
				saisieCorrecte = true;
				
			} catch (SavePizzaException e) {
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
