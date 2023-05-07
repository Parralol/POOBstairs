package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jugadores extends JFrame implements ActionListener {
    static int numero;
    JComboBox<String> numeroJugadores;   // JComboBox is used for creating dropdown of player count.
    JLabel mensajeJugadores;
    final String[] nums;

    Jugadores() {
        nums = new String[]{"2", "3", "4"};
        numeroJugadores = new JComboBox<>(nums);
        numeroJugadores.addActionListener(this);
        mensajeJugadores = new JLabel("Select number of Jugadores : ");
        add(mensajeJugadores);
        add(numeroJugadores);
        setSize(200, 150);
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
            dispose();
            new Juego();
        }
    }
}
