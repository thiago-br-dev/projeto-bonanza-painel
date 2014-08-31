package controllers;

import java.sql.SQLException;
import java.util.List;

import models.Preferencia;
import repository.IRepositorioPreferencia;
import repository.RepositorioPreferencia;

public class ControllerPreferencia {
	private IRepositorioPreferencia preferenciaRepositorio;

	// GETs e SETs do clienteRepositorio
	// --------------------------------------------------------------------
	public IRepositorioPreferencia preferenciaRepositorio() {
		return preferenciaRepositorio;
	}

	public void setPreferenciaRepositorio(
			IRepositorioPreferencia preferenciaRepositorio) {
		this.preferenciaRepositorio = preferenciaRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerPreferencia(RepositorioPreferencia RepositorioPreferencia) {
		this.setPreferenciaRepositorio(RepositorioPreferencia);
	}

	// -------------------------------------------------------------------
	public List<Preferencia> listar() throws SQLException {
		return preferenciaRepositorio.listarPreferencia();
	}

	// -------------------------------------------------------------------
}
