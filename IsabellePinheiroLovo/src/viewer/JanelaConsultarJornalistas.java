package viewer; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import controller.jornalista.CtrlManterJornalistas;
import model.Jornalista;

public class JanelaConsultarJornalistas extends JanelaAbstrata {
	//
	// ATRIBUTOS
	//
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable tabela;
	private Jornalista[] listaJornalistas;

	/**
	 * Create the frame.
	 */
	public JanelaConsultarJornalistas(CtrlManterJornalistas c, Jornalista[] conjJornalistas) {
		super(c);
		setTitle("Jornalistas");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btSair = new JButton("Sair");
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btSair.setBounds(335, 227, 89, 23);
		contentPane.add(btSair);

		// A chamada a 'atualizarDados' precisa vir antes
		// da criação do JScrollPane
		this.atualizarDados(conjJornalistas);

		JScrollPane scrollPane = new JScrollPane(tabela);
		scrollPane.setBounds(10, 11, 414, 200);
		contentPane.add(scrollPane);

		JButton btIncluir = new JButton("Incluir");
		btIncluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Notifico ao controlador que o usuário quer iniciar o caso de uso
				// Incluir Agência
				CtrlManterJornalistas ctrl = (CtrlManterJornalistas)getCtrl(); 
				ctrl.iniciarIncluirJornalista();
			}
		});
		btIncluir.setBounds(10, 227, 89, 23);
		contentPane.add(btIncluir);

		JButton btExcluir = new JButton("Excluir");
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou alguma agência
				Jornalista j = obterLinhaSelecionada();
				// Se o usuário selecionou alguma agência
				if (j != null) {
					CtrlManterJornalistas ctrl = (CtrlManterJornalistas)getCtrl(); 
					ctrl.iniciarExcluirJornalista(j);
				} else
					notificar("Selecione um jornalista para exclusão");
			}
		});
		btExcluir.setBounds(121, 227, 89, 23);
		contentPane.add(btExcluir);

		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Verificando se o usuário selecionou alguma agência
				Jornalista j = obterLinhaSelecionada();
				// Se o usuário selecionou alguma agência
				if (j != null) {
					CtrlManterJornalistas ctrl = (CtrlManterJornalistas)getCtrl(); 
					ctrl.iniciarAlterarJornalista(j);
				} else
					notificar("Selecione um jornalista para alteração");
			}
		});
		btAlterar.setBounds(231, 227, 89, 23);
		contentPane.add(btAlterar);

		this.setVisible(true);
	}

	/**
	 * Atualiza os dados apresentados no JTable da janela
	 */
	public void atualizarDados(Jornalista[] conjJornalistas) {
		this.listaJornalistas = conjJornalistas;
		HelperTableModel h = new HelperTableModel(this.listaJornalistas);
		if (this.tabela == null)
			this.tabela = new JTable(h.getTableModel());
		else
			this.tabela.setModel(h.getTableModel());
	}

	/**
	 * Retorna qual objeto
	 * 
	 * @return
	 */
	public Jornalista obterLinhaSelecionada() {
		int numLinhaSelecionada = this.tabela.getSelectedRow();
		if (numLinhaSelecionada != -1)
			return this.listaJornalistas[numLinhaSelecionada];
		return null;
	}
}