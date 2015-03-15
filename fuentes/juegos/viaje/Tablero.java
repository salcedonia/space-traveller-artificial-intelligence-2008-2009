package juegos.viaje;

//***************************************************************************//
/**
 * Clase que representa los diferentes movimientos dentro del viaje.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {
	
	// ATRIBUTOS
	public static String IR_AL = "Ir a Almería.";
	public static String IR_CA = "Ir a Cádiz.";
	public static String IR_CO = "Ir a Córdoba.";
	public static String IR_GR = "Ir a Granada.";
	public static String IR_HU = "Ir a Huelva.";
	public static String IR_JA = "Ir a Jaen.";
	public static String IR_MA = "Ir a Málaga.";
	public static String IR_SE = "Ir a Sevilla.";
	
	private String _situacion;
		
	public static String[] _operadores = {IR_AL, IR_CA, IR_CO, IR_GR, IR_HU, IR_JA, IR_MA, IR_SE};
	
	// **********************************************************************//
	/**
	 * Constructor por defecto, inicializa el problema:
	 * la situación del inicio del problema es Sevilla
	 */
	public Tablero() {
		
		_situacion = "SEVILLA";
	}
	
	// **********************************************************************//
	/**
	 * Constructor que se utiliza en la clase EstadoFinal
	 */
	public Tablero(String sit) {
		
		_situacion = sit;
		if (sit.equals("")) _situacion = "SEVILLA";
	}

	// **********************************************************************//
	/**
	 * Devuelve la situación.
	 * 
	 * @return La situación.
	 */
	public String getSituacion() {
		
		return _situacion;
	}

	// **********************************************************************//
	/**
	 * Comprueba si se puede realizar un movimiento.
	 * Sólo nos podemos mover hacia una ciudad vecina de la que nos encontremos. 
	 * 
	 * @param s Movimiento a realizar.
	 * 
	 * @return Verdadero si se puede realizar y falso en caso contrario.
	 */
	public boolean movimientoPosible(String s) {
		
		boolean b = false;

		if 		(s.equals(IR_AL) && (_situacion.equals("GRANADA") || _situacion.equals("JAEN"))) 	  b = true;
		else if (s.equals(IR_CA) && (_situacion.equals("HUELVA") || _situacion.equals("MALAGA")
							     ||  _situacion.equals("SEVILLA"))) 								  b = true;
		else if (s.equals(IR_CO) && (_situacion.equals("GRANADA") || _situacion.equals("JAEN")
								 ||	 _situacion.equals("MALAGA") || _situacion.equals("SEVILLA")))  b = true;		
		else if (s.equals(IR_GR) && (_situacion.equals("ALMERIA") || _situacion.equals("CORDOBA")
								 ||  _situacion.equals("JAEN") || _situacion.equals("MALAGA")))     b = true;
		else if (s.equals(IR_HU) && (_situacion.equals("CADIZ") || _situacion.equals("SEVILLA")))   b = true;
		else if	(s.equals(IR_JA) && (_situacion.equals("ALMERIA") || _situacion.equals("CORDOBA")
								 ||  _situacion.equals("GRANADA")))                                b = true;
		else if (s.equals(IR_MA) && (_situacion.equals("CADIZ") || _situacion.equals("CORDOBA")
								 ||  _situacion.equals("GRANADA") || _situacion.equals("SEVILLA"))) b = true;
		else if (s.equals(IR_SE) && (_situacion.equals("CADIZ") || _situacion.equals("CORDOBA")
								 ||  _situacion.equals("HUELVA") || _situacion.equals("MALAGA")))   b = true;
		
		return b;
	}
	
	// **********************************************************************//
	/**
	 * Hace el movimiento indicado por el string s.
	 * Se da por hecho que se ha comprobado que se puede hacer.
	 * 
	 * @param s el movimiento a hacer
	 */
	public void mover(String s) {
		
		if      (s.equals(IR_AL)) 	{ _situacion = "ALMERIA"; }
		else if (s.equals(IR_CA))   { _situacion = "CADIZ"; }
		else if (s.equals(IR_CO))   { _situacion = "CORDOBA"; }
		else if (s.equals(IR_GR))   { _situacion = "GRANADA"; }
		else if (s.equals(IR_HU)) 	{ _situacion = "HUELVA"; }
		else if (s.equals(IR_JA)) 	{ _situacion = "JAEN"; }
		else if (s.equals(IR_MA))  	{ _situacion = "MALAGA"; }
		else if (s.equals(IR_SE))  	{ _situacion = "SEVILLA"; }		
	}
	
	// **********************************************************************//
	/**
	 * Convierte la clase Tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero
	 */
	@Override
	public String toString() {
		return _situacion;
	}		
	
	// **********************************************************************//
	/**
	 * Comprueba si dos objetos son iguales.
	 * 
	 * @param o Objeto a comparar.
	 * 
	 * @return Verdadero si son iguales y falso en caso contrario.
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

		if (!_situacion.equals(t.getSituacion()))	return false;  
				
		return true;
	}
}