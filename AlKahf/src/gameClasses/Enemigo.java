package gameClasses;

import javax.swing.*;
import java.util.Random;

/**
 * Clase que representa un enemigo en el juego.
 */
public class Enemigo {
    private JLabel bodyEnemigo;
    private int velocidad;
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int vida;
    protected ImageIcon GIF_ABAJO;
    protected ImageIcon GIF_ARRIBA;
    protected ImageIcon GIF_DERECHA;
    protected ImageIcon GIF_IZQUIERDA;


    /**
     * Constructor de la clase Enemigo. Inicializa las características del enemigo.
     */
    public Enemigo() {
        this.velocidad = 4;
        this.x = (int)(Math.random()*(600-98+1)+98);
        this.y = (int)(Math.random()*(540-98+1)+98);
        this.vida = 2;
        this.GIF_ABAJO = new ImageIcon("src/images/esqueleto/skeleton_down.gif");
        this.GIF_ARRIBA = new ImageIcon("src/images/esqueleto/skeleton_up.gif");
        this.GIF_DERECHA = new ImageIcon("src/images/esqueleto/skeleton_right.gif");
        this.GIF_IZQUIERDA = new ImageIcon("src/images/esqueleto/skeleton_left.gif");
        this.bodyEnemigo = new JLabel();
        this.bodyEnemigo.setIcon(GIF_ABAJO);
        this.bodyEnemigo.setSize(64,64);

        empezarMovimiento();
        cambiarPosicion();
    }

// ---------------------------------------------------------- Getters --------------------------------------------------------------------

    public JLabel getBodyEnemigo() {
        return bodyEnemigo;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }


    // ----------------------------------------------------- Métodos --------------------------------------------------------------------


    /**
     * Inicia el movimiento del enemigo de manera aleatoria, ya sea en dirección horizontal o vertical.
     */
    private void empezarMovimiento() {
        Random random = new Random();
        boolean isHorizontal = random.nextBoolean();

        if (isHorizontal) { //horizontal
            if (random.nextBoolean()) { // Si el siguiente valor aleatorio es verdadero.
                dx += velocidad; // Se incrementa la posición en el eje x para moverse hacia la derecha.
                bodyEnemigo.setIcon(GIF_DERECHA);
            } else { // Si el siguiente valor aleatorio es falso.
                dx -= velocidad; // Se decrementa la posición en el eje x para moverse hacia la izquierda.
                bodyEnemigo.setIcon(GIF_IZQUIERDA);
            }
            dy = 0; // La posición en el eje y se mantiene en cero.
        } else { //vertical
            if (random.nextBoolean()) {
                dy += velocidad; // Se incrementa la posición en el eje y para moverse hacia abajo.
                bodyEnemigo.setIcon(GIF_ABAJO);
            } else {
                dy -= velocidad; // Se decrementa la posición en el eje y para moverse hacia arriba.
                bodyEnemigo.setIcon(GIF_ARRIBA);
            }
            dx = 0; // La posición en el eje x se mantiene en cero.
        }

    }

    /**
     * Realiza el movimiento del enemigo en la dirección establecida por los valores de dx y dy.
     * Se verifica que el nuevo movimiento mantenga al enemigo dentro de los límites del área de juego.
     * Si el movimiento resulta en que el enemigo salga de los límites del área de juego, se cambia su dirección.
     */
    public void mover() {
        int nuevaX = x + dx; // Calcula la nueva posición en el eje x sumando el desplazamiento horizontal.
        int nuevaY = y + dy; // Calcula la nueva posición en el eje y sumando el desplazamiento vertical.

        // Verifica si la nueva posición mantiene al enemigo dentro de los límites del área de juego.
        if (nuevaX >= 82 && nuevaX + bodyEnemigo.getWidth() <= 82 + 40 * 16 && nuevaY >= 82 && nuevaY + bodyEnemigo.getHeight() <= 82 + 32 * 16){
            x += dx; // Actualiza la posición en el eje x con el desplazamiento horizontal.
            y += dy; // Actualiza la posición en el eje y con el desplazamiento vertical.
            cambiarPosicion();
        }else {
            cambiarDireccion();
        }

    }

    /**
     * Actualiza la posición visual del enemigo estableciendo su ubicación en las coordenadas (x, y).
     */
    public void cambiarPosicion() {
        bodyEnemigo.setLocation(x, y);
    }

    /**
     * Cambia la dirección del movimiento del enemigo.
     * Si el enemigo se mueve horizontalmente, invierte su dirección en el eje x y actualiza su icono.
     * Si el enemigo se mueve verticalmente, invierte su dirección en el eje y y actualiza su icono.
     */
    public void cambiarDireccion() {

        if (dx != 0) { //si se mueve horizontal
            dx = -dx; // invertir la dirección horizontal
            if (dx > 0) {
                bodyEnemigo.setIcon(GIF_DERECHA);
            } else {
                bodyEnemigo.setIcon(GIF_IZQUIERDA);
            }
        } else if (dy != 0) { //si se mueve vertical
            dy = -dy; // invertir la dirección vertical
            if (dy > 0) {
                bodyEnemigo.setIcon(GIF_ABAJO);
            } else {
                bodyEnemigo.setIcon(GIF_ARRIBA);
            }
        }
    }


}
