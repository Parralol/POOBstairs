package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicio extends JFrame {
    ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");
    public PantallaInicio() {

        setIconImage(ico.getImage());
        setTitle("Pantalla de Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JButton btnIniciar = new JButton("Iniciar");
        JButton btnCargarJuego = new JButton("Cargar Juego");
        JButton btnReglas = new JButton("Reglas");
        JButton btnSalir = new JButton("Salir");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(btnIniciar);
        panel.add(btnCargarJuego);
        panel.add(btnReglas);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Jugadores();
            }
        });

        btnCargarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "En construcción...");
            }
        });

        btnReglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Escaleras y serpientes es un juego de mesa que consiste\n" +
                        "en desplazarse por un tablero con el fin de ser el primero en\n" +
                        "llegar a la casilla final. \n" +
                        "En este juego participan dos o más personas en un tablero\n" +
                        "dividido en casillas numeradas que contiene escaleras\n" +
                        "(permiten llegar más rápido a la meta) y serpientes (hacen\n" +
                        "que el jugador retroceda). \n" +
                        "Los movimientos se determinan con un dado lanzado por los\n" +
                        "jugadores en su turno correspondiente.\n" +
                        "Es un antiguo juego indio diseñado para dar algunas\n" +
                        "lecciones de moral, donde el progreso de un jugador en el\n" +
                        "tablero representa una vida influida por virtudes —las\n" +
                        "escaleras— y por vicios —las serpientes—.");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmClose();
            }
        });
    }

    /**
     * Accion que genera un mensaje de confirmacion para cerrar ka ventana.
     */
    private void confirmClose() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

}
