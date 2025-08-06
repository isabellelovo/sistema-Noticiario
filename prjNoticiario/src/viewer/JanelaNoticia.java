package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.noticia.CtrlAbstratoNoticia;
import controller.noticia.CtrlExcluirNoticia;
import model.Jornalista;
import model.Noticia;
import model.dao.DaoJornalista;

public class JanelaNoticia extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNumero;
	private JTextField tfData;
	private JTextField tfTexto;
	private JComboBox  cbJornalista;

	/**
	 * Sobrecarga (Overload) de construtores na classe, pois
	 * terá dois construtores
	 */
	public JanelaNoticia(CtrlAbstrato ctrl, Noticia noticia) {
		//
		// Chamada ao construtor DESTA CLASSE que recebe somente 
		// um parâmetro. Para isso usamos a INSTRUÇÃO this(...)
		//
		this(ctrl);
		
		//
		// Se a operação é de "Alteração" ou "Exclusão", vou preencher
		// os campos do formulário para o usuário ver os dados do objeto 
		// a ser alterado/excluído
		//
		if (noticia != null) {
			tfNumero.setText(Integer.toString(noticia.getNumero()));
			tfData.setText(noticia.getData());
			tfTexto.setText(noticia.getTexto());
			cbJornalista.setSelectedItem(noticia.getJornalista());			
		}
		
		// Se a operação é de Exclusão, vou inabilitar os textfields
		if(ctrl instanceof CtrlExcluirNoticia) {
			tfNumero.setEnabled(false);
			tfData.setEnabled(false);
			tfTexto.setEnabled(false);
			cbJornalista.setEnabled(false);
			
			JLabel lbMsg = new JLabel("Deseja excluir essa Notícia?");
			lbMsg.setForeground(new Color(255, 0, 0));
			lbMsg.setFont(new Font("Calibri", Font.BOLD, 16));
			lbMsg.setBounds(135, 257, 187, 14);
			contentPane.add(lbMsg);
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public JanelaNoticia(CtrlAbstrato ctrl) {
		super(ctrl);
		setTitle("Notícia");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Número:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 60, 69, 14);
		contentPane.add(lblNewLabel);

		tfNumero = new JTextField();
		tfNumero.setBounds(89, 55, 111, 20);
		contentPane.add(tfNumero);
		tfNumero.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Data:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(0, 105, 79, 14);
		contentPane.add(lblNewLabel_1);

		tfData = new JTextField();
		tfData.setBounds(89, 100, 311, 20);
		contentPane.add(tfData);
		tfData.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfNumero' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfNumero.getText();
				int numero;
				try {
					// Convertendo a string lida para inteiro
					numero = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo número da notícia!");
					return;
				}
				// Mando para 'tfNomeCorrentista' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String data = tfData.getText();

				// Mando para 'tfChavePix' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String texto = tfTexto.getText();

				// Mando para 'cbAgencia' a mensagem 'getSelectedItem()'
				// Que retorna a agência selecionada
				Jornalista jornalista = (Jornalista)cbJornalista.getSelectedItem();

				//
				// Após ter recuperado o que o usuário definiu, vamos
				// acionar o controlador de caso de uso associado à janela
				//
				CtrlAbstratoNoticia ctrl = (CtrlAbstratoNoticia) getCtrl();
				ctrl.efetuar(numero, data, texto, jornalista);
			}
		});
		btOk.setBounds(104, 282, 89, 23);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btCancelar.setBounds(238, 282, 89, 23);
		contentPane.add(btCancelar);

		JLabel lblNewLabel_1_1 = new JLabel("Texto:");
		lblNewLabel_1_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 153, 69, 14);
		contentPane.add(lblNewLabel_1_1);

		tfTexto = new JTextField();
		tfTexto.setColumns(10);
		tfTexto.setBounds(89, 148, 311, 20);
		contentPane.add(tfTexto);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Jornalista:");
		lblNewLabel_1_1_1_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBackground(UIManager.getColor("Button.background"));
		lblNewLabel_1_1_1_1_1.setBounds(10, 202, 69, 14);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		DaoJornalista dao = new DaoJornalista();		
		cbJornalista = new JComboBox( dao.obterTodos() );
		cbJornalista.setBounds(89, 196, 311, 22);
		contentPane.add(cbJornalista);
	}
}
