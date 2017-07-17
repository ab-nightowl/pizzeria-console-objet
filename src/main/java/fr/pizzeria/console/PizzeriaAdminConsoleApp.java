package fr.pizzeria.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.ihm.Menu;

public class PizzeriaAdminConsoleApp {

	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaAdminConsoleApp.class);

	public static void main(String[] args) {
		LOG.debug("Création du menu de bienvenue");
		Menu menu = new Menu();
		menu.manage();
	}

}
