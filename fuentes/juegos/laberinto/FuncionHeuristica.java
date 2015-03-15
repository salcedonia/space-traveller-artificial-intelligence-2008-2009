package juegos.laberinto;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Funci�n Heur�stica de distancia hasta la salida del laberinto.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// ATRIBUTO
	static String _nombre = "Pythagore";
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heur�stica.
	 * 
	 * @param estado Estado actual del juego.
	 * 
	 * @return El valor de la heur�stica.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		return Math.abs(t._objetivoX - t._coordenadaX) + Math.abs(t._objetivoY - t._coordenadaY);
	}
}