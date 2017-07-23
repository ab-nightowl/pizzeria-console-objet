package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoJDBCTest.class);
	
	private static Connection conn;
	
	private List<Pizza> pizzas;
	
	private PizzaDaoJDBC pizzaDaoJDBC;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SQLException, ClassNotFoundException {
		LOG.info("setUpBeforeClass...");
		
		LOG.info("Chargement du driver.");
		Class.forName("org.h2.Driver");
		
		LOG.info("Création d'une connection vers la BDD.");
		conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		
		LOG.info("...setUpBeforeClass terminé");
	}
	
	@Before
	public void setUp() throws SQLException, ClassNotFoundException {
		LOG.info("setUp avant test...");
		
		LOG.info("Verification si la table 'pizza' existe déjà en base.");
		try {
			PreparedStatement findAllPizzaSt = conn.prepareStatement("SELECT * FROM pizza");
			findAllPizzaSt.executeQuery();
			
		} catch (SQLException e) {
			LOG.error("La table 'pizza' existe déjà.");
			
			LOG.info("Suppression de la table 'pizza'...");
			PreparedStatement dropTableSt = conn.prepareStatement("DROP TABLE pizza;");
			dropTableSt.execute();
			LOG.info("...Table 'pizza' supprimée.");
			
		} finally {
			LOG.info("Création de la table 'pizza'...");
			String sqlCreate = "CREATE TABLE `pizza` (" 
							+ "`id` int(11) NOT NULL auto_increment," 
							+ "`nom` varchar(255) NOT NULL,"
							+ "`code` varchar(10) NOT NULL," 
							+ "`prix` decimal(5,2) NOT NULL," 
							+ "`url_image` varchar(255) NULL);";
			
			PreparedStatement createTableSt = conn.prepareStatement(sqlCreate);
			createTableSt.execute();
			LOG.info("...Table 'pizza' créée en base.");
			
			LOG.info("Création d'une nouvelle liste de 8 pizzas.");
			pizzas = new ArrayList<>();
			pizzas.add(new Pizza("PEP", "Pépéroni", 12.50));
			pizzas.add(new Pizza("MAR", "Margherita", 14.00));
			pizzas.add(new Pizza("REI", "La Reine", 11.50));
			pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00));
			pizzas.add(new Pizza("CAN", "La Cannibale", 12.50));
			pizzas.add(new Pizza("SAV", "La Savoyarde", 13.00));
			pizzas.add(new Pizza("ORI", "L'Orientale", 13.50));
			pizzas.add(new Pizza("IND", "L'Indienne", 14.00));
			
			LOG.info("Insertion des 8 pizzas dans la table pizza.");
			String sqlInsert = "INSERT INTO pizza(code, nom, prix) VALUES (?, ?, ?);";
			for (Pizza pizza : pizzas) {
				PreparedStatement insertPizzaSt = conn.prepareStatement(sqlInsert);
				insertPizzaSt.setString(1, pizza.getCode());
				insertPizzaSt.setString(2, pizza.getNom());
				insertPizzaSt.setDouble(3, pizza.getPrix());
				insertPizzaSt.executeUpdate();
			}
		}
		
		LOG.info("...setUp terminé");
	}
	
	@Test
	public void testFindAll() throws DeletePizzaException, SQLException {
		LOG.info("testFindAllPizza:");
		
		LOG.info("Etant donné une instance de la classe PizzaDaoJDBC.");
		pizzaDaoJDBC = new PizzaDaoJDBC("", "jdbc:h2:mem:testdb", "sa", "");
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée.");
		pizzas = pizzaDaoJDBC.findAllPizzas();
		
		LOG.info("Alors j'obtiens une liste de 8 pizzas.");
		assertThat(pizzas.size()).isEqualTo(8);
		
	}

	@Test
	public void testFindByCode() throws SQLException {
		LOG.info("Etant donné une instance de la classe PizzaDaoJDBC.");
		pizzaDaoJDBC = new PizzaDaoJDBC("", "jdbc:h2:mem:testdb", "sa", "");
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée.");
		pizzas = pizzaDaoJDBC.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas.");
		assertThat(pizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP.");
		Boolean trouvePizzaPep = pizzaDaoJDBC.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP.");
		assertThat(trouvePizzaPep).isEqualTo(true);
	}

	@Test
	public void testSaveNewPizza() throws SavePizzaException, SQLException {
		LOG.info("testSaveNewPizza:");
		
		LOG.info("Etant donné une instance de la classe Pizza avec le code NEW.");
		Pizza pizza = new Pizza("NEW", "New pizza", 10);

		LOG.info("Lorsque la méthode saveNewPizza est invoquée.");
		pizzaDaoJDBC.saveNewPizza(pizza);
		
		LOG.info("Lorsque la méthode findByCode est invoquée.");
		Boolean trouve = pizzaDaoJDBC.findByCode("NEW");
		
		LOG.info("Alors j'obtiens true pour le code NEW.");
		assertThat(trouve).isEqualTo(true);
	}
	
	@Test
	public void testUpdatePizza() throws UpdatePizzaException, SQLException {
		LOG.info("testUpdatePizza");
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée.");
		pizzas = pizzaDaoJDBC.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas.");
		assertThat(pizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP.");
		Boolean trouvePizzaPep = pizzaDaoJDBC.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP.");
		assertThat(trouvePizzaPep).isEqualTo(true);
		
		LOG.info("Etant donné une instance de la classe Pizza avec le code UPD.");
		Pizza updatedPizza = new Pizza("UPD", "Updated pizza", 10);

		LOG.info("Lorsque la méthode updateNewPizza est invoquée.");
		pizzaDaoJDBC.updatePizza("PEP", updatedPizza);

		LOG.info("Lorsque la méthode findByCode est invoquée avec le code UPD.");
		Boolean trouvePizzaUpd = pizzaDaoJDBC.findByCode("UPD");
		LOG.info("Alors j'obtiens true pour le code UPD (mis à jour)");
		assertThat(trouvePizzaUpd).isEqualTo(true);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP.");
		Boolean nonTrouve = pizzaDaoJDBC.findByCode("PEP");
		LOG.info("Alors j'obtiens false pour le code PEP (ancien).");
		assertThat(nonTrouve).isEqualTo(false);
	}

	@Test
	public void testDeletePizza() throws DeletePizzaException, SQLException {
		LOG.info("testDeletePizza");
		
		LOG.info("Lorsque la méthode findAllPizzas est invoquée.");
		pizzas = pizzaDaoJDBC.findAllPizzas();
		LOG.info("Alors j'obtiens une liste 8 pizzas.");
		assertThat(pizzas.size()).isEqualTo(8);
		
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP.");
		Boolean trouvePizzaPep = pizzaDaoJDBC.findByCode("PEP");
		LOG.info("Alors j'obtiens true pour le code PEP.");
		assertThat(trouvePizzaPep).isEqualTo(true);
		
		LOG.info("Lorsque la méthode deletePizza est invoquée.");
		pizzaDaoJDBC.deletePizza("PEP");
		LOG.info("Lorsque la méthode findByCode est invoquée avec le code PEP.");
		Boolean trouve = pizzaDaoJDBC.findByCode("PEP");
		
		LOG.info("Alors j'obtiens false pour le code PEP (ancien).");
		assertThat(trouve).isEqualTo(false);
	}
	
}
