package juegos.pollitos;

import java.util.ArrayList;
import java.util.List;

import micromundo.Micromundo;
import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//**************************************************************************//
/**
 * Funcion que genera los posibles sucesores del estado actual.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {
	
	// **********************************************************************//
	/**
	 * Añade los posibles sucesores del estado actual a la
	 * lista de sucesores.
	 * 
	 * @param estado Estado actual.
	 * 
	 * @return Devuelve todos los estados sucesores posibles.
	 */
	public List<Successor> getSuccessors(Object estate) {
		
		Tablero t = (Tablero) estate;
		List<Successor> sucesores = new ArrayList<Successor>();
		if (Micromundo.is_controlCiclos() && t.equals(new Tablero())){
			Micromundo.resetControlCiclos();
			Micromundo.get_hashCiclos().put(t.toString().hashCode(), true);
		}
		for(int i = 0; i < Tablero._operadores.length; i++){			
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

		return sucesores;
	}
	
	// **********************************************************************//
	/**
	 * Hace una copia de la huevera.
	 * 
	 * @param t Tablero a copiar.
	 *  
	 * @return Devuelve la copia de la huevera que le hemos pasado.
	 */
	private Tablero copyOf(Tablero t) {
		
		return new Tablero(t.getTablero());
	}
}
