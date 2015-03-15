package juegos.reinas;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística del juego de las reinas.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heurística.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return El valor de la heurística.
	 */
	public double getHeuristicValue(Object estado) {
		
		Tablero tablero = (Tablero) estado;
		return tablero.size - tablero.getNumberOfQueensOnBoard();
	}
}