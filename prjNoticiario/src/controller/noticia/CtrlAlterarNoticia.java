package controller.noticia;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.Noticia;
import model.ModelException;
import model.dao.DaoNoticia;

public class CtrlAlterarNoticia extends CtrlAbstratoNoticia {
	//
	// MÉTODOS
	//

	/**
	 * Quando instanciarmos um objeto CtrlAlterarConta, nós desejamos iniciar a
	 * execução do caso de uso 'Alterar Conta'
	 */
	public CtrlAlterarNoticia(CtrlAbstrato ctrlPai, Noticia noticiaASerAlterada) {
		super(ctrlPai, noticiaASerAlterada);
	}

	/**
	 * Método que recebe os dados do viewer para alteração do Conta
	 */
	public void efetuar(int numero, String data, String texto, Jornalista jornalista) {
		try {
			// Alterando o estado do objeto que guardamos no construtor
			this.noticia.setNumero(numero);
			this.noticia.setData(data);
			this.noticia.setTexto(texto);
			this.noticia.setJornalista(jornalista);
			
			// Solicitando a um DaoConta para guardar o objeto
			DaoNoticia dao = new DaoNoticia();
			
			dao.alterar(this.noticia); 
			
			// Mando a notificação de sucesso para o usuário
			this.janela.notificar("Notícia " + this.noticia + " foi alterada com sucesso!");
			
			// Encerro o caso de uso
			this.encerrar();
		} catch (ModelException me) {
			// Informo ao usuário que ocorreu algum problema
			// na instanciação do aluno
			this.janela.notificar(me.getMessage());
		}
	}
}
