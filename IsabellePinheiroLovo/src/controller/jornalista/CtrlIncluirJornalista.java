package controller.jornalista;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.ModelException;
import model.dao.DaoJornalista;

public class CtrlIncluirJornalista extends CtrlAbstratoJornalista {

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir Agencia'
	 */
	public CtrlIncluirJornalista(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do Agencia
	 */
	public void efetuar(String cpf, String nome, int matrFunc) {
		try {
			// Instanciando a Agência
			Jornalista j = new Jornalista(cpf, nome, matrFunc);

			// Solicitando a um DaoAgencia para guardar o objeto
			DaoJornalista dao = new DaoJornalista();
			dao.incluir(j); 
			
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Jornalista " + j + " incluído com sucesso!");
			
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
