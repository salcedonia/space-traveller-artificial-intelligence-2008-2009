package juegos.garrafas;

import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Estado final del juego de las Garrafas.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest  {
	
//	*************************************************************************//
	/**
	 * Comprueba si en la garrafa de 4 litros contiene 2 litros.
	 * 
	 * @return Devuelve cierto en caso de que la garrafa de 4 litros contenga 
	 * 2 litros y falso en caso contrario.
	 */
	public boolean isGoalState(Object state){
		
		Tablero t = (Tablero) state;
		return t.equals(new Tablero(2));
	}
}
