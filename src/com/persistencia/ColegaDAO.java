package com.persistencia;

import java.sql.SQLException;
import java.util.Collection;

import com.modelo.entidades.Colega;

public interface ColegaDAO {

	public void insertarNuevoColega(Colega colega) throws SQLException;

	public void borrarColegaPorId(int id) throws SQLException;

	public void modificarColega(Colega colega) throws SQLException;

	public Colega verColegaporId(int id) throws SQLException;

	public Collection<Colega> verTodosLosColegas() throws SQLException;
}
