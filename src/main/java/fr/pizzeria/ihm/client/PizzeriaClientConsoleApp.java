package fr.pizzeria.ihm.client;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ClientDaoJpa;
import fr.pizzeria.dao.CommandeDaoJpa;
import fr.pizzeria.dao.IClientDao;
import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.ihm.client.menu.MenuClient;
import fr.pizzeria.ihm.client.menu.MenuClientConnecte;
import fr.pizzeria.ihm.client.menu.MenuClientNonConnecte;

public class PizzeriaClientConsoleApp {
	
	private static final Logger LOG = LoggerFactory.getLogger(PizzeriaClientConsoleApp.class);

	public static void main(String[] args) {
		
		LOG.debug("Création d'une instance de ClientDaoJpa de type déclaré IClientDao");
		IClientDao clientDao = new ClientDaoJpa();
		
		LOG.debug("Création d'une instance de CommandeDaoJpa de type déclaré ICommandeDao");
		ICommandeDao commandeDao = new CommandeDaoJpa();
		
		try (Scanner scanner = new Scanner(System.in)) {
			LOG.debug("Création d'une instance de MenuClientNonConnecte");
			MenuClient menuNonConnecte = new MenuClientNonConnecte(clientDao, scanner);
			
			LOG.debug("La methode manage est invoquée");
			menuNonConnecte.manage();
			
			LOG.debug("Création d'une instance de MenuClientConnecte");
			MenuClient menuConnecte = new MenuClientConnecte(commandeDao, scanner);
			
			LOG.debug("La methode manage est invoquée");
			menuConnecte.manage();
		}
	}

}
