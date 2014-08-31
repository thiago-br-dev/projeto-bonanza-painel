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
	public boolean inserirChamada(Chamada chamada) throws SQLException {
		String sql = "insert into chamada values (?,?,?,?)";

		try {

			Connection conIntranet = new ConnectionFactory()
					.getConnectionIntranet();

			PreparedStatement stm1 = (PreparedStatement) conIntranet
					.prepareStatement(sql);

			stm1.setInt(1, 0);
			stm1.setString(2, chamada.getHora());
			stm1.setString(3, chamada.getData());
			stm1.setInt(4, chamada.getCaixaId());
			stm1.execute();
			stm1.close();
			conIntranet.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//----------------------------------------------------------------------------------
	public Chamada retornaSenha() throws SQLException {
		Chamada chamada;

		String dataSistema = new SimpleDateFormat("dd/MM/yyyy")
		.format(new Date());
		
		String sql = "SELECT * FROM chamada WHERE data='"+dataSistema+"'  ORDER BY `id` DESC LIMIT 1";

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
				chamada.setCaixaId(rs1.getInt(4));

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
