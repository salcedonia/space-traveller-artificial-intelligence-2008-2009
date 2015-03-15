package juegos.puzzle8;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego del puzzle-8.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
		
	
	// **********************************************************************//
	/**
	 * Comprueba si el tablero está en un estado final.
	 * 
	 * @param estado Estado actual del tablero.
	 * 
	 * @return Verdadero si está en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estado) {
		
		Tablero tablero = (Tablero) estado;
		return tablero.equals(new Tablero(new int[] { 1, 2, 3, 4, 0, 5, 6, 7, 8 }));
	}

}