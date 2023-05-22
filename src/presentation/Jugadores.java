package presentation;

import javax.swing.*;

import domain.Humano;
import domain.Jugador;
import domain.POOBStairs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jugadores extends JFrame implements ActionListener {
    static int numero;
    private POOBStairs juego;
    private static Color[] colores = { Color.YELLOW, Color.BLUE, Color.PINK, Color.RED };
    private JComboBox<String> numeroJugadores; // JComboBox is used for creating dropdown of player count.
    private JLabel mensajeJugadores;
    private final String[] nums;
    ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

    Jugadores() {
        this.setTitle("Jugadores");
        setIconImage(ico.getImage());
        juego = new POOBStairs();
        nums = new String[] { "2", "3", "4" };
        numeroJugadores = new JComboBox<>(nums);
        numeroJugadores.addActionListener(this);
        mensajeJugadores = new JLabel("Selecciona el número de Jugadores: ");
        add(mensajeJugadores);
        add(numeroJugadores);
        setSize(250, 150);
        setLayout(new FlowLayout());
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == numeroJugadores) {
            String str = new String();
            str = numeroJugadores.getSelectedItem().toString();
            numero = Integer.parseInt(str);
            for (int i = 0; i < numero; i++) {
                Jugador xd = new Humano(i);
                xd.setColor(colores[i]);
                int turno = i + 1;
                xd.setName("j" + turno);
                try {
                    juego.addJugador(xd);
                } catch (Exception r) {
                    JOptionPane.showMessageDialog(null, r.getMessage());
                }
            }
            dispose();
            new Juego(juego);
        }
    }
}
