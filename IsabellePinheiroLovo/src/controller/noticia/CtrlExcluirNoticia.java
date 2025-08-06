package controller.noticia;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.Noticia;
import model.dao.DaoNoticia;

public class CtrlExcluirNoticia extends CtrlAbstratoNoticia {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlExcluirConta, nós desejamos iniciar a
	 * execução do caso de uso 'Excluir Conta'
	 */
	public CtrlExcluirNoticia(CtrlAbstrato ctrlPai, Noticia noticiaASerExcluida) {
		// Guardando que é o controlador pai do CtrlAlterarConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		super(ctrlPai, noticiaASerExcluida);
	}

	/**
	 * Método que recebe a confirmação para excluirmos uma Agência
	 */
	public void efetuar(int numero, String data, String texto, Jornalista jornalista) {
		
		// Solicitando a um DaoConta para excluir o objeto
		DaoNoticia dao = new DaoNoticia();
		
		dao.remover(this.noticia);
		// Mando a notificação de sucesso para o usuário
		this.janela.notificar("Notícia " + this.noticia + " foi excluída com sucesso!");
		
		// Encerro o caso de uso
		this.encerrar();
	}
}
