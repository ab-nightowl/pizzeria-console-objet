package fr.pizzeria.ihm.menu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.ihm.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.ihm.menu.option.MettreAJourPizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.NouvellePizzaOptionMenu;
import fr.pizzeria.ihm.menu.option.OptionMenu;
import fr.pizzeria.ihm.menu.option.SortirOptionMenu;
import fr.pizzeria.ihm.menu.option.SupprimerPizzaOptionMenu;

public class Menu {

	private static final Logger LOG = LoggerFactory.getLogger(Menu.class);
	private static final int NUMERO_OPTION_SORTIE = 99;
	
	private Scanner sc;
	
	private ListerPizzasOptionMenu listerPizza;
	private NouvellePizzaOptionMenu nouvellePizza;
	private MettreAJourPizzaOptionMenu mettreAJourPizza;
	private SupprimerPizzaOptionMenu supprimerPizza;
	private SortirOptionMenu sortir;

	private Map<Integer, OptionMenu> actions = new HashMap<>();
	
	public Menu(IPizzaDao pizzaDao, Scanner scanner) {
		this.sc = scanner;
		
		this.listerPizza = new ListerPizzasOptionMenu(pizzaDao);
		this.nouvellePizza = new NouvellePizzaOptionMenu(pizzaDao, scanner);
		this.mettreAJourPizza = new MettreAJourPizzaOptionMenu(pizzaDao, scanner);
		this.supprimerPizza = new SupprimerPizzaOptionMenu(pizzaDao, scanner);
		this.sortir = new SortirOptionMenu(pizzaDao);

		actions.put(1, listerPizza);
		actions.put(2, nouvellePizza);
		actions.put(3, mettreAJourPizza);
		actions.put(4, supprimerPizza);
		actions.put(99, sortir);
	}

	public void manage() {
		this.afficher();
	}

	public void afficher() {
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

}
