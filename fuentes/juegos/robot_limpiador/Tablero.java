package juegos.robot_limpiador;

//***************************************************************************//
/**
 * Representa el micro-escenario de un robot que tiene limpiar habitaciones
 * en el paradigma de Espacio de estados
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {
	
	// ATRIBUTOS
	public static String _limpiar	= "Limpia la habitacion";
	public static String _moverNorte = "El robot se mueve a la habitacion del norte.";
	public static String _moverEste	= "El robot se mueve a la habitacion del este.";
	public static String _moverSur	= "El robot se mueve a la habitacion del sur.";
	public static String _moverOeste  = "El robot se mueve a la habitacion del oeste.";
	
	public static String[] _operadores = { _moverNorte, _moverEste , _moverSur , _moverOeste , _limpiar };
	
	boolean[][] _tablero;
	int _coordenadaX;
	int _coordenadaY;
	
	// **********************************************************************//
	/**
	 * Constructor por defecto.
	 */
	public Tablero() {
		
		_tablero = new boolean[][]  {	
				{ false , false , false },
				{ false , false , false },
				{ false , false , false } 
				};
		_coordenadaX = 0;
		_coordenadaY = 0;
	}
	
	// **********************************************************************//
	/**
	 * Constructor con tab de boolean
	 */
	public Tablero(boolean t[][]) {
		_tablero = t;
	}
	
	// **********************************************************************//
	/**
	 * Constructor por copia
	 */
	public Tablero(Tablero t) {
		
		_tablero = new boolean[3][3];
		
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++)
				_tablero[i][j] = t._tablero[i][j];
		}
		
		_coordenadaX = t._coordenadaX;
		_coordenadaY = t._coordenadaY;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve el tablero.
	 * 
	 * @return el tablero como array de booleanos
	 */
	public boolean[][] getBoard() {
		
		return _tablero;
	}
	
	/**
	 * Set Contexto de otro Contexto
	 * 
	 * @return el estado
	 */
	public void setBoard(boolean[][] board , int x, int y) {
		
		_tablero = board;
		_coordenadaX = x;
		_coordenadaY = y;
	}
			
	// **********************************************************************//
	/**
	 * Mueve el Robot incluye Clean
	 * 
	 * @param s Movimento tiene que hacer
	 */
	public void mover(String s){

		if( s.equals(_limpiar) ) _tablero[_coordenadaX][_coordenadaY] = true;		
		if( s.equals(_moverNorte) ) _coordenadaY--;		
		if( s.equals(_moverEste) ) _coordenadaX++;		
		if( s.equals(_moverSur) ) _coordenadaY++;		
		if( s.equals(_moverOeste) ) _coordenadaX--;
	}	
	
	// **********************************************************************//
	/**
	 * Determina si el robot puede mover o limpiar
	 * 
	 * @param s hacia donde queremos moverlo.
	 * @return si se puede mover o no.
	 */
	public boolean movimientoPosible(String s) {
				
		if (s.equals(_limpiar)) return !_tablero[_coordenadaX][_coordenadaY];
		else if (s.equals(_moverNorte)) return _coordenadaY!=0;
		else if (s.equals(_moverEste)) return _coordenadaX!=2;
		else if (s.equals(_moverSur)) return _coordenadaY!=2;
		else if (s.equals(_moverOeste)) return _coordenadaX!=0;
		return true;
	}
	
	// **********************************************************************//
	/**
	 * Convierte la clase Tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero
	 */
	@Override
	public String toString() {
		String retVal = _tablero[0][0] + " " + _tablero[0][1] + " " + _tablero[0][2] + "\n"
					  + _tablero[1][0] + " " + _tablero[1][1] + " " + _tablero[1][2] + "\n"
					  + _tablero[2][0] + " " + _tablero[2][1] + " " + _tablero[2][2] + "\n" +
					   "X:" + _coordenadaX + " Y:" + _coordenadaY + "\n";
		return retVal;
	}	
	
	// **********************************************************************//
	/**
	 * Comprueba si dos objetos son iguales.
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
		Tablero t = (Tablero) o;
		for (int x = 0; x < 3; x++){
			for (int y = 0; y < 3; y++) {
				if (this._tablero[x][y] != t._tablero[x][y]) {
					return false;
				}
			}
		}		
		return true;
	}

	public boolean habitacionLimpia(int x, int y) {
		return _tablero[x][y];
	}
}
