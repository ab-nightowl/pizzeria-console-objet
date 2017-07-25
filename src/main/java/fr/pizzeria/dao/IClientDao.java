package fr.pizzeria.dao;

import fr.pizzeria.model.Client;

public interface IClientDao extends IPizzeriaDB {

	void saveNewClient(Client client) throws Exception;

	Client findByEmail(String userChoice);

	
}
