package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.Caixa;
import util.ConnectionFactory;

public class RepositorioCaixa implements IRepositorioCaixa {

	//-----------------------------------------------------------------------
	public Caixa retornaObjeto(int id) throws SQLException {
		Caixa caixa;

		String sql = "SELECT * FROM caixa WHERE id="+id;

		try {
			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			ResultSet rs1 = stm1.executeQuery();

			while (rs1.next()) {

				caixa = new Caixa();
				caixa.setId(rs1.getInt(1));
				caixa.setCaixa(rs1.getString(2));
				caixa.setAtendente(rs1.getString(3));
				caixa.setDataHoraCadastro(rs1.getString(4));
				caixa.setAdministradorId(rs1.getInt(5));

				return caixa;

			}
			rs1.close();
			stm1.close();
			conIntranet.close();
		} catch (Exception e) {
				e.printStackTrace();
		}

		return null;
	}
	//---------------------------------------------------------------------------------
}
