package juegos.garrafas;

//***************************************************************************//
/**
 * Clase que representa el contenido de las garrafas.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class Tablero {

	// ATRIBUTOS
	public static String llenarG4 = "Llenar la garrafa de 4 litros.";
	public static String llenarG3 = "Llenar la garrafa de 3 litros.";
	public static String vaciarG4 = "Vaciar la garrafa de 4 litros.";
	public static String vaciarG3 = "Vaciar la garrafa de 3 litros.";
	public static String transvasarG3aG4 = "Despositar el contenido de la garrafa de 3 litros en la garrafa de 4 litros.";
	public static String transvasarG4aG3 = "Despositar el contenido de la garrafa de 4 litros en la garrafa de 3 litros.";
	
	private int _garrafa4;
	private int _garrafa3;

	public static String[] _operadores = { llenarG4, llenarG3, 
										   vaciarG4,vaciarG3, 
										   transvasarG3aG4, transvasarG4aG3 };

	// *************************************************************************//
	/**
	 * Constructor por defecto, inicializa el problema con las 2 garrafas
	 * vacías.
	 */
	public Tablero() {

		_garrafa4 = 0;
		_garrafa3 = 0;
	}

	// *************************************************************************//
	/**
	 * Constructor que se utiliza en la clase EstadoFinal. Inicializa la garrafa
	 * de 4 litros a 2 litros. La otra nos da igual el contenido.
	 */
	public Tablero(int g4) {

		_garrafa4 = g4;
	}

	// *************************************************************************//
	/**
	 * Constructor por campos.
	 * 
	 * @param g4
	 *            Garrafa de 4 litros de capacidad (Puede contener 0, 1, 2, 3 o
	 *            4)
	 * @param g3
	 *            Garrafa de 3 litros de capacidad (Puede contener 0, 1, 2 o 3)
	 */
	public Tablero(int g4, int g3) {

		_garrafa4 = g4;
		_garrafa3 = g3;
	}

	// *************************************************************************//
	/**
	 * Devuelve el número de litros de la garrafa4.
	 * 
	 * @return El número de litros de la garrafa4.
	 */
	public int getGarrafa4() {

		return _garrafa4;
	}

	// ***************************************************************************//
	/**
	 * Devuelve el número de litros de la garrafa3.
	 * 
	 * @return El número de litros de la garrafa3.
	 */
	public int getGarrafa3() {

		return _garrafa3;
	}

	// ***************************************************************************//
	/**
	 * Comprueba si el movimiento es posible.
	 * 
	 * @param s
	 *            Movimiento a realizar.
	 */
	public boolean movimientoPosible(String s) {

		boolean b = false;

		if (s.equals(llenarG4) && (_garrafa4 < 4))
			b = true;
		else if (s.equals(llenarG3) && (_garrafa3 < 3))
			b = true;
		else if (s.equals(vaciarG4) && (_garrafa4 > 0))
			b = true;
		else if (s.equals(vaciarG3) && (_garrafa3 > 0))
			b = true;
		else if (s.equals(transvasarG3aG4) && (_garrafa4 < 4)
				&& (_garrafa3 > 0))
			b = true;
		else if (s.equals(transvasarG4aG3) && (_garrafa3 < 3)
				&& (_garrafa4 > 0))
			b = true;

		return b;
	}

	// *************************************************************************//
	/**
	 * Hace el movimiento indicado por el string s. Se da por hecho que se ha
	 * comprobado que se puede hacer.
	 * 
	 * @param s 
	 * 			Movimiento a realizar.
	 */
	public void mover(String s) {

		if (s.equals(llenarG4)) {
			_garrafa4 = 4;
		} 
		else if (s.equals(llenarG3)) {
			_garrafa3 = 3;
		} 
		else if (s.equals(vaciarG4)) {
			_garrafa4 = 0;
		} 
		else if (s.equals(vaciarG3)) {
			_garrafa3 = 0;
		}
		else if (s.equals(transvasarG3aG4)) {
			int _garrafa4p = Math.min(4, _garrafa4 + _garrafa3);
			_garrafa3 = _garrafa3 - (_garrafa4p - _garrafa4);
			_garrafa4 = _garrafa4p;
		}
		else if (s.equals(transvasarG4aG3)) {
			int _garrafa3p = Math.min(3, _garrafa3 + _garrafa4);
			_garrafa4 = _garrafa4 - (_garrafa3p - _garrafa3);
			_garrafa3 = _garrafa3p;
		}
	}

	// **********************************************************************//
	/**
	 * Convierte el tablero a String.
	 * 
	 * @return El String correspondiente a la clase Tablero.
	 */
	@Override
	public String toString() {
		
		String retVal = "Garrafa4:" + String.valueOf(_garrafa4) + 
						" Garrafa3:" + String.valueOf(_garrafa3) + "\n";
		return retVal;		
	}	
	
	// *************************************************************************//
	/**
	 * Método que comprueba si dos objetos son iguales.
	 */
	@Override
	public boolean equals(Object o) {

		if (this == o)
			return true;
		
		if ((o == null) || (this.getClass() != o.getClass())) 
			return false;

		Tablero aContenido = (Tablero) o;

		return _garrafa4 == aContenido.getGarrafa4() && _garrafa3 == aContenido.getGarrafa3();
	}
}
