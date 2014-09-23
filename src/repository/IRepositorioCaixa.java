package repository;

import java.sql.SQLException;

import models.Caixa;

public interface IRepositorioCaixa {
	
	Caixa retornaObjeto(int id) throws SQLException;
		
	
}
