package juegos.mono;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego del platano y el mono.
 * 
 * @author Javier Salcedo G�mez
 */
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si la base est� en un estado final.
	 * 
	 * @param estado Estado actual de la partida.
	 * 
	 * @return Verdadero si est� en un estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
        return t.damePlatano();
	}
}