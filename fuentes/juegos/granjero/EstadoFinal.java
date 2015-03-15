package juegos.granjero;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
* Clase que representa el estado final del juego del granjero.
* 
* @author Javier Salcedo Gómez, Carlos Loredo Iglesias
*/
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si el tablero está en un estado final.
	 * 
	 * @return Verdadero si estamos en estado objetivo y falso en caso
	 * contrario.
	 */
	public boolean isGoalState(Object state) {
		
		Tablero t = (Tablero) state;
		return t.equals(new Tablero(false, false, false, false));
	}
}