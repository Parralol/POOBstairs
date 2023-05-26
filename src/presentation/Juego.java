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

public class Juego extends JFrame implements ActionListener {
    private Board b;
    private POOBStairs juego;

    private int num;
    private static int turn = 0;
    private static int[] playerPos = new int[Jugadores.numero];
    // objetos para la pantalla de juego
    private JButton botonDado, replay;
    private JLabel l1, l2, l3, l4;
    private JLabel[] playerList = new JLabel[Jugadores.numero];

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

    ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

    Juego(POOBStairs juego) {
        this.setTitle("Control de Juego");
        b = new Board(juego);
        this.juego = juego;
        int num = Jugadores.numero;
        for (int i = 0; i < num; i++) {
            playerPos[i] = 0;
        }
        int y = 20;
        for (int i = 0; i < num; i++) {
            playerList[i] = new JLabel(juego.getJugadores().get(i).getName());
            playerList[i].setBounds(20, y, 80, 20);
            add(playerList[i]);
            y += 30;
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
        l3 = new JLabel("Turno de Jugador");
        l3.setBounds(140, 20, 120, 20);
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
        //setSize(600, 200);
        setLayout(null);
        setVisible(true);
        //setLocation(650, 300);
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

    public int checkTurn() {
        int chance = (turn + 1) % num;
        l3.setText("Turn of player " + (chance + 1)); // Displays which player has to roll the dice.
        return turn % num;
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == botonDado) {
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
        }

        // if (e.getSource() == replay) {
        // dispose();
        // turn = 0;
        // new Juego(juego);
        // }
        b.dispose();
        b = null;
        b = new Board(juego);
    }

    private void jugar() {
        int b = juego.rollDice();
        // System.out.println(b);
        try {
            this.juego.jugar(b);
        } catch (POOBStairsException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // for (Jugador a : juego.getJugadores()) {
        // System.out.println(Arrays.toString(a.getFicha().getPos()));
        // }
    }

}
