package fr.pizzeria.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.model.Commande;

public class CommandeDaoJpa implements ICommandeDao {

	private static final Logger LOG = LoggerFactory.getLogger(CommandeDaoJpa.class);

	@Override
	public List<Commande> findAllCommandes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveNewCommande(Commande commande) throws Exception {
		// TODO Auto-generated method stub
		
	}

	
}
