package juegos.hanoi3;


import java.util.ArrayList;
import java.util.List;

import micromundo.Micromundo;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Representa la función sucesor del juego de las torres de hanoi.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve la lista de sucesores de un estado.
	 * 
	 * @param state Estado actual del juego.
	 * 
	 * @return La lista de sucesores que se pueden aplicar al estado actual.
	 */
	public List<Successor> getSuccessors(Object state) {
		
		Tablero t = (Tablero) state;
		if (Micromundo.is_controlCiclos() && t.equals(new Tablero())){
			Micromundo.resetControlCiclos();
			Micromundo.get_hashCiclos().put(t.toString().hashCode(), true);
		}
		List<Successor> sucesores = new ArrayList<Successor>();
		
		for(int i = 0; i < Tablero._operadores.length; i++){
			
			if (t.movimientoPosible(Tablero._operadores[i])) {
				Tablero t2 = copyOf(t);
				t2.mover(Tablero._operadores[i]);
				if (Micromundo.is_controlCiclos()){
					if (Micromundo.get_hashCiclos().get(t2.toString().hashCode())== null){
						Micromundo.get_hashCiclos().put(t2.toString().hashCode(), true);
						sucesores.add(new Successor(Tablero._operadores[i], t2));				
					}
				}
				else
					sucesores.add(new Successor(Tablero._operadores[i], t2));
			}
		}

		return sucesores;
	}
	
	// **********************************************************************//
	/**
	 * Hace una copia de un estado de la partida.
	 * 
	 * @param estado Estado de la partida a copiar.
	 * 
	 * @return La copia del estado <b>estado</b>.
	 */
	private Tablero copyOf(Tablero estado) {	// Hace un copy de un estado
		
		Tablero newBoard = new Tablero();
		newBoard.setTablero(estado.getTablero());
		return newBoard;
	}
}