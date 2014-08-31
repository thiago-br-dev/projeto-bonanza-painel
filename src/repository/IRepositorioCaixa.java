package repository;

import java.sql.SQLException;
import java.util.List;
import models.Caixa;

public interface IRepositorioCaixa {
	
	List<Caixa> listarCaixa() throws SQLException;
	
	Caixa retornaObjeto(int id) throws SQLException;
		
	
}
