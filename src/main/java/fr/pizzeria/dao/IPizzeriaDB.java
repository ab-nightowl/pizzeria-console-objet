package fr.pizzeria.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface IPizzeriaDB {
	public EntityManagerFactory emf = Persistence.createEntityManagerFactory("pizzeria-console-new");

	
}
