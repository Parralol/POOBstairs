package presentation;

import javax.swing.*;
import domain.Humano;
import domain.Jugador;
import domain.Maquina;
import domain.POOBStairs;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * The type Jugadores.
 */
public class Jugadores extends JFrame implements ActionListener {
    /**
     * The Numero.
     */
    static int numero;
    private POOBStairs juego;
    private static Color[] colores = { Color.YELLOW, Color.BLUE, Color.PINK, Color.RED };
    private JComboBox<String> modoJuego;
    private JLabel mensajeJugadores;
    private final String[] nums;
    private ImageIcon ico = new ImageIcon("C:/Users/USER/Downloads/POOBStairs media/Ficha-Roja.png");

    /**
     * Instantiates a new Jugadores.
     */
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
        Object[] color = {"Amarillo", "Azul" ,"Rosado", "Rojo"};
        String modo = Objects.requireNonNull(modoJuego.getSelectedItem()).toString();
        if (modo != null) {
            if (modo.equals("Jugador vs Maquina")) {
                numero = 2;
                Jugador maquina = new Maquina(1);
                int cMaq = 0;
                Jugador xd = new Humano(0);
                xd.setColor(colores[0]);
                String nombre = JOptionPane.showInputDialog(this,  "Ingrese el nombre del Jugador 1");
                int res = JOptionPane.showOptionDialog(null, "Elige un color de los siguientes",
                "Elige un color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, color, color[0]);
                if (nombre != null) {
                    for(int i= 0; i<=3 ; i++ ){
                        if(i != res){
                            cMaq = i;
                        }
                    }
                    maquina.setColor(color(cMaq));
                    maquina.setName("Maquina");
                    xd.setName(nombre);
                    xd.setColor(color(res));
                    try {
                        juego.addJugador(maquina);
                        juego.addJugador(xd);
                    } catch (Exception r) {
                        JOptionPane.showMessageDialog(null, r.getMessage());
                        dispose();
                        new Jugadores();
                    }

                } else {
                    modoJuego.setSelectedIndex(0);
                    return;
                }
                dispose();
                new Config(1, juego);
            } else {
                numero = 2;
                for (int i = 0; i < numero; i++) {
                    Jugador xd = new Humano(i);
                    xd.setColor(colores[i]);
                    String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del Jugador " + (i + 1));
                    int res = JOptionPane.showOptionDialog(null, "Elige un color de los siguientes",
                "Elige un color",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, color, color[0]);
                    if (nombre != null) {
                        xd.setName(nombre);
                        xd.setColor(color(res));
                        try {
                            juego.addJugador(xd);
                        } catch (Exception r) {
                            JOptionPane.showMessageDialog(null, r.getMessage());
                            dispose();
                            new Jugadores();
                        }
                    } else {
                        modoJuego.setSelectedIndex(0);
                        return;
                    }
                }
                dispose();
                new Config(0, juego);
            }
        }



       

            dispose();
            //new Juego(juego);
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
        private Color color(int i){
            Color res = null;
            if(i == 0) res = Color.YELLOW;
            if(i == 1) res = Color.BLUE;
            if(i == 2) res = Color.PINK;
            if(i == 3) res = Color.RED;
            return res;
        }
    }


