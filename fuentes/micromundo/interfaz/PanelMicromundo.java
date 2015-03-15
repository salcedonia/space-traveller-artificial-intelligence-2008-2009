package micromundo.interfaz;

import java.awt.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import juegos.GestorDeProblemas;

import micromundo.Micromundo;
import micromundo.Tablero;

//***************************************************************************//
/**
 * Panel donde se muestra el micromundo.
 * 
 * @author Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class PanelMicromundo extends JPanel{

	// CONSTANTES
	private static final long serialVersionUID = 1L;
		
	// IMAGENES
	private Image 	_imgPlaneta, _imgPlanetaActual, _imgPlanetaDestino, _imgSolucion;
	
	// COORDENADAS DE LA NAVE
	private int _coordenadaX;
	private int _coordenadaY;
	
	//LÓGICA
	private Micromundo _micromundo;
	private boolean _mostrarSolucion = false; // Hay que mostrar la solución?
    
	public boolean is_mostrarSolucion() {
		return _mostrarSolucion;
	}

	public void set_mostrarSolucion(boolean solucion) {
		_mostrarSolucion = solucion;
	}
	
//	***********************************************************************
	/**
	 * Constructor de la clase PanelMicromundo.
	 */
	public PanelMicromundo(int x, int y, Micromundo m){
		
		_coordenadaX = x;
		_coordenadaY = y;
		
		_micromundo = m;
		
		// Obtenemos los recursos necesarios para la visualización del tablero
		try {
			obtenerRecursos();
				
			// Establecemos el tamaño del panel del tablero
			setPreferredSize(new Dimension( _imgPlaneta.getWidth(null) * Tablero._TAMANO, 
											_imgPlaneta.getHeight(null) * Tablero._TAMANO));
		} 
		catch (IOException e) {}	
	}
			
//	***********************************************************************
	/**
	 * Dibuja en un contexto gráfico "g" sobre el componente. Es invocado al 
	 * principio y cada vez que el componente necesite ser repintado. Recorre 
	 * el tablero gestionado por la ventana y dibuja las celdas de dicho 
	 * tablero usando imágenes.
	 * 
	 * @param g Contexto gráfico en el que se dibuja.
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		int coordX = 0, coordY = 0;
		Image imagen = null;
	
		for(int fila = 0; fila < Tablero._TAMANO; fila++){
			
			for(int columna = 0; columna < Tablero._TAMANO; columna++){
													
				//Planeta actual				
				if(columna == _coordenadaX && fila == _coordenadaY){					
					imagen = _imgPlanetaActual;
					coordX = columna * _imgPlanetaActual.getWidth(null);
					coordY = fila * _imgPlanetaActual.getHeight(null);	
				}
				else
					// Planetas de destino
					if((columna == 0 && fila ==0)||
							(columna == 0 && fila == Tablero._TAMANO-1)||
							(columna == Tablero._TAMANO-1 && fila == 0)||
							(columna == Tablero._TAMANO-1 && fila == Tablero._TAMANO-1)){
						
						imagen = _imgPlanetaDestino;
						coordX = columna * _imgPlanetaDestino.getWidth(null);
						coordY = fila * _imgPlanetaDestino.getHeight(null);
					}							
					else		
						
						if(_mostrarSolucion && (GestorDeProblemas.tieneSolucion(_micromundo.getEscenario().getViaje(columna, fila, 0), true) ||
								GestorDeProblemas.tieneSolucion(_micromundo.getEscenario().getViaje(columna, fila, 1), true) ||
								columna > 0 && GestorDeProblemas.tieneSolucion(_micromundo.getEscenario().getViaje(columna-1, fila, 1), true) ||
								fila > 0 && GestorDeProblemas.tieneSolucion(_micromundo.getEscenario().getViaje(columna, fila-1, 0), true))){					
							imagen = _imgSolucion;
							coordX = columna * _imgSolucion.getWidth(null);
							coordY = fila * _imgSolucion.getHeight(null);
							g.drawImage(_imgSolucion, coordX, coordY, null);
						}
						else{
							imagen = _imgPlaneta;
							coordX = columna * _imgPlaneta.getWidth(null);
							coordY = fila * _imgPlaneta.getHeight(null);		
						}
				
				// Dibujamos la imagen correspondiente en las coordenadas correspondientes
				g.drawImage(imagen, coordX, coordY, null);
			}
		}
	}
	
//	***********************************************************************
	/**
	 * Carga las imágenes necesarias para la visualización del tablero.
	 * 
	 * @throws IOException Lanza esta excepción si hay un problema al cargar
	 * las imágenes con las que se dibuja el tablero y la propaga a un 
	 * nivel superior.
	*/
	private void obtenerRecursos() throws IOException {
		
		// Cargamos las imágenes de las fichas
		_imgPlaneta = ImageIO.read(this.getClass().getResource("/micromundo/interfaz/img/planeta.png"));
		_imgPlanetaActual = ImageIO.read(this.getClass().getResource("/micromundo/interfaz/img/planeta_actual.png"));
		_imgPlanetaDestino = ImageIO.read(this.getClass().getResource("/micromundo/interfaz/img/planeta_destino.png"));	
		_imgSolucion = ImageIO.read(this.getClass().getResource("/micromundo/interfaz/img/planeta_solucion.png"));	
	}
	
	// ***********************************************************************
	/**
	 * Actualiza las coordenadas de la nave.
	 * 
	 * @param x coordenada X de la nave.
	 * @param y coordenada Y de la nave.
	 */
	public void setCoordenadas(int x, int y){
		
		_coordenadaX = x;
		_coordenadaY = y;
		
		repaint();
	}
}
