package presentation;

import domain.POOBStairs;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
        botonDado.setBounds(450, 30, 100, 100);
        replay = new JButton("RE-PLAY");
        replay.setBounds(340, 30, 100, 100);
        replay.setVisible(false);
        l1 = new JLabel("ROLL");
        l1.setBounds(480, 130, 100, 30);
        l2 = new JLabel();
        l2.setBounds(470, 5, 100, 20);
        l3 = new JLabel("Turno de Jugador");
        l3.setBounds(240, 20, 120, 20);
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
        setIconImage(ico.getImage());
        setSize(600, 200);
        setLayout(null);
        setVisible(true);
        setLocation(650, 300);
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // for (Jugador a : juego.getJugadores()) {
        // System.out.println(Arrays.toString(a.getFicha().getPos()));
        // }
    }
}
