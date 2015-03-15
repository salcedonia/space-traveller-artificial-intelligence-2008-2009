package juegos.misioneros;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica empleada en el juego de los misioneros.
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
	public double getHeuristicValue(Object o) {
		
		Tablero t = (Tablero) o;		
		return t.getNumCanibalesEnIzq() + t.getNumMisionerosEnIzq();
		
	}
}