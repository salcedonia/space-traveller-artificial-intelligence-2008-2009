package juegos.misioneros;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego de los misioneros.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si el tablero est� en un estado final.
	 * 
	 * @param Estado actual del tablero.
	 * 
	 * @return Verdadero si est� en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estado) {
		
		Tablero t = (Tablero) estado;
		
		return t.equals(new Tablero(0, 0, false));
	}
}