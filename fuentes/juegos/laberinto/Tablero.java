package juegos.laberinto;

//**********************************************************************//
/**
 * Esta clase representa la Mapa del laberinto con la posición del jugador.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// ATRIBUTOS
	public static String _izquierda	= "Izquierda";
	public static String _derecha = "Derecha";
	public static String _arriba = "Arriba";
	public static String _abajo	= "Abajo";

	public static String[] _operadores = new String[] {_arriba, _izquierda, _abajo, _derecha };
	
	public int[][] _laberinto;

	public int _coordenadaX;
	public int _coordenadaY;
	public int _objetivoX;
	public int _objetivoY;

	// **********************************************************************//
	/**
	 * Constructor por defecto. Inicializa el problema
	 */
	public Tablero() {		
		_laberinto = new int[][] { 
				{ 1 , 0 , 1 , 0 , 0 , 0 , 1 , 1 },
				{ 1 , 0 , 1 , 1 , 1 , 1 , 1 , 1 },
				{ 1 , 0 , 1 , 0 , 1 , 0 , 0 , 0 },
				{ 1 , 0 , 1 , 0 , 1 , 0 , 1 , 1 },
				{ 1 , 0 , 1 , 0 , 1 , 1 , 1 , 0 },
				{ 1 , 0 , 1 , 0 , 1 , 0 , 1 , 0 },
				{ 1 , 0 , 1 , 0 , 1 , 0 , 1 , 1 },
				{ 1 , 1 , 1 , 0 , 1 , 1 , 1 , 1 }
		};

		_coordenadaX = 0;
		_coordenadaY = 0;
		_objetivoX = 7;
		_objetivoY = 7;
	}

	// **********************************************************************//
	/**
	 * Constructor con campos.
	 * 
	 * @param matriz Mapa del laberinto.
	 * @param _coordenadaX Coordenada _coordenadaX del jugador.
	 * @param y Coordenada y del jugador.
	 * @param x_obj Coordenada _coordenadaX del objetivo.
	 * @param y_obj Coordenada y del objetivo. 
	 */
	public Tablero(int[][] matriz , int x , int y, int x_obj, int y_obj) {
		
		_laberinto = matriz;
		_coordenadaX = x;
		_coordenadaY = y;
		_objetivoX = x_obj;
		_objetivoY = y_obj;
	}

	// **********************************************************************//
	/**
	 * Devuelve la coordenada X del jugador.
	 * 
	 * @return La coordenada X del jugador.
	 */
	public int getCoordenadaX() {
		
		return _coordenadaX;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve la coordenada Y del jugador.
	 * 
	 * @return La coordenada Y del jugador.
	 */
	public int getCoordenadaY() {
		
		return _coordenadaY;
	}
	
	// **********************************************************************//
	/**
	 * Comprueba si s es un movimento posible.
	 * 
	 * @param s Movimiento a realizar.
	 */
	public boolean movimientoPosible(String s) {

		// Primero miro si el movimiento es posible (si hay suficientes para pasar)
		if( 	(s.equals(_izquierda) 	&& (_coordenadaX == 0)) ||
				(s.equals(_derecha) 	&& (_coordenadaX == 7))	||
				(s.equals(_abajo) 		&& (_coordenadaY == 7))	||
				(s.equals(_arriba) 		&& (_coordenadaY == 0)) )	return false;

		// ¿Es una situación de peligro?
		if ( 	(s.equals(_izquierda)  	&& (_laberinto[_coordenadaY][_coordenadaX-1] == 0) )||
				(s.equals(_derecha)  	&& (_laberinto[_coordenadaY][_coordenadaX+1] == 0) )||
				(s.equals(_abajo)  		&& (_laberinto[_coordenadaY+1][_coordenadaX] == 0) )||
				(s.equals(_arriba)  	&& (_laberinto[_coordenadaY-1][_coordenadaX] == 0) )) return false;
		else 
			return true;
	}

	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s El movimiento a hacer.
	 */
	public void mover(String s) {
		
		if(s.equals(_izquierda)) _coordenadaX--;
		if(s.equals(_derecha)) _coordenadaX++;
		if(s.equals(_abajo)) _coordenadaY++;
		if(s.equals(_arriba)) _coordenadaY--;
	}

	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "X:" + _coordenadaX + " Y:" + _coordenadaY + "\n";
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

		if (_coordenadaX != t.getCoordenadaX()) return false;  
		if (_coordenadaY != t.getCoordenadaY()) return false;

		return true;
	}
}
