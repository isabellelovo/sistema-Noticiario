package controller.noticia;

import controller.CtrlAbstrato;
import model.Jornalista;
import model.Noticia;
import viewer.JanelaNoticia;

abstract public class CtrlAbstratoNoticia extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	protected JanelaNoticia janela;
	protected Noticia       noticia;

	//
	// MÉTODOS
	//
	
	/**
	 * Quando instanciarmos um objeto CtrlAbstratoConta, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir' OU 'Alterar' OU 'Excluir' Conta	
	 */
	public CtrlAbstratoNoticia(CtrlAbstrato ctrlPai, Noticia noticia) {
		super(ctrlPai);
		
		// Guardando o objeto a ser alterado
		this.noticia = noticia;
		
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaNoticia(this, noticia);
		this.janela.setVisible(true);
	}

	/**
	 * Sobrecarga do construtor para o caso da Inclusão
	 * @param ctrlPai
	 */
	public CtrlAbstratoNoticia(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlAlterarConta; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		this(ctrlPai, null);
	}

	/**
	 * Método promove a efetivação da ação do controlador
	 */
	abstract public void efetuar(int numero, String data, String texto, Jornalista jornalista);
	
	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de alteração de Conta
		this.janela.setVisible(false);

		// Recuperando o controlador pai de 'Alterar Conta'
		CtrlManterNoticias ctrl = (CtrlManterNoticias) this.getCtrlPai();
		ctrl.fimEditarNoticia();		
	}

	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.noticia;
	}

}
