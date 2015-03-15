package juegos.mono;

//***************************************************************************//
/**
 * 
 * Hay un mono en la puerta de una habitación. En el centro de la habitación hay
 * un plátano colgado del techo. El mono está hambriento y quiere conseguir el
 * plátano pero no alcanza porque está muy alto. En la habitación también hay
 * una ventana y debajo de ella hay una caja que le permitiría alcanzar el
 * plátano si se subiera a ella. El mono puede realizar las siguientes acciones:
 * andar por el suelo, subirse a la caja, empujar la caja (si el mono está en la
 * misma posición que la caja pero no subido a ella) y coger el plátano (si está subido encima de la
 * caja y la caja está justo debajo del plátano).<br>
 * \t0: el mono/caja se encuentran en la puerta<br>
 * \t1: el mono/caja se encuentran en la centro<br>
 * \t2: el mono/caja se encuentran en el ventana
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero{

	// ATRIBUTOS
	public static String _andaHaciaVentana  = "El mono va hacia la ventana.";
	public static String _andaHaciaPuerta = "El mono va hacia la puerta.";
	public static String _empujaCajaHaciaVentana  = "El mono empueja la caja hacia la ventana.";
	public static String _empujaCajaHaciaPuerta = "El mono empuja la caja hacia la puerta.";
	public static String _subeCaja = "El mono se sube a la caja.";
	public static String _bajaCaja = "El mono se sube a la caja.";
	public static String _cogePlatano = "El mono coge el platano.";
	
	
	public static String[] _operadores = {_andaHaciaVentana, _andaHaciaPuerta, 
										  _empujaCajaHaciaVentana, _empujaCajaHaciaPuerta, 
										  _subeCaja, _bajaCaja,
										  _cogePlatano};	
	
	/* Las posibles posiciones son:
	 * 0 : en la puerta
	 * 1 : en el centro
	 * 2 : en la ventana 
	 */
	private int _pos; //Posicion del mono
	private int _caja; //Posicion de la caja
	private boolean _sobreCaja; //Si esta sobre la caja
	private boolean _tienePlatano; //Si ha cogido el plátano



	/**
	 * Constructor por defecto, inicializa el problema con el mono en la puerta y la caja en la ventana.
	 * vacías.
	 */
	public Tablero() {
		_pos = 0;
		_sobreCaja = false;
		_caja = 2;
		_tienePlatano = false;
	}
	
	/**
	 * Constructor por copia.
	 * 
	 * @param tablero Estado del tablero.
	 */
	public Tablero(Tablero tablero) {
		
		_pos = tablero.damePos();
		_sobreCaja = tablero.dameSubido();
		_caja = tablero.damePosCaja();
		_tienePlatano = tablero.damePlatano();
	}

	public int damePos() {
		return _pos;
	}

	public boolean dameSubido() {
		return _sobreCaja;
	}

	public int damePosCaja() {
		return _caja;
	}

	public boolean damePlatano() {
		return _tienePlatano;
	}
	
	// **********************************************************************//
	/**
	 * Comprueba si se puede realizar un movimiento.
	 * 
	 * @param s Movimiento a realizar.
	 * 
	 * @return Verdadero si el movimiento se puede realizar y falso en caso
	 * contrario.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean b = false;

		if (s.equals(_andaHaciaVentana)) 			 b = _pos!=2 && !_sobreCaja;
		else if (s.equals(_andaHaciaPuerta)) 		 b = _pos!=0 && !_sobreCaja;
		else if (s.equals(_empujaCajaHaciaVentana))  b = _pos!=2 && _pos==_caja && !_sobreCaja; 
		else if (s.equals(_empujaCajaHaciaPuerta)) 	 b = _pos!=0 && _pos==_caja && !_sobreCaja; 
		else if (s.equals(_subeCaja))				 b = _pos==_caja && !_sobreCaja;
		else if (s.equals(_bajaCaja)) 				 b = _sobreCaja;
		else if (s.equals(_cogePlatano)) 			 b = _pos==1 && _sobreCaja;
		
		return b;
	}		
	
	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s el movimiento a hacer.
	 */
	public void mover(String s) {
		
		if (s.equals(_andaHaciaVentana)) 			 _pos++;
		else if (s.equals(_andaHaciaPuerta)) 		 _pos--;
		else if (s.equals(_empujaCajaHaciaVentana)) {_pos++; _caja++; }
		else if (s.equals(_empujaCajaHaciaPuerta))  {_pos--; _caja--; }
		else if (s.equals(_subeCaja))				 _sobreCaja=true;
		else if (s.equals(_bajaCaja)) 				 _sobreCaja=false;
		else if (s.equals(_cogePlatano)) 			 _tienePlatano=true;
	}
	
	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "Mono:" + String.valueOf(_pos) + 
						" Caja:" + String.valueOf(_caja) +
						" Subido:" + String.valueOf(_sobreCaja) + 
						" Planato:" + String.valueOf(_tienePlatano) + "\n";
		return retVal;		
	}	

	// **********************************************************************//
	/**
	 * Compara si dos objetos son iguales o no.
	 * 
	 * @param o Objeto a comparar.
	 * 
	 * @return Verdadero si los dos objetos son iguales.
	 */
	@Override
	public boolean equals(Object o) {
		
		if (this == o)
			return true;
		
		if ((o == null) || (this.getClass() != o.getClass()))
			return false;
		
		Tablero t = (Tablero) o;

		if (_pos			!= t.damePos())		return false;  
		if (_caja			!= t.damePosCaja())	return false;  
		if (_sobreCaja		!= t.dameSubido())	return false;  
		if (_tienePlatano	!= t.damePlatano())	return false;  

		return true;
	}

}