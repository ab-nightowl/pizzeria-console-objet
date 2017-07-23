package fr.pizzeria.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.exception.DeletePizzaException;
import fr.pizzeria.dao.exception.SavePizzaException;
import fr.pizzeria.dao.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class PizzaDaoJDBCTest {
	private static Connection conn;

	private static final Logger LOG = LoggerFactory.getLogger(PizzaDaoMemoireTest.class);

	@BeforeClass
	public static void initTest() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
		String sql = "CREATE TABLE `pizza` (" + "`id` int(11) NOT NULL auto_increment," + "`nom` varchar(255) NOT NULL,"
				+ "`code` varchar(10) NOT NULL," + "`prix` decimal(5,2) NOT NULL," + "`url_image` varchar(255) NULL);";
		PreparedStatement createTable = conn.prepareStatement(sql);
		createTable.execute();
	}

	@Test
	public void testFindAll() throws DeletePizzaException, SQLException {
		LOG.info("testFindAllPizza");
		
		PizzaDaoJDBC pizzaDaoJDBC = new PizzaDaoJDBC("", "jdbc:h2:mem:testdb", "sa", "");
		
		List<Pizza> listPizzas = pizzaDaoJDBC.findAllPizzas();
		
		// TODO assert sur la listPizzas
		
	}
	
	@Test
	public void testSaveNewPizza() throws SavePizzaException, SQLException {
		LOG.info("testSaveNewPizza");
		
		PizzaDaoJDBC pizzaDaoJDBC = new PizzaDaoJDBC("", "jdbc:h2:mem:testdb", "sa", "");

		List<Pizza> listPizzas = pizzaDaoJDBC.findAllPizzas();

		// TODO assert sur la listPizzas

		while (result.next()) {
			Integer id = result.getInt("id");
			String code = result.getString("code");
			String nom = result.getString("nom");
			Double prix = result.getDouble("prix");
			LOG.info("[id=" + id + " code=" + code + " nom=" + nom + " prix=" + prix + "]");
			
			assertThat(code).isEqualTo("PEP");
		}
	}
	
	@Test
	public void testFindByCode() throws SQLException {
		LOG.info("testFindByCode");
		
		String sqlInsert = "INSERT INTO pizza (code, nom, prix) VALUES (?, ?, ?)";
		PreparedStatement savePizzaSt = conn.prepareStatement(sqlInsert);
		savePizzaSt.setString(1, "PEP");
		savePizzaSt.setString(2, "Pépéroni");
		savePizzaSt.setDouble(3, 12.50f);
		savePizzaSt.executeUpdate();

		String sqlSelect = "SELECT * FROM pizza WHERE code = ?";
		PreparedStatement findByCodeSt = conn.prepareStatement(sqlSelect);
		findByCodeSt.setString(1, "PEP");
		ResultSet result = findByCodeSt.executeQuery();
		
		while (result.next()) {
			Integer id = result.getInt("id");
			String code = result.getString("code");
			String nom = result.getString("nom");
			Double prix = result.getDouble("prix");
			LOG.info("[id=" + id + " code=" + code + " nom=" + nom + " prix=" + prix + "]");
			
			assertThat(code).isEqualTo("PEP");
		}
	}

	@Test
	public void testUpdatePizza() throws UpdatePizzaException, SQLException {
		LOG.info("testUpdatePizza");
		
		String sqlInsert = "INSERT INTO pizza (code, nom, prix) VALUES (?, ?, ?)";
		PreparedStatement savePizzaSt = conn.prepareStatement(sqlInsert);
		savePizzaSt.setString(1, "PEP");
		savePizzaSt.setString(2, "Pépéroni");
		savePizzaSt.setDouble(3, 12.50f);
		savePizzaSt.executeUpdate();

		String sqlUpdate = "UPDATE pizza SET code = 'PIZ', nom = 'Pizza', prix = 10 WHERE code = ?";
		PreparedStatement updatePizzaSt = conn.prepareStatement(sqlUpdate);
		updatePizzaSt.setString(1, "PEP");
		updatePizzaSt.executeUpdate();
		
		String sqlSelect = "SELECT * FROM pizza WHERE code = ?";
		PreparedStatement findByCodeSt = conn.prepareStatement(sqlSelect);
		findByCodeSt.setString(1, "PEP");
		ResultSet result = findByCodeSt.executeQuery();
		
		while (result.next()) {
			Integer id = result.getInt("id");
			String code = result.getString("code");
			String nom = result.getString("nom");
			Double prix = result.getDouble("prix");
			LOG.info("[id=" + id + " code=" + code + " nom=" + nom + " prix=" + prix + "]");
			
			assertThat(code).isEqualTo("PIZ");
		}
	}

	@Test
	public void testDeletePizza() throws DeletePizzaException, SQLException {
		LOG.info("testDeletePizza");
		
		String sql = "DELETE FROM WHERE code = ?";
		PreparedStatement deletePizzaSt = conn.prepareStatement(sql);
		deletePizzaSt.setString(1, "PEP");
		
	}
	
}
