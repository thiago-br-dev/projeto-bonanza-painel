package exceptions;

import javax.swing.JOptionPane;

public class ExceptionSql extends Exception {

	private static final long serialVersionUID = -1241580297905484289L;

	public ExceptionSql() {
		// System.out.println("Ocorreu um Erro com a Conex�o com a Base de Dados!");
		JOptionPane
				.showMessageDialog(
						null,
						"Ops ! Ocorreu um erro com a conex�o do seu servidor. Verifique e tente novamente.",
						"Erro de Conex�o", JOptionPane.ERROR_MESSAGE);
	}
}
