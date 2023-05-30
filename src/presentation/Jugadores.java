package presentation;

import javax.swing.*;
import domain.Humano;
import domain.Jugador;
import domain.POOBStairs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class Jugadores extends JFrame implements ActionListener {
    static int numero;
    private POOBStairs juego;
    private static Color[] colores = { Color.YELLOW, Color.BLUE, Color.PINK, Color.RED };
    private JComboBox<String> modoJuego;
    private JLabel mensajeJugadores;
    private final String[] nums;
    private ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

    Jugadores() {
        this.setTitle("Jugadores");
        setIconImage(ico.getImage());
        juego = new POOBStairs();
        nums = new String[] {"Jugador vs Maquina", "Jugador vs Jugador"};
        modoJuego = new JComboBox<>(nums);
        modoJuego.addActionListener(this);
        mensajeJugadores = new JLabel("Selecciona el modo de juego:");
        add(mensajeJugadores);
        add(modoJuego);

        setSize(250, 150);
        setLayout(new FlowLayout());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String modo = Objects.requireNonNull(modoJuego.getSelectedItem()).toString();
        if (modo != null) {
            if (modo.equals("Jugador vs Maquina")) {
                Jugador xd = new Humano(0);
                xd.setColor(colores[0]);
                String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador 1");
                if (nombre != null) {
                    xd.setName(nombre);
                    try {
                        juego.addJugador(xd);
                    } catch (Exception r) {
                        JOptionPane.showMessageDialog(null, r.getMessage());
                    }

                } else {
                    modoJuego.setSelectedIndex(0);
                    return;
                }
                dispose();
                new Config(1);
            } else {
                String str = modoJuego.getSelectedItem().toString();
                numero = 2;

                for (int i = 0; i < numero; i++) {
                    Jugador xd = new Humano(i);
                    xd.setColor(colores[i]);
                    String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador " + (i + 1));
                    if (nombre != null) {
                        xd.setName(nombre);
                        try {
                            juego.addJugador(xd);
                        } catch (Exception r) {
                            JOptionPane.showMessageDialog(null, r.getMessage());
                        }
                    } else {
                        modoJuego.setSelectedIndex(0);
                        return;
                    }
                }
                dispose();
                new Config(0);
            }
        }



        if (e.getSource() == modoJuego) {
            String str = modoJuego.getSelectedItem().toString();
            numero = Integer.parseInt(str);

            for (int i = 0; i < numero; i++) {
                Jugador xd = new Humano(i);
                xd.setColor(colores[i]);


                String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador " + (i + 1));
                if (nombre != null) {
                    xd.setName(nombre);
                    try {
                        juego.addJugador(xd);
                    } catch (Exception r) {
                        JOptionPane.showMessageDialog(null, r.getMessage());
                    }
                } else {

                    modoJuego.setSelectedIndex(0);
                    return;
                }
            }

            dispose();
            new Juego(juego);
        }

    }

        /**
         *

        if (e.getSource() == modoJuego) {
            String str = modoJuego.getSelectedItem().toString();
            numero = Integer.parseInt(str);

            for (int i = 0; i < numero; i++) {
                Jugador xd = new Humano(i);
                xd.setColor(colores[i]);


                String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador " + (i + 1));
                if (nombre != null) {
                    xd.setName(nombre);
                    try {
                        juego.addJugador(xd);
                    } catch (Exception r) {
                        JOptionPane.showMessageDialog(null, r.getMessage());
                    }
                } else {

                    modoJuego.setSelectedIndex(0);
                    return;
                }
            }

            dispose();
            new Juego(juego);
        }
         **/
    }

