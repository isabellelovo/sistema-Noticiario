package controller.noticia;

import controller.CtrlAbstrato;
import controller.CtrlPrograma;
import model.Noticia;
import model.dao.DaoNoticia;
import viewer.JanelaConsultarNoticias;

public class CtrlManterNoticias extends CtrlAbstrato {

	//
	// ATRIBUTOS
	//
	private JanelaConsultarNoticias janela;
	private CtrlAbstratoNoticia     ctrlNoticia;

	//
	// MÉTODOS
	//
	public CtrlManterNoticias(CtrlAbstrato ctrlPai) {
		// Chamando o construtor de CtrlAbstrato para determinar
		// quem é o controlador que está solicitando a execução
		// do caso de uso "Consultar Contas"
		super(ctrlPai);
		
		// Instanciando um DaoConta
		DaoNoticia dao = new DaoNoticia();
		
		// Recuperando os objetos Conta existentes
		Noticia[] conjNoticias = dao.obterTodos();
		// Instancio a JanelaConsultarContas, passando a lista inicial de Contas
		this.janela = new JanelaConsultarNoticias(this, conjNoticias);
		// Torno a janela visível
		this.janela.setVisible(true);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Incluir Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarIncluirNoticia() {
		// Iniciando a execução do caso de uso 'Incluir Conta' a partir
		// da instanciação do objeto CtrlIncluirConta. Observe que vamos
		// guardar como atributo desta classe a referência para o controlador
		// do caso de uso 'Incluir Conta'
		this.ctrlNoticia = new CtrlIncluirNoticia(this);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Alterar Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarAlterarNoticia(Noticia noticiaSelecionada) {
		if (noticiaSelecionada == null)
			this.janela.notificar("Selecione uma notícia para alteração");
		else
			// Iniciando a execução do caso de uso 'Alterar Conta' a partir
			// da instanciação do objeto CtrlAlterarConta. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Alterar Conta'
			this.ctrlNoticia = new CtrlAlterarNoticia(this, noticiaSelecionada);
	}

	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Excluir Conta' a partir deste caso de
	 * uso de consulta de Contas
	 */
	public void iniciarExcluirNoticia(Noticia noticiaSelecionada) {
		if (noticiaSelecionada == null)
			this.janela.notificar("Selecione uma notícia para exclusão");
		else
			// Iniciando a execução do caso de uso 'Excluir Conta' a partir
			// da instanciação do objeto CtrlExcluirConta. Observe que vamos
			// guardar como atributo desta classe a referência para o controlador
			// do caso de uso 'Excluir Conta'
			this.ctrlNoticia = new CtrlExcluirNoticia(this, noticiaSelecionada);
	}

	/**
	 * Método que será executado quando o caso de uso 'Excluir Conta' for finalizado
	 */
	public void fimEditarNoticia() {
		this.ctrlNoticia = null;
		// Instanciando um DaoConta
		DaoNoticia dao = new DaoNoticia();
		// Recuperando os objetos Contas existentes
		Noticia[] conjNoticias = dao.obterTodos();
		// Informo à janela quais são os objetos para exibição
		this.janela.atualizarDados(conjNoticias);
	}

	/* *********************************************************** */

	@Override
	public void encerrar() {
		CtrlPrograma ctrl = (CtrlPrograma) getCtrlPai();
		ctrl.fimConsultarNoticias();
	}

	@Override
	public Object getBemTangivel() {
		DaoNoticia dao = new DaoNoticia();
		// Recuperando os objetos Conta existentes
		return dao.obterTodos();
	}

}
