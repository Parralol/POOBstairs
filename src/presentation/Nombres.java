package presentation;

import domain.POOBStairs;

import javax.swing.*;
import java.util.HashMap;

/**
 * The type Nombres.
 */
public class Nombres extends JPanel{
    private JTextField nombreJ;
    private String nombre;
    private int numeroJ, contador;
    private HashMap<String, Integer> jugadores;
    private JLabel nombreJugador;
    private JPanel inputPanel;
    private JPanel buttonsPanel;
    private JButton confirmar;
    private JButton cancelar;
    private JButton atras;

    /**
     * Instantiates a new Nombres.
     *
     * @param n the n
     */
    public Nombres(int n) {
    }
}
