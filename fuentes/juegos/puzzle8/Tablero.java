package juegos.puzzle8;

import java.util.ArrayList;
import java.util.List;

import aima.basic.XYLocation;

//***************************************************************************//
/**
 * Representa un tablero de puzzle-8.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// ATRIBUTOS
	public static String LEFT	= "Izquierda";
	public static String RIGHT	= "Derecha";
	public static String UP		= "Arriba";
	public static String DOWN	= "Abajo";

	/**
	 * Array que contiene todos los _operadores aplicables
	 */	
	public static String[] _operadores = new String[] { LEFT , RIGHT , UP , DOWN};	
	
	int[] _tablero;

	// **********************************************************************//
	/**
	 * Devuelve el tablero.
	 * 
	 * @return el tablero como array de enteros
	 */
	public int[] getBoard() {
		
		return _tablero;
	}

	// **********************************************************************//
	/**
	 * Constructor por defecto.
	 * 
	 */
	public Tablero() {
		
		_tablero = new int[]  { 1, 2, 3, 4, 5, 6, 7, 8, 0};
	}

	// **********************************************************************//
	/**
	 * Constructor del tablero.
	 * 
	 * @param tablero el array de enteros (9 posiciones) que representa al tablero.
	 * 
	 * @see #EightPuzzleBoard()
	 */
	public Tablero(int[] tablero) {
		
		_tablero = tablero;
	}
	
	// **********************************************************************//
	/**
	 * Constructor del tablero.
	 * 
	 * @param tablero el array de enteros (9 posiciones) que representa al tablero.
	 * 
	 * @see #EightPuzzleBoard()
	 */
	public Tablero(Tablero t) {
		
		_tablero = t._tablero;
	}	
	


	// **********************************************************************//
	/**
	 * Traduce una coordenada asboluta [0..9] a XY [0..3][0..3]
	 * 
	 * @param _coordenadaX la coordenada absoluta
	 * 
	 * @return un par de coordenadas XY
	 * 
	 * @see #absoluteCoordinatesFromXYCoordinates(int, int)
	 */
	private int[] xycoordinatesFromAbsoluteCoordinate(int x) {
		
		int[] retVal = null;
		
		switch (x) {
			case 0:
				retVal = new int[] { 0, 0 };
				break;
			case 1:
				retVal = new int[] { 0, 1 };
				break;
			case 2:
				retVal = new int[] { 0, 2 };
				break;
			case 3:
				retVal = new int[] { 1, 0 };
				break;
			case 4:
				retVal = new int[] { 1, 1 };
				break;
			case 5:
				retVal = new int[] { 1, 2 };
				break;
			case 6:
				retVal = new int[] { 2, 0 };
				break;
			case 7:
				retVal = new int[] { 2, 1 };
				break;
			case 8:
				retVal = new int[] { 2, 2 };
				break;	
		}
		
		return retVal;
	}

	// **********************************************************************//
	/**
	 * Traduce unas coordenadas[0..3][0..3] XY a una coordenada absoluta [0..9]
	 * 
	 * @param _coordenadaX coordenada horizontal
	 * @param y coordenada vertical
	 * 
	 * @return coordenada absoluta
	 * 
	 * @see #xycoordinatesFromAbsoluteCoordinate(int)
	 */
	private int absoluteCoordinatesFromXYCoordinates(int x, int y) {
		return x * 3 + y;
	}

	// **********************************************************************//
	/**
	 * Devuelve el valor de una casilla
	 * 
	 * @param _coordenadaX coordenada horizontal
	 * @param y coordenada vertical
	 * 
	 * @return el valor almacenado
	 */
	public int getValueAt(int x, int y) {
		// refactor this use either case or a div/mod soln
		return _tablero[absoluteCoordinatesFromXYCoordinates(x, y)];
	}

	// **********************************************************************//
	/**
	 * Busca la posición absoluta de un valor dado
	 * 
	 * @param val el valor a buscar
	 * 
	 * @return la posición [0..9] o -1 si no lo ha encontrado
	 */
	private int getPositionOf(int val) {
		int retVal = -1;
		for (int i = 0; i < 9; i++) {
			if (_tablero[i] == val) {
				retVal = i;
			}
		}
		return retVal;
	}

	// **********************************************************************//
	/**
	 * Busca la posición absoluta del hueco (el cero)
	 * 
	 * @return La posición absoluta del hueco.
	 */
	private int getGapPosition() {
		
		return getPositionOf(0);
	}

	// **********************************************************************//
	/**
	 * Busca la posición XY de un valor dado
	 * 
	 * @param val el valor a buscar
	 * 
	 * @return la posición [0..3][0..3] del valor buscado
	 */
	public XYLocation getLocationOf(int val) {
		
		int abspos = getPositionOf(val);
		int xpos = xycoordinatesFromAbsoluteCoordinate(abspos)[0];
		int ypos = xycoordinatesFromAbsoluteCoordinate(abspos)[1];
		
		return new XYLocation(xpos, ypos);
	}

	// **********************************************************************//
	/**
	 * Asigna un valor a una casilla
	 * 
	 * @param xPos posición horizontal
	 * @param yPos posición vertical
	 * @param val valor a asignar
	 */
	private void setValue(int xPos, int yPos, int val) {
		
		int abscoord = absoluteCoordinatesFromXYCoordinates(xPos, yPos);
		_tablero[abscoord] = val;
	}

	// **********************************************************************//
	/**
	 * Devuelve el valor de una casilla dado un valor XYLocation
	 * 
	 * @param loc la casilla
	 * 
	 * @return el valor almacenado
	 */
	public int getValueAt(XYLocation loc) {
		return getValueAt(loc.getXCoOrdinate(), loc.getYCoOrdinate());
	}

	// **********************************************************************//
	/**
	 * Mueve el hueco a la derecha si es posible
	 */
	public void moveGapRight() {

		int gapPosition = getGapPosition();
		int xpos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[0];
		int ypos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[1];
		
		if (!(ypos == 2)) {
			int valueOnRight = getValueAt(xpos, ypos + 1);
			setValue(xpos, ypos, valueOnRight);
			setValue(xpos, ypos + 1, 0);
		}
	}

	// **********************************************************************//
	/**
	 * Mueve el hueco a la izquierda si es posible
	 */
	public void moveGapLeft() {
		
		int gapPosition = getGapPosition();
		int xpos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[0];
		int ypos = xycoordinatesFromAbsoluteCoordinate(getGapPosition())[1];
		
		if (!(ypos == 0)) {
			int valueOnLeft = getValueAt(xpos, ypos - 1);
			setValue(xpos, ypos, valueOnLeft);
			setValue(xpos, ypos - 1, 0);
		}
	}

	// **********************************************************************//
	/**
	 * Mueve el hueco hacia abajo si es posible
	 */
	public void moveGapDown() {
		
		int gapPosition = getGapPosition();
		int xpos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[0];
		int ypos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[1];
		
		if (!(xpos == 2)) {
			int valueOnBottom = getValueAt(xpos + 1, ypos);
			setValue(xpos, ypos, valueOnBottom);
			setValue(xpos + 1, ypos, 0);
		}
	}

	// **********************************************************************//
	/**
	 * Mueve el hueco hacia arriba si es posible
	 */
	public void moveGapUp() {
		
		int gapPosition = getGapPosition();
		int xpos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[0];
		int ypos = xycoordinatesFromAbsoluteCoordinate(gapPosition)[1];
		
		if (!(xpos == 0)) {
			int valueOnTop = getValueAt(xpos - 1, ypos);
			setValue(xpos, ypos, valueOnTop);
			setValue(xpos - 1, ypos, 0);
		}
	}
	
	// *************************************************************************//
	/**
	 * Hace el movimiento indicado por el string s. Se da por hecho que se ha
	 * comprobado que se puede hacer.
	 * 
	 * @param s 
	 * 			Movimiento a realizar.
	 */
	public void mover(String s) {
		
		if (s.equals(LEFT)) 	  moveGapLeft();
		else if (s.equals(RIGHT)) moveGapRight();
		else if (s.equals(UP)) 	  moveGapUp();
		else if (s.equals(DOWN))  moveGapDown();
	}	

	// **********************************************************************//
	/**
	 * Devuelve la lista de localizaciones.
	 * 
	 * @return La lista de localizaciones.
	 */
	public List<XYLocation> getPositions() {
		
		ArrayList<XYLocation> retVal = new ArrayList<XYLocation>();
		
		for (int i = 0; i < 9; i++) {
			int[] res = xycoordinatesFromAbsoluteCoordinate(getPositionOf(i));
			XYLocation loc = new XYLocation(res[0], res[1]);
			retVal.add(loc);
		}
		return retVal;
	}

	// **********************************************************************//
	/**
	 * Establece el tablero con una lista de localizaciones.
	 * 
	 * @param locs Localizaciones.
	 */
	public void setBoard(List<XYLocation> locs) {
		
		int count = 0;
		
		for (int i = 0; i < locs.size(); i++) {
			XYLocation loc = locs.get(i);
			this.setValue(loc.getXCoOrdinate(), loc.getYCoOrdinate(), count);
			count = count + 1;
		}
	}

	// **********************************************************************//
	/**
	 * Determina si el hueco se puede mover o no (porque esté en un extremo)
	 * 
	 * @param s hacia dónde queremos moverlo
	 * 
	 * @return si se puede mover o no
	 */
	public boolean movimientoPosible(String s) {
		
		boolean retVal = true;
		
		int absPos = getPositionOf(0);
		
		if (s.equals(LEFT))  		retVal = !((absPos == 0) || (absPos == 3) || (absPos == 6));		
		else if (s.equals(RIGHT)) 	retVal = !((absPos == 2) || (absPos == 5) || (absPos == 8));		
		else if (s.equals(UP)) 		retVal = !((absPos == 0) || (absPos == 1) || (absPos == 2));
		else if (s.equals(DOWN)) 	retVal = !((absPos == 6) || (absPos == 7) || (absPos == 8));		

		return retVal;
	}
	
	// **********************************************************************//
	/**
	 * Convierte la clase Tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero
	 */
	@Override
	public String toString() {
		String retVal = _tablero[0] + " " + _tablero[1] + " " + _tablero[2] + "\n"
					  + _tablero[3] + " " + _tablero[4] + " " + _tablero[5] + "\n"
					  + _tablero[6] + " " + _tablero[7] + " " + _tablero[8];
		return retVal;
	}
	
	// **********************************************************************//
	/**
	 * Comprueba si dos objetos son iguales.
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

		for (int i = 0; i < 8; i++) {
			if (this.getPositionOf(i) != aBoard.getPositionOf(i)) {
				return false;
			}
		}
		return true;
	}	

}