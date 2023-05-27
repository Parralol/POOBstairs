package presentation;

import javax.swing.*;
import java.awt.*;

public class Config {
    JFrame frame;
    JPanel jugadores;
    JLabel nombreJugador;
    JLabel nombreJugador2;
    JTextField nombreJ1;
    JTextField nombreJ2;
    JButton atras;
    JButton continuar;
    ImageIcon ico = new ImageIcon("src/resources/Ficha-Roja.png");





    public Config(int n) {
        frame.setIconImage(ico.getImage());
        if (n == 0) {
            prepareElementsJVJ();
            frame.setTitle("Configuracion jugador contra jugador");
        } else {
            prepareElementsJVM();
            frame.setTitle("Configuracion jugador contra maquina");
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Preparacion de elementos si el modo de juego es jugador vs jugador
     */
    public void prepareElementsJVJ(){
        frame = new JFrame();
        frame.setSize(500, 500);
        jugadores = new JPanel();
        nombreJugador = new JLabel("Nombre del jugador 1");
        nombreJugador2 = new JLabel("Nombre del jugador 2");
        nombreJ1 = new JTextField();
        nombreJ1.setPreferredSize(new Dimension(100,20));
        nombreJ2 = new JTextField();
        nombreJ2.setPreferredSize(new Dimension(100,20));
        jugadores.add(nombreJugador);
        jugadores.add(nombreJ1);
        jugadores.add(nombreJugador2);
        jugadores.add(nombreJ2);

        atras = new JButton("Atras");
        continuar = new JButton("Continuar");

        frame.add(atras);
        frame.add(continuar);

        frame.add(jugadores);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);


    }

    /**
     * Preparacion de elementos si el modo de juego es jugador vs maquina
     */
    private void prepareElementsJVM(){
        frame = new JFrame();
        frame.setSize(500, 500);
        jugadores = new JPanel();
        nombreJugador = new JLabel("Nombre del jugador 1");
        nombreJ1 = new JTextField();
        nombreJ1.setPreferredSize(new Dimension(100,20));
        jugadores.add(nombreJugador);
        jugadores.add(nombreJ1);
        frame.add(jugadores);

        atras = new JButton("Atras");
        continuar = new JButton("Continuar");

        frame.add(atras);
        frame.add(continuar);

        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }
}
