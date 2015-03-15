package juegos.hanoi3;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego de las torres de hanoi.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
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
	 * Comprueba si la base est� en un estado final.
	 * 
	 * @param estate Estado actual de la partida.
	 * 
	 * @return Verdadero si est� en un estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estate) {
		
		Tablero t = (Tablero) estate;
		return t.equals(new Tablero( new char[] { 'B' , 'C', '3' } ));
	}
}