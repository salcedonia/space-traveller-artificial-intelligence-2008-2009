package juegos.pollitos;

import aima.search.framework.GoalTest;

//**************************************************************************//
/**
 * Esta clase implementa el estado objetivo
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {

	// **********************************************************************//
	/**
	 * Comprueba si el tablero est� en un estado final. Es decir si en todos los
	 * huevos hay un pollito.
	 * 
	 * @param estado Estado actual del tablero.
	 * 
	 * @return Verdadero si est� en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estado) {
		
		Tablero tablero = (Tablero) estado;
		return tablero.equals(new Tablero(3));
	}
}
