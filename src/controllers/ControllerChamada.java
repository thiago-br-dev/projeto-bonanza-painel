package controllers;

import java.sql.SQLException;

import models.Chamada;
import repository.IRepositorioChamada;
import repository.RepositorioChamada;

public class ControllerChamada {

	private IRepositorioChamada chamadaRepositorio;

	// GETs e SETs do chamadaRepositorio
	// --------------------------------------------------------------------
	public IRepositorioChamada caixaRepositorio() {
		return chamadaRepositorio;
	}

	public void setCaixaRepositorio(IRepositorioChamada chamadaRepositorio) {
		this.chamadaRepositorio = chamadaRepositorio;
	}

	// -------------------------------------------------------------------
	public ControllerChamada(RepositorioChamada RepositorioCaixa) {
		this.setCaixaRepositorio(RepositorioCaixa);
	}

	
	public Chamada retornaSenha() throws SQLException {
		return chamadaRepositorio.retornaSenha();
	}

	// -------------------------------------------------------------------
}
