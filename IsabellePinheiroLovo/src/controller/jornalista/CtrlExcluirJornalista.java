package controller.jornalista;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.dao.DaoJornalista;

public class CtrlExcluirJornalista extends CtrlAbstratoJornalista {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlExcluirAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Excluir Agencia'
	 */
	public CtrlExcluirJornalista(CtrlAbstrato ctrlPai, Jornalista jornalistaASerExcluido) {
		// Guardando que é o controlador pai do CtrlAlterarAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai, jornalistaASerExcluido);
	}

	/**
	 * Método que recebe a confirmação para excluirmos uma Agência
	 */
	public void efetuar(String cpf, String nome, int matrFunc) {
		// Solicitando a um DaoAgencia para excluir o objeto
		DaoJornalista dao = new DaoJornalista();

		dao.remover(this.jornalista);
		
		// Mando a notificação de sucesso para o usuário
		this.janela.notificar("Jornalista " + this.jornalista + " foi excluído com sucesso!");
		
		// Encerro o caso de uso
		this.encerrar();
	}
}
