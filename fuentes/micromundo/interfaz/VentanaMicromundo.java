package micromundo.interfaz;

import javax.swing.*;

import java.awt.*;

import juegos.Conversor;
import juegos.TipoBusqueda;
import micromundo.Micromundo;

//***************************************************************************//
/**
 * Ventana del micromundo. Representa el estado del micromundo además de 
 * mostrar los resultados que se vayan obteniendo.
 * 
 * @author  Javier Salcedo Gómez, Carlos Loredo Iglesias
 */
public class VentanaMicromundo extends javax.swing.JFrame {

    // CONSTANTES
	private static final long serialVersionUID = 1L;
	
    // VARIABLES
    private JMenuBar _barraMenu;
    private JButton _btnAbajo;
    private JButton _btnArriba;
    private JButton _btnDerecha;
    private JButton _btnIzquierda;
    private PanelMicromundo _panelCanvasMicromundo;
    private JMenu _menuResolvedor;
    private JMenuItem _opcionActivarResolvedor;
    private JMenuItem _opcionTiempoMaximoResolvedor;
    private JMenuItem _opcionUsarCache;
    private JMenuItem _regenerarCache;
    private JMenuItem _opcionControlCiclos;
    private JMenu _menuAlgoritmos;
    private JMenu _menuMicromundo;    
    private JMenuItem _opcionMostrarSolucion;
    private JMenuItem _opcionGenerarAleatorio;
    private JMenuItem _opcionEstablecerDificultad;
    private JMenuItem _opcionCargarMicromundo;
    private JMenuItem _opcionGuardarMicromundo;    
    private JButton _btnResolver;    
    private JMenuItem _opcionAEstrella;    
    private JMenuItem _opcionCosteUniforme;
    private JMenuItem _opcionEnfriamientoSimulado;
    private JMenuItem _opcionPrimeroEnAnchura;
    private JMenuItem _opcionPrimeroEnProfundidad;
    private JMenuItem _opcionProfundidadIterativa;
    private JMenuItem _opcionProfundidadLimitada;
    private JMenuItem _opcionVoraz;
    private JPanel _panelBitacora;
    private JPanel _panelMicromundo;
    private JPanel _panelNavegacion;
    private JPanel _panelSolucionMicromundo;
    private JScrollPane _scrollPanelCuadernoBitacora;
    private JScrollPane _scrollPanelSolucionMicromundo;
    private JTextArea _textBitacora;
    private JTextArea _textSolucionMicromundo;
    private JLabel _lblAlgoritmo;
	
    // LOGICA
    private Micromundo _micromundo;
    private String _cuadernoBitacora = "";
    private String _solucion = "";
    private TipoBusqueda _tipoBusqueda;
	private boolean _estaGenerado = false; // Hay algún escenario generado?

	// **********************************************************************//
	/** 
	 * Constructor de la clase VentanaMicromundo.
	 */
    public VentanaMicromundo(Micromundo micromundo) {
    
    	setTitle("IAIC: El Micromundo");
    	setResizable(false);
    	
    	_micromundo = micromundo;
    	
     	initComponents();
    }

	// **********************************************************************//
    /**
     * Inicia los componentes de la ventana.
     */
    private void initComponents() {

        _panelMicromundo = new JPanel();
        _panelBitacora = new JPanel();
        _scrollPanelCuadernoBitacora = new JScrollPane();
        _textBitacora = new JTextArea();
        _panelNavegacion = new JPanel();
        _btnArriba = new JButton();
        _btnIzquierda = new JButton();
        _btnDerecha = new JButton();
        _btnAbajo = new JButton();
        _panelSolucionMicromundo = new JPanel();
        _scrollPanelSolucionMicromundo = new JScrollPane();
        _textSolucionMicromundo = new JTextArea();
        _barraMenu = new JMenuBar();
        _menuMicromundo = new JMenu();
        _opcionMostrarSolucion = new JMenuItem();
        _opcionGenerarAleatorio = new JMenuItem();
        _opcionEstablecerDificultad = new JMenuItem();
        _opcionCargarMicromundo = new JMenuItem();
        _opcionGuardarMicromundo = new JMenuItem();
        _menuResolvedor = new JMenu();        
        _opcionTiempoMaximoResolvedor = new JMenuItem();
        _opcionUsarCache = new JMenuItem();
        _regenerarCache = new JMenuItem();
        _opcionControlCiclos = new JMenuItem();        
        _opcionActivarResolvedor = new JMenuItem();
        _menuAlgoritmos = new JMenu();
        _opcionProfundidadIterativa = new JMenuItem();
        _opcionPrimeroEnAnchura = new JMenuItem();
        _opcionPrimeroEnProfundidad = new JMenuItem();
        _opcionProfundidadLimitada = new JMenuItem();
        _opcionCosteUniforme = new JMenuItem();
        _opcionAEstrella = new JMenuItem();
        _opcionVoraz = new JMenuItem();
        _opcionEnfriamientoSimulado = new JMenuItem();
        _btnResolver = new JButton();               
        _panelCanvasMicromundo = new PanelMicromundo(Micromundo._xInicial, Micromundo._yInicial, _micromundo);
        _lblAlgoritmo = new JLabel("Algoritmo: ");
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       
        _panelMicromundo.setBorder(BorderFactory.createTitledBorder("Micromundo"));
        _panelMicromundo.setMinimumSize(new Dimension(800, 400));
        _panelMicromundo.setName("panelMicromundo"); // NOI18N
       		
        javax.swing.GroupLayout _panelMicromundoLayout = new javax.swing.GroupLayout(_panelMicromundo);
        _panelMicromundo.setLayout(_panelMicromundoLayout);
        _panelMicromundoLayout.setHorizontalGroup(
            _panelMicromundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelMicromundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_panelCanvasMicromundo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        _panelMicromundoLayout.setVerticalGroup(
            _panelMicromundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelMicromundoLayout.createSequentialGroup()
                .addComponent(_panelCanvasMicromundo, javax.swing.GroupLayout.PREFERRED_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );

        _panelBitacora.setBorder(javax.swing.BorderFactory.createTitledBorder("Cuaderno de Bitacora"));
        _panelBitacora.setName("panelBitacora"); // NOI18N

        _scrollPanelCuadernoBitacora.setName("scrollPanelCuadernoBitacora"); // NOI18N

        _textBitacora.setColumns(20);
        _textBitacora.setEditable(false);
        _textBitacora.setRows(10);
        _textBitacora.setName("textBitacora"); // NOI18N
        _scrollPanelCuadernoBitacora.setViewportView(_textBitacora);

        javax.swing.GroupLayout _panelBitacoraLayout = new javax.swing.GroupLayout(_panelBitacora);
        _panelBitacora.setLayout(_panelBitacoraLayout);
        _panelBitacoraLayout.setHorizontalGroup(
            _panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelBitacoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_scrollPanelCuadernoBitacora, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
        );
        _panelBitacoraLayout.setVerticalGroup(
            _panelBitacoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelBitacoraLayout.createSequentialGroup()
                .addComponent(_scrollPanelCuadernoBitacora, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap())
        );

        _panelNavegacion.setBorder(javax.swing.BorderFactory.createTitledBorder("Panel de Navegacion"));
        _panelNavegacion.setName("panelNavegacion"); // NOI18N

        _btnArriba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/micromundo/interfaz/img/mover_arriba.png"))); // NOI18N
        _btnArriba.setName("btnArriba"); // NOI18N
        _btnArriba.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnArribaActionPerformed(evt);
            }
        });

        _btnIzquierda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/micromundo/interfaz/img/mover_izquierda.png"))); // NOI18N
        _btnIzquierda.setName("btnIzquierda"); // NOI18N
        _btnIzquierda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnIzquierdaActionPerformed(evt);
            }
        });

        _btnDerecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/micromundo/interfaz/img/mover_derecha.png"))); // NOI18N
        _btnDerecha.setName("btnDerecha"); // NOI18N
        _btnDerecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnDerechaActionPerformed(evt);
            }
        });

        _btnAbajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/micromundo/interfaz/img/mover_abajo.png"))); // NOI18N
        _btnAbajo.setName("btnAbajo"); // NOI18N
        _btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnAbajoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout _panelNavegacionLayout = new javax.swing.GroupLayout(_panelNavegacion);
        _panelNavegacion.setLayout(_panelNavegacionLayout);
        _panelNavegacionLayout.setHorizontalGroup(
            _panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, _panelNavegacionLayout.createSequentialGroup()
                .addGroup(_panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(_panelNavegacionLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(_btnIzquierda)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(_panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(_btnDerecha))
                .addContainerGap())
            .addGroup(_panelNavegacionLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(_panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_btnAbajo)
                    .addComponent(_btnArriba))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        _panelNavegacionLayout.setVerticalGroup(
            _panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelNavegacionLayout.createSequentialGroup()
                .addComponent(_btnArriba)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(_panelNavegacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(_btnDerecha)
                    .addGroup(_panelNavegacionLayout.createSequentialGroup()
                        .addComponent(_btnIzquierda)
                        .addGap(7, 7, 7)
                        .addComponent(_btnAbajo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        _panelSolucionMicromundo.setBorder(javax.swing.BorderFactory.createTitledBorder("Solucion del Micromundo"));
        _panelSolucionMicromundo.setName("panelMicromundo"); // NOI18N

        _scrollPanelSolucionMicromundo.setName("scrollPanelSolucionMicromundo"); // NOI18N

        _textSolucionMicromundo.setColumns(20);
        _textSolucionMicromundo.setEditable(false);
        _textSolucionMicromundo.setRows(5);
        _textSolucionMicromundo.setName("_textSolucionMicromundo"); // NOI18N
        _scrollPanelSolucionMicromundo.setViewportView(_textSolucionMicromundo);

        javax.swing.GroupLayout _panelSolucionMicromundoLayout = new javax.swing.GroupLayout(_panelSolucionMicromundo);
        _panelSolucionMicromundo.setLayout(_panelSolucionMicromundoLayout);
        _panelSolucionMicromundoLayout.setHorizontalGroup(
            _panelSolucionMicromundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelSolucionMicromundoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(_scrollPanelSolucionMicromundo, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        _panelSolucionMicromundoLayout.setVerticalGroup(
            _panelSolucionMicromundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(_panelSolucionMicromundoLayout.createSequentialGroup()
                .addComponent(_scrollPanelSolucionMicromundo, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        _barraMenu.setName("barraMenu"); // NOI18N

        _menuMicromundo.setText("Micromundo");
        _menuMicromundo.setName("menuMicromundo"); // NOI18N
        
        _opcionMostrarSolucion.setText("Mostrar solución");
        _opcionMostrarSolucion.setName("opcionMostrarSolucion"); // NOI18N
        _opcionMostrarSolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionMostrarSolucionActionPerformed(evt);
            }
        });
        _menuMicromundo.add(_opcionMostrarSolucion);        
        
        _opcionGenerarAleatorio.setText("Generar Aleatorio");
        _opcionGenerarAleatorio.setName("opcionGenerarAleatorio"); // NOI18N
        _opcionGenerarAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionGenerarAleatorioActionPerformed(evt);
            }
        });
        _menuMicromundo.add(_opcionGenerarAleatorio);

        _opcionEstablecerDificultad.setText("Establecer dificultad máxima");
        _opcionEstablecerDificultad.setName("opcionEstablecerDificultad"); // NOI18N
        _opcionEstablecerDificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_opcionEstablecerDificultadActionPerformed(evt);
            }
        });
        _menuMicromundo.add(_opcionEstablecerDificultad);        
        
        _opcionCargarMicromundo.setText("Cargar Micromundo");
        _opcionCargarMicromundo.setName("opcionCargarMicromundo"); // NOI18N
        _opcionCargarMicromundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionCargarMicromundoActionPerformed(evt);
            }
        });
        _menuMicromundo.add(_opcionCargarMicromundo);                

        _opcionGuardarMicromundo.setText("Guardar Micromundo");
        _opcionGuardarMicromundo.setName("opcionGuardarMicromundo"); // NOI18N
        _opcionGuardarMicromundo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionGuardarMicromundoActionPerformed(evt);
            }
        });
        _menuMicromundo.add(_opcionGuardarMicromundo);

        _barraMenu.add(_menuMicromundo);
        
        _menuResolvedor.setText("Resolvedor");
        _menuResolvedor.setName("menuResolvedor"); // NOI18N

        _opcionActivarResolvedor.setText("Activar resolvedor");
        _opcionActivarResolvedor.setName("opcionActivarResolvedor"); // NOI18N
        _opcionActivarResolvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_opcionActivarResolvedorActionPerformed(evt);
            }
        });
        _menuResolvedor.add(_opcionActivarResolvedor);
        
        _opcionTiempoMaximoResolvedor.setText("Establecer tiempo de espera máximo");
        _opcionTiempoMaximoResolvedor.setName("opcionTiempoMaximoResolvedor"); // NOI18N
        _opcionTiempoMaximoResolvedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_opcionTiempoMaximoResolvedorActionPerformed(evt);
            }
        });
        _menuResolvedor.add(_opcionTiempoMaximoResolvedor);  
        
        _opcionUsarCache.setText("Desactivar uso de cache de soluciones");
        _opcionUsarCache.setName("opcionUsarCache"); // NOI18N
        _opcionUsarCache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_opcionUsarCacheActionPerformed(evt);
            }
        });
        _menuResolvedor.add(_opcionUsarCache); 
        
        _regenerarCache.setText("Regenerar cache de soluciones");
        _regenerarCache.setName("regenerarCache"); // NOI18N
        _regenerarCache.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_regenerarCacheActionPerformed(evt);
            }
        });
        _menuResolvedor.add(_regenerarCache);    
        
        _opcionControlCiclos.setText("Desactivar control de ciclos");
        _opcionControlCiclos.setName("opcionControlCiclos"); // NOI18N
        _opcionControlCiclos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	_opcionControlCiclosActionPerformed(evt);
            }
        });
        _menuResolvedor.add(_opcionControlCiclos);             
        
        
                       
        _barraMenu.add(_menuResolvedor);
        
        _menuAlgoritmos.setText("Algoritmos");
        _menuAlgoritmos.setName("menuAlgoritmos"); // NOI18N

        _opcionPrimeroEnAnchura.setText("Primero en Anchura");
        _opcionPrimeroEnAnchura.setName("opcionPrimeroEnAnchura"); // NOI18N
        _opcionPrimeroEnAnchura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionPrimeroEnAnchuraActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionPrimeroEnAnchura);

        _opcionPrimeroEnProfundidad.setText("Primero en Profundidad");
        _opcionPrimeroEnProfundidad.setName("opcionPrimeroEnProfundidad"); // NOI18N
        _opcionPrimeroEnProfundidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionPrimeroEnProfundidadActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionPrimeroEnProfundidad);

        _opcionProfundidadLimitada.setText("Profundidad Limitada (21)");
        _opcionProfundidadLimitada.setName("opcionProfundidadLimitada"); // NOI18N
        _opcionProfundidadLimitada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionProfundidadLimitadaActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionProfundidadLimitada);

        _opcionCosteUniforme.setText("Coste Uniforme");
        _opcionCosteUniforme.setName("opcionCosteUniforme"); // NOI18N
        _opcionCosteUniforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionCosteUniformeActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionCosteUniforme);
        
        _opcionProfundidadIterativa.setText("Profundidad Iterativa");
        _opcionProfundidadIterativa.setName("opcionProfundidadIterativa"); // NOI18N
        _opcionProfundidadIterativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionProfundidadIterativaActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionProfundidadIterativa);        

        _opcionAEstrella.setText("A*");
        _opcionAEstrella.setName("opcionAEstrella"); // NOI18N
        _opcionAEstrella.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionAEstrellaActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionAEstrella);

        _opcionVoraz.setText("Voraz");
        _opcionVoraz.setName("opcionVoraz"); // NOI18N
        _opcionVoraz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionVorazActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionVoraz);

        _opcionEnfriamientoSimulado.setText("Enfriamiento Simulado");
        _opcionEnfriamientoSimulado.setName("opcionEnfriamientoSimulado"); // NOI18N
        _opcionEnfriamientoSimulado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _opcionEscaladaSimpleActionPerformed(evt);
            }
        });
        _menuAlgoritmos.add(_opcionEnfriamientoSimulado);

        _barraMenu.add(_menuAlgoritmos);

        _btnResolver.setText("Resolver");
        _btnResolver.setName("btnResolver"); // NOI18N
        _btnResolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                _btnResolverActionPerformed(evt);
            }
        });
        
        setJMenuBar(_barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(_panelBitacora, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        	.addComponent(_btnResolver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)                            	
                        	.addComponent(_lblAlgoritmo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)    
                        	.addComponent(_panelSolucionMicromundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(_panelNavegacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(_panelMicromundo, javax.swing.GroupLayout.PREFERRED_SIZE,410, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(_panelNavegacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(_btnResolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                       
                        .addComponent(_lblAlgoritmo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(25, 25, 25)
                        .addComponent(_panelSolucionMicromundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(_panelMicromundo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(_panelBitacora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        
    }

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre la opción del menú guardarMicromundo.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionGuardarMicromundoActionPerformed(java.awt.event.ActionEvent evt) {
	
		if(_estaGenerado){
			
			final JFileChooser chooser = new JFileChooser("escenarios/");
			final int seleccion = chooser.showSaveDialog(null);

			if (seleccion == JFileChooser.APPROVE_OPTION)
				_micromundo.getEscenario().guardarEscenarioEnFichero(chooser.getSelectedFile().getPath());
		}
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}

	// **********************************************************************//
	/**
	 * Ejecuta las acciones coorespondientes para reiniciar los datos y vistas
	 * mostradas en la ventana.
	 */
	@SuppressWarnings("static-access")
	private void reiniciarVentana() {
		
		// Vaciamos los bufferes a mostrar
		_cuadernoBitacora = "";		
		_solucion = "";
		insertarTextoEnSolucion("");

		// Reinicia las posiciones de la nave
		_micromundo.getEscenario().reiniciarPosicion();
		
		String cadena = "Estamos en el Planeta Origen.\n" +
						"----------------------------------------------------------------\n" +
		                "Nuestra posición es (X = " + _micromundo.getEscenario().getCoordenadaX() + 
		                ", Y = " + _micromundo.getEscenario().getCoordenadaY() + ")\n" + 
		                "----------------------------------------------------------------\n";
		
		// Actualizamos el cuaderno de bitácora
		insertarTextoEnBitacora(cadena);
		
		// Reflejamos que ya está generado
		_estaGenerado = true;

		// No hemos elegido un algoritmo todavía
		/*_lblAlgoritmo.setText("Algoritmo: ");
		_tipoBusqueda = null;*/
				
		// Repintamos el tablero
		repintar(_micromundo.getEscenario().getCoordenadaX(), _micromundo.getEscenario().getCoordenadaX());
		
        if (_micromundo.is_resolvedor())
        	insertarTextoEnSolucion("Resolvedor activado.\n");
        else
        	insertarTextoEnSolucion("Resolvedor desactivado.\n");
        insertarTextoEnSolucion("Dificultad máxima para los problemas de \n " + 
        		"la solución establecida a "+_micromundo.getEscenario().get_dificultadMaxima()+".\n");
        if (_micromundo.is_usarCache())
        	insertarTextoEnSolucion("Uso de cache de soluciones activado.\n");
        else
        	insertarTextoEnSolucion("Uso de cache de soluciones desactivado.\n");
        if (_micromundo.is_controlCiclos())
        	insertarTextoEnSolucion("Control de ciclos activado.\n");
        else
        	insertarTextoEnSolucion("Control de ciclos desactivado.\n");
        
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre la opción del menú CargarMicromundo.
	 * 
	 * @param evt Evento de pulsación.
	 */
	@SuppressWarnings("static-access")
	private void _opcionEstablecerDificultadActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			int d = Integer.parseInt(JOptionPane.showInputDialog("Introduzca la dificultad máxima para los problemas de la solución(1-12):"));
			_micromundo.getEscenario().set_dificultadMaxima(d);
			insertarTextoEnSolucion("Dificultad máxima para los problemas de \n " + 
					"la solución establecida a "+d+".\n");
		}
		catch(Exception e){}
	}	
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre la opción del menú CargarMicromundo.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionCargarMicromundoActionPerformed(java.awt.event.ActionEvent evt) {
	
		final JFileChooser chooser = new JFileChooser("escenarios/");
		final int seleccion = chooser.showOpenDialog(null);

		if (seleccion == JFileChooser.APPROVE_OPTION){
			
			_micromundo.getEscenario().cargarEscenarioDeFichero(chooser.getSelectedFile().getPath());
			
			reiniciarVentana();
		}
	}

	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre la opción del menú mostrar solución.
	 * 
	 * @param evt Evento de pulsación.
	 */	
	private void _opcionMostrarSolucionActionPerformed(java.awt.event.ActionEvent evt) {
	
		if (this._panelCanvasMicromundo.is_mostrarSolucion()){
			this._panelCanvasMicromundo.set_mostrarSolucion(false);
			_opcionMostrarSolucion.setText("Mostrar solución");			
		}
		else{
			this._panelCanvasMicromundo.set_mostrarSolucion(true);
			_opcionMostrarSolucion.setText("No mostrar solución");			
		}
		
		
		reiniciarVentana();		
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre la opción del menú generarAleatorio.
	 * 
	 * @param evt Evento de pulsación.
	 */	
	private void _opcionGenerarAleatorioActionPerformed(java.awt.event.ActionEvent evt) {
	
		// Generamos el micromundo de forma aleatoria
		_micromundo.getEscenario().generaTableroAleatorio();
		
		reiniciarVentana();		
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de navegación Izquierda.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _btnIzquierdaActionPerformed(java.awt.event.ActionEvent evt) {
		
		if(_estaGenerado)
			if(!_micromundo.is_resolvedor() || _micromundo.is_resolvedor() && _tipoBusqueda != null)
				_micromundo.moverNave("Izquierda", _tipoBusqueda);
			else
				JOptionPane.showMessageDialog(this,
					    "Debes seleccionar un método de búsqueda primero.",
					    "Método de búsqueda no seleccionado",
					    JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de navegación Derecha.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _btnDerechaActionPerformed(java.awt.event.ActionEvent evt) {
	
		if(_estaGenerado)
			if(!_micromundo.is_resolvedor() || _micromundo.is_resolvedor() && _tipoBusqueda != null)
				_micromundo.moverNave("Derecha", _tipoBusqueda);
			else
				JOptionPane.showMessageDialog(this,
					    "Debes seleccionar un método de búsqueda primero.",
					    "Método de búsqueda no seleccionado",
					    JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de navegación Arriba.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _btnArribaActionPerformed(java.awt.event.ActionEvent evt) {
	
		if(_estaGenerado)
			if(!_micromundo.is_resolvedor() || _micromundo.is_resolvedor() && _tipoBusqueda != null)
				_micromundo.moverNave("Arriba", _tipoBusqueda);
			else
				JOptionPane.showMessageDialog(this,
					    "Debes seleccionar un método de búsqueda primero.",
					    "Método de búsqueda no seleccionado",
					    JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de navegación Abajo.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {
	
		if(_estaGenerado)
			if(!_micromundo.is_resolvedor() || _micromundo.is_resolvedor() && _tipoBusqueda != null)
				_micromundo.moverNave("Abajo", _tipoBusqueda);
			else
				JOptionPane.showMessageDialog(this,
					    "Debes seleccionar un método de búsqueda primero.",
					    "Método de búsqueda no seleccionado",
					    JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}
		
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de establecer el tiempo de espera máximo
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionTiempoMaximoResolvedorActionPerformed(java.awt.event.ActionEvent evt) {
		try{
			int t = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el tiempo máximo para la búsqueda de la solución(ms):"));
			_micromundo.set_tiempoMAX(t);
			insertarTextoEnSolucion("Tiempo máximo para la búsqueda de la solución establecido a "+t+".\n");
		}
		catch(Exception e){}
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de acivar/desactivar el uso de la cache de soluciones
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionUsarCacheActionPerformed(java.awt.event.ActionEvent evt) {
		if (_micromundo.is_usarCache()){
			_micromundo.set_usarCache(false);
			_opcionUsarCache.setText("Activar uso de cache de soluciones");
			insertarTextoEnSolucion("Cache de soluciones desactivada.\n");
		}
		else{
			_micromundo.set_usarCache(true);
			_opcionUsarCache.setText("Desactivar uso de cache de soluciones");
			insertarTextoEnSolucion("Cache de soluciones activada.\n");
		}
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de acivar/desactivar el uso de la cache de soluciones
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _regenerarCacheActionPerformed(java.awt.event.ActionEvent evt) {
		_micromundo.regenerarInfoProblemas();	
		insertarTextoEnSolucion("Control de ciclos desactivado. \n" + 
				"Debes generar un nuevo mapa aleatorio para \n" + 
				"que los cambios en la cache de soluciones \n" + 
				"sean apreciables.\n");				
	}	
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de acivar/desactivar el uso de la cache de soluciones
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	@SuppressWarnings("static-access")
	private void _opcionControlCiclosActionPerformed(java.awt.event.ActionEvent evt) {
		if (_micromundo.is_controlCiclos()){
			_micromundo.set_controlCiclos(false);
			_opcionControlCiclos.setText("Activar control de ciclos");
			insertarTextoEnSolucion("Control de ciclos desactivado. \n" + 
					"Si el uso de la cache de soluciones está activado, \n" + 
					"ésta debe ser regenerada  para apreciar el \n" +
					"cambio en los problemas del micromundo. \n");
		}
		else{
			_micromundo.set_controlCiclos(true);
			_opcionControlCiclos.setText("Desactivar control de ciclos");
			insertarTextoEnSolucion("Control de ciclos activado.\n");
		}
	}	
	
	
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón de activar o desactivar el resolvedor
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionActivarResolvedorActionPerformed(java.awt.event.ActionEvent evt) {
	
		if (_micromundo.is_resolvedor()){
			_micromundo.set_resolvedor(false);
			_opcionActivarResolvedor.setText("Activar resolvedor");
			insertarTextoEnSolucion("Resolvedor desactivado.\n");
		}
		else{
			_micromundo.set_resolvedor(true);
			_opcionActivarResolvedor.setText("Desactivar resolvedor");
			insertarTextoEnSolucion("Resolvedor activado.\n");
		}		
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda ProfundidadIterativa
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionProfundidadIterativaActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.PROFUNDIDAD_ITERATIVA;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda Primero en Anchura
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionPrimeroEnAnchuraActionPerformed(java.awt.event.ActionEvent evt) {
		
		_tipoBusqueda = TipoBusqueda.PRIMERO_EN_ANCHURA;		
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
	}

	// **********************************************************************//
	/**
	 * 
	 * @param evt
	 */
	private void _opcionPrimeroEnProfundidadActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.PRIMERO_EN_PROFUNDIDAD;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda ProfundidadLimitada
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionProfundidadLimitadaActionPerformed(java.awt.event.ActionEvent evt) {
	 
		_tipoBusqueda = TipoBusqueda.PROFUNDIDAD_LIMITADA;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda CosteUniforme
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionCosteUniformeActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.COSTE_UNIFORME;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
		
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda A*
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionAEstrellaActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.A_ESTRELLA;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
		
	}
	
	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda Voraz
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionVorazActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.VORAZ;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
		
	}

	// **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el tipo de búsqueda EscaladaSimple
	 * del menú.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _opcionEscaladaSimpleActionPerformed(java.awt.event.ActionEvent evt) {
	
		_tipoBusqueda = TipoBusqueda.ENFRIAMIENTO;
		_lblAlgoritmo.setText("Algoritmo usado: " + Conversor.tipoBusquedaToDescripcion(_tipoBusqueda));
		
	}
	
//	 **********************************************************************//
	/**
	 * Atiende la acción de pulsación sobre el botón resolver.
	 * 
	 * @param evt Evento de pulsación.
	 */
	private void _btnResolverActionPerformed(java.awt.event.ActionEvent evt) {
		
		if(_estaGenerado)			
			if(_tipoBusqueda != null)
				_micromundo.resolverMicromundo(_tipoBusqueda);
			else
				JOptionPane.showMessageDialog(this,
						    "Debes seleccionar un método de búsqueda primero.",
						    "Método de búsqueda no seleccionado",
						    JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this,
				    "Debes iniciar un micromundo primero.",
				    "Micromundo no iniciado",
				    JOptionPane.ERROR_MESSAGE);
	}
		
	// **********************************************************************//
	/**
	 * Inserta el texto en el cuaderno de bitácora.
	 *  
	 * @param texto Texto a insertar.
	 */
	public void insertarTextoEnBitacora(String texto){
		
		_cuadernoBitacora = _cuadernoBitacora + texto;
		_textBitacora.setText(_cuadernoBitacora);
	}
		
	// **********************************************************************//
	/**
	 * Inserta el texto en el cuaderno de bitácora.
	 *  
	 * @param texto Texto a insertar.
	 */
	public void insertarTextoEnSolucion(String texto){
		
		_solucion = _solucion + texto;
		_textSolucionMicromundo.setText(_solucion);
	}
	
	// **********************************************************************//
	/**
	 * Le pasa las coordenadas actuales de la nave al panel encargado de 
	 * pintar el micromundo.
	 * 
	 * @param x CoordenadaX actual de la nave.
	 * @param y CoordenadaY actual de la nave.
	 */
	public void repintar(int x, int y){
		
		_panelCanvasMicromundo.setCoordenadas(x,y);
		repaint();
	}
}
