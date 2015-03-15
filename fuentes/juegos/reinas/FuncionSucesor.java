package juegos.reinas;

import java.util.ArrayList;
import java.util.List;

import aima.basic.XYLocation;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Funcion que genera los posibles sucesores del estado actual.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {

	// **********************************************************************//
	/**
	 * Devuelve la lista de sucesores que se obtienen al aplicar un operador.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return La lista de sucesores.
	 */
	public List<Successor> getSuccessors(Object state) {
		
		List<Successor> successors = new ArrayList<Successor>();
		Tablero board = (Tablero) state;
		
		int numQueens = board.getNumberOfQueensOnBoard();
		int boardSize = board.getSize();
		
		for (int i = 0; i < boardSize; i++) {
			if (!(board.isSquareUnderAttack(new XYLocation(numQueens, i)))) {
				Tablero child = placeQueenAt(numQueens, i, board);
				successors.add(new Successor("Colocar reina en " + numQueens + "  "
						+ i, child));
			}
		}

		return successors;
	}

	// **********************************************************************//
	/**
	 * Coloca una reina en la posición especificada.
	 * 
	 * @param row Fila.
	 * @param column Columna.
	 * @param parentBoard Tablero.
	 * 
	 * @return El tablero con la reina colocada.
	 */
	private Tablero placeQueenAt(int row, int column, Tablero parentBoard) {

		Tablero newBoard = new Tablero(parentBoard.getSize());
		List<XYLocation> queenPositionsOnParentBoard = parentBoard.getQueenPositions();
		queenPositionsOnParentBoard.add(new XYLocation(row, column));
		newBoard.setBoard(queenPositionsOnParentBoard);
		return newBoard;
	}
}