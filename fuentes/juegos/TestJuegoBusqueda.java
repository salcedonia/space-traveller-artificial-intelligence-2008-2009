package juegos;

public class TestJuegoBusqueda {
	
	public static void test(boolean verbose){
				
		for (int j = 1; j < 12; j++)
			for (int b = 0; b < 7; b++){
				
				if (verbose)
					System.out.print(Conversor.tipoJuegoToDescripcion(Conversor.intToTipoJuego(j)) + " -> " + Conversor.tipoBusquedaToDescripcion(Conversor.intToTipoBusqueda(b)));
				else
					System.out.print(j + "->" + b);
												
				long inicio = System.currentTimeMillis();
				if (GestorDeProblemas.resolverProblema(Conversor.tipoJuegoToTablero(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToSucessorFunction(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToGoalTest(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToHeuristicFunction(Conversor.intToTipoJuego(j)),
						Conversor.tipoBusquedaToSearch(Conversor.intToTipoBusqueda(b)))){
					long fin = System.currentTimeMillis();
										
					System.out.println(" -> SÍ. " + (fin-inicio) + "ms. Nodos: " +GestorDeProblemas.get_agent().getInstrumentation().getProperty("nodesExpanded"));
					//System.out.print(GestorDeProblemas.getSolucion());
								
				}
				else{					
					System.out.println(" -> NO.\n");
				}				
			}
	}
	
//	*************************************************************************//	
	/**
	 * Método main de la aplicación.
	 * 
	 * @param args Argumentos de entrada a la aplicación.
 	 */
	public static void main (String[] args) {
							
	    test(true);		
	}
}
