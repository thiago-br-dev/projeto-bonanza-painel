package repository;

import java.sql.SQLException;
import models.Chamada;

public interface IRepositorioChamada {

	
	Chamada retornaSenha() throws SQLException;
}
