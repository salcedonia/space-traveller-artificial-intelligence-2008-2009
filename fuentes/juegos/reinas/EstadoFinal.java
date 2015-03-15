package juegos.reinas;

import java.util.List;

import aima.basic.XYLocation;
import aima.search.framework.GoalTest;

//***************************************************************************//
/**
 * Representa el estado objetivo del juego de las 8 reinas.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class EstadoFinal implements GoalTest {
	
	// ATRIBUTOS
	Tablero _tablero;

	// **********************************************************************//
	/**
	 * Comprueba si el tablero está en un estado final.
	 * 
	 * @param estado Estado actual del tablero.
	 * 
	 * @return Verdadero si está en el estado objetivo y falso en caso contrario.
	 */
	public boolean isGoalState(Object estado) {

		_tablero = (Tablero) estado;
		
		return (allQueensPlaced() && allQueenPositionsHaveZeroAttacks(_tablero
				.getQueenPositions()));
	}

	// **********************************************************************//
	/**
	 * Comprueba si todas las reinas están puestas en el tablero.
	 * 
	 * @return Verdadero si todas las reinas están puestas en el tablero y falso
	 * en caso contrario.
	 */
	private boolean allQueensPlaced() {
		
		return _tablero.getNumberOfQueensOnBoard() == _tablero.getSize();
	}

	// **********************************************************************//
	/**
	 * Comprueba si todas las reinas colocadas no pueden ser atacadas por
	 * ninguna de las otras reinas.
	 * 
	 * @param positions Posiciones de las reinas.
	 * 
	 * @return Verdadero si todas las reinas no pueden ser atacadas por ninguna
	 * de las otras reinas.
	 */
	private boolean allQueenPositionsHaveZeroAttacks(List<XYLocation> positions) {

		for (int i = 0; i < positions.size(); i++) {
			XYLocation location = (XYLocation) positions.get(i);
			if (_tablero.getNumberOfAttacksOn(location) != 0) {
				return false;
			}
		}
		return true;
	}
}