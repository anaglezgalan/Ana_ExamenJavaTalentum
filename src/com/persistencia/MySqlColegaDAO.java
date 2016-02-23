package com.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;

import javax.sql.DataSource;

import com.modelo.entidades.Colega;

public class MySqlColegaDAO implements ColegaDAO {

	private static final String INSERT_COLEGA = "INSERT INTO colega (id, nombre, ciudad, fecha) VALUES (?, ?, ?, ?)";
	private static final String DELETE_COLEGA = "DELETE FROM colega WHERE id = ?";
	private static final String UPDATE_COLEGA = "UPDATE colega SET nombre = ?, ciudad= ?, fecha =? WHERE id = ?";
	private static final String SELECT_COLEGA_BY_ID = "SELECT * FROM  colega WHERE id = ?";
	private static final String SELECT_COLEGA = "SELECT * FROM  colega";

	private DataSource ds;

	public MySqlColegaDAO(DataSource ds) {

		super();
		this.ds = ds;

	}

	@Override
	public void insertarNuevoColega(Colega colega) throws SQLException {
		// 1. Obtener la conexion
		Connection connection = null;
		try {
			connection = ds.getConnection();

			// 2. Obtener el statement
			PreparedStatement ps = connection.prepareStatement(INSERT_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date(colega.getFecha().getTime()));

			// 3. Ejecutar la sentencia
			ps.executeUpdate();

			// 4. (opcional) Solo cuando sea una sentencia de tipo executeQuery
			// Procesado de los resultados
		} finally {

			// 5. Cerrar conexion
			if (connection != null) {
				connection.close();
			}
		}

	}

	@Override
	public void borrarColegaPorId(int id) throws SQLException {
		Connection connection = null;
		try {
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement(DELETE_COLEGA);
			ps.setInt(1, id);

			ps.executeUpdate();

		} finally {

			if (connection != null) {
				connection.close();
			}
		}

	}

	@Override
	public void modificarColega(Colega colega) throws SQLException {

		Connection connection = null;
		try {
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement(UPDATE_COLEGA);
			ps.setInt(1, colega.getId());
			ps.setString(2, colega.getNombre());
			ps.setString(3, colega.getCiudad());
			ps.setDate(4, new java.sql.Date(colega.getFecha().getTime()));

			// 3. Ejecutar la sentencia
			ps.executeUpdate();

			// 4. (opcional) Solo cuando sea una sentencia de tipo executeQuery
			// Procesdo de los resultados
		} finally {

			// 5. Cerrar conexion
			if (connection != null) {
				connection.close();
			}
		}

	}

	@Override
	public Colega verColegaporId(int id) throws SQLException {
		Connection connection = null;
		try {
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA_BY_ID);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {

				return new Colega(id, rs.getString("nombre"), rs.getString("ciudad"), rs.getDate("fecha"));

			} else {

				return null;
			}

		} finally {

			if (connection != null) {
				connection.close();
			}
		}
	}

	@Override
	public Collection<Colega> verTodosLosColegas() throws SQLException {

		Collection<Colega> cuadrilla = new HashSet<>();

		Connection connection = null;
		try {
			connection = ds.getConnection();

			PreparedStatement ps = connection.prepareStatement(SELECT_COLEGA);

			ResultSet rs = ps.executeQuery();

			if (rs.first()) {
				do {

					Colega colega = new Colega(rs.getInt("id"), rs.getString("nombre"), rs.getString("ciudad"),
							rs.getDate("fecha"));

					cuadrilla.add(colega);

				} while (rs.next());
			}

			return cuadrilla;

		} finally {

			if (connection != null) {
				connection.close();
			}
		}
	}

}
