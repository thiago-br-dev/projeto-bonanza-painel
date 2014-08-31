/**
 * interface que contempla todas as funcionalidades do sistema.
 * 
 * @author			Paulo Lima
 * @corporation 	TeckSoft 2014
 * @project 		Araujo Seguros
 * @version 		1.0 
 * @since			Release 1  16/02/2014
 */
package facade;

import java.sql.SQLException;
import java.util.List;

import models.Caixa;
import models.Chamada;
import models.Preferencia;
import repository.RepositorioCaixa;
import repository.RepositorioChamada;
import repository.RepositorioPreferencia;
import controllers.ControllerCaixa;
import controllers.ControllerChamada;
import controllers.ControllerPreferencia;

public class Fachada {

	private static Fachada instancia;
	private ControllerChamada chamada;
	private ControllerCaixa caixa;
	private ControllerPreferencia preferencia;

	// Metodo para iniciar todos os Repositorios e controladores
	// ---------------------------------------------------------------------
	private void iniciarControladores() {
		RepositorioChamada rc = new RepositorioChamada();
		chamada = new ControllerChamada(rc);
		
		RepositorioCaixa ra = new RepositorioCaixa();
		caixa = new ControllerCaixa(ra);
		
		RepositorioPreferencia rp = new RepositorioPreferencia();
		preferencia = new ControllerPreferencia(rp);
	}

	// Contrutor da classe
	// ---------------------------------------------------------------------
	private Fachada() {
		iniciarControladores();
	}

	// Metodo para verificar se a fachada ja existe ou instancia o mesmo.
	// ---------------------------------------------------------------------
	public static Fachada getInstance() {

		if (instancia == null) {
			instancia = new Fachada();
		}
		return instancia;
	}
	// ---------------------------------------------------------------------
	// ************************* chamada *******************************
	// ---------------------------------------------------------------------
	public boolean inserirChamada(Chamada chamada)
			throws SQLException {
		return this.chamada.inserir(chamada);
	}
	// ---------------------------------------------------------------------
	public Chamada retornaSenha()
			throws SQLException {
		return this.chamada.retornaSenha();
	}
	// ---------------------------------------------------------------------
	// ************************* caixa *******************************
	// ---------------------------------------------------------------------
	public List<Caixa> listarCaixa() throws SQLException {
		return this.caixa.listar();
	}

	// ---------------------------------------------------------------------
	public Caixa retornaObjetoCaixa(int id) throws SQLException {
		return this.caixa.retornaObjeto(id);
	}

	// ---------------------------------------------------------------------
	// ************************* Preferencia *******************************
	//----------------------------------------------------------------------
	public List<Preferencia> listarPreferencia() throws SQLException {
		return this.preferencia.listar();
	}

	// ---------------------------------------------------------------------
}
