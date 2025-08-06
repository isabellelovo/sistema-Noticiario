package controller.jornalista;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.ModelException;
import model.dao.DaoJornalista;
import viewer.JanelaJornalista;

public class CtrlAlterarJornalista extends CtrlAbstratoJornalista {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlAlterarAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Alterar Agencia'
	 */
	public CtrlAlterarJornalista(CtrlAbstrato ctrlPai, Jornalista jornalistaASerAlterado) {
		super(ctrlPai, jornalistaASerAlterado);
	}

	/**
	 * Método que recebe os dados do viewer para alteração do Agencia
	 */
	public void efetuar(String cpf, String nome, int matrFunc) {
		try {
			// Alterando o estado do objeto que guardamos no construtor
			this.jornalista.setCpf(cpf);
			this.jornalista.setNome(nome);
			this.jornalista.setMatrFunc(matrFunc);
			
			// Solicitando a um DaoAgencia para guardar o objeto
			DaoJornalista dao = new DaoJornalista();
			dao.alterar(this.jornalista);
			
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Jornalista " + this.jornalista + " foi alterado com sucesso!");
			
			// Encerro o caso de uso
			this.encerrar();
			
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
