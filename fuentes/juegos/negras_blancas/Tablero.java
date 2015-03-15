package juegos.negras_blancas;

//***************************************************************************//
/**
 * Representa un tablero de Fichas Negras y Blancas.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// ATRIBUTOS
	public static String 	_izquierda1	= "Intercambiar el hueco con la ficha adyacente de la izquierda.";
	public static String 	_izquierda2	= "Intercambiar el hueco con la ficha de la izquierda siguiente a la adyacente.";	
	public static String 	_derecha1 = "Intercambiar el hueco con la ficha adyacente de la derecha.";
	public static String 	_derecha2 = "Intercambiar el hueco con la ficha de la derecha siguiente a la adyacente.";	
	public int 			 	_maxDerecha = 7;
	public static int 	 	_minIzquierda = 0;
	
	static String[] _operadores = new String[]  { _izquierda1 , _izquierda2 , _derecha1 , _derecha2 };
	
	char[] _tablero;

	// **********************************************************************//
	/**
	 * Devuelve el tablero.
	 * 
	 * @return el tablero como array de enteros.
	 */
	public char[] getBoard() {
		
		return _tablero;
	}
	
	// **********************************************************************//
	/**
	 * Constructor por defecto.
	 */
	public Tablero() {
		
		_tablero = new char[]  { 'B', 'B', 'B', 'o', 'N', 'N', 'N' };
	}

	// **********************************************************************//
	/**
	 * Constructor por defecto.
	 * 
	 * @param estado Estado del juego.
	 */
	public Tablero(Tablero estado) {
		
		_tablero = new char[7];
		
		for(int i = 0; i < 7; i++)
			_tablero[i] = estado._tablero[i];
	}
	
	// **********************************************************************//
	/**
	 * Constructor del tablero.
	 * 
	 * @param tablero Array de enteros (7 posiciones) que representa al tablero
	 */
	public Tablero(char[] tablero) {
		
		_tablero = new char[7];
		
		for(int i = 0; i < 7; i++)
			_tablero[i] = tablero[i];
	}
	
	public boolean negrasEnLaIzquierda(){
		//número de negras encontradas
		int n = 0;
		for (int i = 0; i < _maxDerecha; i++) {
			if (_tablero[i] == 'N') n++;
			else if ((_tablero[i] == 'B') && n<3) return false;
		}	
		return true;
	}
	
	// **********************************************************************//
	/**
	 * Busca la posición absoluta de un valor dado.
	 * 
	 * @param val El valor a buscar.
	 * 
	 * @return La posición [0..7].
	 */
	private int getPositionOf(char val) {
		
		for (int i = 0; i < _maxDerecha; i++) {
			if (_tablero[i] == val) {
				return i;
			}
		}
		return -1;
	}

	// **********************************************************************//
	/**
	 * Busca la posición absoluta del hueco (el cero).
	 * 
	 * @return La posición absoluta del hueco (el cero).
	 */
	private int damePosHueco() {
		return getPositionOf('o');
	}
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de una casilla dado un valor XYLocation.
	 * 
	 * @param loc La casilla.
	 * 
	 * @return El valor almacenado.
	 */
	public char getValueAt(int loc) {
		
		return _tablero[loc];
	}
	
	// **********************************************************************//
	/**
	 * Mueve el hueco en la dirección indicada.
	 * 
	 * @param s Dirección del movimiento.
	 */
	public void mover(String s){
		
		if ( s.equals(_derecha1) ) {	int hPos = damePosHueco();
										_tablero[hPos] = _tablero[hPos+1];
										_tablero[hPos+1] = 'o'; }
		if ( s.equals(_derecha2) ) {	int hPos = damePosHueco();
										_tablero[hPos] = _tablero[hPos+2];
										_tablero[hPos+2] = 'o'; }
		if ( s.equals(_izquierda1) ) {	int hPos = damePosHueco();
										_tablero[hPos] = _tablero[hPos-1];
										_tablero[hPos-1] = 'o'; }
		if ( s.equals(_izquierda2) ) {	int hPos = damePosHueco();
										_tablero[hPos] = _tablero[hPos-2];
										_tablero[hPos-2] = 'o'; }
	}

	// **********************************************************************//
	/**
	 * Determina si el hueco se puede mover o no (porque esté en un extremo)
	 * @param s hacia dónde queremos moverlo.
	 * 
	 * @return Si se puede mover o no.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean ret = false;
		
		if (s.equals(_derecha1)) ret = damePosHueco()<_maxDerecha-1;
		if (s.equals(_derecha2)) ret = damePosHueco()<_maxDerecha-2;
		if (s.equals(_izquierda1)) ret = damePosHueco()>=_minIzquierda+1;
		if (s.equals(_izquierda2)) ret = damePosHueco()>=_minIzquierda+2;

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
		
		String retVal = _tablero[0] + "|" + _tablero[1] + "|" + _tablero[2] + "|" +  _tablero[3] + "|" + _tablero[4] + "|" + _tablero[5] +"|" + _tablero[6] + "\n";
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

		if (this == o) {
			return true;
		}
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		Tablero aBoard = (Tablero) o;

		for (int i = 0; i < _maxDerecha; i++) {
			if (this.getValueAt(i) != aBoard.getValueAt(i)) {
				return false;
			}
		}
		return true;
	}	
}