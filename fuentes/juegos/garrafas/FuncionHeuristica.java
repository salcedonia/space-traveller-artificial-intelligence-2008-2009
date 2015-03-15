package juegos.garrafas;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística empleada en el juego de las garrafas
 * 
 * @author Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heurística.
	 * 
	 * @param estado Estado actual del juego.
	 * 
	 * @return El valor de la heurística.
	 */
	public double getHeuristicValue(Object state) {
		
		Tablero t = (Tablero) state;			
		return Math.abs(2-t.getGarrafa4());
		
	}
}