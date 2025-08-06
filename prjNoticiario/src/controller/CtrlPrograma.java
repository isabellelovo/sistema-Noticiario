package controller;

import controller.jornalista.CtrlManterJornalistas;
import controller.noticia.CtrlManterNoticias;
import model.dao.Serializador;
import viewer.JanelaPrincipal;

public class CtrlPrograma extends CtrlAbstrato {
	//
	// ATRIBUTOS
	//
	private JanelaPrincipal    janela; 
	private CtrlManterJornalistas ctrlConsultarJornalistas;
	private CtrlManterNoticias   ctrlConsultarNoticias;
	
	//
	// MÉTODOS
	//
	public CtrlPrograma() {
		// Chamada ao construtor de CtrlAbstrato. Como o CtrlPrograma é o
		// único que não tem um CtrlPai, passamos 'null' como parâmetro
		super(null);
		
		// Recuperando os objetos da Serialização
		Serializador.recuperarObjetos();
		
		// Instanciando a JanelaPrincipal
		this.janela = new JanelaPrincipal(this);
		
		// Estamos mandando a mensagem 'setVisible(true)' para
		// o objeto apontado por 'janela'
		this.janela.setVisible(true);
	}
	
	public Object getBemTangivel() {
		// Como é o CtrlPrograma, não temos um bem tangível para retornar
		return null;
	}
	
	/**
	 * Define o que deve ser feito ao encerrar o controlador
	 */
	public void encerrar() {
		// Notifico ao cliente que o sistema será encerrado
		this.janela.notificar("Encerrando a execução do sistema");
		// Fecho a janela principal
		this.janela.setVisible(false);
		// Solicito a persistência dos objetos 
		Serializador.salvarObjetos();
		// Encerro o programa
		System.exit(0);
	}
	
	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Jornalistas'
	 */
	public void iniciarConsultarJornalistas() {
		this.ctrlConsultarJornalistas = new CtrlManterJornalistas(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Jornalistas'
	 * for finalizado
	 */
	public void fimConsultarJornalistas() {
		this.ctrlConsultarJornalistas = null;
	}
	
	/* *********************************************************** */

	/**
	 * Iniciando a execução do caso de uso 'Consultar Noticias'
	 */
	public void iniciarConsultarNoticias() {
		this.ctrlConsultarNoticias = new CtrlManterNoticias(this);
	}
	
	/**
	 * Método que será executado quando o caso de uso 'Consultar Contas'
	 * for finalizado
	 */
	public void fimConsultarNoticias() {
		this.ctrlConsultarNoticias = null;
	}
	
	/* *********************************************************** */
	/**
	 * Método main
	 */
	public static void main(String[] args) {
		new CtrlPrograma();
	}
}
