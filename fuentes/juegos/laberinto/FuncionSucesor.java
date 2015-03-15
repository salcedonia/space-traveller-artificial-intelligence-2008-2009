package juegos.laberinto;

import java.util.ArrayList;
import java.util.List;

import micromundo.Micromundo;

import aima.search.framework.Successor;
import aima.search.framework.SuccessorFunction;

//***************************************************************************//
/**
 * Función sucesor del laberinto en 2D.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionSucesor implements SuccessorFunction {

	// **********************************************************************//
	/**
	 * Comprueba que el sucesor existe y se puede añadir a la lista de 
	 * sucesores.
	 * 
	 * @param estate Estado del juego.
	 * 
	 * @return La lista de sucesores.
	 */
	public List<Successor> getSuccessors(Object estate) {
		
		Tablero t = (Tablero) estate;
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
	 * Realiza una copia del estado del juego.
	 * 
	 * @param mapa Estado actual del juego.
	 * 
	 * @return La copia del estado del juego.
 	 */
	private Tablero copyOf(Tablero mapa) {
		
		return new Tablero( mapa._laberinto, mapa.getCoordenadaX(), mapa.getCoordenadaY() , mapa._objetivoX, mapa._objetivoY);
	}
}