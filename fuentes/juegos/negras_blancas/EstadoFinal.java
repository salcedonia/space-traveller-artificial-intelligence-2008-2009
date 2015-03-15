package juegos.negras_blancas;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego de las negras y blancas.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si el tablero est� en un estado final.
	 * 
	 * @param estado Estado actual del tablero.
	 * 
	 * @return Verdadero si est� en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
		return t.negrasEnLaIzquierda();
	}

}