package controller.jornalista;

import controller.CtrlAbstrato;
import model.Jornalista;
import viewer.JanelaJornalista;

abstract public class CtrlAbstratoJornalista extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	protected JanelaJornalista janela;
	protected Jornalista       jornalista;

	//
	// MÉTODOS
	//
	
	/**
	 * Quando instanciarmos um objeto CtrlAbstratoAgencia, nós desejamos iniciar a
	 * execução do caso de uso 'Incluir' OU 'Alterar' OU 'Excluir' Agencia	
	 */
	public CtrlAbstratoJornalista(CtrlAbstrato ctrlPai, Jornalista jornalista) {
		super(ctrlPai);
		// Guardando o objeto a ser alterado
		this.jornalista = jornalista;
		// Instanciando o viewer associado ao caso de uso
		this.janela = new JanelaJornalista(this, jornalista);
		this.janela.setVisible(true);
	}

	/**
	 * Sobrecarga do construtor para o caso da Inclusão
	 * @param ctrlPai
	 */
	public CtrlAbstratoJornalista(CtrlAbstrato ctrlPai) {
		// Guardando que é o controlador pai do CtrlAlterarAgencia; ou seja,
		// estamos guardando quem solicitou a execução deste caso de uso
		this(ctrlPai, null);
	}

	/**
	 * Método promove a efetivação da ação do controlador
	 */
	abstract public void efetuar(String cpf, String nome, int matrFunc);

	/**
	 * Método que indica o encerramento do caso de uso
	 */
	public void encerrar() {
		// Fechando a janela de alteração de Agencia
		this.janela.setVisible(false);

		// Recuperando o controlador pai de 'Alterar Agencia'
		CtrlManterJornalistas ctrl = (CtrlManterJornalistas) this.getCtrlPai();
		ctrl.fimEditarJornalista();		
	}

	/**
	 * Retorna o bem tangível produzido pelo caso de uso
	 */
	public Object getBemTangivel() {
		return this.jornalista;
	}

}
