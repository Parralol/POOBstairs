package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que representa la configuracion del juego
 */
public class Config {
    JFrame frame;
    JPanel tamano;
    JPanel escaleras;
    JPanel serpientes;
    JPanel modificadores;
    JLabel seleccionarTamano;
    private static int size = 10;
    JComboBox<String> tamanoTablero;
    JLabel mensajeEscaleras;
    JLabel mensajeSerpientes;
    JLabel mensajeModificadores;
    JComboBox<Integer> porcEscaleras = new JComboBox<Integer>();
    JComboBox<Integer> porcSerpientes = new JComboBox<Integer>();
    JComboBox<Integer> probabilidadModificadores = new JComboBox<Integer>();
    JButton atras;
    JButton continuar;
    ImageIcon ico = new ImageIcon("src/resources/Ficha-Roja.png");

    POOBStairs juego;

    /**
     * Constructor de la clase Config
     * @param n
     */
    public Config(int n, POOBStairs juego) {
        this.juego = juego;
        frame = new JFrame();
        if (n == 0) {
            prepareElementsJVJ();
            frame.setTitle("Configuracion jugador contra jugador");
            frame.setIconImage(ico.getImage());
            prepareActions();

        } else {
            prepareElementsJVM();
            frame.setTitle("Configuracion jugador contra maquina");
            frame.setIconImage(ico.getImage());
            prepareActions();
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);

    }

    /**
     * Preparacion de elementos si el modo de juego es jugador vs jugador
     */
    public void prepareElementsJVJ(){
        frame = new JFrame();
        frame.setSize(350, 250);
        tamano = new JPanel();
        escaleras = new JPanel();
        serpientes = new JPanel();
        modificadores = new JPanel();

        seleccionarTamano = new JLabel("Selecciona el tamaño del tablero");
        tamanoTablero = new JComboBox<>(new String[]{"Tablero pequeño", "Tablero mediano", "Tablero grande"});
        tamanoTablero.addActionListener(e -> {
            String tamano = tamanoTablero.getSelectedItem().toString();
            if (tamano != null) {
                if (tamano.equals("Tablero pequeño")) {
                    size = 10;
                } else if (tamano.equals("Tablero mediano")) {
                    size = 20;
                } else {
                    size = 30;
                }
            }
        });
        tamano.add(seleccionarTamano);
        tamano.add(tamanoTablero);

        mensajeEscaleras = new JLabel("Probabilidad de escaleras");
        for (int i = 10; i <= 60; i+= 10) {
             porcEscaleras.addItem(i);
        }
        escaleras.add(mensajeEscaleras);
        escaleras.add(porcEscaleras);

        mensajeSerpientes = new JLabel("Probabilidad de serpientes");
        for (int i = 10; i <= 60; i+= 10) {
            porcSerpientes.addItem(i);
        }
        serpientes.add(mensajeSerpientes);
        serpientes.add(porcSerpientes);

        mensajeModificadores = new JLabel("Probabilidad de modificadores");
        for (int i = 10; i <= 60; i+= 10) {
            probabilidadModificadores.addItem(i);
        }
        modificadores.add(mensajeModificadores);
        modificadores.add(probabilidadModificadores);



        atras = new JButton("Atras");
        continuar = new JButton("Continuar");

        frame.add(tamano);
        frame.add(escaleras);
        frame.add(serpientes);
        frame.add(modificadores);

        frame.add(atras);
        frame.add(continuar);

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);


    }

    /**
     * Preparacion de elementos si el modo de juego es jugador vs maquina
     */
    private void prepareElementsJVM(){
        frame = new JFrame();
        frame.setSize(400, 300);
        tamano = new JPanel();
        escaleras = new JPanel();
        serpientes = new JPanel();
        modificadores = new JPanel();

        seleccionarTamano = new JLabel("Selecciona el tamaño del tablero");
        tamanoTablero = new JComboBox<>(new String[]{"Tablero pequeño", "Tablero mediano", "Tablero grande"});
        tamanoTablero.addActionListener(e -> {
            String tamano = tamanoTablero.getSelectedItem().toString();
            if (tamano != null) {
                if (tamano.equals("Tablero pequeño")) {
                    size = 10;
                } else if (tamano.equals("Tablero mediano")) {
                    size = 20;
                } else {
                    size = 30;
                }
            }
        });
        tamano.add(seleccionarTamano);
        tamano.add(tamanoTablero);

        mensajeEscaleras = new JLabel("Porcentaje de escaleras");
        for (int i = 10; i <= 60; i+= 10) {
            porcEscaleras.addItem(i);
        }
        escaleras.add(mensajeEscaleras);
        escaleras.add(porcEscaleras);

        mensajeSerpientes = new JLabel("Porcentaje de serpientes");
        for (int i = 10; i <= 60; i+= 10) {
            porcSerpientes.addItem(i);
        }
        serpientes.add(mensajeSerpientes);
        serpientes.add(porcSerpientes);

        mensajeModificadores = new JLabel("Probabilidad de modificadores");
        for (int i = 10; i <= 60; i+= 10) {
            probabilidadModificadores.addItem(i);
        }
        modificadores.add(mensajeModificadores);
        modificadores.add(probabilidadModificadores);

        atras = new JButton("Atras");
        continuar = new JButton("Continuar");

        frame.add(tamano);
        frame.add(escaleras);
        frame.add(serpientes);
        frame.add(modificadores);

        frame.add(atras);
        frame.add(continuar);

        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
    }

    /**
     * Preparacion de acciones de los botones
     */
    private void prepareActions(){
        atras.addActionListener(e -> {
            frame.dispose();
            new Jugadores();
        });
        continuar.addActionListener(e -> {
            
            int pesc = (int) porcEscaleras.getSelectedItem();
            int pserp = (int) porcSerpientes.getSelectedItem();
            int pesp = (int) probabilidadModificadores.getSelectedItem() /100;
            juego.createTablero(pesc, pserp, pesp, getSize());
            frame.dispose();
            new Juego(juego);
        });
    }

    /**
     * Retorna el tamaño del tablero
     * @return
     */
    public static int getSize(){
        return size;
    }
}
