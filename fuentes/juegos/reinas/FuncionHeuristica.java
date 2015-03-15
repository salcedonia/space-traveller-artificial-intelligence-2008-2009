package juegos.reinas;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica del juego de las reinas.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heur�stica.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return El valor de la heur�stica.
	 */
	public double getHeuristicValue(Object estado) {
		
		Tablero tablero = (Tablero) estado;
		return tablero.size - tablero.getNumberOfQueensOnBoard();
	}
}