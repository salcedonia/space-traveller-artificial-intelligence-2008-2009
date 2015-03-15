package micromundo;

import aima.search.framework.StepCostFunction;
import juegos.Conversor;

//***************************************************************************//
/**
 * Función de coste del micromundo.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class FuncionCoste implements StepCostFunction {

	// **********************************************************************//
	/**
	 * Devuelve el coste de movernos de un planeta a otro. La calcula en función
	 * de la dificultad asociada a cada Viaje.
	 * 
	 * @return El coste de movernos de un planeta a otro. Viene determinado por
	 *         la dificultad asociada a cada Viaje.
	 */
	public Double calculateStepCost(Object o, Object y, String s) {

		int c = 1;
		Tablero tablero = (Tablero) o;

		if (s.equals("Arriba"))
			c = Conversor.tipoJuegoToDificultad(
					tablero.getViaje(
							tablero.getCoordenadaX(),
							tablero.getCoordenadaY()-1, 0).getJuego());
		
		else if (s.equals("Derecha"))
			c = Conversor.tipoJuegoToDificultad(
					tablero.getViaje(
							tablero.getCoordenadaX(),
							tablero.getCoordenadaY(), 1).getJuego());
		
		else if (s.equals("Abajo"))
			c = Conversor.tipoJuegoToDificultad(
					tablero.getViaje(
							tablero.getCoordenadaX(),
							tablero.getCoordenadaY(), 0).getJuego());
		
		else if (s.equals("Izquierda"))
			c = Conversor.tipoJuegoToDificultad(
					tablero.getViaje(
							tablero.getCoordenadaX()-1,
							tablero.getCoordenadaY(), 1).getJuego());
		
		return new Double(c);
	}
}