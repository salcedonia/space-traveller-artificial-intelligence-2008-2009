package juegos.granjero;

import java.util.ArrayList;
import java.util.List;

import micromundo.Micromundo;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Función sucesor del problema del granjero.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {
	
	// **********************************************************************//
	/**
	 * Devuelve la lista de sucesores que se pueden aplicar en un momento
	 * determinado.
	 * 
	 * @param state Estado.
	 * 
	 * @return La lista de sucesores que se pueden aplicar en un momento
	 * determinado.
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

//	***************************************************************************//
	/**
	 * Realiza la copia del tablero.
	 * 
	 * @param rio Valor a copiar.
	 * 
	 * @return La copia del tablero rio.
	 */
	private Tablero copyOf(Tablero rio) {
		
		Tablero newRio = new Tablero(rio.isGranjeroEnIzq(), rio.isLoboEnIzq(), rio.isCabraEnIzq(), rio.isColEnIzq());
		return newRio;
	}
}