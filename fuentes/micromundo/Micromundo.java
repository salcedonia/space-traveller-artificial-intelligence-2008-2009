package micromundo;

import java.util.HashMap;

import javax.swing.JOptionPane;

import aima.search.framework.Problem;
import aima.search.framework.SearchAgent;

import juegos.GestorDeProblemas;
import juegos.Conversor;
import juegos.ResolutorProblemas;
import juegos.TipoBusqueda;
import juegos.TipoJuego;

import micromundo.interfaz.VentanaMicromundo;

//***************************************************************************//
/**
 * Clase que se encarga de mostrar la ventana de la aplicaci�n.
 * 
 * @author Javier Salcedo G�mez, Carlos Loredo Iglesias, David Hern�ndez Plaza
 */
public class Micromundo {

	// CONSTANTES
	public final static int _xInicial = Tablero._TAMANO/2;
	public final static int _yInicial = Tablero._TAMANO/2;
	
	// ATRIBUTOS
	private Tablero _escenario;
	private VentanaMicromundo _ventana;	
	
	private static HashMap<Integer, Boolean> _hashCiclos;
	private static boolean _controlCiclos = true;
	
	//USO DE LA CACHE
	//permite definir si queremos que se utilice la cache de soluciones al movernos por los problemas del micromundo
	private boolean _usarCache = true;
	
	//permite definir si queremos que el resolvedor recalcule la soluci�n en cada uno de nuestros pasos
	private boolean _resolvedor = false;

	private int _tiempoMAX = 10000;
	private int _tiempoRestante;

	// **********************************************************************//
	/**
	 * Devuelve verdadero si el control de ciclos est� activado y falso en 
	 * caso contrario.
	 * 
	 * @return Verdadero si el control de ciclos est� activado y falso en 
	 * caso contrario.
	 */
	public static boolean is_controlCiclos() {
		return _controlCiclos;
	}

	// **********************************************************************//
	/**
	 * Activa o desactiva el control de ciclos.
	 * 
	 * @param ciclos Activa o desactiva el control de ciclos.
	 */
	public static void set_controlCiclos(boolean ciclos) {
		_controlCiclos = ciclos;
	}

	// **********************************************************************//
	/**
	 * Devuelve la estructura Hash que se encarga de guardar el control 
	 * sobre los ciclos.
	 * 
	 * @return La estructura Hash que se encarga de guardar el control 
	 * sobre los ciclos.
	 */
	public static HashMap<Integer, Boolean> get_hashCiclos() {
		return _hashCiclos;
	}
	
	// **********************************************************************//
	/**
	 * Reinicia la estructura para el control de ciclos.
	 */
	public static void resetControlCiclos() {
		_hashCiclos = new HashMap<Integer, Boolean>();
	}	

	// **********************************************************************//
	/**
	 * Activa o desactiva el uso del resolvedor del micromundo.
	 * 
	 * @param Resolvedor Activa o desactiva el uso del resolvedor del micromundo.
	 */
	public void set_resolvedor(boolean resolvedor) {
		
		_resolvedor = resolvedor;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve verdadero si el resolvedor est� activado y falso en caso contrario.
	 * 
	 * @return Verdadero si el resolvedor est� activado y falso en caso contrario.
	 */
	public boolean is_resolvedor() {
	
		return _resolvedor;
	}
	
	// **********************************************************************//
	/**
	 * Activa o desactiva el uso de la cach�.
	 * 
	 * @param Activa o desactiva el uso de la cach�. 
	 */
	@SuppressWarnings("static-access")
	public void set_usarCache(boolean usarCache) {
		
		_escenario.set_usarCache(usarCache);
		_usarCache = usarCache;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve verdadero si el uso de la cach� est� activado y falso en
	 * caso contrario.
	 * 
	 * @return Verdadero si el uso de la cach� est� activado y falso en
	 * caso contrario.
	 */
	public boolean is_usarCache() {
		
		return _usarCache;
	}	

	// **********************************************************************//
	/**
	 * Establece el tiempo restante que tiene todav�a de margen para resolver
	 * el micromundo.
	 * 
	 * @param restante
	 */
	public void set_tiempoRestante(int restante) {
		
		_tiempoRestante = restante;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve el tiempo m�ximo para la resoluci�n del micromundo.
	 * 
	 * @return El tiempo m�ximo para la resoluci�n del micromundo.
	 */
	public int get_tiempoMAX() {
		
		return _tiempoMAX;
	}

	// **********************************************************************//
	/**
	 * Establece el tiempo m�ximo de espera para la resoluci�n del micromundo.
	 * Si tarda m�s de ese tiempo entonces el micromundo usando el algoritmo
	 * seleccionado en ese momento no tendr� soluci�n.
	 * 
	 * @param restante Tiempo m�ximo para la resoluci�n del problema.
	 */
	public void set_tiempoMAX(int _tiempomax) {

		_tiempoMAX = _tiempomax;
	}

	// **********************************************************************//
	/**
	 * Constructor por defecto. Genera la ventana principal.
	 */
	public Micromundo() {
		
		// Generamos la informacion relativa a los viajes
		GestorDeProblemas.unserializeInfoViajes();
		
		// Crea el tablero asociado al micromundo planetario
		_escenario = new Tablero(_xInicial, _yInicial);		
		
		// Creamos la ventana
		_ventana = new VentanaMicromundo(this);		
		_ventana.setVisible(true);
	}
	
	// **********************************************************************//
 	/**
	 * Regenera la informaci�n de los problemas usados en la pr�ctica.
	 */
	public void regenerarInfoProblemas(){
		
		GestorDeProblemas.generarInfoViajes();
		GestorDeProblemas.serializeInfoViajes();
		GestorDeProblemas.unserializeInfoViajes();
	}
	
	// **********************************************************************//
	/**
	 * Mueve la nave en la direcci�n correspondiente.
	 * 
	 * @param dir Direcci�n hacia la que se mueve la nave.
	 * 
	 * @param busqueda Para ver la soluci�n.
	 */
	public void moverNave(String dir, TipoBusqueda busqueda) {

		if(dir.equals("Arriba")){
			
			// Si ya estamos arriba del todo
			if (_escenario.getCoordenadaY() == 0)
				
				JOptionPane.showMessageDialog(_ventana,
						"Hacia arriba de nuestra posici�n no hay m�s planetas.",
					    "Fuera de l�mites",
					    JOptionPane.WARNING_MESSAGE);
			
			else {
				if (resolverProblema(_escenario.getViaje(_escenario.getCoordenadaX(), _escenario.getCoordenadaY()-1, 0)))
					_escenario.setCoordenadaY(_escenario.getCoordenadaY() - 1);		
			}
		}
		else if(dir.equals("Derecha")){
			
			// Si ya estamos a la derecha del todo
			if (_escenario.getCoordenadaX() == (Tablero._TAMANO - 1))
				
				JOptionPane.showMessageDialog(_ventana,
						"Hacia la derecha de nuestra posici�n no hay m�s planetas.",
					    "Fuera de l�mites",
					    JOptionPane.WARNING_MESSAGE);
			
			else 
				if (resolverProblema(_escenario.getViaje(_escenario.getCoordenadaX(), _escenario.getCoordenadaY(), 1)))
					_escenario.setCoordenadaX(_escenario.getCoordenadaX() + 1);
		}
		else if(dir.equals("Abajo")){
			
			// Si ya estamos abajo del todo
			if (_escenario.getCoordenadaY() == (Tablero._TAMANO - 1))
				
				JOptionPane.showMessageDialog(_ventana,
						"Hacia abajo de nuestra posici�n no hay m�s planetas.",
					    "Fuera de l�mites",
					    JOptionPane.WARNING_MESSAGE);
			
			else 
				if (resolverProblema(_escenario.getViaje(_escenario.getCoordenadaX(), _escenario.getCoordenadaY(), 0)))
					_escenario.setCoordenadaY(_escenario.getCoordenadaY() + 1);
		}	
		else if(dir.equals("Izquierda")){
			
			// Si ya estamos a la izquierda del todo
			if (_escenario.getCoordenadaX() == 0)
				
				JOptionPane.showMessageDialog(_ventana,
						"Hacia la izquierda de nuestra posici�n no hay m�s planetas.",
					    "Fuera de l�mites",
					    JOptionPane.WARNING_MESSAGE);
			
			else 
				if (resolverProblema(_escenario.getViaje(_escenario.getCoordenadaX()-1, _escenario.getCoordenadaY(), 1)))
					_escenario.setCoordenadaX(_escenario.getCoordenadaX() - 1);
		}
		
		String cadena = "----------------------------------------------------------------\n"
						+ "Nuestra posici�n es (X = " + _escenario.getCoordenadaX()
						+ ", Y = " + _escenario.getCoordenadaY() + ")\n"
						+ "----------------------------------------------------------------\n";

		_ventana.insertarTextoEnBitacora(cadena);
		
		// Repintamos el tablero del micromundo
		_ventana.repintar(_escenario.getCoordenadaX(), _escenario.getCoordenadaY());

		// Comprobamos si hemos llegado al estado objetivo
		if(_escenario.estaEnPlanetaObjetivo()){
			
			JOptionPane.showMessageDialog(_ventana,
					"Ha alcanzado el planeta objetivo.",
				    "Planeta Objetivo Alcanzado",
				    JOptionPane.INFORMATION_MESSAGE);
		}
		
		// Recalculamos la ruta
		if (_resolvedor)
			resolverMicromundo(busqueda);
	}
	
	// **********************************************************************//
	/**
	 * Resuelve el micromundo para que nos vaya aconsejando hacia adonde 
	 * movernos para encontrar la soluci�n. 
	 */
	@SuppressWarnings("deprecation")
	public void resolverMicromundo(TipoBusqueda tipoBusqueda) {
		
		if (_controlCiclos)
			resetControlCiclos();
		_tiempoRestante = _tiempoMAX;
		
		long inicio = System.currentTimeMillis();
	
		try {	
			Problem problem = new Problem(_escenario, new FuncionSucesor(), new EstadoFinal(), new FuncionCoste(), new FuncionHeuristica());
			ResolutorProblemas resolutorProblemas = new ResolutorProblemas(problem, Conversor.tipoBusquedaToSearch(tipoBusqueda));						
			Thread thread = new Thread(resolutorProblemas);
			 
			// Inicia el hilo y espera 1000 milisegundos a que lo resuelva 
			thread.start();
			while (_tiempoRestante >= 0 && resolutorProblemas.getSearchAgent() == null) {
					
				_tiempoRestante -= 500;				
				thread.join(500);
			}			
			thread.stop();	
				
			SearchAgent agent = resolutorProblemas.getSearchAgent();
				
			_ventana.insertarTextoEnSolucion("Problema: "
						+ Conversor.tipoJuegoToDescripcion(TipoJuego.MICROMUNDO) + "\n");
			_ventana.insertarTextoEnSolucion("M�todo de B�squeda: "
						+ Conversor.tipoBusquedaToDescripcion(tipoBusqueda) + "\n");
				
			// Si el problema tiene soluci�n la mostramos
			if(agent != null){

				String salida = ""; 
				salida += "N�mero de pasos de la soluci�n: "+ agent.getInstrumentation().getProperty("pathCost") 
			          +"\nPasos de la soluci�n:\n";
			
				// Mostrar acciones por consola
				for (int i = 0; i < agent.getActions().size(); i++) {
				
					String action = (String) agent.getActions().get(i);
					salida += " -> " + action + "\n";
				}
			
				salida 	+= "Tiempo empleado: "
						+ (System.currentTimeMillis() - inicio) + "ms."
						+ "\nNodos expandidos: "
						+ agent.getInstrumentation().getProperty("nodesExpanded")
						+ "\n\n";

				_ventana.insertarTextoEnSolucion(salida);
				if (agent.getActions().size()>0)
					_ventana.insertarTextoEnSolucion("Debes ir a :"+(String)agent.getActions().get(0) + "\n");
			}
			else {
				
				_ventana.insertarTextoEnSolucion("No se ha encontrado la soluci�n al problema.\n");
			}
		} catch (Exception ex) {
			
			ex.printStackTrace();
		}			
	}

	// **********************************************************************//
	/**
	 * Comprueba si el Viaje v se puede resolver o no.
	 * 
	 * @param v Viaje a comprobar.
	 * 
	 * @return Verdadero en caso de que el viaje se pueda resolver y falso 
	 * en caso contrario.
	 */
	public boolean resolverProblema(Viaje v) {
		
		_ventana.insertarTextoEnBitacora("Problema: "
				+ Conversor.tipoJuegoToDescripcion(v.getJuego()) + "\n");
		_ventana.insertarTextoEnBitacora("M�todo de B�squeda: "
				+ Conversor.tipoBusquedaToDescripcion(v.getBusqueda()) + "\n");
		
		// Si el problema tiene soluci�n la mostramos
		if(GestorDeProblemas.tieneSolucion(v, _usarCache)){
			
			if (_usarCache){
				Viaje viaje = GestorDeProblemas.getTienenSolucion().elementAt(GestorDeProblemas.getTienenSolucion().indexOf(v));
				_ventana.insertarTextoEnBitacora(viaje.getSolucion());
			}
			else{
				_ventana.insertarTextoEnBitacora(GestorDeProblemas.getSolucion());
			}
			
			return true;
		} 
		else {
			
			_ventana.insertarTextoEnBitacora("No se ha encontrado la soluci�n al problema.\n");
			return false;
		}
	}

	// **********************************************************************//
	/**
	 * Devuelve el escenario del micromundo.
	 * 
	 * @return El escenario del micromundo.
	 */
	public Tablero getEscenario() {
		
		return _escenario;
	}	
}