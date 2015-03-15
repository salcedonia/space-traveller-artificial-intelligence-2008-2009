package juegos.laberinto;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego del laberinto.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
	
	// **********************************************************************//
	/**
	 * Comprueba si el jugador a llegado a la salida.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return Verdadero si estamos en un estado objetivo y falso en caso 
	 * contrario.
	 */
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
		return ((t._coordenadaX == t._objetivoX ) && (t._coordenadaY == t._objetivoY));
	}
}