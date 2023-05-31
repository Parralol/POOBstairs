package presentation;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import domain.POOBStairs;
import domain.POOBStairsException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * The type Pantalla inicio.
 */
public class PantallaInicio extends JFrame {
    /**
     * The Ico.
     */
    ImageIcon ico = new ImageIcon("Ficha-Roja.png");
    /**
     * The Background image.
     */
    Image backgroundImage;

    /**
     * Instantiates a new Pantalla inicio.
     */
    public PantallaInicio() {
        try {
            backgroundImage = ImageIO.read(new File("src/resources/Sponge1.png")).getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        setIconImage(ico.getImage());
        setTitle("Pantalla de Inicio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, this);
            }
        };
        contentPane.setLayout(new BorderLayout());

        JButton btnIniciar = new JButton("Iniciar");
        JButton btnCargarJuego = new JButton("Cargar Juego");
        JButton btnReglas = new JButton("Reglas");
        JButton btnSalir = new JButton("Salir");

        //cancion
        try {
            File audioFile = new File("src/resources/THEBEST.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            ex.printStackTrace();
        }

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(btnIniciar);
        panel.add(btnCargarJuego);
        panel.add(btnReglas);
        panel.add(btnSalir);

        contentPane.add(panel, BorderLayout.SOUTH);
        setContentPane(contentPane);
        setVisible(true);

        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Jugadores();
            }
        });

        btnCargarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("Extension archivo .dat", ".dat"));
                int respuesta = fileChooser.showOpenDialog(null);
        
                if (respuesta == JFileChooser.APPROVE_OPTION) {
                    POOBStairs xd = new POOBStairs();
                    try {
                        dispose();
                        xd = xd.open(fileChooser.getSelectedFile());
                        new Juego(xd);
                        
                    } catch (POOBStairsException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
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
