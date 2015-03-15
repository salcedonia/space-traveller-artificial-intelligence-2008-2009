package juegos.hanoi3;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego de las torres de hanoi.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {

	/*
	 * 	         A
	 *          BBB
	 *         CCCCC
	 * |---|---|---|
	 *   1   2   3
	 */
	
	// **********************************************************************//
	/**
	 * Comprueba si la base está en un estado final.
	 * 
	 * @param estate Estado actual de la partida.
	 * 
	 * @return Verdadero si está en un estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
		return t.equals(new Tablero( new char[] { 'B' , 'C', '3' } ));
	}
}