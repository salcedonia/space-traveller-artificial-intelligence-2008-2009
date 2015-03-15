package juegos.granjero;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica empleada en el juego del granjero.
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
		int h=0;
		if (t.isCabraEnIzq()) h++;
		if (t.isColEnIzq()) h++;
		if (t.isLoboEnIzq()) h++;		
		return h;
		
	}
}