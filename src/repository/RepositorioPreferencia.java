package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Preferencia;
import util.ConnectionFactory;

public class RepositorioPreferencia implements IRepositorioPreferencia {

	// ---------------------------------------------------------------------------------
	public List<Preferencia> listarPreferencia() throws SQLException {
		
		ArrayList<Preferencia> preferenciaDB = new ArrayList<>();
		Preferencia preferencia;
		String sql = "SELECT * FROM preferencia";

			
			Connection conIntranet = new ConnectionFactory()
			.getConnectionIntranet();
			
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			
			ResultSet rs1 = stm1.executeQuery();
			
			while (rs1.next()) {

				preferencia = new Preferencia();
				preferencia.setId(rs1.getInt(1));
				preferencia.setTexto(rs1.getString(2));

				preferenciaDB.add(preferencia);

			}
			rs1.close();
			stm1.close();
			conIntranet.close();

		return preferenciaDB ;
	}
	// ---------------------------------------------------------------------------------
}
