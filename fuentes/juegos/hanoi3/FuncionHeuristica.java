package juegos.hanoi3;

import aima.search.framework.HeuristicFunction;

//***************************************************************************//
/**
 * Representa la función heurística empleada en el juego de las torres de hanoi.
 * 
 * @author Carlos Loredo Iglesias
 */
public class FuncionHeuristica implements HeuristicFunction {	
	
	// **********************************************************************//
	/**
	 * Devuelve el valor de la heurística para el juego de las torres de hanoi.
	 * 
	 * @param estate Estado actual del juego.
	 * 
	 * @return El valor de la heurística.
	 */
	public double getHeuristicValue(Object estate) {
		
		Tablero t = (Tablero) estate;
		
		char _discos[] = {'A','B','C'};
		int h = 0;
		
		for (int i = 1; i < 3; i++) {
			char _posicion = t.damePosicionDe(_discos[i]);
			h += evaluateManhattanDistanceOf(t, _discos[i], _posicion );
		}
		
		return h;
	}

	// **********************************************************************//
	/**
	 * Evalua los pasos que quedan para poner un disco que está en determinada posición
	 * en la posición correcta.
	 * 
	 * @param t Tablero que refleja el estado actual de la partida.
	 * @param d Disco.
	 * @param p Posicion donde se encuentra el disco.
	 * 
	 * @return La distancia al estado objetivo.
	 */
	public int evaluateManhattanDistanceOf(Tablero t, char d, char p ) {
		
		int retVal = -1;

		switch (d) {

			case 'A':
				if		(p == '1') 	retVal = 0;
				else if	(p == '2') 	retVal = 1;
				else 				retVal = 2;
				break;
				
			case 'B': 
				if		(p == '1') retVal = 1;				
				else if	(p == '2')
					if (t.tieneAlgunDiscoEncima('C')) retVal = 3;
					else retVal = 2;				
				else
					retVal = 0;
				break;
				
			case 'C':
				if (p == '1') 
					if (t.tieneAlgunDiscoEncima('C')) 	retVal = 3;
					else 								retVal = 2;
				else if	(p == '2') 						retVal = 1;
				else 									retVal = 0;
		}
		
		return retVal;
	}	
	
		
}