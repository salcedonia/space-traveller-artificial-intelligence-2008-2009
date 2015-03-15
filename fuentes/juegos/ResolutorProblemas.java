package juegos;

import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;

//***************************************************************************//
/**
 * Clase que implementa el hilo de resoluci�n de cada problema de forma 
 * independiente.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias
 */
public class ResolutorProblemas implements Runnable { 
	
	// ATRIBUTOS
	private Search _busqueda;	
	private Problem _problema;
	private SearchAgent _agenteDeBusqueda;
	
//	*************************************************************************//
	/**
	 * Constructor de la clase ResolutorProblemas.
	 * Almacena el problema y el m�todo de b�squeda asociado para resolverlo.
	 * 
	 * @param p GestorDeProblemas.
	 * @param s M�todo de b�squeda.
	 */
	public ResolutorProblemas(Problem p, Search s){
		_agenteDeBusqueda = null;
		_problema = p;
		_busqueda = s;		
	}
	
//	*************************************************************************//
	/**
	 * Devuelve el _agenteDeBusqueda.
	 * 
	 * @return El _agenteDeBusqueda.
	 */
	public SearchAgent getSearchAgent() {
		
		return _agenteDeBusqueda;
	}

//	*************************************************************************//
	/**
	 * M�todo que ejecuta el hilo del problema. Crea un nuevo agente a partir 
	 * de los datos recibidos cuando se configura el ResolutorProblemas.
	 */
	public synchronized void run() {

		try {
			
			 _agenteDeBusqueda = new SearchAgent(_problema, _busqueda);	
		}
		catch (Exception ex) {			
			ex.printStackTrace();
			//System.gc();
		}
	}
}
