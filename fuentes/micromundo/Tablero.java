package micromundo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import juegos.Conversor;
import juegos.GestorDeProblemas;

//***************************************************************************//
/**
 * Esta clase representa el micromundo. Consiste en un tablero 15x15 en donde
 * cada casilla representa un planeta determinado por las coordenadas X e Y y 
 * cada planeta tiene 4 posibles direcciones (arriba, derecha, abajo, izquierda)
 * en las que se encontrará un problema a resolver con una función de búsqueda
 * representado con la clase Viaje.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// CONSTANTES
	public static final int _TAMANO = 15;
	public static final int _DIRECCIONES = 2;
	
	// ATRIBUTOS
	public static String ARRIBA = "Arriba";
	public static String DERECHA = "Derecha";
	public static String ABAJO = "Abajo";
	public static String IZQUIERDA = "Izquierda";
	
	//GENERACIÓN DE MICROMUNDOS ALEATORIOS
	//probabilidades de las diferentes direcciones al generar el micromundo
	private static double _probD0, _probD1, _probD2, _probD3;

	//USO DE LA CACHE
	//permite definir si queremos que se utilice la cache de soluciones al buscar la solución del micromundo
	private static boolean _usarCache = true;

	public static void set_usarCache(boolean usarCache) {
		_usarCache = usarCache;
	}
	public static boolean is_usarCache() {
		return _usarCache;
	}		
	
	public static String[] _operadores = new String[] { ARRIBA, DERECHA, ABAJO, IZQUIERDA };

	/**
	 * Las dos primeras componentes indican el planeta en el que estamos 
	 * repostando. La última componente indica el problema y algoritmo de búsqueda
	 * en caso de tomar la dirección: 0: arriba, 1: derecha, 2: abajo, 3: izquierda
	 */
	private Viaje[][][] _micromundo; 
	
	public Viaje[][][] get_micromundo() {
		return _micromundo;
	}
	
	
	/**
	 * Indica la dificultad máxima que los problemas que habrá que superar para llegar a la solucón tendrán.
	 * La utilidad de esto es para que los algoritmos tarden menos al resolver la búsqueda global del micromundo.
	 */
	private static int _dificultadMaxima = 12;
	
	public static int get_dificultadMaxima() {
		return _dificultadMaxima;
	}

	public static void set_dificultadMaxima(int maxima) {
		_dificultadMaxima = maxima;
	}

	// COORDENADAS
	private int _coordenadaY; //(0-14)
	private int _coordenadaX; //(0-14)
	
	// **********************************************************************//
	/**
	 * Constructor por defecto. Inicializa el micromundo.
	 * 
	 * @param x Coordenada x del planeta inicial.
	 * @param y Coordenada y del planeta inicial.
	 */
	public Tablero(int x, int y) {

		_micromundo = new Viaje[_TAMANO][_TAMANO][_DIRECCIONES];
		
		// Llenamos el tablero con un juego que no se puede resolver
		for (int columna = 0; columna < _micromundo.length; columna++) {
			for (int fila = 0; fila < _micromundo[columna].length; fila++) {
				for (int direccion = 0; direccion < _DIRECCIONES; direccion++)
					_micromundo[columna][fila][direccion] = new Viaje();
			}
		}
		
		_coordenadaX = x;
		_coordenadaY = y;
				
	}
	
	// **********************************************************************//
	/**
	 * Genera todos los viajes del micromundo con problemas irresolubles
	 */
	public void generaTableroAleatorio(){
				
		int juego, busqueda;
		
		// Lo llenamos 
		for (int columna = 0; columna < _micromundo.length; columna++) {
			for (int fila = 0; fila < _micromundo[columna].length; fila++) {
				
				// Generamos sólo abajo y la derecha, así tendremos la siguiente
				// generada la izquierda y arriba automáticamente
				for (int direccion = 0; direccion <= 1; direccion++) {
					
					// Calculamos un juego aleatorio sin solución
					do{
						juego = new Double(Math.random() * 12).intValue()+1;
						busqueda = new Double(Math.random() * 8).intValue();
					}while(GestorDeProblemas.tieneSolucion(new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)), true));
				
					// Asignamos el valor correspondiente a la derecha y abajo
					_micromundo[columna][fila][direccion] = new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda));
 					
				}
			}
		}	
		
		// Y le asignamos un camino aleatorio a cada solución
		_probD0 = 1.0; _probD1 = 1.0; _probD2 = 0; _probD3 = 0;
		generaCaminoAleatorio();
		_probD0 = 1.0; _probD1 = 0; _probD2 = 0; _probD3 = 1.0;
		generaCaminoAleatorio();	
		_probD0 = 0; _probD1 = 1.0; _probD2 = 1.0; _probD3 = 0;
		generaCaminoAleatorio();	
		_probD0 = 0; _probD1 = 0; _probD2 = 1.0; _probD3 = 1.0;
		generaCaminoAleatorio();			
		//mostrarMatriz();
	}

	// **********************************************************************//
	/**
	 * Muestra por consola el micromundo creado
	 */
	private void mostrarMatriz(){
		String r = "";
		for (int columna = 0; columna < _micromundo.length; columna++) 
			for (int fila = 0; fila < _micromundo[columna].length; fila++)
				for (int direccion = 0; direccion < _DIRECCIONES; direccion++) 
					r = r + "Columna: " + columna + 
							"; Fila: " + fila +
							"; Direccion: " + direccion +
							"; Viaje: \n" + _micromundo[columna][fila][direccion] + "\n";
		System.out.println(r);
	}	
	
	// **********************************************************************//
	/**
	 * Comprueba que un planeta es adecuado para incluirlo en el camino aleatorio
	 */
	private boolean planetaValido(Localizacion loc, int d){
		Localizacion l = (Localizacion)loc.clone();
		l.avanza(d);
		int x=l.getX();
		int y=l.getY();
		
	    //si la localizacion se ha salido del mapa
		if ( (x<0) || (x>=_TAMANO) || (y<0) || (y>=_TAMANO) )
	    	return false;
    	//si en ese planeta ya estaba conectado
		else {	            	        			
        	if (GestorDeProblemas.tieneSolucion(_micromundo[x][y][0], true) ||
        		GestorDeProblemas.tieneSolucion(_micromundo[x][y][1], true) ||
        		(y>0) && GestorDeProblemas.tieneSolucion(_micromundo[x][y-1][0], true) ||
        		(x>0) && GestorDeProblemas.tieneSolucion(_micromundo[x-1][y][1], true))        	        		    	
        		return false;
	        //solo queda por comprobar los planetas adyacentes
	        else {
	         	//contendrá el número de planetas adyacentes a los que se puede viajar
	        	int c=0; 
	        	
	        	if ((x-1>=0) 	&& (GestorDeProblemas.tieneSolucion(_micromundo[x-1][y][1], true))) c++;
	        	if ((x+1<_TAMANO) && (GestorDeProblemas.tieneSolucion(_micromundo[x][y][1], true))) c++;
	        	if ((y-1>=0) 	&& (GestorDeProblemas.tieneSolucion(_micromundo[x][y-1][0], true))) c++;
	        	if ((y+1<_TAMANO)  && (GestorDeProblemas.tieneSolucion(_micromundo[x][y][0], true))) c++;
	         		
	         	if (c<=1)
	         		return true;
	         	else
	         		return false;
	        }
	    }

	}

	// **********************************************************************//
	/**
	 * Genera un camino aleatorio con problemas resolubles
	 */
	public void generaCaminoAleatorio(){
	
		int juego, busqueda;
		double _prob;
		Stack<Localizacion> pila = new Stack<Localizacion>();			
		Localizacion l = new Localizacion(_coordenadaX,_coordenadaY);
		pila.push(l);
		
		ArrayList<Integer> d = new ArrayList<Integer>();
		
		while (!pila.empty()){
			l = pila.lastElement();			
			d.clear();
			
			_prob = new Double(Math.random());
			if (_probD0>=_prob)
				if (planetaValido(l,0))	d.add(0);
			_prob = new Double(Math.random());
			if (_probD1>=_prob)
				if (planetaValido(l,1))	d.add(1);
			_prob = new Double(Math.random());
			if (_probD2>=_prob)
				if (planetaValido(l,2))d.add(2);
			_prob = new Double(Math.random());
			if (_probD3>=_prob)
				if (planetaValido(l,3))	d.add(3);
			
			/*if (planetaValido(l,0))	d.add(0);
			if (planetaValido(l,1))	d.add(1);
			if (planetaValido(l,2))d.add(2);
			if (planetaValido(l,3))	d.add(3);*/		
	        
	        if (d.size()>0){
	        	int i = (int)(Math.random()*d.size());	        	
	        	Localizacion l2 = (Localizacion)l.clone();
	        	l2.avanza(d.get(i));	       
	        	pila.push(l2);	        	
				
	        	// Calculamos un juego aleatorio con solución
				do{
					juego = new Double(Math.random() * _dificultadMaxima).intValue()+1;
					busqueda = new Double(Math.random() * 8).intValue();
				}while(!GestorDeProblemas.tieneSolucion(new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)), true));
				
	        	//ahora colocamos el viaje en el planeta en función de la dirección
	        	switch (d.get(i)) {				
					case 0: _micromundo[l.getX()][l.getY()-1][0]= new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)); break;
					case 1: _micromundo[l.getX()][l.getY()][1]= new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)); break;
					case 2: _micromundo[l.getX()][l.getY()][0]= new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)); break;
					case 3: _micromundo[l.getX()-1][l.getY()][1]= new Viaje(Conversor.intToTipoJuego(juego), Conversor.intToTipoBusqueda(busqueda)); break;
	        	}				        		        
	        }
	        else	        	
	        	break;
	        	//si queremos permitir caminos alternativos sin salida a parte de la solución, descomentamos esta linea
	        	//pila.pop();
			
		}
		
	}	
	
	// **********************************************************************//
	/**
	 * Constructor por copia.
	 *
	 * @param micromundo Micromundo.
	 * @param x Coordenada X.
	 * @param y Coordenada Y.
	 */
	public Tablero(Viaje[][][] micromundo, int x, int y) {
		
		_micromundo = micromundo;
		_coordenadaX = x;
		_coordenadaY = y;
	}
	
	// **********************************************************************//
	/**
	 * Constructor por copia.
	 *
	 * @param micromundo Micromundo a copiar.
	 */
	public Tablero(Tablero micromundo) {
		
		_micromundo = micromundo._micromundo;
		_coordenadaX = micromundo._coordenadaX;
		_coordenadaY = micromundo._coordenadaY;
	}
	
	// **********************************************************************//
	/**
	 * Devuelve un viaje entre dos planetas determinados.
	 * 
	 * @param x Coordenada X.
	 * @param y Coordenada Y.
	 * @param direccion Dirección hacia la que hay que viajar desde el planeta(x,y).
	 * 
	 * @return El viaje asociado a las coordenadas correspondientes.
	 */
	public Viaje getViaje(int x, int y, int direccion) {
		
		return _micromundo[x][y][direccion];
	}

	// **********************************************************************//
	/**
	 * Comprueba si M es un movimiento posible
	 * 
	 * @param direccion Dirección hacia la que nos movemos.
	 */
	public boolean movimientoPosible(String direccion) {
		
		Viaje v = null;
		
		boolean enRango = false;
		
		if (direccion.equals("Arriba"))
			enRango = (_coordenadaY != 0);
		else if (direccion.equals("Derecha"))
			enRango = (_coordenadaX != _TAMANO - 1);
		else if (direccion.equals("Abajo"))
			enRango = (_coordenadaY != _TAMANO - 1);
		else if (direccion.equals("Izquierda"))
			enRango = (_coordenadaX != 0);
		
		// Si no nos salimos
		if (enRango) {
			
			// Obtenemos el problema con su búsqueda en la direccion adecuada
			if (direccion.matches("Arriba"))
				v = getViaje(_coordenadaX, _coordenadaY-1, 0);
			else if (direccion.matches("Derecha"))
				v = getViaje(_coordenadaX, _coordenadaY, 1);
			else if (direccion.matches("Abajo"))
				v = getViaje(_coordenadaX, _coordenadaY, 0);
			else if (direccion.matches("Izquierda"))
				v = getViaje(_coordenadaX-1, _coordenadaY, 1);
			
			// Vemos si nos podemos desplazar dependiendo del juego			
				return GestorDeProblemas.tieneSolucion(v, _usarCache);			
		}
		
		return false;
	}

	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s. Se da por hecho que se ha
	 * comprobado que se puede hacer.
	 * 
	 * @param s
	 *            el movimiento a hacer
	 */
	public void mover(String s) {
		
		if (s.equals(IZQUIERDA))
			_coordenadaX--;
		else if (s.equals(DERECHA))
			_coordenadaX++;
		else if (s.equals(ABAJO))
			_coordenadaY++;
		else if (s.equals(ARRIBA))
			_coordenadaY--;
	}

	// **********************************************************************//
	/**
	 * Vuelve a colocar la posición inicial.
	 */
	public void reiniciarPosicion() {
		
		// Posición central
		_coordenadaX = _TAMANO/2;
		_coordenadaY = _TAMANO/2;
	}

	// **********************************************************************//
	/**
	 * Comprueba si la nave está en un planeta objetivo.
	 * 
	 * @return Verdadero si está en un estado objetivo y falso en caso contrario.
	 */
	public boolean estaEnPlanetaObjetivo(){
		
		return 	(_coordenadaX == 0 && _coordenadaY == 0) || 
				((_coordenadaX == Tablero._TAMANO - 1) && _coordenadaY == 0) || 
				(_coordenadaX == 0 && _coordenadaY == (Tablero._TAMANO - 1)) || 
				((_coordenadaX == Tablero._TAMANO - 1) && (_coordenadaY == Tablero._TAMANO - 1)); 
	}

	// **********************************************************************//
	/**
	 * Guarda el escenario generado de forma aleatoria en un fichero XML
	 * en la ruta indicada.
	 * 
	 * @param ruta Ruta completa del fichero XML donde se guardará el escenario
	 * generado aleatoriamente.
	 */
	public void guardarEscenarioEnFichero(String ruta) {

		try {
			
			FileWriter write = new FileWriter(ruta);
			PrintWriter text = new PrintWriter(write);
			text.println("<micromundo>");
			text.println("<ancho>"+_TAMANO+"</ancho><alto>"+_TAMANO+"</alto>");
			
			for (int columna = 0; columna < _TAMANO; columna++) {
				for (int fila = 0; fila < _TAMANO; fila++) {
						
					text.println("	<planeta>");					
					text.println("  	<x>" + columna + "</x><y>" + fila + "</y>");
					text.println("      	<juegoArriba>"
							+ Conversor.tipoJuegoToInt(_micromundo[columna][fila][0]
									.getJuego()) + "</juegoArriba>");
					text.println("        	<busquedaArriba>"
							+ Conversor.tipoBusquedaToInt(_micromundo[columna][fila][0]
									.getBusqueda()) + "</busquedaArriba>");
					text.println("        	<juegoDerecha>"
							+ Conversor.tipoJuegoToInt(_micromundo[columna][fila][1]
									.getJuego()) + "</juegoDerecha>");
					text.println("        	<busquedaDerecha>"
							+ Conversor.tipoBusquedaToInt(_micromundo[columna][fila][1]
									.getBusqueda()) + "</busquedaDerecha>");					
					text.println("	</planeta>");
				}
			}
			
			text.println("</micromundo>");
			text.flush();
			write.close();
		} 
		catch (IOException e) {
		
			e.printStackTrace();
		}
	}

	// **********************************************************************//
	/**
	 * Carga un escenario desde un fichero XML.
	 * 
	 * @param ruta
	 *            Ruta donde se encuentra el fichero que contiene los datos del
	 *            escenario.
	 */
	public void cargarEscenarioDeFichero(String ruta) {

		int x, y, j0, b0, j1, b1;
		String r = "";

		try {

			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(ruta));

			doc.getDocumentElement().normalize();

			NodeList planetas = doc.getElementsByTagName("planeta");

			for (int s = 0; s < planetas.getLength(); s++) {
				
				Node planeta = planetas.item(s);
				Element planetaElement = (Element) planeta;
				
				// COORDENADA X
				NodeList data = planetaElement.getElementsByTagName("x");
				Element dataElement = (Element) data.item(0);
				NodeList textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				x = Integer.parseInt(r);
				
				// COORDENADA Y
				data = planetaElement.getElementsByTagName("y");
				dataElement = (Element) data.item(0);
				textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				y = Integer.parseInt(r);
				
				// 
				data = planetaElement.getElementsByTagName("juegoArriba");
				dataElement = (Element) data.item(0);
				textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				j0 = Integer.parseInt(r);
				
				data = planetaElement.getElementsByTagName("busquedaArriba");
				dataElement = (Element) data.item(0);
				textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				b0 = Integer.parseInt(r);
				
				data = planetaElement.getElementsByTagName("juegoDerecha");
				dataElement = (Element) data.item(0);
				textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				j1 = Integer.parseInt(r);
				
				data = planetaElement.getElementsByTagName("busquedaDerecha");
				dataElement = (Element) data.item(0);
				textDataList = dataElement.getChildNodes();
				r = ((Node) textDataList.item(0)).getNodeValue().trim();
				b1 = Integer.parseInt(r);
								
				
				_micromundo[x][y][0].set(Conversor.intToTipoJuego(j0), Conversor
						.intToTipoBusqueda(b0));
				_micromundo[x][y][1].set(Conversor.intToTipoJuego(j1), Conversor
						.intToTipoBusqueda(b1));	
			}

		} 
		catch (IOException e) {

			System.out.println("No se ha encontrado el archivo " + ruta);
			System.out.println("Se utilizará configuración por defecto.");
		} 
		
		catch (SAXParseException err) {

			System.out.println("** Error en el archivo XML: " + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());
		} 
		
		catch (SAXException e) {}

		catch (Throwable t) {
			
			t.printStackTrace();
		}
	}

	// **********************************************************************//
	/**
	 * Devuelve la coordenadaX donde está situada la nave.
	 * 
	 * @return La coordenadaX donde está situada la nave.
	 */
	public int getCoordenadaX() {
		
		return _coordenadaX;
	}

	// **********************************************************************//
	/**
	 * Establece la coordenadaX de la nave a valor coordenadaX
	 * 
	 * @param coordenadaX El nuevo valor a establecer.
	 */
	public void setCoordenadaX(int coordenadaX) {
	
		_coordenadaX = coordenadaX;
	}

	// **********************************************************************//
	/**
	 * Devuelve la coordenadaY de la nave.
	 * 
	 * @return La coordenadaY de la nave.
	 */
	public int getCoordenadaY() {
		
		return _coordenadaY;
	}

	// **********************************************************************//
	/**
	 * Establece la coordenadaY de la nave a valor coordenadaY.
	 * 
	 * @param coordenadaY El nuevo valor a establecer.
	 */
	public void setCoordenadaY(int coordenaday) {
	
		_coordenadaY = coordenaday;
	}

	// **********************************************************************//
	/**
	 * Devuelve el tablero del micromundo.
	 * 
	 * @return El tablero del micromundo.
	 */
	public Viaje[][][] getMicromundo() {
	
		return _micromundo;
	}

	// **********************************************************************//
	/**
	 * Establece un nuevo tablero.
	 * 
	 * @param micromundo Nuevo valor a establecer.
	 */
	public void setMicromundo(Viaje[][][] micromundo) {
	
		_micromundo = micromundo;
	}
	
	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "X:" + _coordenadaX + " Y:" + _coordenadaY + "\n";		
		return retVal;		
	}
	
	/**********************************************************************/
	/**
	 * Comprueba si dos objetos son iguales o no.
	 * 
	 * @param o Objeto a comparar.
	 * 
	 * @return Verdadero si los dos objetos son iguales y falso en caso contrario.
	 */
	@Override
	public boolean equals(Object o) {
	
		if (this == o) {
			return true;
		}
		
		if ((o == null) || (this.getClass() != o.getClass())) {
			return false;
		}
		
		Tablero t = (Tablero) o;
		
		return (_coordenadaX == t._coordenadaX && _coordenadaY == t._coordenadaY);
						
	}
}