package juegos.negras_blancas;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la funci�n heur�stica empleada en el juego de las negras _blancas.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
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
	public double getHeuristicValue(Object estado) {
		
		Tablero tablero = (Tablero) estado;
		return evaluateDistanceOf(tablero);
	}

	// **********************************************************************//
	/**
	 * Devuelve el n�mero de fichas negras que hay a la derecha de cada ficha blanca.
	 * 
	 * @param tablero Estado actual de la partida.
	 * 
	 * @return La distancia que falta hasta llegar al estado objetivo.
	 */
	public int evaluateDistanceOf(Tablero tablero) {
		
		int retVal = 0;
		
		for(int i = 0; i < tablero._maxDerecha; i++){
		
			if(tablero.getValueAt(i) == 'B'){
				
				for(int j = i; j < tablero._maxDerecha; j++){
					if(tablero.getValueAt(j) == 'N') retVal++;
				}
			}
		}
		
		return retVal;
	}
}