package micromundo;

import java.util.ArrayList;
import java.util.List;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Función sucesor del laberinto.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias, David Hernández Plaza
 */
public class FuncionSucesor implements SuccessorFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve la lista de sucesores asociados a un estado del micromundo.
	 * Comprueba que el sucesor existe _Y lo añade a la lista de sucesores.
	 * 
	 * @return La lista de sucesores asociados a un estado del micromundo.
	 */
	public List<Successor> getSuccessors(Object state) {

		Tablero t = (Tablero) state;

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
	 * Realiza una copia del tablero mapa.
	 * 
	 * @param micromundo
	 *            Tablero a copiar.
	 * 
	 * @return La copia del tablero mapa.
	 */
	private Tablero copyOf(Tablero micromundo) {

		return new Tablero(micromundo);
	}
}