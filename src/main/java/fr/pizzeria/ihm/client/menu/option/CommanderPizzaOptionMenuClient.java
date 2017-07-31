package fr.pizzeria.ihm.client.menu.option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.admin.menu.option.ListerPizzasOptionMenu;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;

public class CommanderPizzaOptionMenuClient extends OptionMenuClient {

private static final Logger LOG = LoggerFactory.getLogger(CommanderPizzaOptionMenuClient.class);
	
	private ICommandeDao commandeDao;
	private Scanner sc;
	private Client currentClient;

	public CommanderPizzaOptionMenuClient(ICommandeDao commandeDao, Scanner scanner, Client currentClient) {
		super();
		this.commandeDao = commandeDao;
		this.sc = scanner;
		this.currentClient = currentClient;
	}

	@Override
	public String getLibelle() {
		return "Commander une pizza";
	}

	@Override
	public boolean execute() {
		PizzaDaoJpa pizzaDaoJpa = new PizzaDaoJpa();
		ListerPizzasOptionMenu listerPizzas = new ListerPizzasOptionMenu(pizzaDaoJpa);
		listerPizzas.execute();
		
		boolean saisieCorrecte = false;

		String code = "";
		do {
			LOG.info("Veuillez saisir le code de la pizza à commander");
			code = sc.next().trim();

			if (!(code.matches("^[A-Z]{3}$"))) {
				LOG.info("Veuillez saisir un code valide svp");
				
			} else {
				saisieCorrecte = true;
			}
		} while (!saisieCorrecte);
		
		Commande commande = new Commande();
		
		LocalDateTime dateCommande = LocalDateTime.now();
		String dateToS = dateCommande.format(DateTimeFormatter.ofPattern("hhmmss"));
		String idToS = String.valueOf(commande.getId());
		
		StringBuilder stringBuilder = new StringBuilder();
		String numeroCommande = stringBuilder.append(dateToS).append("-").append(idToS).toString();
		
		commande = new Commande(numeroCommande, dateCommande, currentClient);
		
		try {
			commandeDao.saveNewCommande(commande);
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
		
		return false;
	
	}

}
