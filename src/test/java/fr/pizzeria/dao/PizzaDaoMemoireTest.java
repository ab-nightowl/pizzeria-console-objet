package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoireTest.class);
	
	private List<Pizza> pizzas;
	
	@Before
	public void setUp() throws SQLException {
		LOG.info("setUp avant test...");
		
		LOG.info("Initialisation des pizzas...");
		
		LOG.debug("Création d'une nouvelle liste de 8 pizzas");
		pizzas = new ArrayList<>();
		pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00));
		pizzas.add(new Pizza("REI", "La Reine", 11.50));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
		pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
		pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
		pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
		pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
		LOG.info("...pizzas initialisées");
		
		LOG.info("...setUp terminé");
	}
	
	@Test
	public void testFindAll() {
		LOG.info("Etant donné une instance de la classe PizzaDaoMemoire");
		PizzaDaoMemoire pizzaDaoMemoire = new PizzaDaoMemoire();
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée");
		List<Pizza> carteDesPizzas = pizzaDaoMemoire.findAllPizzas();
		
		LOG.info("Alors j'obtiens une liste de 8 pizzas");
		assertThat(carteDesPizzas.size()).isEqualTo(8);
	}
	
	@Test
	public void testFindByCode() {
		LOG.info("Etant donné une instance de la classe PizzaDaoMemoire");
		PizzaDaoMemoire pizzaDaoMemoire = new PizzaDaoMemoire();
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée");
		List<Pizza> carteDesPizzas = pizzaDaoMemoire.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas");
		assertThat(carteDesPizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP");
		Boolean trouvePizzaPep = pizzaDaoMemoire.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP");
		assertThat(trouvePizzaPep).isEqualTo(true);
	}
	
	@Test
	public void testSaveNewPizza() throws SavePizzaException {
		LOG.info("Etant donné une instance de la classe Pizza avec le code NEW");
		Pizza pizza = new Pizza("NEW", "New pizza", 10);

		LOG.info("Etant donné une instance de la classe PizzaDaoMemoire");
		PizzaDaoMemoire pizzaDaoMemoire = new PizzaDaoMemoire();

		LOG.info("Lorsque la méthode saveNewPizza est invoquée");
		pizzaDaoMemoire.saveNewPizza(pizza);

		LOG.info("Lorsque la méthode findByCode est invoquée");
		Boolean trouve = pizzaDaoMemoire.findByCode("NEW");
		
		LOG.info("Alors j'obtiens true pour le code NEW");
		assertThat(trouve).isEqualTo(true);
	}

	@Test
	public void testUpdatePizza() throws UpdatePizzaException {
		LOG.info("Etant donné une instance de la classe PizzaDaoMemoire");
		PizzaDaoMemoire pizzaDaoMemoire = new PizzaDaoMemoire();

		LOG.info("Lorsque la méthode findAllPizzas est invoquée");
		List<Pizza> carteDesPizzas = pizzaDaoMemoire.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas");
		assertThat(carteDesPizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP");
		Boolean trouvePizzaPep = pizzaDaoMemoire.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP");
		assertThat(trouvePizzaPep).isEqualTo(true);
		
		LOG.info("Etant donné une instance de la classe Pizza avec le code UPD");
		Pizza updatedPizza = new Pizza("UPD", "Updated pizza", 10);

		LOG.info("Lorsque la méthode updateNewPizza est invoquée");
		pizzaDaoMemoire.updatePizza("PEP", updatedPizza);

		LOG.info("Lorsque la méthode findByCode est invoquée avec le code UPD");
		Boolean trouvePizzaUpd = pizzaDaoMemoire.findByCode("UPD");
		LOG.info("Alors j'obtiens true pour le code UPD (mis à jour)");
		assertThat(trouvePizzaUpd).isEqualTo(true);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP");
		Boolean nonTrouve = pizzaDaoMemoire.findByCode("PEP");
		LOG.info("Alors j'obtiens false pour le code PEP (ancien)");
		assertThat(nonTrouve).isEqualTo(false);
	}
	
	@Test
	public void testDeletePizza() throws DeletePizzaException {
		LOG.info("Etant donné une instance de la classe PizzaDaoMemoire");
		PizzaDaoMemoire pizzaDaoMemoire = new PizzaDaoMemoire();

		LOG.info("Lorsque la méthode findAllPizzas est invoquée");
		List<Pizza> carteDesPizzas = pizzaDaoMemoire.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas");
		assertThat(carteDesPizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP");
		Boolean trouvePizzaPep = pizzaDaoMemoire.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP");
		assertThat(trouvePizzaPep).isEqualTo(true);
		
		LOG.info("Lorsque la méthode deletePizza est invoquée");
		pizzaDaoMemoire.deletePizza("PEP");
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP");
		Boolean trouve = pizzaDaoMemoire.findByCode("PEP");
		
		LOG.info("Alors j'obtiens false pour le code PEP (ancien)");
		assertThat(trouve).isEqualTo(false);
	}

}
