package juegos.robot_limpiador;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego del robot limpiador.
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
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
		return t.equals(new Tablero(new boolean[][] {{true,true,true},
													{true,true,true},
													{true,true,true}}));
	}
}