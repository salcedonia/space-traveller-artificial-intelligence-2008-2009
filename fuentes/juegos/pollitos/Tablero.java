
package juegos.pollitos;

//***************************************************************************//
/**
 * Clase que representa la huevera de 5x2, contiene el estado de 
 * los 10 huevos en cada momento.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {
	
	// ATRIBUTOS
	public static String SEL_00 = "Selecciona el huevo (0,0)";
	public static String SEL_01 = "Selecciona el huevo (0,1)";
	public static String SEL_02 = "Selecciona el huevo (0,2)";
	public static String SEL_03 = "Selecciona el huevo (0,3)";
	public static String SEL_04 = "Selecciona el huevo (0,4)";
	public static String SEL_10 = "Selecciona el huevo (1,0)";
	public static String SEL_11 = "Selecciona el huevo (1,1)";
	public static String SEL_12 = "Selecciona el huevo (1,2)";
	public static String SEL_13 = "Selecciona el huevo (1,3)";
	public static String SEL_14 = "Selecciona el huevo (1,4)";
	
	/**
	 * Array que contiene todos los _operadores aplicables
	 */	
	public static String[] _operadores = new String[] { SEL_00 , SEL_01 , SEL_02 , SEL_03 , SEL_04, 
													   SEL_10 , SEL_11 , SEL_12 , SEL_13 , SEL_14};
	
	/**
	 * Matriz que guarda los estados de cada huevo
	 * Los estados posibles son los siguientes:
	 * 	0 - El huevo es blanco
	 * 	1 - El huevo es azul
	 * 	2 - El huevo es rojo
	 *  3 - Es pollito 
	 */
	private int[][] _tablero;
	
	// **********************************************************************//
	/**
	 * Constructor por defecto, inicializa el problema:
	 * Todos los huevos son blancos.	  
	 */
	public Tablero() {
		
		_tablero = new int[][]{
				{ 0 , 0 , 0 , 0 , 0 },
				{ 0 , 0 , 0 , 0 , 0 }
		};
	}
	
	// **********************************************************************//
	/**
	 * Constructor que inicializa los estados de los huevos
	 * a un valor en concreto.
	 * 
	 * @param val Número de huevos.
	 */
	public Tablero(int val) {
		
		_tablero = new int[][]{
				{ val , val , val , val , val },
				{ val , val , val , val , val }
		};
	}
	
	// **********************************************************************//
	/**
	 * Constructor que crea una _huevera a partir de los estados
	 * de los huevos de otra.
	 * 
	 * @param h Tablero a copiar.
	 */
	public Tablero(int[][] h){
		
		_tablero = new int[2][5];
		
		for(int i = 0; i <= 1; i++)
			for(int j = 0; j <= 4; j++)
				_tablero[i][j] = h[i][j];
	}
	
	// **********************************************************************//
	/**
	 * Accesor para el atributo _huevera.
	 * 
	 * @return Devuelve el estado de los huevos que la componen.
	 */
	public int[][] getTablero(){
		
		return _tablero;
	}
	
	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s el movimiento a hacer.
	 */
	public void mover(String s) {
		if     (s.equals(SEL_00)) cambiaEstado(0,0);
		else if(s.equals(SEL_01)) cambiaEstado(0,1);	
		else if(s.equals(SEL_02)) cambiaEstado(0,2);
		else if(s.equals(SEL_03)) cambiaEstado(0,3);
		else if(s.equals(SEL_04)) cambiaEstado(0,4);
		else if(s.equals(SEL_10)) cambiaEstado(1,0);
		else if(s.equals(SEL_11)) cambiaEstado(1,1);
		else if(s.equals(SEL_12)) cambiaEstado(1,2);
		else if(s.equals(SEL_13)) cambiaEstado(1,3);
		else if(s.equals(SEL_14)) cambiaEstado(1,4);
				
	}
	
	// **********************************************************************//
	/**
	 * Función que cambia el estado del huevo que se le pasa
	 * como parámetro, además de a sus huevos vecinos.
	 * 
	 * @param x coordenada x de la huevera
	 * @param y coordenada y de la huevera
	 */
	public void cambiaEstado(int x, int y){
		
		_tablero[x][y] = (_tablero[x][y] + 1) % 4;
		
		if(y > 0)  _tablero[x][y-1] = (_tablero[x][y-1] + 1) % 4;
		if(y < 4)  _tablero[x][y+1] = (_tablero[x][y+1] + 1) % 4;
		if(x == 0) _tablero[x+1][y] = (_tablero[x+1][y] + 1) % 4;
		if(x == 1) _tablero[x-1][y] = (_tablero[x-1][y] + 1) % 4;
	}
	
	public String toString() {
		
		String retVal = _tablero[0][0] + "|" + _tablero[0][1] + "|" + _tablero[0][2] + "|" +  _tablero[0][3] + "|" + _tablero[0][4] + "\n";
		retVal += _tablero[1][0] + "|" + _tablero[1][1] + "|" + _tablero[1][2] + "|" +  _tablero[1][3] + "|" + _tablero[1][4] + "\n";
		return retVal;
	}	
	
	// **********************************************************************//
	/**
	 * Comprueba si dos objetos son iguales.
	 */
	@Override
	public boolean equals(Object o) {
		
		if (this == o) 
			return true;
		
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		
		Tablero tablero = (Tablero) o;
		
		for(int i = 0; i <= 1; i++)
			for(int j = 0; j <= 4; j++)
				if(_tablero[i][j] != tablero._tablero[i][j])
					return false;
				
		return true;
	}
	
	
}
