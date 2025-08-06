package model.dao;

import model.ModelException;
import model.Noticia;

public class DaoNoticia {
	//
	// CONSTANTES
	//
	final public static int TAM_INICIAL_ELEMENTOS = 5;
	final public static int FATOR_CRESCIMENTO = 3;
	final public static int NAO_ESTA_PRESENTE = -1;

	//
	// ATRIBUTOS
	//
	private static int numElementos = 0;
	private static Noticia[] arrayDeElementos = new Noticia[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoNoticia() {
		super();
	}
	
	/**
	 * Inclui uma nova Noticia no array de elementos do Dao
	 */
	public boolean incluir(Noticia nova) throws ModelException{
		
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(nova == null)
			return false;
		
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
		int tamanho = DaoNoticia.arrayDeElementos.length;
		
		if(DaoNoticia.numElementos == tamanho) {
			// Criamos um array novo com tamanho maior
			Noticia[] novoArray = new Noticia[tamanho + FATOR_CRESCIMENTO];
			
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoNoticia.arrayDeElementos[i];
			
			// Determinamos que o array novo é o arrayDeElementos 
			DaoNoticia.arrayDeElementos = novoArray;
		}
		
		for (int i=0; i < numElementos; i++) {
			if(nova.getNumero() == DaoNoticia.arrayDeElementos[i].getNumero())
				throw new ModelException("Já existe uma notícia com esse número.");
		}
		
		// Incluindo a nova Noticia no array de elementos do Dao
		DaoNoticia.arrayDeElementos[ DaoNoticia.numElementos ] = nova;
		
		// Incrementamos numElementos
		DaoNoticia.numElementos++;
		
		// retornamos true informando que incluímos a nova Noticia
		return true;		
	}
	
	
	/**
	 * Altera uma Noticia no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Noticia noticiaAlterada) {
		if(this.posicaoDe(noticiaAlterada) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Noticia c) {
		for(int i = 0; i < DaoNoticia.numElementos; i++)
			if(DaoNoticia.arrayDeElementos[i] == c)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Noticia ex) {
		int pos;
		
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoNoticia.numElementos; pos++) 
			if(DaoNoticia.arrayDeElementos[pos] == ex) 
				break;
		
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoNoticia.numElementos)
			return false;
		
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoNoticia.numElementos-1; i++)
			DaoNoticia.arrayDeElementos[i] = DaoNoticia.arrayDeElementos[i++];  
		
		// Colocando null na antiga última posição
		DaoNoticia.arrayDeElementos[numElementos - 1] = null;
		
		// Decrementando o número de elementos do Dao
		DaoNoticia.numElementos--;
		
		// retornamos true, informando que efetuamos a operação
		return true;
	}
	
	/**
	 * Retorna a Noticia cujo número foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Noticia obterNoticiaPeloNumero(int numero) {
		// Para cada Noticia presente dentro do array de elementos
		for(int i = 0; i < DaoNoticia.numElementos; i++) {
			int numDaNoticia = DaoNoticia.arrayDeElementos[i].getNumero();
			if(numDaNoticia == numero)
				return DaoNoticia.arrayDeElementos[i];
		}
		
		return null;
	}

	/**
	 * Retorna todos os objetos Noticia gerenciados pelo DAO
	 */
	public static Noticia[] obterTodos() {
		return DaoNoticia.arrayDeElementos;
	}
	
	/**
	 * Método que determina o novo arrayDeElementos a ser gerenciado
	 * pelo DAO. Observe que não há indicação de visibilidade (public,
	 * private ou protected). Isso em Java indica que a visibilidade é
	 * "package"; ou seja, somente as classes que pertencem ao mesmo 
	 * pacote sabem da existência deste método. Como somente a classe
	 * Serializador vai usar (e ela faz parte de model.dao), optamos
	 * por deixar a visibilidade package.
	 */
	static void recuperarTodos(Noticia[] array) {
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoNoticia.arrayDeElementos = array;
		
		// Contando quantos objetos Noticia estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
