/*
 * Created on Sep 12, 2004
 *
 */
package juegos.puzzle8;

import aima.basic.XYLocation;
import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística del juego del puzzle8.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heurística para el juego del 8-puzzle.
	 * 
	 * @param estate Estado del juego.
	 * 
	 * @return El valor de la heurística.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		
		int retVal = 0;
		
		for (int i = 1; i < 9; i++) {
			
			XYLocation loc = t.getLocationOf(i);
			retVal += evaluateManhattanDistanceOf(i, loc);
		}
		return retVal;
	}

	// **********************************************************************//
	/**
	 * Calcula la distancia al estado objetivo.
	 * 
	 * @param i Dirección.
	 * @param loc Posición.
	 * 
	 * @return La distancia al objetivo.
	 */
	public int evaluateManhattanDistanceOf(int i, XYLocation loc) {
		
		int retVal = -1;
		int xpos = loc.getXCoOrdinate();
		int ypos = loc.getYCoOrdinate();
		
		switch (i) {

		case 1:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 0);
			break;
		case 2:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 1);
			break;
		case 3:
			retVal = Math.abs(xpos - 0) + Math.abs(ypos - 2);
			break;
		case 4:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 0);
			break;
		case 5:
			retVal = Math.abs(xpos - 1) + Math.abs(ypos - 2);
			break;
		case 6:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 0);
			break;
		case 7:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 1);
			break;
		case 8:
			retVal = Math.abs(xpos - 2) + Math.abs(ypos - 2);
			break;

		}
		return retVal;
	}
}