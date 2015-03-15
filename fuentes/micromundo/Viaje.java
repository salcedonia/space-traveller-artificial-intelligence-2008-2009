package micromundo;

import java.io.Serializable;

import juegos.TipoBusqueda;
import juegos.TipoJuego;

//***************************************************************************//	
/**
 * Clase que representa los viajes que se pueden hacer desde un planeta. A más
 * bajo nivel indica el problema _Y la estatregia de búsqueda que se presentarán
 * al querer desplazarse de un planeta a otro.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias, David Hernández Plaza
 */
public class Viaje implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2491117253750322002L;
	
	// ATRIBUTOS
	private TipoJuego _juego;	
	private TipoBusqueda _busqueda;
	private String _solucion = null;
	
	// **********************************************************************//
	/**
	 * Constructor de la clase Viaje. Establece por defecto el juego de las
	 * garrafas y el método de búsqueda asociado al de primero en anchura.
	 */
	public Viaje() {
		_juego = TipoJuego.LABERINTO;
		_busqueda = TipoBusqueda.PRIMERO_EN_PROFUNDIDAD;
	}

	// *************************************************************************//
	/**
	 * Constructor de la clase Viaje. Establece por el juego a valor j y el
	 * método de búsqueda asociado a valor b.
	 * 
	 * @param j
	 *            Tipo de juego a establecer.
	 * @param b
	 *            Tipo Búsqueda a establecer.
	 */
	public Viaje(TipoJuego j, TipoBusqueda b) {

		_juego = j;
		_busqueda = b;
		_solucion = null;
	}

	// *************************************************************************//
	/**
	 * Constructor de la clase Viaje. Establece por el juego a valor j y el
	 * método de búsqueda asociado a valor b.
	 * 
	 * @param j
	 *            Tipo de juego a establecer.
	 * @param b
	 *            Tipo Búsqueda a establecer.
	 */
	public Viaje(TipoJuego j, TipoBusqueda b, String solucion) {

		_juego = j;
		_busqueda = b;
		_solucion = solucion;
	}
	// *************************************************************************//
	/**
	 * Devuelve el tipo de juego asociado al Viaje.
	 * 
	 * @return El tipo de juego asociado al Viaje.
	 */
	public TipoJuego getJuego() {

		return _juego;
	}

	// *************************************************************************//
	/**
	 * Devuelve el tipo de búsqueda asociada al Viaje.
	 * 
	 * @return el tipo de búsqueda asociada al Viaje.
	 */
	public TipoBusqueda getBusqueda() {

		return _busqueda;
	}

	// *************************************************************************//
	/**
	 * Devuelve la solución asociada al problema con la búsqueda correspondiente
	 * si es que tiene.
	 */
	public String getSolucion(){
		
		return _solucion;
	}
	
	// *************************************************************************//
	/**
	 * Establece el tipo de juego y el tipo de búsqueda asociada a valores j y b
	 * respectivamente.
	 * 
	 * @param j
	 *            Tipo de juego asociado.
	 * @param b
	 *            Tipo de búsqueda asociada.
	 */
	public void set(TipoJuego j, TipoBusqueda b) {

		_juego = j;
		_busqueda = b;
	}

	// *************************************************************************//
	/**
	 * Transforma la clase Viaje a un String
	 * 
	 * @return El String correspondiente a la clase Viaje.
	 */
	public String toString(){
		
		String cadena = "Problema: " + _juego + 
						"\nAlgoritmo: " + _busqueda + "\n";
		
		return cadena;
	}
	
	// *************************************************************************//
	/**
	 * Compara si dos objetos son iguales.
	 * 
	 * @return Verdadero si los dos objetos son iguales y falso en caso
	 *         contrario.
	 */
	public boolean equals(Object o) {

		if (o == null) {
			return false;
		}

		if (o == this) {
			return true;
		}

		if (!(o instanceof Viaje)) {
			return false;
		}

		Viaje v = (Viaje) o;

		return  v.getJuego() == _juego 
				&& v.getBusqueda() == _busqueda;
	}	
}
