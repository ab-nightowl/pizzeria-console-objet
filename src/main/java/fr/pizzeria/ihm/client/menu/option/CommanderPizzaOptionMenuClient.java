package fr.pizzeria.ihm.client.menu.option;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.ICommandeDao;
import fr.pizzeria.dao.PizzaDaoJpa;
import fr.pizzeria.ihm.admin.menu.option.ListerPizzasOptionMenu;

public class CommanderPizzaOptionMenuClient extends OptionMenuClient {

private static final Logger LOG = LoggerFactory.getLogger(CommanderPizzaOptionMenuClient.class);
	
	private ICommandeDao commandeDao;
	private Scanner sc;

	public CommanderPizzaOptionMenuClient(ICommandeDao commandeDao, Scanner scanner) {
		super();
		this.commandeDao = commandeDao;
		this.sc = scanner;
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

/**		TO DO: trouver le client connecté 
		
		EntityManager em = emf.createEntityManager();
		String sql = "select c from Client c where c.email='userChoice'";
		TypedQuery<Client> query = em.createQuery(sql, Client.class);
		Client client = query.getResultList().get(0);
		em.close();
		
		LocalDateTime dateCommande = LocalDateTime.now();
		String numeroCommande = 
		new Commande(numeroCommande, dateCommande, client)
		
		TO DO: sauver l'objet commande en base via la méthode saveNewCommande
		
		try {
			commandeDao.saveNewCommande(commande);;
		} catch (Exception e) {
			LOG.info(e.getMessage());
		}
*/
		
		return false;
	
	}

}
