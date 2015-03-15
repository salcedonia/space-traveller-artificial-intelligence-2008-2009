package juegos.viaje;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego del viaje.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest  {	
	
	// **********************************************************************//
	/**
	 * Comprueba si el tablero está en un estado final.
	 * 
	 * @param estado Estado actual del tablero.
	 * 
	 * @return Verdadero si está en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estate){
		
		Tablero t = (Tablero) estate;
		return t.equals(new Tablero("ALMERIA"));
	}

}