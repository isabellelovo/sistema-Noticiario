package viewer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.CtrlAbstrato;
import controller.jornalista.CtrlAbstratoJornalista;
import controller.jornalista.CtrlExcluirJornalista;
import model.Jornalista;

public class JanelaJornalista extends JanelaAbstrata {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfCpf;
	private JTextField tfNome;
	private JTextField tfMatr;

	/**
	 * Sobrecarga (Overload) de construtores na classe, pois
	 * terá dois construtores
	 */
	public JanelaJornalista(CtrlAbstrato ctrl, Jornalista jornalista) {
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
		if (jornalista != null) {
			tfCpf.setText(jornalista.getCpf());
			tfNome.setText(jornalista.getNome());
			tfMatr.setText(Integer.toString(jornalista.getMatrFunc()));
		}
		
		// Se a operação é de Exclusão, vou inabilitar os textfields
		if(ctrl instanceof CtrlExcluirJornalista) {
			tfCpf.setEnabled(false);
			tfNome.setEnabled(false);
			tfMatr.setEnabled(false);
			
			JLabel lbMsg = new JLabel("Deseja excluir esse Jornalista?");
			lbMsg.setForeground(new Color(255, 0, 0));
			lbMsg.setFont(new Font("Calibri", Font.PLAIN, 14));
			lbMsg.setBounds(139, 150, 187, 14);
			contentPane.add(lbMsg);
		}
	}
	
	/**
	 * @wbp.parser.constructor
	 */
	public JanelaJornalista(CtrlAbstrato ctrl) {
		super(ctrl);
		setTitle("Jornalista");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 252);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Matrícula:");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel.setBounds(14, 117, 65, 14);
		contentPane.add(lblNewLabel);

		tfMatr = new JTextField();
		tfMatr.setBounds(89, 112, 111, 20);
		contentPane.add(tfMatr);
		tfMatr.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("CPF:");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(47, 44, 32, 14);
		contentPane.add(lblNewLabel_1);

		tfCpf = new JTextField();
		tfCpf.setBounds(89, 39, 311, 20);
		contentPane.add(tfCpf);
		tfCpf.setColumns(10);

		JButton btOk = new JButton("Ok");
		btOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Mando para 'tfCodigo' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String aux = tfMatr.getText();
				int matrFunc;
				try {
					// Convertendo a string lida para inteiro
					matrFunc = Integer.parseInt(aux);
				} catch (NumberFormatException nfe) {
					// Se não conseguiu fazer a conversão, então colocamos a dialog
					// com o texto de erro e saímos do método
					JOptionPane.showMessageDialog(btOk, "Valor não numérico no campo matrícula do Jornalista!");
					return;
				}
				// Mando para 'tfBairro' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String cpf = tfCpf.getText();

				// Mando para 'tfCidade' a mensagem 'getText()'
				// Que retorna o conteúdo digitado dentro do textfield
				String nome = tfNome.getText();

				//
				// Após ter recuperado o que o usuário definiu, vamos
				// acionar o controlador de caso de uso associado à janela
				//
				CtrlAbstratoJornalista ctrl = (CtrlAbstratoJornalista) getCtrl();
				ctrl.efetuar(cpf, nome, matrFunc);
			}
		});
		btOk.setBounds(103, 167, 89, 23);
		contentPane.add(btOk);

		JButton btCancelar = new JButton("Cancelar");
		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getCtrl().encerrar();
			}
		});
		btCancelar.setBounds(237, 167, 89, 23);
		contentPane.add(btCancelar);

		JLabel lblNewLabel_1_1 = new JLabel("Nome:");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(33, 81, 46, 14);
		contentPane.add(lblNewLabel_1_1);

		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(89, 76, 311, 20);
		contentPane.add(tfNome);		
	}
}
