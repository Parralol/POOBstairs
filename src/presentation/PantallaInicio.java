package presentation;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PantallaInicio extends JFrame {
    public PantallaInicio() {
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
                JOptionPane.showMessageDialog(null, "Iniciar juego");
            }
        });

        btnCargarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "En construcción...");
                JOptionPane.showMessageDialog(null, "Cargar juego");
            }
        });

        btnReglas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Ver reglas");
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
