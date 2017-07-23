package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoMemoireTest {

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoireTest.class);
	
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
