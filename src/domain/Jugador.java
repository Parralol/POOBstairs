package domain;

import java.awt.Color;

/**
 * cuerpo para la clase Jugador
 */
public abstract class Jugador {

    private String nombre;
    private int turno;
    private Color color;
    private boolean humano;
    private Ficha ficha;

    /**
     * constructor para Jugador
     */
    public Jugador(boolean humano, int turno) {
        ficha = new Ficha();
        this.humano = humano;
        this.turno = turno;
    }

    // METODOS PARA ASIGNAR DATOS

    /**
     * define el nombre del jugador
     * 
     * @param nombre
     */
    public void setName(String nombre) {
        this.nombre = nombre;
    }

    /**
     * define el color del jugador
     * 
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    // METODOS PARA OBTENER DATOS

    /**
     * retorna el turno del jugador
     * 
     * @return
     */
    public int getTurno() {
        return turno;
    }

    /**
     * retorna el color del jugador como un valor de Java awt
     * 
     * @return
     */
    public Color getColor() {
        return color;
    }

    /**
     * retorna un valor booleano, si es true es humano, si es false es maquina.
     * 
     * @return
     */
    public boolean isItHuman() {
        return humano;
    }

    /**
     * metodo para retornar el nombre del jugador
     * 
     * @return
     */
    public String getName() {
        return nombre;
    }

    /**
     * retorna la ficha del jugador
     * 
     * @return
     */
    public Ficha getFichas() {
        return ficha;
    }

}
