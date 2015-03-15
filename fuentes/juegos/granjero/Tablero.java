package juegos.granjero;

//***************************************************************************//
/**
 * Esta clase representa el río, con el granjero, el lobo, la cabra y la col.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {
	
	// CONSTANTES
	public final static String _granjero	= "El granjero cruza el río.";
	public final static String _lobo		= "El granjero y el lobo cruzan el río.";
	public final static String _cabra	= "El granjero y la cabra cruzan el río.";
	public final static String _col		= "El granjero y la col cruzan el río.";
		
	public final static String[] _operadores = {_granjero, _lobo, _cabra, _col};
	
	// ATRIBUTOS
	private boolean _granjeroEnIzq;	// True si el granjero está en la orilla izquierda
	private boolean _loboEnIzq;		// True si el lobo está en la orilla izquierda
	private boolean _cabraEnIzq;	// True si la cabra está en la orilla izquierda
	private boolean _colEnIzq;		// True si la col está en la orilla izquierda
	
	// **********************************************************************//
	/**
	 * Constructor por defecto. Inicializa el problema (todos a la izquierda).
	 */
	public Tablero() {
		
		_granjeroEnIzq = true;
		_loboEnIzq     = true;
		_cabraEnIzq    = true;
		_colEnIzq      = true;
	}
	
	// **********************************************************************//
	/**
	 * Constructor por campos.
	 * 
	 * @param granjeroEnIzq	true si el granjero está en la orilla izquierda.
	 * @param loboEnIzq		true si el lobo está en la orilla izquierda.
	 * @param cabraEnIzq		true si la cabra está en la orilla izquierda.
	 * @param colEnIzq		true si la col está en la orilla izquierda.
	 */
	public Tablero(boolean granjeroEnIzq, boolean loboEnIzq, boolean cabraEnIzq, boolean colEnIzq) {
		
		_granjeroEnIzq = granjeroEnIzq;
		_loboEnIzq     = loboEnIzq;
		_cabraEnIzq    = cabraEnIzq;
		_colEnIzq      = colEnIzq;
	}

	// **********************************************************************//
	/**
	 * Devuelve si el granjero está en la orilla izquierda o no.
	 * 
	 * @return Verdadero si el granjero está en la orilla izquierda y falso 
	 * en caso contrario.
	 */
	public boolean isGranjeroEnIzq() {
		
		return _granjeroEnIzq;
	}

	// **********************************************************************//
	/**
	 * Devuelve cierto si el lobo está en la orilla izquierda y falso en caso
	 * contrario.
	 * 
	 * @return Cierto si el lobo está en la orilla izquierda y falso en caso
	 * contrario.	 
	 */
	public boolean isLoboEnIzq() {
		
		return _loboEnIzq;
	}

	// **********************************************************************//
	/**
	 * Devuelve cierto si la cabra está en la orilla izquierda y falso en caso
	 * contrario.
	 * 
	 * @return Cierto si la cabra está en la orilla izquierda y falso en caso
	 * contrario.	 
	 */
	public boolean isCabraEnIzq() {
		
		return _cabraEnIzq;
	}

	// **********************************************************************//
	/**
	 * Devuelve verdadero si la col está en la orilla izquierda y falso en 
	 * caso contrario.
	 * 
	 * @return Verdadero si la col está en la orilla izquierda y falso en 
	 * caso contrario.
	 */
	public boolean isColEnIzq() {
		
		return _colEnIzq;
	}

	// **********************************************************************//
	/**
	 * Comprueba si se puede realizar el movimiento.
	 * 
	 * @param s Movimiento a realizar.
	 * 
	 * @return Verdadero si se puede mover y falso en caso contrario.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean b = false;

		if (_granjeroEnIzq) {			
			if 		(s.equals(_granjero) && !(_loboEnIzq && _cabraEnIzq) && !(_cabraEnIzq && _colEnIzq)) 	b = true;
			else if (s.equals(_lobo)     && _loboEnIzq && !(_cabraEnIzq && _colEnIzq)) 						b = true;
			else if (s.equals(_cabra)    && _cabraEnIzq)                            						b = true;
			else if (s.equals(_col)      && _colEnIzq  && !(_loboEnIzq && _cabraEnIzq)) 					b = true;
		}
		else {			
			if 		(s.equals(_granjero) && !(!_loboEnIzq && !_cabraEnIzq) && !(!_cabraEnIzq && !_colEnIzq)) 	b = true;
			else if	(s.equals(_lobo)  && !_loboEnIzq && !(!_cabraEnIzq && !_colEnIzq))  						b = true;
			else if (s.equals(_cabra) && !_cabraEnIzq)                              							b = true;
			else if (s.equals(_col)   && !_colEnIzq  && !(!_loboEnIzq && !_cabraEnIzq))							b = true;
		}
		return b;
	}
	
	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s el movimiento a hacer
	 */
	public void mover(String s) {
		
		if (_granjeroEnIzq) {			
			if      (s.equals(_granjero)) { _granjeroEnIzq = false;                    	 }
			else if (s.equals(_lobo))     { _granjeroEnIzq = false; _loboEnIzq  = false; }
			else if (s.equals(_cabra))    { _granjeroEnIzq = false; _cabraEnIzq = false; }
			else if (s.equals(_col))      { _granjeroEnIzq = false; _colEnIzq   = false; }
		}
		else { 
			if      (s.equals(_granjero)) { _granjeroEnIzq = true;                   	}
			else if (s.equals(_lobo))     { _granjeroEnIzq = true;	_loboEnIzq  = true; }
			else if (s.equals(_cabra))    { _granjeroEnIzq = true;	_cabraEnIzq = true; }
			else if (s.equals(_col))	  { _granjeroEnIzq = true;	_colEnIzq   = true; }
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
		
		String retVal = "Granjero:" + String.valueOf(_granjeroEnIzq) + 
						" Lobo:" + String.valueOf(_loboEnIzq) +
						" Cabra:" + String.valueOf(_cabraEnIzq) +
						" Col:" + String.valueOf(_colEnIzq) + "\n";
		return retVal;		
	}
	
	// **********************************************************************//
	/**
	 * Compara si dos objetos son iguales o no.
	 * 
	 * @param o Objeto a comparar.
	 * 
	 * @return Verdadero si los dos objetos son iguales.
	 */
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		
		Tablero t = (Tablero) o;

		if (_granjeroEnIzq	!= t.isGranjeroEnIzq())	return false;  
		if (_loboEnIzq		!= t.isLoboEnIzq())		return false;  
		if (_cabraEnIzq		!= t.isCabraEnIzq())	return false;  
		if (_colEnIzq		!= t.isColEnIzq())		return false;  

		return true;
	}
}