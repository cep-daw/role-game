package swing;
import gameClasses.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * La clase Main gestiona la interfaz gráfica y la lógica del juego.
 */
public class Main {
    public JPanel panelMain;
    private JPanel panelPrincipal;
    private JLabel imagenPrincipal;
    private JLabel imagenTitulo;
    private JButton clickEmpezar;
    private JPanel panelElegirPersonaje;
    private JLabel labelElegirPersonaje;
    private JButton clickMago;
    private JButton clickGuerrero;
    private JButton clickSacerdote;
    private static JPanel panelMazmorra;
    private ArrayList<JLabel> labelMuro;
    private ArrayList<JLabel> labelSuelo;
    private ArrayList<JLabel> labelEntradaYSalida;
    private JPanel panelTitulo;
    private Timer timerSeconds;
    private static int seconds = 0;
    private JLabel labelTiempo;
    private static Personaje role = null;
    private JLabel labelPersonajeBody;
    private JLabel espada;
    private JLabel mitra;
    private JLabel pocion;
    private JLabel oro;
    private Enemigo enemigo;
    private ArrayList<Enemigo> enemigos = new ArrayList<>();
    private JLabel labelOro;
    private Timer timer;

    /**
     * El método principal que inicia la aplicación.
     *
     * @param args los argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Game role: Al-Kahf");
        frame.setContentPane(new Main().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Image icon = pantalla.getImage("src/images/caverna.png");
        frame.setIconImage(icon);
    }

    /**
     * Constructor de la clase Main.
     * Inicializa todos los paneles y componentes de la interfaz gráfica.
     */
    public Main(){
        panelMain = new JPanel();
        crearPanelMain();
        crearPanelPrincipal();
        crearLabelImagenPrincipal();
        crearLabelImagenTitulo();
        crearButtonClickEmpezar();
        verPanelPrincipal();
    }

    // ------------------------------------------------------------------ FUNCIONES / MÉTODOS ---------------------------------------------------

    // ------------------------------------------------------------------- Panel Principal -----------------------------------------------------



    /**
     * Crea y configura el panel principal de la interfaz gráfica.
     * Establece el tamaño, el diseño y otras propiedades necesarias para el panel principal.
     */
    private void crearPanelMain() {
        panelMain.setPreferredSize(new Dimension(800, 700));
        panelMain.setLayout(null);
        panelMain.setSize(new Dimension(800, 700));
        panelMain.setFocusable(true);
    }

    /**
     * Crea y configura el panel principal dentro del panel principal de la interfaz gráfica.
     * Establece el tamaño y el diseño del panel principal.
     */
    private void crearPanelPrincipal() {
        panelPrincipal = new JPanel();
        panelPrincipal.setSize(panelMain.getSize());
        panelPrincipal.setLayout(null);
    }

    /**
     * Crea y configura la etiqueta para la imagen principal.
     * Establece el tamaño, la ubicación y el icono de la etiqueta de la imagen principal.
     */
    private void crearLabelImagenPrincipal() {
        imagenPrincipal = new JLabel();
        imagenPrincipal.setSize(panelPrincipal.getSize());
        imagenPrincipal.setLocation(0,0);
        ImageIcon image1 = new ImageIcon("src/images/cuevaPrincipal.jpg");
        Icon icon = new ImageIcon(
                image1.getImage().getScaledInstance(panelPrincipal.getWidth(), panelPrincipal.getHeight(), Image.SCALE_SMOOTH)
        );
        imagenPrincipal.setIcon(icon);
        panelPrincipal.add(imagenPrincipal);
    }

    /**
     * Crea y configura la etiqueta para la imagen del título.
     * Establece el tamaño, la ubicación y el icono de la etiqueta de la imagen del título.
     */
    private void crearLabelImagenTitulo() {
        imagenTitulo = new JLabel();
        imagenTitulo.setSize(400,200);
        imagenTitulo.setLocation(panelPrincipal.getWidth() / 2 - imagenTitulo.getWidth() / 2,150 );
        ImageIcon image2 = new ImageIcon("src/images/Al-Kahf.png");
        Icon icon2 = new ImageIcon(
                image2.getImage().getScaledInstance(imagenTitulo.getWidth(), imagenTitulo.getHeight(), Image.SCALE_SMOOTH)
        );
        imagenTitulo.setIcon(icon2);
        panelPrincipal.add(imagenTitulo);
        panelPrincipal.setComponentZOrder(imagenTitulo,0);
    }

    /**
     * Crea y configura el botón "EMPEZAR".
     * Establece las propiedades del botón, como el texto, los colores, la fuente, el tamaño y la ubicación.
     * Añade un ActionListener para manejar los eventos de clic en el botón.
     */
    private void crearButtonClickEmpezar() {
        clickEmpezar = new JButton();
        clickEmpezar.setText("EMPEZAR");
        clickEmpezar.setBackground(new Color(208, 169, 51));
        clickEmpezar.setForeground(new Color(0x684E00));
        clickEmpezar.setFont(new Font("Monospaced", Font.BOLD, 18));
        clickEmpezar.setSize(120, 40);
        clickEmpezar.setLocation(panelPrincipal.getWidth() / 2 - clickEmpezar.getWidth() / 2,350);
        clickEmpezar.addActionListener(new ButtonEmpezarListener());
        panelPrincipal.add(clickEmpezar);
        panelPrincipal.setComponentZOrder(clickEmpezar,0);
    }

    /**
     * Muestra el panel principal añadiéndolo al panel principal.
     * Redibuja el panel principal para reflejar los cambios.
     */
    private void verPanelPrincipal() {
        panelMain.add(panelPrincipal);
        panelMain.repaint();
    }



    // -------------------------------------------------------------- Panel Elegir Personaje ----------------------------------------------------------



    /**
     * Crea y muestra el panel para elegir el personaje.
     * Este método llama a otros métodos que crean el panel y sus componentes:
     * el texto instructivo y los botones para seleccionar diferentes personajes.
     */
    private void crearYVerPanelElegirPersonaje() {
        crearPanelElegirPersonaje();
        crearLabelTextoElegirPersonaje();
        crearButtonMago();
        crearButtonGuerrero();
        crearButtonSacerdote();

    }

    /**
     * Crea y configura el panel para elegir personaje, eliminando cualquier componente previo del panel principal.
     */
    private void crearPanelElegirPersonaje() {
        panelPrincipal.removeAll();

        panelElegirPersonaje = new JPanel();
        panelElegirPersonaje.setSize(panelMain.getSize());
        panelElegirPersonaje.setLayout(null);
        panelMain.add(panelElegirPersonaje);
        panelElegirPersonaje.add(imagenPrincipal);
        panelElegirPersonaje.repaint();
    }

    /**
     * Crea y añade la etiqueta de texto que indica al jugador que elija un personaje.
     */
    private void crearLabelTextoElegirPersonaje() {
        labelElegirPersonaje = new JLabel("Elige a tu personaje: ");
        labelElegirPersonaje.setFont(new Font("Monospaced", Font.BOLD, 40));
        labelElegirPersonaje.setForeground(new Color(208, 169, 51));
        labelElegirPersonaje.setSize(600,50);
        labelElegirPersonaje.setLocation(panelElegirPersonaje.getWidth() / 2 - labelElegirPersonaje.getWidth() / 2, 150);
        panelElegirPersonaje.add(labelElegirPersonaje);
        panelElegirPersonaje.setComponentZOrder(labelElegirPersonaje,0);
        panelElegirPersonaje.repaint();
    }

    /**
     * Crea y añade el botón para elegir el personaje "Mago".
     */
    private void crearButtonMago() {
        clickMago = new JButton();
        clickMago.setText("Mago");
        clickMago.setSize(220, 220);
        clickMago.setBorderPainted(false);
        clickMago.setContentAreaFilled(false);
        clickMago.setLocation(panelElegirPersonaje.getWidth() / 4 - clickMago.getWidth() / 2,250);
        ImageIcon imageMago = new ImageIcon("src/images/mago.png");
        Icon iconMago = new ImageIcon(
                imageMago.getImage().getScaledInstance(clickMago.getWidth(), clickMago.getHeight(), Image.SCALE_SMOOTH)
        );
        clickMago.setIcon(iconMago);
        clickMago.addActionListener(new ButtonElegirPersonajeListener());
        panelElegirPersonaje.add(clickMago);
        panelElegirPersonaje.setComponentZOrder(clickMago,0);
        panelElegirPersonaje.repaint();
    }

    /**
     * Crea y añade el botón para elegir el personaje "Guerrero".
     */
    private void crearButtonGuerrero() {
        clickGuerrero = new JButton();
        clickGuerrero.setText("Guerrero");
        clickGuerrero.setSize(220, 220);
        clickGuerrero.setBorderPainted(false);
        clickGuerrero.setContentAreaFilled(false);
        clickGuerrero.setLocation(panelElegirPersonaje.getWidth() / 2 - clickGuerrero.getWidth() / 2,250);
        ImageIcon imageGuerrero = new ImageIcon("src/images/guerrero.png");
        Icon iconGuerrero = new ImageIcon(
                imageGuerrero.getImage().getScaledInstance(clickGuerrero.getWidth(), clickGuerrero.getHeight(), Image.SCALE_SMOOTH)
        );
        clickGuerrero.setIcon(iconGuerrero);
        clickGuerrero.addActionListener(new ButtonElegirPersonajeListener());
        panelElegirPersonaje.add(clickGuerrero);
        panelElegirPersonaje.setComponentZOrder(clickGuerrero,0);
        panelElegirPersonaje.repaint();
    }

    /**
     * Crea y añade el botón para elegir el personaje "Sacerdote".
     */
    private void crearButtonSacerdote() {
        clickSacerdote = new JButton();
        clickSacerdote.setText("Guerrero");
        clickSacerdote.setSize(220, 220);
        clickSacerdote.setBorderPainted(false);
        clickSacerdote.setContentAreaFilled(false);
        clickSacerdote.setLocation(panelElegirPersonaje.getWidth() * 3/4 - clickSacerdote.getWidth() / 2,250);
        ImageIcon imageSacerdote = new ImageIcon("src/images/sacerdote.png");
        Icon iconSacerdote = new ImageIcon(
                imageSacerdote.getImage().getScaledInstance(clickSacerdote.getWidth(), clickSacerdote.getHeight(), Image.SCALE_SMOOTH)
        );
        clickSacerdote.setIcon(iconSacerdote);
        clickSacerdote.addActionListener(new ButtonElegirPersonajeListener());
        panelElegirPersonaje.add(clickSacerdote);
        panelElegirPersonaje.setComponentZOrder(clickSacerdote,0);
        panelElegirPersonaje.repaint();
    }

    /**
     * Crea un nuevo personaje con el nombre proporcionado por el usuario.
     *
     * @param personaje El objeto de tipo Personaje que se desea crear.
     * @return El personaje creado si se proporciona un nombre válido, de lo contrario, devuelve null.
     */
    private Personaje crearPersonaje(Personaje personaje) {
        if (personaje.pedirNombre()) {
            return personaje;
        } else {
            return null;
        }
    }



    // -------------------------------------------------------------------- Panel Mazmorra ------------------------------------------------------------



    /**
     * Crea el panel de la mazmorra y muestra la interfaz gráfica del juego.
     */
    private void crearYVerPanelMazmorra() {
        panelElegirPersonaje.removeAll();
        labelSuelo=new ArrayList<>();
        labelMuro=new ArrayList<>();
        labelEntradaYSalida = new ArrayList<>();
        timerSeconds = new Timer(1000, new TimerSecondsListener());
        timerSeconds.start();

        crearPanelMazmorra();
        crearYverPanelTitulo();
        creacionMapaMazmorra();
        panelMazmorra.setFocusable(true);
        panelMazmorra.requestFocusInWindow();
        panelMazmorra.addKeyListener(new KeyMoverPersonaje(role));
        panelMazmorra.repaint();

    }

    /**
     * Crea el panel de la mazmorra.
     */
    private void crearPanelMazmorra() {
        panelMazmorra = new JPanel();
        panelMazmorra.setSize(panelMain.getSize());
        panelMazmorra.setLayout(null);
        panelMain.add(panelMazmorra);
        panelMazmorra.setBackground(Color.black);
        panelMazmorra.repaint();
    }

    /**
     * Crea y muestra el panel del título del juego.
     */
    private void crearYverPanelTitulo() {

        panelTitulo = new JPanel();
        panelTitulo.setSize(panelMain.getSize());
        panelTitulo.setLayout(null);
        panelTitulo.setBounds(0, 0, 800, 50);

        if (role != null){
            JLabel labelNombre = new JLabel(role.getNombre());
            labelNombre.setBounds(100, 0, 100, 50);
            panelTitulo.add(labelNombre);

            labelOro = new JLabel();
            labelOro.setText("Oro: " + role.getOro());
            labelOro.setBounds(250, 0, 100, 50);
            panelTitulo.add(labelOro);

            JLabel labelVida = new JLabel();
            labelVida.setText("Vidas: " + role.getVidas());
            labelVida.setBounds(350, 0, 100, 50);
            panelTitulo.add(labelVida);

            labelTiempo = new JLabel();
            labelTiempo.setText("Tiempo: " + seconds);
            labelTiempo.setBounds(650, 0, 100, 50);
            panelTitulo.add(labelTiempo);
        }

        panelMazmorra.add(panelTitulo);
        panelMazmorra.setComponentZOrder(panelTitulo, 0);
    }

    /**
     * Crea el mapa de la mazmorra con base en una matriz predefinida.
     */
    private void creacionMapaMazmorra() {
        int[][] mapa = {
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
                {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
        };
        // Posiciones iniciales para la colocación de elementos gráficos
        int x = 82;
        int y = 82;

        JLabel bloque;
        ImageIcon imagen;

        // Recorre la matriz del mapa
        for (int i = 0; i < 34; i++) {
            for (int j = 0; j < 40; j++) {
                bloque = new JLabel();
                bloque.setSize(16,16);
                bloque.setLocation(x+(j*bloque.getWidth()),y+(i*bloque.getHeight()));
                if (mapa[i][j] == 1) {
                    imagen = new ImageIcon("src/images/cueva/muro.png");
                    Icon icon = imagen;
                    bloque.setIcon(icon);
                    labelMuro.add(bloque);
                }else if (mapa[i][j] == 2) {
                    imagen = new ImageIcon("src/images/cueva/suelo2.png");
                    Icon icon = imagen;
                    bloque.setIcon(icon);
                    labelEntradaYSalida.add(bloque);
                }else {
                    imagen = new ImageIcon("src/images/cueva/suelo.png");
                    Icon icon = imagen;
                    bloque.setIcon(icon);
                    labelSuelo.add(bloque);
                }
                panelMazmorra.add(bloque);
                panelMazmorra.setComponentZOrder(bloque,0);

            }
        }


    }



    // ----------------------------------------------------------------- Lógica del Juego --------------------------------------------------------------



    /**
     * Agrega el cuerpo del personaje al panel de la mazmorra.
     *
     * @param role El objeto Personaje que se va a agregar al panel.
     */
    private void agregarPersonajeBody(Personaje role) {
        // Obtiene las coordenadas iniciales
        int xInicial = role.getX();
        int yInicial = role.getY();

        ImageIcon imagenPersonaje = role.getGifAbajo();

        // Nuevo JLabel con la imagen del personaje y lo posiciona en las coordenadas iniciales
        labelPersonajeBody = new JLabel(imagenPersonaje);
        labelPersonajeBody.setBounds(xInicial, yInicial, imagenPersonaje.getIconWidth(), imagenPersonaje.getIconHeight());

        // Ajusta las coordenadas iniciales si el personaje se encuentra fuera del área visible del panel
        if (xInicial + imagenPersonaje.getIconWidth() > panelMazmorra.getWidth()) {
            xInicial = panelMazmorra.getWidth() - imagenPersonaje.getIconWidth();
        }
        if (yInicial + imagenPersonaje.getIconHeight() > panelMazmorra.getHeight()) {
            yInicial = panelMazmorra.getHeight() - imagenPersonaje.getIconHeight();
        }
        panelMazmorra.add(labelPersonajeBody);
        panelMazmorra.setComponentZOrder(labelPersonajeBody, 0);
        panelMazmorra.revalidate();


    }

    /**
     * Agrega objetos aleatorios (espada, mitra, poción y oro) al panel de la mazmorra.
     */
    private void agregarObjetosRandom() {
        mostrarEspada();
        mostrarMitra();
        mostrarPocion();
        mostrarOro();
    }

    /**
     * Muestra una imagen de oro en una ubicación aleatoria dentro del panel de la mazmorra.
     */
    private void mostrarOro() {
        oro = new JLabel();
        ImageIcon imageicon = new ImageIcon("src/images/cueva/dollar.png");
        oro.setSize(16,16);
        Icon icon = new ImageIcon(
                imageicon.getImage().getScaledInstance(oro.getWidth(),oro.getHeight(), Image.SCALE_SMOOTH)
        );
        oro.setIcon(icon);
        oro.setLocation((int)(Math.random()*(600-98+1)+98),(int)(Math.random()*(540-98+1)+98));
        panelMazmorra.add(oro);
        panelMazmorra.setComponentZOrder(oro, 0);
        panelMazmorra.repaint();
    }

    /**
     * Muestra una imagen de poción en una ubicación aleatoria dentro del panel de la mazmorra.
     */
    private void mostrarPocion() {
        pocion = new JLabel();
        pocion.setText("pocion");
        ImageIcon imageicon = new ImageIcon("src/images/cueva/potion.png");
        pocion.setSize(16,16);
        Icon icon = new ImageIcon(
                imageicon.getImage().getScaledInstance(pocion.getWidth(),pocion.getHeight(), Image.SCALE_SMOOTH)
        );
        pocion.setIcon(icon);
        pocion.setLocation((int)(Math.random()*(600-98+1)+98),(int)(Math.random()*(540-98+1)+98));
        panelMazmorra.add(pocion);
        panelMazmorra.setComponentZOrder(pocion, 0);
        panelMazmorra.repaint();

    }

    /**
     * Muestra una imagen de mitra en una ubicación aleatoria dentro del panel de la mazmorra.
     */
    private void mostrarMitra() {
        mitra = new JLabel();
        mitra.setText("mitra");
        ImageIcon imageicon = new ImageIcon("src/images/cueva/mitra.png");
        mitra.setSize(16,16);
        Icon icon = new ImageIcon(
                imageicon.getImage().getScaledInstance(mitra.getWidth(),mitra.getHeight(), Image.SCALE_SMOOTH)
        );
        mitra.setIcon(icon);
        mitra.setLocation((int)(Math.random()*(600-98+1)+98),(int)(Math.random()*(540-98+1)+98));        panelMazmorra.add(mitra);
        panelMazmorra.setComponentZOrder(mitra, 0);
        panelMazmorra.repaint();

    }

    /**
     * Muestra una imagen de espada en una ubicación aleatoria dentro del panel de la mazmorra.
     */
    private void mostrarEspada() {
        espada = new JLabel();
        espada.setText("espada");
        ImageIcon imageicon = new ImageIcon("src/images/cueva/sword.png");
        espada.setSize(16,16);
        Icon icon = new ImageIcon(
                imageicon.getImage().getScaledInstance(espada.getWidth(),espada.getHeight(), Image.SCALE_SMOOTH)
        );
        espada.setIcon(icon);
        espada.setLocation((int)(Math.random()*(600-98+1)+98),(int)(Math.random()*(540-98+1)+98));        panelMazmorra.add(espada);
        panelMazmorra.setComponentZOrder(espada, 0);
        panelMazmorra.repaint();

    }

    /**
     * Mueve el personaje a las coordenadas especificadas.
     * Si hay colisión con un objeto, el movimiento se revierte.
     * Después de mover el personaje, verifica si puede recoger objetos en la nueva posición.
     *
     * @param dx La nueva coordenada X del personaje.
     * @param dy La nueva coordenada Y del personaje.
     */
    private void moverPersonaje(int dx, int dy) {
        // Guarda las coordenadas actuales del personaje
        int nuevaX = labelPersonajeBody.getX();
        int nuevaY = labelPersonajeBody.getY();

        labelPersonajeBody.setLocation(dx,dy);

        if (checkColision()){
            // Si hay colisión, revierte el movimiento
            labelPersonajeBody.setLocation(nuevaX,nuevaY);
        }
        checkRecogerObjetos();
    }

    /**
     * Verifica si hay colisión del personaje con los muros y la entrada/salida.
     *
     * @return true si hay colisión, false si no la hay.
     */
    private boolean checkColision() {
        boolean colision = false;

        Rectangle rectanglePersonaje = labelPersonajeBody.getBounds();

        // Verifica colisión con los muros
        for (JLabel muro: labelMuro) {
            Rectangle rectangleMuro = muro.getBounds();
            if (rectanglePersonaje.intersects(rectangleMuro) && !colision){
                colision = true;
            }
        }

        // Verifica colisión con la entrada/salida
        for (JLabel entrada: labelEntradaYSalida){
            Rectangle rectangleEntrada = entrada.getBounds();
            if (rectanglePersonaje.intersects(rectangleEntrada) && !colision){
                if (!(role.getOro() >= 50)){ // Si el personaje no tiene suficiente oro para pasar
                    colision = true;
                }else {
                    timerSeconds.stop();
                    timer.stop();

                    mostrarMensajeFinJuego("¡Has ganado! Felicidades :)");

                }

            }
        }

        return colision;
    }

    /**
     * Verifica si el personaje recoge algún objeto en la mazmorra y realiza las acciones correspondientes.
     */
    private void checkRecogerObjetos() {
        Rectangle rectanglePersonaje = labelPersonajeBody.getBounds();
        Rectangle rectangleEspada = espada.getBounds();
        Rectangle rectangleMitra = mitra.getBounds();
        Rectangle rectangleOro = oro.getBounds();
        Rectangle rectanglePocion = pocion.getBounds();

        if (rectanglePersonaje.intersects(rectangleEspada)){
            role.getObjetos().add(espada);
            panelMazmorra.remove(espada);
        }
        if (rectanglePersonaje.intersects(rectangleMitra)){
            role.getObjetos().add(mitra);
            panelMazmorra.remove(mitra);
        }
        if (rectanglePersonaje.intersects(rectangleOro)){
            oro.setLocation((int)(Math.random()*(600-98+1)+98),(int)(Math.random()*(540-98+1)+98));
            role.sumarOro();
            labelOro.setText("Oro: "+role.getOro());
        }

        if (rectanglePersonaje.intersects(rectanglePocion)){
            role.getObjetos().add(pocion);
            panelMazmorra.remove(pocion);
        }
        if (role != null && !role.getObjetos().isEmpty()) {
            int x=500,y=10;
            for (int i = 0; i < role.getObjetos().size(); i++) {
                role.getObjetos().get(i).setLocation(x+(role.getObjetos().get(i).getWidth()*i),y);
                panelTitulo.add(role.getObjetos().get(i));
            }
        }
    }

    /**
     * Agrega enemigos de forma aleatoria en la mazmorra y establece las condiciones para jugar.
     */
    private void agregarEnemigosRandom() {

        for (int i = 0; i < 6; i++) {
            enemigo = new Enemigo();
            enemigos.add(enemigo);
            panelMazmorra.add(enemigo.getBodyEnemigo());
            panelMazmorra.setComponentZOrder(enemigo.getBodyEnemigo(),0);
            panelMazmorra.repaint();
        }
        condicionesJugar(role);

    }

    /**
     * Establece las condiciones de juego para controlar el movimiento de los enemigos y las interacciones con el personaje.
     *
     * @param role El personaje del jugador.
     */
    private void condicionesJugar(Personaje role) {

        timer = new Timer(100, new ActionListener() { // mover cada 100 ms
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Enemigo> iterator = enemigos.iterator(); // Itera sobre la lista de enemigos
                while (iterator.hasNext()) {
                    Enemigo enemigo = iterator.next();
                    enemigo.mover();
                    // Verifica si colisiona con los bordes de la mazmorra
                    if (enemigo.getX() < 0 || enemigo.getX() > panelMazmorra.getWidth() - enemigo.getBodyEnemigo().getWidth()) {
                        enemigo.cambiarDireccion();
                    }
                    if (enemigo.getY() < 0 || enemigo.getY() > panelMazmorra.getHeight() - enemigo.getBodyEnemigo().getHeight()) {
                        enemigo.cambiarDireccion();
                    }

                    enemigo.cambiarPosicion();

                    // Verifica la colisión entre el enemigo y el personaje
                    if (enemigo.getBodyEnemigo().getBounds().intersects(labelPersonajeBody.getBounds())) {
                        boolean objetoUsado = false;
                        // Itera sobre los objetos del personaje
                        for (Iterator<JLabel> objIterator = role.getObjetos().iterator(); objIterator.hasNext();) {
                            JLabel objeto = objIterator.next();
                            // Verifica las interacciones según el tipo de personaje y objeto
                            if (role instanceof Guerrero && objeto.getText().equals("espada")) {
                                iterator.remove(); // Eliminar el enemigo de la lista de manera segura
                                panelMazmorra.remove(enemigo.getBodyEnemigo()); // Eliminar el JLabel del enemigo del panel
                                objIterator.remove(); // Eliminar el objeto usado
                                objetoUsado = true;
                                crearYverPanelTitulo();
                                break;

                            } else if (role instanceof Mago && objeto.getText().equals("pocion")) {
                                labelPersonajeBody.setLocation(100, 98);
                                role.ganarVida();
                                objIterator.remove();
                                objetoUsado = true;
                                crearYverPanelTitulo();
                                break;

                            } else if (role instanceof Sacerdote && objeto.getText().equals("mitra")) {
                                labelPersonajeBody.setLocation(100, 98);
                                objIterator.remove();
                                objetoUsado = true;
                                crearYverPanelTitulo();
                                break;
                            }
                        }
                        // Si no se utilizó ningún objeto, el personaje pierde vida
                        if (!objetoUsado) {
                            labelPersonajeBody.setLocation(100, 98);
                            role.perderVida();
                            crearYverPanelTitulo();
                            gameOver(timer);
                        }
                    }
                }
                panelMazmorra.repaint();
            }
        });
        timer.start();
        gameOver(timer);
    }

    /**
     * Verifica si el juego ha terminado debido a que el personaje ha perdido todas sus vidas.
     * Detiene los temporizadores si el juego ha terminado y muestra un mensaje de fin de juego.
     *
     * @param timer El temporizador del juego.
     */
    private void gameOver(Timer timer) {
        // Verifica si el personaje ha perdido todas sus vidas
        if (role.getVidas() <= 0) {
            timer.stop();
            timerSeconds.stop();
            mostrarMensajeFinJuego("¡Has perdido! Te has quedado sin vidas.");
        }
    }

    /**
     * Muestra un mensaje de fin de juego en una ventana emergente.
     * Detiene la ejecución del juego y, si el jugador acepta el mensaje, guarda los datos del juego en una base de datos y cierra la aplicación.
     *
     * @param mensaje El mensaje a mostrar al jugador.
     */
    private void mostrarMensajeFinJuego(String mensaje) {
        int opcion = JOptionPane.showOptionDialog(panelMazmorra, mensaje, "Fin del juego", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new Object[] { "Aceptar" }, "Aceptar");

        if (opcion == JOptionPane.OK_OPTION) {
            javaConBaseDeDatos();
            System.exit(0);
        }

    }




    // -------------------------------------------------------------------------- Conexión con Base de Datos ---------------------------------------------


    /**
     * Establece una conexión con una base de datos MySQL y guarda los datos de la partida actual.
     *
     * @throws SQLException si ocurre un error al conectar con la base de datos o al ejecutar la consulta.
     */
    private static void javaConBaseDeDatos() {
        String db_url = "jdbc:mysql://localhost:3306/alkahf";
        String user = "root";
        String paswd = "mysql";
        String insertQy = "insert into partida (nombre, tiempo, monedas)values (?,?,?)";

        try {
            // Establece la conexión con la base de datos
            Connection con = DriverManager.getConnection(db_url,user,paswd);
            // Prepara la consulta SQL para insertar los datos
            PreparedStatement ps = con.prepareStatement(insertQy);
            // Establece los valores de los parámetros en la consulta SQL
            ps.setString(1, role.getNombre());
            ps.setInt(2,seconds);
            ps.setInt(3,role.getOro());

            int addRows = ps.executeUpdate();
            if (addRows > 0){
                System.out.println("Valores insertados");
            }

            ps.close();
            con.close();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(panelMazmorra,"Error en la conexión");
        }

    }


    // ------------------------------------------------------------------------------ EVENTOS --------------------------------------------------------------------


    /**
     * Clase interna que maneja las acciones del botón "Empezar".
     * Al pulsar el botón, se crea y muestra el panel para elegir el personaje.
     */
    public class ButtonEmpezarListener implements ActionListener {
        /**
         * Método invocado cuando se realiza una acción sobre el botón "Empezar".
         *
         * @param e el evento de acción generado por el botón "Empezar"
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            crearYVerPanelElegirPersonaje();
        }
    }

    /**
     * Clase interna que maneja las acciones de los botones para elegir el personaje.
     * Al pulsar uno de los botones, se crea el personaje correspondiente y se inicia el juego.
     */
    public class ButtonElegirPersonajeListener implements ActionListener{
        /**
         * Método invocado cuando se realiza una acción sobre los botones para elegir el personaje.
         *
         * @param e el evento de acción generado por los botones para elegir el personaje
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource() == clickMago){
                role = crearPersonaje(new Mago(""));
            } else if (e.getSource() == clickGuerrero) {
                role = crearPersonaje(new Guerrero(""));
            } else if (e.getSource() == clickSacerdote) {
                role = crearPersonaje(new Sacerdote(""));
            }

            if(role != null){
                crearYVerPanelMazmorra();
                agregarObjetosRandom();
                agregarPersonajeBody(role);
                agregarEnemigosRandom();
            }

        }
    }

    /**
     * Clase interna que implementa ActionListener para manejar eventos de temporizador.
     */
    private class TimerSecondsListener implements ActionListener {
        /**
         * Incrementa el contador de segundos y actualiza la etiqueta de tiempo en la interfaz gráfica.
         *
         * @param e Evento de acción que desencadena el temporizador.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds++;
            labelTiempo.setText("Tiempo: " + seconds);
        }
    }

    /**
     * Clase para manejar eventos de teclado para mover al personaje en la interfaz gráfica.
     */
    public class KeyMoverPersonaje extends KeyAdapter implements KeyListener {
        Personaje role;

        /**
         * Constructor de la clase.
         *
         * @param role El personaje que se moverá en la interfaz gráfica.
         */
        public KeyMoverPersonaje(Personaje role) {
            this.role = role;
        }

        /**
         * Maneja los eventos de teclado cuando se presionan las teclas de dirección.
         *
         * @param e Evento de teclado que desencadena la acción.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);

            int x = labelPersonajeBody.getX();
            int y = labelPersonajeBody.getY();
            int velocidad = role.getVelocidad();

            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    y -= velocidad;
                    labelPersonajeBody.setIcon(role.getGifArriba());
                    break;
                case KeyEvent.VK_DOWN:
                    y += velocidad;
                    labelPersonajeBody.setIcon(role.getGifAbajo());
                    break;
                case KeyEvent.VK_LEFT:
                    x -= velocidad;
                    labelPersonajeBody.setIcon(role.getGifIzquierda());
                    break;
                case KeyEvent.VK_RIGHT:
                    x += velocidad;
                    labelPersonajeBody.setIcon(role.getGifDerecha());
                    break;
            }

            moverPersonaje(x,y);
        }
    }





}