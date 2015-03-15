package juegos.mono;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica empleada en el juego del mono.
 * 
 * @author Javier Salcedo G�mez
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heur�stica para el juego del mono.
	 * 
	 * @param estate Estado actual del juego.
	 * 
	 * @return El valor de la heur�stica.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		
		if (t.damePos() == 0)
			return 5;
		if (t.damePos() == 2 && t.damePosCaja() != 2)
			return 5;
		if (t.damePos() == 1 && t.damePosCaja() != 1)
			return 4;
		if (t.damePos() == 2 && t.damePosCaja() == 2 && !t.dameSubido())
			return 3;
		if (t.damePos() == 2 && t.damePosCaja() == 2 && t.dameSubido())
			return 2;
		if (t.damePos() == 1 && t.dameSubido() && !t.damePlatano())
			return 1;
		if (t.damePos() == 1 && t.dameSubido() && t.damePlatano())
			return 0;
		return 6;
	}
}