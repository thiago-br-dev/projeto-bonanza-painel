package models;

public class Preferencia {
	
	private int id;
	private String texto;
	private int AdministradorId;
	private String dataHoraModificacao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public int getAdministradorId() {
		return AdministradorId;
	}
	public void setAdministradorId(int administradorId) {
		AdministradorId = administradorId;
	}
	public String getDataHoraModificacao() {
		return dataHoraModificacao;
	}
	public void setDataHoraModificacao(String dataHoraModificacao) {
		this.dataHoraModificacao = dataHoraModificacao;
	}

	

}
