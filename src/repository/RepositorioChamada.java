package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.Chamada;
import util.ConnectionFactory;

public class RepositorioChamada implements IRepositorioChamada {

	// ---------------------------------------------------------------------------------
	public Chamada retornaSenha() throws SQLException {
		Chamada chamada;

		String dataSistema = new SimpleDateFormat("yyyy-MM-dd")
		.format(new Date());
		
		String horaSistema = new SimpleDateFormat("HH:mm:ss").format(new Date());
		
		String sql = "SELECT * FROM chamada WHERE data='"+dataSistema+"' AND hora <='"+horaSistema+"'  ORDER BY `hora` DESC LIMIT 1";

		try {
			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();
			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);
			ResultSet rs1 = stm1.executeQuery();

			while (rs1.next()) {

				chamada = new Chamada();
				chamada.setId(rs1.getInt(1));
				chamada.setData(rs1.getString(2));
				chamada.setHora(rs1.getString(3));
				chamada.setEspera(rs1.getString(4));
				chamada.setCaixaId(rs1.getInt(5));

				return chamada;

			}
			rs1.close();
			stm1.close();
			conIntranet.close();
		} catch (Exception e) {
				e.printStackTrace();
		}

		return null;
	}
}
