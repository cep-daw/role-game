package gameClasses;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Clase Abstracta Personaje
 */
public abstract class Personaje {
    protected String nombre;
    protected int vidas;
    protected int oro;
    protected int velocidad;
    protected ArrayList<JLabel> objetos;
    protected int x;
    protected int y;
    protected ImageIcon GIF_ABAJO;
    protected ImageIcon GIF_ARRIBA;
    protected ImageIcon GIF_DERECHA;
    protected ImageIcon GIF_IZQUIERDA;
    private JLabel body;


    /**
     * Constructor de la clase Personaje.
     * @param nombre Nombre del personaje.
     * @param vidas Número de vidas del personaje.
     * @param oro Cantidad de oro del personaje.
     * @param velocidad Velocidad de movimiento del personaje.
     */
    public Personaje(String nombre, int vidas, int oro, int velocidad) {
        this.nombre = nombre;
        this.vidas = vidas;
        this.oro = oro;
        this.velocidad = velocidad;
        this.body = new JLabel();
        this.body.setSize(32,32);
        this.x = 100;
        this.y = 98;
        this.objetos = new ArrayList<>();
    }

    // --------------------------------------------------------- Setters & Getters -----------------------------------------------------------
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getVidas() {
        return this.vidas;
    }
    public int getOro() {
        return this.oro;
    }
    public int getVelocidad() {
        return this.velocidad;
    }
    public ArrayList<JLabel> getObjetos() {
        return this.objetos;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public ImageIcon getGifAbajo() {
        return GIF_ABAJO;
    }
    public  ImageIcon getGifArriba() {
        return GIF_ARRIBA;
    }
    public  ImageIcon getGifDerecha() {
        return GIF_DERECHA;
    }
    public  ImageIcon getGifIzquierda() {
        return GIF_IZQUIERDA;
    }


    // ----------------------------------------------------- Métodos --------------------------------------------------------------------

    /**
     * Método abstracto para solicitar al usuario el nombre del personaje.
     * @return true si se proporciona un nombre válido para el personaje, false en caso contrario.
     */
    public abstract boolean pedirNombre();

    /**
     * Incrementa la cantidad de oro del personaje en 10 unidades.
     */
    public void sumarOro(){
        this.oro += 10;
    }

    /**
     * Reduce en 1 la cantidad de vidas del personaje.
     */
    public void perderVida(){
        this.vidas -= 1;
    }

    /**
     * Aumenta en 1 la cantidad de vidas del personaje.
     */
    public void ganarVida(){
        this.vidas += 1;
    }


}

