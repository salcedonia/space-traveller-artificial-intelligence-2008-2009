package juegos.misioneros;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística empleada en el juego de los misioneros.
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
	public double getHeuristicValue(Object o) {
		
		Tablero t = (Tablero) o;		
		return t.getNumCanibalesEnIzq() + t.getNumMisionerosEnIzq();
		
	}
}