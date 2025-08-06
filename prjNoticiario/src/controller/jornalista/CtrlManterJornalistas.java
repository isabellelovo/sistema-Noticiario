package controller.jornalista;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Jornalista;
import model.dao.DaoJornalista;
import viewer.JanelaConsultarJornalistas;

public class CtrlManterJornalistas extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarJornalistas janela;
	private CtrlAbstratoJornalista     ctrlJornalista;

	//
	// MÉTODOS
	//
	public CtrlManterJornalistas(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Agencias"
		super(ctrlPai);
		
		// Instanciando um DaoAgencia
		DaoJornalista dao = new DaoJornalista();
		// Recuperando os objetos Agencia existentes
		Jornalista[] conjJornalistas = dao.obterTodos();
		// Instancio a JanelaConsultarAgencias, passando a lista inicial de Agencias
		this.janela = new JanelaConsultarJornalistas(this, conjJornalistas);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarIncluirJornalista() {
		// Iniciando a execução do caso de uso 'Incluir Agencia' a partir
		// da instanciação do objeto CtrlIncluirAgencia. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Agencia'
		this.ctrlJornalista = new CtrlIncluirJornalista(this);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Alterar Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarAlterarJornalista(Jornalista jornalistaSelecionado) {
		if (jornalistaSelecionado == null)
			this.janela.notificar("Selecione um Jornalista para alteração");
		else
			// Iniciando a execução do caso de uso 'Alterar Agencia' a partir
			// da instanciação do objeto CtrlAlterarAgencia. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Alterar Agencia'
			this.ctrlJornalista = new CtrlAlterarJornalista(this, jornalistaSelecionado);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Excluir Agencia' a partir deste caso de
	 * uso de consulta de Agencias
	 */
	public void iniciarExcluirJornalista(Jornalista jornalistaSelecionado) {
		if (jornalistaSelecionado == null)
			this.janela.notificar("Selecione um jornalista para exclusão");
		else
			// Iniciando a execução do caso de uso 'Excluir Agencia' a partir
			// da instanciação do objeto CtrlExcluirAgencia. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Excluir Agencia'
			this.ctrlJornalista = new CtrlExcluirJornalista(this, jornalistaSelecionado);
	}

	/**
	 * Método que será executado quando o caso de uso 'Excluir Agencia' for finalizado
	 */
	public void fimEditarJornalista() {
		this.ctrlJornalista = null;
		// Instanciando um DaoAgencia
		DaoJornalista dao = new DaoJornalista();
		// Recuperando os objetos Agencias existentes
		Jornalista[] conjJornalistas = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjJornalistas);
	}

	/* *********************************************************** */

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarJornalistas();
	}

	@Override
	public Object getBemTangivel() {
		DaoJornalista dao = new DaoJornalista();
		// Recuperando os objetos Agencia existentes
		return dao.obterTodos();
	}

}
