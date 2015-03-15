package juegos.hanoi3;

//***************************************************************************//
/**
 * Representa un base de torres de hanoi en espacio de estados.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// ATRIBUTOS
	public static String _moverA_1	= "Mover el disco A a la posición 1";
	public static String _moverB_1	= "Mover el disco B a la posición 1";
	public static String _moverC_1	= "Mover el disco C a la posición 1";
	
	public static String _moverA_2	= "Mover el disco A a la posición 2";
	public static String _moverB_2	= "Mover el disco B a la posición 2";
	public static String _moverC_2	= "Mover el disco C a la posición 2";
	
	public static String _moverA_3	= "Mover el disco A a la posición 3";
	public static String _moverB_3	= "Mover el disco B a la posición 3";
	public static String _moverC_3	= "Mover el disco C a la posición 3";
	
	static String[] _operadores = new String[]  { _moverA_1 , _moverA_2 , _moverA_3 , _moverB_1 , _moverB_2 , _moverB_3 , _moverC_1 , _moverC_2 , _moverC_3  };

	/*
	 * Representa el tablero de la siguiente manera:
	 * Caracter 0 : información del disco A
	 * Caracter 1 : información del disco B
	 * Caracter 2 : información del disco C
	 * Cada caracter representa sobre qué se encuentra cada uno de los 3 discos.
	 * Un disco se puede encontrar sobre otro disco o sobre una de las 3 posiciones de la base.
	 * El tablero { 'B' , 'C' ,  '1' } indica que el disco A está sobre B, el disco B está sobre C y el disco
	 * C está en la posición 1.
	 *   
	 *   A
	 *  BBB
	 * CCCCC
	 * |---|---|---|
	 *   1   2   3
	 */ 
	char[] _tablero; 
	
	// **********************************************************************//
	/**
	 * Constructor por defecto.
	 */
	public Tablero() {
		
		_tablero = new char[]  { 'B' , 'C' ,  '1' };
	}

	// **********************************************************************//
	/**
	 * Constructor del base.
	 * 
	 * @param tablero el array de char que representa las p
	 */
	public Tablero(char[] tablero) {
		
		_tablero = tablero;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve el tablero.
	 * 
	 * @return El estado como array de char.
	 */
	public char[] getTablero() {
	
		return _tablero;
	}

	// **********************************************************************//
	/**
	 * Pone el tablero con una nueva base.
	 * 
	 * @param base Número de bases que contiene el tablero.
	 */
	public void setTablero(char[] base) {

		for(int i=0;i<3;i++) 
			_tablero[i] = base[i];
	}
	
	// **********************************************************************//
	/**
	 * En qué posición se encuentra el disco A, B o C.
	 * 
	 * @param c la letra que representa el disco.
	 * 
	 * @return La posición en la que se encuentra el disco.
	 */
	public char damePosicionDe(char c) {
			
		int i = 0;
		
		switch(c){
		
			case 'A' : i = 0; break;
			case 'B' : i = 1; break;
			case 'C' : i = 2; break;
		}
		
		// Si no se encuentra sobre la base de alguna de las 3 posiciones...
		if( (_tablero[i] != '1') && (_tablero[i] != '2') && (_tablero[i] != '3'))
			return damePosicionDe(_tablero[i]); // ..se encuentra sobre algún disco
		else 
			return _tablero[i];
	}
	
	// **********************************************************************//
	/**
	 * Determina si un disco tiene otros discos sobre él.
	 * 
	 * @param c la letra que representa el disco.
	 * 
	 * @return Verdadero si tiene otros discos sobre él y falso en caso contrario. 
	 */
	public boolean tieneAlgunDiscoEncima(char c) {
		
		switch(c) {
		
			case 'A' : 	return false; 
			
			//solo el disco A puede estar sobre B
			case 'B' : 	if( _tablero[0] != 'B' ) 
							return false; 
						break;
			//solo los discos A y B pueden estar C						
			case 'C' : 	if( ( _tablero[0] != 'C' ) && ( _tablero[1] != 'C' ) ) 
							return false; 
						break; 
		}

		return true;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve el disco que está en la cima de una base.
	 * 
	 * @param b el número que representa la base.
	 * 
	 * @return El disco que ocupa la cima de la base.
	 */
	public char dameCimaDeBase(char b) {
	
		if( b == damePosicionDe('A') )
			return 'A';		
		else if( b == damePosicionDe('B') )
			return 'B';		
		else if( b == damePosicionDe('C') ) 
			return 'C';		
		else 
			return b;			
		
	}

	// **********************************************************************//
	/**
	 * Mueve un disco.
	 * 
	 * @param mov Movimiento a realizar.
	 */
	public void mover(String mov) {
		
		if ( mov.equals(_moverA_1) ) _tablero[0] = dameCimaDeBase('1');
		if ( mov.equals(_moverA_2) ) _tablero[0] = dameCimaDeBase('2');
		if ( mov.equals(_moverA_3) ) _tablero[0] = dameCimaDeBase('3');
		
		if ( mov.equals(_moverB_1) ) _tablero[1] = dameCimaDeBase('1');
		if ( mov.equals(_moverB_2) ) _tablero[1] = dameCimaDeBase('2');
		if ( mov.equals(_moverB_3) ) _tablero[1] = dameCimaDeBase('3');
		
		if ( mov.equals(_moverC_1) ) _tablero[2] = '1';
		if ( mov.equals(_moverC_2) ) _tablero[2] = '2';
		if ( mov.equals(_moverC_3) ) _tablero[2] = '3';
	}
	
	// **********************************************************************//
	/**
	 * Determina si el movimiento es posible.
	 * 
	 * @param s hacia dónde y qué queremos mover.
	 * 
	 * @return si se puede mover o no.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean ret = true;
		
		//si quieres mover un disco a la posición 1 y ese disco ya está en esa posición...
		if ( s.equals(_moverA_1) && (damePosicionDe('A') == '1') ) ret = false;
		if ( s.equals(_moverB_1) && (damePosicionDe('B') == '1') ) ret = false;
		if ( s.equals(_moverC_1) && (damePosicionDe('C') == '1') ) ret = false;
		//si quieres mover un disco a la posición 2 y ese disco ya está en esa posición...
		if ( s.equals(_moverA_2) && (damePosicionDe('A')  == '2') ) ret = false;
		if ( s.equals(_moverB_2) && (damePosicionDe('B')  == '2') ) ret = false;
		if ( s.equals(_moverC_2) && (damePosicionDe('C')  == '2') ) ret = false;
		//si quieres mover un disco a la posición 3 y ese disco ya está en esa posición...
		if ( s.equals(_moverA_3) && (damePosicionDe('A')  == '3') ) ret = false;
		if ( s.equals(_moverB_3) && (damePosicionDe('B')  == '3') ) ret = false;
		if ( s.equals(_moverC_3) && (damePosicionDe('C')  == '3') ) ret = false;
		
		// si quieres mover un disco que tiene otros discos encima...
		if ( (s.equals(_moverA_1) || s.equals(_moverA_2) ||  s.equals(_moverA_3)) && tieneAlgunDiscoEncima('A') ) 	ret = false;
		if ( (s.equals(_moverB_1) || s.equals(_moverB_2) ||  s.equals(_moverB_3)) && tieneAlgunDiscoEncima('B') ) 	ret = false;
		if ( (s.equals(_moverC_1) || s.equals(_moverC_2) ||  s.equals(_moverC_3)) && tieneAlgunDiscoEncima('C') ) 	ret = false;
		
		//si quieres mover un disco a la posición 1 ya hay un disco más pequeño...
		if ( s.equals(_moverB_1) && (damePosicionDe('A') == '1') ) ret = false;
		if ( s.equals(_moverC_1) && (damePosicionDe('B') == '1') ) ret = false;
		if ( s.equals(_moverC_1) && (damePosicionDe('A') == '1') ) ret = false;
		//si quieres mover un disco a la posición 2 ya hay un disco más pequeño...		
		if ( s.equals(_moverB_2) && (damePosicionDe('A') == '2') ) ret = false;
		if ( s.equals(_moverC_2) && (damePosicionDe('B') == '2') ) ret = false;
		if ( s.equals(_moverC_2) && (damePosicionDe('A') == '2') ) ret = false;
		//si quieres mover un disco a la posición 3 ya hay un disco más pequeño...		
		if ( s.equals(_moverB_3) && (damePosicionDe('A') == '3') ) ret = false;
		if ( s.equals(_moverC_3) && (damePosicionDe('B') == '3') ) ret = false;
		if ( s.equals(_moverC_3) && (damePosicionDe('A') == '3') ) ret = false;

		return ret;
	}
	
	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "A:" + damePosicionDe('A') + " B:" + damePosicionDe('B') + " C:" + damePosicionDe('C') + "\n";
		return retVal;		
	}
	
	// **********************************************************************//
	/**
	 * Compara si dos objetos son iguales.
	 * 
	 * @param o Objeto a comparar.
	 * 
	 * @return Verdadero si son iguales y falso en caso contrario.
	 */
	@Override
	public boolean equals(Object o) {

		if (this == o) 
			return true;
		
		if ((o == null) || (this.getClass() != o.getClass())) 
			return false;
		
		Tablero t = (Tablero) o;

		for (int i = 0; i < 3; i++)
			if (_tablero[i] != t._tablero[i]) 
				return false;
		
		return true;
	}	
}