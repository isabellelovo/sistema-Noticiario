package controller.noticia;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.Noticia;
import model.ModelException;
import model.dao.DaoNoticia;

public class CtrlIncluirNoticia extends CtrlAbstratoNoticia {

	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlIncluirConta, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir Conta'
	 */
	public CtrlIncluirNoticia(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlIncluirConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai);
	}

	/**
	 * Método que recebe os dados do viewer para inclusão do Conta
	 */
	public void efetuar(int numero, String data, String texto, Jornalista jornalista) {
		try {
			// Instanciando a Agência
			Noticia n = new Noticia(numero, data, texto, jornalista);
			
			// Solicitando a um DaoConta para guardar o objeto
			DaoNoticia dao = new DaoNoticia();
			dao.incluir(n); 
			
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Notícia " + n + " incluída com sucesso!");
			
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
