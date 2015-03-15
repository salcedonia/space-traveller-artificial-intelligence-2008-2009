package juegos;

import aima.search.framework.GoalTest;
import aima.search.framework.GraphSearch;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.Search;
import aima.search.framework.SuccessorFunction;
import aima.search.framework.TreeSearch;
import aima.search.informed.AStarSearch;
import aima.search.informed.GreedyBestFirstSearch;
import aima.search.informed.SimulatedAnnealingSearch;
import aima.search.uninformed.BreadthFirstSearch;
import aima.search.uninformed.DepthFirstSearch;
import aima.search.uninformed.DepthLimitedSearch;
import aima.search.uninformed.IterativeDeepeningSearch;
import aima.search.uninformed.UniformCostSearch;

//***************************************************************************//
/**
 * Realiza diferentes conversiones entre los enumerados de búsquedas y juegos 
 * empleados en el micromundo y diferentes varios tipos de datos para simplificar
 * el acceso a dichos elementos.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Conversor{

//	*************************************************************************//
	/**
	 * Convierte el TipoBusqueda a tipo Search.
	 * 
	 * @param t TipoBusqueda a convertir.
	 * 
	 * @return El objeto de tipo Search que corresponde a TipoBusqueda.
	 */
	public static Search tipoBusquedaToSearch(TipoBusqueda t){
		
		switch (t) {
		
			case PROFUNDIDAD_ITERATIVA: return new IterativeDeepeningSearch();
			case PRIMERO_EN_ANCHURA: return new BreadthFirstSearch(new TreeSearch()); 			
			case PRIMERO_EN_PROFUNDIDAD: return new DepthFirstSearch(new GraphSearch()); 
			case PROFUNDIDAD_LIMITADA: return new DepthLimitedSearch(21); 
			case COSTE_UNIFORME: return new UniformCostSearch(new GraphSearch()); 
			case A_ESTRELLA:	return new AStarSearch(new GraphSearch()); 
			case VORAZ: return new GreedyBestFirstSearch(new GraphSearch()); 
			case ENFRIAMIENTO: return new SimulatedAnnealingSearch(); 
			default: return null;
		}
	}
	
//	*************************************************************************//
	/**
	 * Convierte un objeto TipoBusqueda a un String.
	 * 
	 * @param t TipoBusqueda correspondiente.
	 * 
	 * @return El string correspondiente a t.
	 */
	public static String tipoBusquedaToString(TipoBusqueda t){
		
		switch (t) {
		
			case PROFUNDIDAD_ITERATIVA: return "PROFUNDIDAD_ITERATIVA"; 
			case PRIMERO_EN_ANCHURA: return "PRIMERO_EN_ANCHURA"; 			
			case PRIMERO_EN_PROFUNDIDAD: return "PRIMERO_EN_PROFUNDIDAD"; 
			case PROFUNDIDAD_LIMITADA: return "PROFUNDIDAD_LIMITADA"; 
			case COSTE_UNIFORME: return "COSTE_UNIFORME"; 
			case A_ESTRELLA: return "A*"; 
			case VORAZ:	return "VORAZ"; 
			case ENFRIAMIENTO: return "ESCALADA"; 
			default: return null;
		}
	}

//	*************************************************************************//
	/**
	 * Convierte un objeto TipoBusqueda a un String que contiene la 
	 * descripción del TipoBusqueda.
	 * 
	 * @param t TipoBusqueda correspondiente.
	 * 
	 * @return El string correspondiente a la descripcion de t.
	 */
	public static String tipoBusquedaToDescripcion(TipoBusqueda t){
		
		switch (t) {
			
			case PROFUNDIDAD_ITERATIVA: return "Profundización Iterativa.";
			case PRIMERO_EN_ANCHURA: return "Primero en Anchura.";
			case PRIMERO_EN_PROFUNDIDAD: return "Primero en Profundidad.";
			case PROFUNDIDAD_LIMITADA: return "Límite de Profundidad (21).";
			case COSTE_UNIFORME: return "Coste Lineal.";
			case A_ESTRELLA: return "A*.";
			case VORAZ: return "Voraz.";
			case ENFRIAMIENTO: return "Enfriamiento simulado.";			
			default: return null;
		}
	}

//	*************************************************************************//
	/**
	 * Convierte un objeto TipoBusqueda a un entero.
	 * 
	 * @param t TipoBusqueda correspondiente.
	 * 
	 * @return El entero correspondiente a t.
	 */
	public static int tipoBusquedaToInt(TipoBusqueda t){
		
		switch (t) {
			
			case PROFUNDIDAD_ITERATIVA: return 0; 
			case PRIMERO_EN_ANCHURA: return 1; 			
			case PRIMERO_EN_PROFUNDIDAD: return 2; 
			case PROFUNDIDAD_LIMITADA: return 3; 
			case COSTE_UNIFORME: return 4; 
			case A_ESTRELLA: return 5; 
			case VORAZ: return 6; 
			case ENFRIAMIENTO: return 7; 
			default: return 0;
		}
	}

//	*************************************************************************//
	/**
	 * Devuelve el TipoBusqueda correspondiente a un entero.
	 * 
	 * @param i Entero.
	 * 
	 * @return El TipoBusqueda correspondiente a un entero.
	 */
	public static TipoBusqueda intToTipoBusqueda(int i){
		
		switch (i) {
			
			case 0: return TipoBusqueda.PROFUNDIDAD_ITERATIVA; 
			case 1:	return TipoBusqueda.PRIMERO_EN_ANCHURA; 			
			case 2:	return TipoBusqueda.PRIMERO_EN_PROFUNDIDAD; 
			case 3:	return TipoBusqueda.PROFUNDIDAD_LIMITADA; 
			case 4: return TipoBusqueda.COSTE_UNIFORME; 
			case 5:	return TipoBusqueda.A_ESTRELLA; 
			case 6:	return TipoBusqueda.VORAZ; 
			case 7:	return TipoBusqueda.ENFRIAMIENTO; 
			default: return null;
		}
	}		

//	*************************************************************************//
	/**
	 * Convierte un TipoJuego a String.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El String correspondiente a TipoJuego.
	 */
	public static String tipoJuegoToDescripcion(TipoJuego t){
		
		switch (t) {
			
			case GRANJERO: return "El granjero, el lobo, la cabra y la col.";
			case HANOI3: return "Las torres de Hanoi con 3 pisos.";
			case GARRAFAS: return "Las garrafas de 3 y 4 litros.";
			case LABERINTO:	return "El laberinto.";
			case MISIONEROS: return "Los misioneros y los caníbales.";
			case NEGRAS_BLANCAS: return "Tablero con negras y blancas.";
			case POLLITOS: return "La huevera y los pollitos.";
			case PUZZLE8: return "El 8-puzzle.";
			case REINAS: return "Las 8 reinas.";
			case ROBOT_LIMPIADOR: return "El robot limpiador.";
			case VIAJE: return "El viaje.";
			case MONO: return "El mono.";
			case MICROMUNDO: return "El Micromundo.";
			default: return null;	
		}
	}

//	*************************************************************************//
	/**
	 * Convierte un TipoJuego a HeuristicFunction.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return La HeuristicFunction correspondiente a t.
	 */
	public static HeuristicFunction tipoJuegoToHeuristicFunction(TipoJuego t){
		
		switch (t) {
			
			case GRANJERO: return new juegos.granjero.FuncionHeuristica();  
			case HANOI3: return new juegos.hanoi3.FuncionHeuristica(); 			
			case GARRAFAS: return new juegos.garrafas.FuncionHeuristica();  
			case LABERINTO:	return new juegos.laberinto.FuncionHeuristica(); 
			case MISIONEROS: return new juegos.misioneros.FuncionHeuristica();  
			case NEGRAS_BLANCAS: return new juegos.negras_blancas.FuncionHeuristica(); 
			case POLLITOS: return new juegos.pollitos.FuncionHeuristica(); 
			case PUZZLE8: return new juegos.puzzle8.FuncionHeuristica(); 
			case REINAS: return new juegos.reinas.FuncionHeuristica(); 
			case ROBOT_LIMPIADOR: return new juegos.robot_limpiador.FuncionHeuristica(); 		
			case VIAJE:	return new juegos.viaje.FuncionHeuristica(); 				
			case MONO: return new juegos.mono.FuncionHeuristica(); 
			case MICROMUNDO: return new micromundo.FuncionHeuristica();
			default: return null;
		}
	}	

//	*************************************************************************//
	/**
	 * Devuelve el GoalTest correspondiente a TipoJuego.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El GoalTest correspondiente a TipoJuego.
 	 */
	public static GoalTest tipoJuegoToGoalTest(TipoJuego t){
		
		switch (t) {
		
			case GRANJERO: return new juegos.granjero.EstadoFinal();
			case HANOI3: return new juegos.hanoi3.EstadoFinal();
			case GARRAFAS: return new juegos.garrafas.EstadoFinal();
			case LABERINTO: return new juegos.laberinto.EstadoFinal();
			case MISIONEROS: return new juegos.misioneros.EstadoFinal();
			case NEGRAS_BLANCAS: return new juegos.negras_blancas.EstadoFinal();
			case POLLITOS: return new juegos.pollitos.EstadoFinal();
			case PUZZLE8: return new juegos.puzzle8.EstadoFinal();
			case REINAS: return new juegos.reinas.EstadoFinal();
			case ROBOT_LIMPIADOR: return new juegos.robot_limpiador.EstadoFinal();
			case VIAJE: return new juegos.viaje.EstadoFinal();
			case MONO: return new juegos.mono.EstadoFinal();
			case MICROMUNDO: return new micromundo.EstadoFinal();
			default: return null;
		}
	}

//	*************************************************************************//
	/**
	 * Convierte un TipoJuego a Tablero.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El Tablero correspondiente a t.
	 */
	public static Object tipoJuegoToTablero(TipoJuego t){

		switch (t) {
		
			case GRANJERO: return new juegos.granjero.Tablero();
			case HANOI3: return new juegos.hanoi3.Tablero();
			case GARRAFAS: return new juegos.garrafas.Tablero();
			case LABERINTO: return new juegos.laberinto.Tablero();
			case MISIONEROS: return new juegos.misioneros.Tablero();
			case NEGRAS_BLANCAS: return new juegos.negras_blancas.Tablero();
			case POLLITOS: return new juegos.pollitos.Tablero();
			case PUZZLE8: return new juegos.puzzle8.Tablero();
			case REINAS: return new juegos.reinas.Tablero(8);
			case ROBOT_LIMPIADOR: return new juegos.robot_limpiador.Tablero();
			case VIAJE: return new juegos.viaje.Tablero();
			case MONO: return new juegos.mono.Tablero();
			case MICROMUNDO: return new micromundo.Tablero(7, 7);
			default: return null;	
		}
	}
	
//	*************************************************************************//
	/**
	 * Convierte un TipoJuego a SucessorFunction.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El SuccesorFunction correspondiente a t.
	 */
	public static SuccessorFunction tipoJuegoToSucessorFunction(TipoJuego t){
		
		switch (t) {
			
			case GRANJERO: return new juegos.granjero.FuncionSucesor();
			case HANOI3: return new juegos.hanoi3.FuncionSucesor();
			case GARRAFAS: return new juegos.garrafas.FuncionSucesor();
			case LABERINTO: return new juegos.laberinto.FuncionSucesor();
			case MISIONEROS: return new juegos.misioneros.FuncionSucesor();
			case NEGRAS_BLANCAS: return new juegos.negras_blancas.FuncionSucesor();
			case POLLITOS: return new juegos.pollitos.FuncionSucesor();
			case PUZZLE8: return new juegos.puzzle8.FuncionSucesor();
			case REINAS: return new juegos.reinas.FuncionSucesor();
			case ROBOT_LIMPIADOR: return new juegos.robot_limpiador.FuncionSucesor();
			case VIAJE: return new juegos.viaje.FuncionSucesor();
			case MONO: return new juegos.mono.FuncionSucesor();
			case MICROMUNDO: return new micromundo.FuncionSucesor();
			default: return null;
		}
	}

//	*************************************************************************//
	/**
	 * Devuelve el nivel de dificutad (1-13) asociado a un TipoJuego.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El nivel de dificutad (1-13) asociado a un TipoJuego.
	 */
	public static int tipoJuegoToDificultad(TipoJuego t){
		
		switch (t) {									 		
			case GARRAFAS: return 1;
			case GRANJERO: return 2;
			case MISIONEROS: return 3;
			case VIAJE:	return 4; 
			case LABERINTO:	return 5; 
			case HANOI3: return 6; 
			case PUZZLE8: return 7;
			case MONO: return 8; 
			case ROBOT_LIMPIADOR: return 9;
			case NEGRAS_BLANCAS: return 10;			
			case POLLITOS: return 11; 			 
			case REINAS: return 12;
			case MICROMUNDO: return 13;			 														
			default: return 0;
		}
	}

//	*************************************************************************//
	/**
	 * Devuelve el entero correspondiente a TipoJuego.
	 * 
	 * @param t TipoJuego.
	 * 
	 * @return El entero correspondiente a t.
	 */
	public static int tipoJuegoToInt(TipoJuego t){
		
		switch (t) {
			
		case GARRAFAS: return 1;
		case GRANJERO: return 2;
		case MISIONEROS: return 3;
		case VIAJE:	return 4; 
		case LABERINTO:	return 5; 
		case HANOI3: return 6; 
		case PUZZLE8: return 7;
		case MONO: return 8; 
		case ROBOT_LIMPIADOR: return 9;
		case NEGRAS_BLANCAS: return 10;			
		case POLLITOS: return 11; 			 
		case REINAS: return 12;
		case MICROMUNDO: return 13;			 														
		default: return 0;			
		
		}
	}

//	*************************************************************************//
	/**
	 * Devuelve el TipoJuego correspondiente a un entero.
	 * 
	 * @param i Entero.
	 * 
	 * @return El TipoJuego correspondiente a un entero.
	 */
	public static TipoJuego intToTipoJuego(int i){
		
		switch (i) {
						
			case 1: return TipoJuego.GARRAFAS;			
			case 2: return TipoJuego.GRANJERO;
			case 3: return TipoJuego.MISIONEROS;
			case 4: return TipoJuego.VIAJE;
			case 5: return TipoJuego.LABERINTO;
			case 6: return TipoJuego.HANOI3;
			case 7: return TipoJuego.PUZZLE8;
			case 8: return TipoJuego.MONO;
			case 9: return TipoJuego.ROBOT_LIMPIADOR;
			case 10: return TipoJuego.NEGRAS_BLANCAS;
			case 11: return TipoJuego.POLLITOS;
			case 12: return TipoJuego.REINAS;
			case 13: return TipoJuego.MICROMUNDO;
			default: return null;
		}
	}		
}
