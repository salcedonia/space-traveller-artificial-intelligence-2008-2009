package juegos.misioneros;

import java.util.ArrayList;
import java.util.List;

import micromundo.Micromundo;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Función sucesor del problema de los misioneros _Y los caníbales
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {
	
	// **********************************************************************//
	/**
	 * Obtiene los sucesores al aplicar un operador.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return La lista de sucesores que se obtienen al aplicar un operador.
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
	 * Realiza la copia de un estado.
	 * 
	 * @param estado Estado del juego de los misioneros a copiar.
	 * 
	 * @return La copia del estado.
	 */
	private Tablero copyOf(Tablero estado) {
		
		return new Tablero(estado.getNumCanibalesEnIzq(), estado.getNumMisionerosEnIzq(), estado.getBoteEnIzq());
	}
}