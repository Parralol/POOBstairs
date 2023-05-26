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
    private JComboBox<String> numeroJugadores;
    private JLabel mensajeJugadores;
    private final String[] nums;
    private ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

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
            String str = numeroJugadores.getSelectedItem().toString();
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

                    numeroJugadores.setSelectedIndex(0);
                    return;
                }
            }

            dispose();
            new Juego(juego);
        }
    }
}
