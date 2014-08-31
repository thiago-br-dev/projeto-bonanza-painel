package repository;

import java.sql.SQLException;
import java.util.List;
import models.Preferencia;

public interface IRepositorioPreferencia {
	
	List<Preferencia> listarPreferencia() throws SQLException;

}
