package micromundo;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Esta clase implementa el GoalTest.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si el jugador a llegado una la salida.
	 * 
	 * @param Estado en el que nos encontramos en el micromundo.
	 * 
	 * @return Verdadero si estamos en el estado final y falso en caso contrario.
	 */
	public boolean isGoalState(Object estado) {
		
		Tablero tablero = (Tablero) estado;

		// Si estamos en cualquiera de las cuatro esquinas
		return 	tablero.estaEnPlanetaObjetivo(); 
	}
}