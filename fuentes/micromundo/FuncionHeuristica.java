package micromundo;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Funcion FuncionHeuristica de distancia hasta llegar a un estado objetivo del
 * micromundo.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias, David Hern�ndez Plaza
 */
public class FuncionHeuristica implements HeuristicFunction {

	// ATRIBUTOS
	static String nombre = "FuncionHeuristica";

	// **********************************************************************//
	/**
	 * Devuelve un real con el valor de la heur�stica asociado al micromundo.
	 * Devuelve la m�nima distancia en funci�n de la posici�n actual de la 
	 * nave a los 4 planetas objetivo, es decir se queda con la distancia 
	 * al planeta objetivo m�s cercano.
	 * 
	 * @param estado
	 *            Estado actual del micromundo.
	 */
	@SuppressWarnings("static-access")
	public double getHeuristicValue(Object estado) {

		Tablero tablero = (Tablero) estado;
		
		int x = tablero.getCoordenadaX();
		int y = tablero.getCoordenadaY();
		int dim = tablero._TAMANO-1;
		
		return Math.min(Math.abs(0-x)+Math.abs(0-y),
			   Math.min(Math.abs(dim-x)+Math.abs(0-y),
			   Math.min(Math.abs(0-x)+Math.abs(dim-y),
			   Math.abs(dim-x)+Math.abs(dim-y))));
	}	
}