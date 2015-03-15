package juegos.misioneros;

//***************************************************************************//
/**
 * Esta clase representa el río, con los misioneros, los caníbales y el bote.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {
	
	// ATRIBUTOS
	public static String M  = "Pasar un  misionero.";
	public static String MM = "Pasar dos misioneros.";
	public static String C  = "Pasar un  caníbal.";
	public static String CC = "Pasar dos caníbales.";
	public static String MC = "Pasar un misionero y un caníbal.";
	
	public static String[] _operadores = {M, MM, C, CC, MC};
	
	private int _numCanibalesEnIzq;	// Número de caníbales en la orilla izquierda
	private int _numMisionerosEnIzq; // Número de misioneros en la orilla izquierda
	private boolean _boteEnIzq;		// Verdadero si el bote está en la orilla izquierda
	
	// **********************************************************************//
	/**
	 * Constructor por defecto. Inicializa el problema (todos a la izquierda).
	 */
	public Tablero() {
		
		_numCanibalesEnIzq = 3;
		_numMisionerosEnIzq = 3;
		_boteEnIzq = true;
	}

	// **********************************************************************//
	/**
	 * Constructor con campos.
	 * 
	 * @param num_canibales Número de caníbales en la orilla izquierda.
	 * @param num_misioneros Número de misioneros en la orilla izquierda.
	 * @param barco Verdaderod si el bote está en la orilla izquierda.
	 */
	public Tablero(int num_canibales, int num_misioneros, boolean barco) {
		
		_numCanibalesEnIzq = num_canibales;
		_numMisionerosEnIzq = num_misioneros;
		_boteEnIzq = barco;
	}

	// **********************************************************************//
	/**
	 * Devuelve el número de caníbales que hay en la orilla izquierda.
	 * 
	 * @return El número de caníbales que hay en la orilla izquierda.
	 */
	public int getNumCanibalesEnIzq() {
		
		return _numCanibalesEnIzq;
	}

	// **********************************************************************//
	/**
	 * Devuelve el número de misioneros que hay en la orilla izquierda.
	 * 
	 * @return El número de misioneros que hay en la orilla izquierda.
	 */
	public int getNumMisionerosEnIzq() {
		
		return _numMisionerosEnIzq;
	}

	// **********************************************************************//
	/**
	 * Comprueba si el bote está en la orilla izquierda.
	 * 
	 * @return Verdadero si el bote está en la orilla izquierda y falso en caso
	 * contrario.
	 */
	public boolean getBoteEnIzq() {
		
		return _boteEnIzq;
	}

	// **********************************************************************//
	/**
	 * Comprueba si se puede realizar un movimiento.
	 * 
	 * @param s Movimiento a realizar.
	 * 
	 * @return Verdadero si el movimiento se puede realizar y falso en caso
	 * contrario.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean b = false;

		if (_boteEnIzq) {
			if 		(s.equals(M)  && _numMisionerosEnIzq >= 1) b = true;
			else if (s.equals(C)  && _numCanibalesEnIzq  >= 1) b = true;
			else if (s.equals(MM) && _numMisionerosEnIzq >= 2) b = true;
			else if (s.equals(CC) && _numCanibalesEnIzq  >= 2) b = true;
			else if (s.equals(MC) && _numMisionerosEnIzq >= 1 && _numCanibalesEnIzq >= 1) b = true;
		}
		else {
			if      (s.equals(M)  && 3-_numMisionerosEnIzq >= 1) b = true;
			else if (s.equals(MM) && 3-_numMisionerosEnIzq >= 2) b = true;
			else if (s.equals(C)  && 3-_numCanibalesEnIzq  >= 1) b = true;
			else if (s.equals(CC) && 3-_numCanibalesEnIzq  >= 2) b = true;
			else if (s.equals(MC) && 3-_numMisionerosEnIzq >= 1 && 3-_numCanibalesEnIzq >= 1) b = true;
		}
		if (b)
			// ¿Es una situación de peligro?
			if ((_numMisionerosEnIzq < _numCanibalesEnIzq && _numMisionerosEnIzq != 0) ||
				(_numMisionerosEnIzq > _numCanibalesEnIzq && _numMisionerosEnIzq != 3))
					b = false;
		return b;
	}
	
	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s el movimiento a hacer.
	 */
	public void mover(String s) {
		
		if (_boteEnIzq) {
			if      (s.equals(M))    _numMisionerosEnIzq -=1;
			else if (s.equals(MM))   _numMisionerosEnIzq -=2;
			else if (s.equals(C))    _numCanibalesEnIzq  -=1;
			else if (s.equals(CC))   _numCanibalesEnIzq  -=2;
			else if (s.equals(MC)) { _numMisionerosEnIzq -=1; _numCanibalesEnIzq -=1; } 
			_boteEnIzq = false;
		}
		else { 
			if      (s.equals(M))    _numMisionerosEnIzq +=1;
			else if (s.equals(MM))   _numMisionerosEnIzq +=2;
			else if (s.equals(C))    _numCanibalesEnIzq  +=1;
			else if (s.equals(CC))   _numCanibalesEnIzq  +=2;
			else if (s.equals(MC)) { _numMisionerosEnIzq +=1; _numCanibalesEnIzq +=1; } 
			_boteEnIzq = true;
		}
	}
	
	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "Misioneros:" + String.valueOf(_numMisionerosEnIzq) + 
						" Caníbales:" + String.valueOf(_numCanibalesEnIzq) +
						" Bote:" + String.valueOf(_boteEnIzq) + "\n";
		return retVal;		
	}
	
	// **********************************************************************//
	/**
	 * Comprueba si dos objetos son iguales o no.
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

		if (_numCanibalesEnIzq != t.getNumCanibalesEnIzq()) return false;  
		if (_numMisionerosEnIzq != t.getNumMisionerosEnIzq()) return false;
		if (_boteEnIzq != t.getBoteEnIzq()) return false;

		return true;
	}
}
