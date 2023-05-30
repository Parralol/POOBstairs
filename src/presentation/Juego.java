package presentation;

import domain.POOBStairs;
import domain.POOBStairsException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * The type Juego.
 */
public class Juego extends JFrame implements ActionListener {
    private Board b;
    private POOBStairs juego;

    private int num;
    private static int turn = 0;
    private static int[] playerPos = new int[Jugadores.numero];
    // objetos para la pantalla de juego
    private JButton botonDado, replay;
    private JLabel l1, l2, l3, l4;
    private JLabel[] playerList = new JLabel[Jugadores.numero * 3];

    /**
     * Crea las escaleras en el tablero.
     */
    final Map<Integer, Integer> escaleras = new HashMap<Integer, Integer>() {
        {

            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                int randomNumberTop = random.nextInt(51) + 50;
                int randomNumberBot = random.nextInt(50);
                // la llave es la base de la escalera y el valor es la cima
                put(randomNumberBot, randomNumberTop);
            }
        }
    };

    /**
     * The Serpientes.
     */
    final Map<Integer, Integer> serpientes = new HashMap<Integer, Integer>() {
        {

            for (int i = 0; i < 5; i++) {
                Random random = new Random();
                int randomNumberTop = random.nextInt(51) + 50;
                int randomNumberBot = random.nextInt(50);
                // la llave es la base de la escalera y el valor es la cima
                put(randomNumberBot, randomNumberTop);
            }
        }
    };

    /**
     * The Ico.
     */
    ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

    /**
     * Instantiates a new Juego.
     *
     * @param juego the juego
     */
    Juego(POOBStairs juego) {


        b = new Board(juego);
        this.juego = juego;

        JLabel backgroundLabel = new JLabel(new ImageIcon("src/resources/R.png"));
        backgroundLabel.setBounds(0, 0, getWidth(), getHeight());
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setOpaque(false);

        contentPane.add(backgroundLabel);

        this.setTitle("Control de Juego");
        int num = Jugadores.numero;
        for (int i = 0; i < num; i++) {
            playerPos[i] = 0;
        }
        int y = 20;
        for (int i = 0; i < num; i++) {
            playerList[i] = new JLabel(juego.getJugadores().get(i).getName());
            playerList[i].setBounds(20, y + 5, 80, 20);

            playerList[i + 1] = new JLabel("Max casillas recorridas:" + juego.getJugadores().get(i).getMaxCas());
            playerList[i + 1].setBounds(20, y + 20, 200, 20);

            playerList[i + 2] = new JLabel("Max escaleras recorridas:" + juego.getJugadores().get(i).getNumEsc());
            playerList[i + 2].setBounds(20, y + 30, 200, 20);

            playerList[i + 3] = new JLabel("Max serpientes recorridas:" + juego.getJugadores().get(i).getNumSer());
            playerList[i + 3].setBounds(20, y + 40, 200, 20);

            add(playerList[i]);
            add(playerList[i + 1]);
            add(playerList[i + 2]);
            add(playerList[i + 3]);
            y += 50;
        }

        botonDado = new JButton();
        botonDado.setBounds(350, 30, 100, 100);
        try {
            Image dInicial = ImageIO.read(new File("src/resources/dadob1.png")).getScaledInstance(100,
                    100, Image.SCALE_DEFAULT);
            botonDado.setIcon(new ImageIcon(dInicial));
        } catch (Exception e) {
            e.printStackTrace();
        }

        replay = new JButton("RE-PLAY");
        replay.setBounds(340, 30, 100, 100);
        replay.setVisible(false);
        l1 = new JLabel("ROLL");
        l1.setBounds(380, 130, 100, 30);
        l2 = new JLabel();
        l2.setBounds(470, 5, 100, 20);
        l3 = new JLabel("Comienza el juego\n Buena suerte y diviertete.");
        l3.setBounds(50, 1, 250, 50);
        l4 = new JLabel();
        l4.setBounds(210, 60, 180, 20);

        botonDado.addActionListener(this);
        replay.addActionListener(this);
        add(botonDado);
        add(replay);
        add(l1);
        add(l2);
        add(l3);
        add(l4);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Rectangle screenBounds = ge.getMaximumWindowBounds();

        int width = 500;
        int height = 200;
        int posX = screenBounds.x + screenBounds.width - width;
        int posY = screenBounds.y + screenBounds.height / 3;


        setBounds(posX, posY, width, height);
        setIconImage(ico.getImage());
        setLayout(null);
        setVisible(true);
        closeAction();
    }

    private void confirmClose() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void closeAction() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmClose();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check turn int.
     *
     * @return the int
     */
    public int checkTurn() {
        int turnoActual = turn % Jugadores.numero;
        if (turnoActual < Jugadores.numero - 1) {
            l3.setText("Turno de " + juego.getNombreJugadorEnTurno());
            // Es el turno de un jugador
        } else {
            l3.setText("Turno de la máquina");
            // Es el turno de la máquina
        }
        return turnoActual;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonDado) {
            l3.setText("Turno de " + juego.getNombreJugadorEnTurno());
            jugar();

            try {

                Image dado1 = ImageIO.read(new File("src/resources/dadob1.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);
                Image dado2 = ImageIO.read(new File("src/resources/dadob2.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);
                Image dado3 = ImageIO.read(new File("src/resources/dadob3.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);
                Image dado4 = ImageIO.read(new File("src/resources/dadob4.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);
                Image dado5 = ImageIO.read(new File("src/resources/dadob5.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);
                Image dado6 = ImageIO.read(new File("src/resources/dadob6.png")).getScaledInstance(100, 100,
                        Image.SCALE_DEFAULT);

                int b = juego.rollDice();
                if (b == 0) {
                    botonDado.setIcon(new ImageIcon(dado1));
                } else if (b == 1) {
                    botonDado.setIcon(new ImageIcon(dado2));
                } else if (b == 2) {
                    botonDado.setIcon(new ImageIcon(dado3));
                } else if (b == 3) {
                    botonDado.setIcon(new ImageIcon(dado4));
                } else if (b == 4) {
                    botonDado.setIcon(new ImageIcon(dado5));
                } else if (b == 5) {
                    botonDado.setIcon(new ImageIcon(dado6));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            turn++;

            if (turn % Jugadores.numero == 0) {
                moverMaquina();
            }
            repaint();
        }


        b.dispose();
        b = null;
        b = new Board(juego);
    }

    /**
     * Mueve la maquina en el tablero
     */
    private void moverMaquina() {
        if (checkTurn() == 0) {
            int b = juego.rollDice();
            //System.out.println(b);
            try {
                this.juego.jugar(b);
            } catch (POOBStairsException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }


    }

    private void jugar() {
        int b = juego.rollDice();
        // System.out.println(b);
        try {
            int m = juego.shouldMultiply();
            if(m != 1){
                JOptionPane.showMessageDialog(null, "Obtienes Multiplicador!!!, Valor:" + m);
            }
            this.juego.jugar(b * m);
            l1.repaint();
        } catch (POOBStairsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

}
