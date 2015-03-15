package juegos.pollitos;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * FuncionHeuristica que asigna a cada estado el n�mero de huevos
 * que a�n no tienen pollito.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve el valor heur�stico de cada estado.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return El valor heur�stico de cada estado.
	 */
	public double getHeuristicValue(Object estado) {
		
		Tablero tablero = (Tablero) estado;
				
		int h = 0;
		for(int i = 0; i <= 1; i++){
			for(int j = 0; j <= 4; j++){
				if (j>0 && tablero.getTablero()[i][j-1] == tablero.getTablero()[i][j]) h++;
				if (j<4 && tablero.getTablero()[i][j+1] == tablero.getTablero()[i][j]) h++;
				if (i==0 && tablero.getTablero()[i+1][j] == tablero.getTablero()[i][j]) h++;
				if (i==1 && tablero.getTablero()[i-1][j] == tablero.getTablero()[i][j]) h++;						
			}
		}
		
		return 26 - h;
	}
}
