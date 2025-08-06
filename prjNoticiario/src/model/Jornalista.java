package model;

import java.io.Serializable;

public class Jornalista implements Serializable {
	
	//
	// CONSTANTES
	//
	final public static int TAM_CPF = 11;
	final public static int TAM_MAX_NOME = 40;
	final public static int TAM_MIN_MATRICULA = 1;
	final public static int TAM_MAX_MATRICULA = 999;
	
	//
	// ATRIBUTOS
	//
	private String cpf;
	private String nome;
	private int matrFunc;
	
	//
	// MÉTODOS
	//
	public Jornalista(String cpf, String nome, int matrFunc) throws ModelException {
		super();
		this.setCpf(cpf);
		this.setNome(nome);
		this.setMatrFunc(matrFunc);
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) throws ModelException{
		Jornalista.validarCpf(cpf);
		this.cpf = cpf;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) throws ModelException {
		Jornalista.validarNome(nome);
		this.nome = nome;
	}

	public int getMatrFunc() {
		return this.matrFunc;
	}

	public void setMatrFunc(int matrFunc) throws ModelException{
		Jornalista.validarMatrFunc(matrFunc);
		this.matrFunc = matrFunc;
	}

	public String toString() {
		return this.matrFunc + " - " + this.getNome();
	}
	
	public static void validarCpf(String cpf) throws ModelException {
		if(cpf == null || cpf.length() == 0)
			throw new ModelException("O CPF não pode ser nulo!");
		
		if(cpf.length() != TAM_CPF)
			throw new ModelException("O CPF deve ter " + TAM_CPF + " caracteres!");
		
		for(int i = 0; i < cpf.length(); i++) {
			char c = cpf.charAt(i);
		
			if (!Character.isDigit(c) && c != '.' && c != '-')
				throw new ModelException("No CPF, há um caracterer inválido '" + c + "' na posição " + i);
		}
	}
	
	public static void validarNome(String nome) throws ModelException {
		if (nome == null || nome.length() == 0)
			throw new ModelException("O nome não pode ser nulo!");
		
		if(nome.length() > TAM_MAX_NOME)
			throw new ModelException("O nome deve ter até " + TAM_MAX_NOME + " caracteres!");
		
		for (int i = 0; i < nome.length(); i++) {
			char c = nome.charAt(i);
			
			if (!Character.isAlphabetic(c) && !Character.isSpaceChar(c))
				throw new ModelException("No nome, há um caracterer inválido '" + c + "' na posição " + i);
		}
	}
		
	public static void validarMatrFunc(int matrFunc) throws ModelException {
		if(!(matrFunc > TAM_MIN_MATRICULA && matrFunc < TAM_MAX_MATRICULA))
			throw new ModelException("A matrícula deve ser um valor entre " + TAM_MIN_MATRICULA + " e " + TAM_MAX_MATRICULA);
	}
}
