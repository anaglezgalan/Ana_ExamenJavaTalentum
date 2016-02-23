package com.modelo.negocio;

import java.sql.SQLException;
import java.util.Collection;

import com.modelo.entidades.Colega;
import com.persistencia.ColegaDAO;

public class ImplGestion implements GestionDAO {

	private ColegaDAO colegaDAO;
	
	public ImplGestion(ColegaDAO colegaDAO) {
		super();
		this.colegaDAO = colegaDAO;
	}

	@Override
	public void insertarNuevoColega(Colega colega) throws SQLException {
		colegaDAO.insertarNuevoColega(colega);
		
	}

	@Override
	public void borrarColegaPorId(int id) throws SQLException {
		colegaDAO.borrarColegaPorId(id);
		
	}

	@Override
	public void modificarColega(Colega colega) throws SQLException {
		colegaDAO.modificarColega(colega);
		
	}

	@Override
	public Colega verColegaporId(int id) throws SQLException {
		return colegaDAO.verColegaporId(id);
	}

	@Override
	public Collection<Colega> verTodosLosColegas() throws SQLException {
		return verTodosLosColegas();
	}


}
