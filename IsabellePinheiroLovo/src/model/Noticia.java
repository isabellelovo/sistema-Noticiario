package model;

import java.io.Serializable;
import java.util.Set;

public class Noticia implements Serializable {
	//
	// CONSTANTES
	//
	final public static int TAM_DATA = 10;
	final public static int TAM_MAX_TEXTO = 80;
	
	//
	// ATRIBUTOS
	//
	private int numero;
	private String data;
	private String texto;
	
	//
	// ATRIBUTO DE RELACIONAMENTO
	//
	private Jornalista jornalista;

	//
	// MÉTODOS
	//
	public Noticia(int numero, String data, String texto, Jornalista jornalista) throws ModelException {
		super();
		this.setNumero(numero);
		this.setData(data);
		this.setTexto(texto);
		this.setJornalista(jornalista);
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int num) throws ModelException {
		Noticia.validarNumero(num);
		this.numero = num;
	}

	public String getData() {
		return this.data;
	}

	public void setData(String data) throws ModelException {
		Noticia.validarData(data);
		this.data = data;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) throws ModelException {
		Noticia.validarTexto(texto);
		this.texto = texto;
	}

	public Jornalista getJornalista() {
		return this.jornalista;
	}

	public void setJornalista(Jornalista jornalista) throws ModelException {
		Noticia.validarJornalista(jornalista);
		this.jornalista = jornalista;
	}

	public String toString() {
		return this.getNumero() + " - " + this.getData();
	}
	
	public static void validarNumero(int numero) throws ModelException {
		if(numero <= 0)
			throw new ModelException("O número da notícia deve ser maior que 0.");
	}
	
	public static void validarData(String data) throws ModelException {
		if(data == null || data.length() == 0)
			throw new ModelException("A data não pode ser nula!");
		
		if(data.length() != TAM_DATA)
			throw new ModelException("A data deve ter " + TAM_DATA + " caracteres!");
		
		for(int i = 0; i < data.length(); i++) {
			char c = data.charAt(i);
		
			if (!Character.isDigit(c) && c != '/')
				throw new ModelException("Na data, há um caracterer inválido '" + c + "' na posição " + i);
		}
	}
		
	public static void validarTexto(String texto) throws ModelException {
		if(texto == null || texto.length() == 0)
			throw new ModelException("O texto não pode ser nulo!");
		
		if(texto.length() > TAM_MAX_TEXTO)
			throw new ModelException("O texto deve ter até " + TAM_MAX_TEXTO + " caracteres!");
		for(int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			
			if(!Character.isAlphabetic(c) && !Character.isSpaceChar(c) && !Character.isDigit(c) && c != ',')
				throw new ModelException("O texto tem um caracter inválido na posição " + i + ": " + c);	
		}
	}
	
	public static void validarJornalista(Jornalista jornalista) throws ModelException {
		if (jornalista == null)
			throw new ModelException("É obrigatório indicar quem é o jornalista da notícia.");
	}
}
