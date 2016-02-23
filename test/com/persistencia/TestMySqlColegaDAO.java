package com.persistencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import com.modelo.entidades.Colega;

public class TestMySqlColegaDAO {

	@Test
	public void testInsertar() {

		// Datos de entrada

		Colega colega = new Colega(110, "Ana", "Madrid", new Date());

		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");

		// sut

		MySqlColegaDAO sut = new MySqlColegaDAO(ds);

		// Ejecucion

		try {

			sut.insertarNuevoColega(colega);

			Colega colegaObtenido = sut.verColegaporId(colega.getId());

			// Asertos

			assertEquals(colega, colegaObtenido);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testBorrar() {

		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");

		// sut

		MySqlColegaDAO sut = new MySqlColegaDAO(ds);

		// Ejecucion

		try {
			sut.borrarColegaPorId(110);

			Colega colegaObtenido = sut.verColegaporId(110);

			// Asertos

			assertNull(colegaObtenido);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testModificar() {

		Colega colega = new Colega(120, "Ana", "Madrid", new Date());
		Colega colegaModificado = new Colega(120, "Ana", "Alicante", new Date());

		BasicDataSource ds = new BasicDataSource();
		ds.setUrl("jdbc:mysql://localhost:3306/colegajdbc");
		ds.setUsername("root");
		ds.setPassword("root");
		ds.setDriverClassName("com.mysql.jdbc.Driver");

		// sut

		MySqlColegaDAO sut = new MySqlColegaDAO(ds);

		// Ejecucion

		try {

			sut.insertarNuevoColega(colega);
			sut.modificarColega(colegaModificado);

			// Asertos
			assertEquals(colega, sut.verColegaporId(120));
			assertEquals(colegaModificado, sut.verColegaporId(120));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
