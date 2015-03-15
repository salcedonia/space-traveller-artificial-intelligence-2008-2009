package juegos.garrafas;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica empleada en el juego de las garrafas
 * 
 * @author Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heur�stica.
	 * 
	 * @param estado Estado actual del juego.
	 * 
	 * @return El valor de la heur�stica.
	 */
	public double getHeuristicValue(Object state) {
		
		Tablero t = (Tablero) state;			
		return Math.abs(2-t.getGarrafa4());
		
	}
}