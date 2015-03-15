package juegos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Vector;

import aima.search.framework.GoalTest;
import aima.search.framework.HeuristicFunction;
import aima.search.framework.Problem;
import aima.search.framework.Search;
import aima.search.framework.SearchAgent;
import aima.search.framework.SuccessorFunction;
import micromundo.Viaje;

//***************************************************************************//
/**
 * Clase que representa a un problema en general dentro del micromundo.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class GestorDeProblemas {
	
	// CONSTANTES
	private static final int TMAX = 500; // Tiempo máximo de espera para que resuelva un problema
	
	// VARIABLES
	private static SearchAgent _agent;
	private static long _tiempoEmpleado;
	
	// CACHE CON INFORMACION SOBRE LOS VIAJES
	private static Vector<Viaje> _tienenSolucion;
	
	private static Thread thread = null;
	
	
//	*************************************************************************//
	/**
	 * Constructor de la clase GestorDeProblemas.
	 * 
	 * Genera la lista con los viajes resolubles y los que no lo son.
	 */
	public static void generarInfoViajes(){		
		
		_tienenSolucion = new Vector<Viaje>();	
		
		System.out.println("Recopilando la información de los problemas...");
		long inicio = System.currentTimeMillis();
		
		int _resueltos = 0, _noResueltos = 0;
		
		for (int j = 1; j < 13; j++){
			for (int b = 0; b < 8; b++){							
				
				System.out.print(Conversor.tipoJuegoToDescripcion(Conversor.intToTipoJuego(j)) + " -> " + Conversor.tipoBusquedaToDescripcion(Conversor.intToTipoBusqueda(b)));				
				if (resolverProblema(Conversor.tipoJuegoToTablero(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToSucessorFunction(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToGoalTest(Conversor.intToTipoJuego(j)), 
						Conversor.tipoJuegoToHeuristicFunction(Conversor.intToTipoJuego(j)),
						Conversor.tipoBusquedaToSearch(Conversor.intToTipoBusqueda(b)))){				
					System.out.println(" -> SÍ.");
					_resueltos++;
					String salida = ""; 
					salida += "Número de pasos de la solución: "+ _agent.getInstrumentation().getProperty("pathCost") 
					          +"\nPasos de la solución:\n";
					
					// Mostrar acciones por consola
					for (int i = 0; i < _agent.getActions().size(); i++) {
						
						String action = (String) _agent.getActions().get(i);
						salida += " -> " + action + "\n";
					}
					
					salida += "Tiempo empleado: "
							+ _tiempoEmpleado + "ms."
							+ "\nNodos expandidos: "
							+ _agent.getInstrumentation().getProperty("nodesExpanded")
							+ "\n\n";
					
					_tienenSolucion.add(new Viaje(Conversor.intToTipoJuego(j), Conversor.intToTipoBusqueda(b), salida));	
				}
				else{
					_noResueltos++;
					System.out.println(" -> NO.");
				}
			}
			try {
			
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		System.out.println("Problemas resueltos:" + _resueltos + " No resueltos:" + _noResueltos);
		System.out.println("Información recopilada en " + (System.currentTimeMillis() - inicio) + " ms.");

	}
	
	// **********************************************************************//
	/**
	 * Guarda la información recopilada de los viajes en el fichero info_viajes.dat.
	 */
	public static void serializeInfoViajes(){
		
		FileOutputStream fout;
		
		try {
		
			fout = new FileOutputStream("info_viajes.dat");
		    ObjectOutputStream oos = new ObjectOutputStream(fout);			
			oos.writeObject(_tienenSolucion);	    
		    oos.close();			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// **********************************************************************//
	/**
	 * Carga la información de los viajes desde el fichero info_viajes.dat.
	 */
	@SuppressWarnings("unchecked")
	public static void unserializeInfoViajes(){

		FileInputStream fin;
		
		try {
			
			fin = new FileInputStream("info_viajes.dat");
		    ObjectInputStream ois = new ObjectInputStream(fin);
		    _tienenSolucion = (Vector<Viaje>) ois.readObject();
		    ois.close();			
		} 
		catch (FileNotFoundException e) {
			
			System.out.println("Archivo info_viajes.dat no encontrado.");
			System.exit(2);
		} 
		catch (StreamCorruptedException e){
		
			System.out.println("Archivo info_viajes.dat inválido.");
			System.exit(2);
		}
		catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}	
	
//	*************************************************************************//
	/**
	 * Devuelve cierto si el problema se puede resolver y falso en caso contrario.
	 * 
	 * @param t Objeto de tipo tablero.
	 * @param fs Función sucesor.
	 * @param gt Estado objetivo.
	 * @param h Heurística utilizada.
	 * @param b Método de búsqueda utilizada.
	 * 
	 * @return Verdadero si el problema tiene solución (_agentMicromundo != null) y falso
	 * en caso contrario.
	 */
	@SuppressWarnings("deprecation")
	public static boolean resolverProblema(Object t, SuccessorFunction fs, GoalTest gt, HeuristicFunction h, Search b){
		
		int tiempo = TMAX;
		_agent = null;
		
		try {			
			long inicio = System.currentTimeMillis();			
			Problem problem = new Problem(t, fs, gt, h);	
			ResolutorProblemas resolutorProblemas = new ResolutorProblemas(problem, b);						
			thread = new Thread(resolutorProblemas);
			 
			// Inicia el hilo y espera 100 milisegundos a que lo resuelva 
			thread.start();
			while (tiempo >= 0 && _agent == null) {
				
				tiempo -= 100;				
				thread.join(100);
			}			
			thread.stop();
			
			long fin = System.currentTimeMillis();
			_tiempoEmpleado = fin-inicio;
			_agent = resolutorProblemas.getSearchAgent();
		} 
		catch (Exception ex) {
			
			ex.printStackTrace();
		}
		
		if (_agent == null)
			return false;
		else if (_agent.getInstrumentation().getProperty("pathCost") == null)			
			return false;		
		else 
			return true;
	}
	
//	*************************************************************************//
	/**
	 * Devuelve Una cadena que contiene la solución a un problema.
	 * 
	 * @return Una cadena que contiene la solución a un problema.
	 */
	public static String getSolucion(){
		
		String salida = ""; 
		salida += "Número de pasos de la solución: "+ _agent.getInstrumentation().getProperty("pathCost") 
		          +"\nPasos de la solución:\n";
		
		// Mostrar acciones por consola
		for (int i = 0; i < _agent.getActions().size(); i++) {
			
			String action = (String) _agent.getActions().get(i);
			salida += " -> " + action + "\n";
		}
		
		salida += "Tiempo empleado: "
				+ _tiempoEmpleado + "ms."
				+ "\nNodos expandidos: "
				+ _agent.getInstrumentation().getProperty("nodesExpanded")
				+ "\n\n";
		
		return salida;
	}
	
//	*************************************************************************//
	/**
	 * Determina si el problema que representa el viaje a realizar entre dos
	 * planetas tiene solución o no.
	 * 
	 * @param v Viaje que representa el viaje a realizar entre un planeta 
	 * y otro.
	 * @param _usarCache boolean que permite elegir si debe utilizarse la cache o no. 
	 * y otro.
	 * 
	 * @return Verdadero si el problema tiene solución y falso en caso contrario.
	 */
	public static boolean tieneSolucion(Viaje v, boolean _usarCache){
		
		if (_usarCache)
			return _tienenSolucion.contains(v);
		else
			return resolverProblema(Conversor.tipoJuegoToTablero(v.getJuego()), 
					Conversor.tipoJuegoToSucessorFunction(v.getJuego()), 
					Conversor.tipoJuegoToGoalTest(v.getJuego()), 
					Conversor.tipoJuegoToHeuristicFunction(v.getJuego()),
					Conversor.tipoBusquedaToSearch(v.getBusqueda()));					
	}
	
	// **********************************************************************//
	/**
	 * Devuelve la lista de viajes que tienen solución.
	 * 
	 * @return La lista de viajes que tienen solución.
	 */
	public static Vector<Viaje> getTienenSolucion() {

		return _tienenSolucion;
	}

	// **********************************************************************//
	/**
	 * Devuelve el agente que ha resuelto el problema.
	 * 
	 * @return El agente que ha tiene la solución.
	 */
	public static SearchAgent get_agent() {
		return _agent;
	}
}