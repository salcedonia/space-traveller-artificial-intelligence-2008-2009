
package juegos.viaje;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística del juego de los viajes.
 *
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction{
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heurística.
	 * 
	 * @param estado Estado del juego.
	 * 
	 * @return El valor de la heurística.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		int valor = -1;
		
		if(t.getSituacion().equals("HUELVA"))  valor = 4;
		if(t.getSituacion().equals("CADIZ"))   valor = 3;
		if(t.getSituacion().equals("SEVILLA")) valor = 3;
		if(t.getSituacion().equals("MALAGA"))  valor = 2;
		if(t.getSituacion().equals("CORDOBA")) valor = 2;
		if(t.getSituacion().equals("JAEN"))    valor = 1;
		if(t.getSituacion().equals("GRANADA")) valor = 1;
		if(t.getSituacion().equals("ALMERIA")) valor = 0;		
		
		return valor;
	}
}
