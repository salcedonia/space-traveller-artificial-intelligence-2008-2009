package juegos.robot_limpiador;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica del juego de las reinas.
 *
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// ATRIBUTOS
	static String nombre = "FuncionHeuristica";
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heur�stica.
	 * 
	 * @param estate Estado del juego.
	 * 
	 * @return El valor de la heur�stica.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		
		int h = 0;
		
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				if(!t.habitacionLimpia(x, y)) h++;							
		
		return h;
	}
}