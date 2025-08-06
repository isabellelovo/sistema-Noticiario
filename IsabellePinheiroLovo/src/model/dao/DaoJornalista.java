package model.dao;

import model.Jornalista;

public class DaoJornalista {
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
	private static Jornalista[] arrayDeElementos = new Jornalista[TAM_INICIAL_ELEMENTOS];
	
	//
	// MÉTODOS
	// 
	public DaoJornalista() {
		super();
	}
	
	/**
	 * Inclui um novo Jornalista no array de elementos do Dao
	 */
	public boolean incluir(Jornalista novo) {
		
		// Não podemos adicionar se o parâmetro recebido for nulo
		if(novo == null)
			return false;
		
		// Se o array de elementos já estiver completo, 
		// Vamos criar um novo array maior
	
		int tamanho = DaoJornalista.arrayDeElementos.length;
		
		if(DaoJornalista.numElementos == tamanho) {
		
			// Criamos um array novo com tamanho maior
			Jornalista[] novoArray = new Jornalista[tamanho + FATOR_CRESCIMENTO];
			
			// copiamos os elementos do array antigo para o novo
			for(int i = 0; i < tamanho; i++)
				novoArray[i] = DaoJornalista.arrayDeElementos[i];
		
			// Determinamos que o array novo é o arrayDeElementos 
			DaoJornalista.arrayDeElementos = novoArray;
		}
		
		// Incluindo o novo Jornalista no array de elementos do Dao
		DaoJornalista.arrayDeElementos[ DaoJornalista.numElementos ] = novo;
		
		// Incrementamos numElementos
		DaoJornalista.numElementos++;
		
		// retornamos true informando que incluímos o novo Jornalista
		return true;		
	}
	
	
	/**
	 * Altera um Jornalista no array de elementos do Dao. Não será preciso
	 * realizar nada específico, pois o objeto já deverá estar presente
	 * no array
	 */
	public boolean alterar(Jornalista jorAlterado) {
		if(this.posicaoDe(jorAlterado) == NAO_ESTA_PRESENTE)
			return false;		
		return true;		
	}
	
	/**
	 * Informa a posição do objeto no arrayDeElementos. Se não estiver
	 * presente, vamos retornar -1 (NAO_ESTA_PRESENTE)
	 */
	public int posicaoDe(Jornalista a) {
		for(int i = 0; i < DaoJornalista.numElementos; i++)
			if(DaoJornalista.arrayDeElementos[i] == a)
				return i;
		return -1;
	}
	
	/**
	 * Remove um objeto do arrayDeElementos, caso ele esteja presente  
	 */
	public boolean remover(Jornalista ex) {
		int pos;
		
		// Varrendo o arrayDeElementos para sabermos em que posição
		// o objeto apontado por 'ex' está
		for(pos = 0; pos < DaoJornalista.numElementos; pos++) 
			if(DaoJornalista.arrayDeElementos[pos] == ex) 
				break;
		
		// Se pos é igual ao numElementos, é porque o objeto apontado
		// por 'ex' não está no arrayDeElementos, logo retornamos false
		if(pos == DaoJornalista.numElementos)
			return false;
		
		// Deslocando os elementos que estão à frente, uma posição para trás
		for(int i = pos; i < DaoJornalista.numElementos-1; i++)
			DaoJornalista.arrayDeElementos[i] = DaoJornalista.arrayDeElementos[i++];  
		
		// Colocando null na antiga última posição
		DaoJornalista.arrayDeElementos[numElementos - 1] = null;
		
		// Decrementando o número de elementos do Dao
		DaoJornalista.numElementos--;
		
		// retornamos true, informando que efetuamos a operação
		return true;
	}
	
	/**
	 * Retorna o Jornalista cujo código foi passado por parâmetro. Caso não tenha, 
	 * retornamos null
	 */
	public Jornalista obterJornalistaPelaMatrFunc(int matrFunc) {
		
		// Para cada Jornalista presente dentro do array de elementos
		for(int i = 0; i < DaoJornalista.numElementos; i++) {
			int matrDoJornalista = DaoJornalista.arrayDeElementos[i].getMatrFunc();
			
			if(matrDoJornalista == matrFunc)
				return DaoJornalista.arrayDeElementos[i];
		}
		
		return null;
	}

	/**
	 * Retorna todos os objetos Jornalista gerenciados pelo DAO
	 */
	public static Jornalista[] obterTodos() {
		return DaoJornalista.arrayDeElementos;
	}
	
	/**
	 * Método que determina o novo arrayDeElementos a ser gerenciado
	 * pelo DAO.
	 */
	static void recuperarTodos(Jornalista[] array) {
		
		// Determinando que o array recebido por parâmetro passa a ser
		// o arrayDeElementos do DAO
		DaoJornalista.arrayDeElementos = array;
		
		// Contando quantos objetos Jornalista estão no array recebido
		for(numElementos = 0; numElementos < array.length; numElementos++)
			if(array[numElementos] == null)
				break;
	}
}
