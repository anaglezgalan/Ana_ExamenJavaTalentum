package com.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import com.modelo.entidades.Colega;

public interface GestionDAO {

	void insertarNuevoColega(Colega colega) throws SQLException;

	void borrarColegaPorId(int id) throws SQLException;

	void modificarColega(Colega colega) throws SQLException;

	Colega verColegaporId(int id) throws SQLException;

	Collection<Colega> verTodosLosColegas() throws SQLException;

}
