package fr.pizzeria.ihm.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaDaoMemoire;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

public class Menu {

	Scanner sc;
	String userInput;

	ListerPizzasOptionMenu listerPizza;
	NouvellePizzaOptionMenu nouvellePizza;
	MettreAJourPizzaOptionMenu mettreAJourPizza;
	SupprimerPizzaOptionMenu supprimerPizza;
	SortirOptionMenu sortir;

	Map<Integer, OptionMenu> actions = new HashMap<>();
	
	private static final int NUMERO_OPTION_SORTIE = 99;

	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);

	public Menu(PizzaDaoMemoire dao) {
		this.sc = new Scanner(System.in);
		this.userInput = "";

		this.listerPizza = new ListerPizzasOptionMenu(dao);
		this.nouvellePizza = new NouvellePizzaOptionMenu(dao);
		this.mettreAJourPizza = new MettreAJourPizzaOptionMenu(dao);
		this.supprimerPizza = new SupprimerPizzaOptionMenu(dao);
		this.sortir = new SortirOptionMenu(dao);

		actions.put(1, listerPizza);
		actions.put(2, nouvellePizza);
		actions.put(3, mettreAJourPizza);
		actions.put(4, supprimerPizza);
		actions.put(99, sortir);
	}

	public void manage() {
		int saisie = NUMERO_OPTION_SORTIE;

		// Afficher le menu tant qu'on ne sort pas (ie : response = 99)
		do {
			// afficher menu
			LOG.info("***** Pizzeria Administration *****");
			actions.forEach((numero, option) -> LOG.info(numero + ". " + option.getLibelle()));

			saisie = sc.nextInt();

			actions.get(saisie).execute();
		} while (saisie != NUMERO_OPTION_SORTIE);
	}

	public void afficher() {
		Collection<OptionMenu> options = actions.values();

		for (OptionMenu option : options) {
			LOG.info(option.getLibelle());
		}
	}

}
