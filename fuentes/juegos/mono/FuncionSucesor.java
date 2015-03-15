package juegos.mono;

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
	
//	*************************************************************************//
	/**
	 * La lista de sucesores posibles al aplicar todos los _operadores.
	 * 
	 * @param state Estado actual del tablero.
	 * 
	 * @return La lista de sucesores posibles al aplicar todos los _operadores.
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

//	*************************************************************************//
	/**
	 * Realiza una copia del tablero contenido.
	 *  
	 * @param contenido Tablero a copiar.
	 * 
	 * @return La copia del tablero contenido.
	 */
	private Tablero copyOf(Tablero t) {
		
		return new Tablero(t);
	}
}