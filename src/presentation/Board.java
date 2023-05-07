package presentation;

import domain.POOBStairs;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


/**
 * The type POOBStairs gui.
 */
public class Board extends javax.swing.JFrame {

    private POOBStairs juego;

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JLabel[][] boardSquares = new JLabel[10][10];

    // ATRIBUTOS GRAFICOS
    private JMenuBar menuB;
    private JMenu opciones;
    private JMenu edit;
    private JMenuItem nuevo;
    private JMenuItem abrir;
    private JMenuItem salvar;
    private JMenuItem salir;
    private JPanel gameBoard;
    private JFileChooser choose;
    private JFileChooser chooseSave;




    /**
     * Instantiates a new Conecta 4 gui.
     */
    public Board() {
        setTitle("POOBStairs");
        prepareElements();
        prepareElementsMenu();
        prepareActions();
        prepareElementsBoard();
        setVisible(true);
    }


    /**
     * Prepare elements.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        juego = new POOBStairs();

    }

    /**
     * Prepare actions.
     */
    public void prepareActions() {
        closeAction();
        prepareActionsMenu();
    }


    /**
     * Prepare elements menu.
     */
    private void prepareElementsMenu() {
        menuB = new JMenuBar();
        edit = new JMenu("Editar");
        opciones = new JMenu("Opciones");

        nuevo = new JMenuItem("Nuevo");
        abrir = new JMenuItem("Abrir");
        salvar = new JMenuItem("Salvar");
        salir = new JMenuItem("Salir");

        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.add(salvar);
        opciones.add(salir);
        menuB.add(opciones);


        setJMenuBar(menuB);
    }

    private void prepareElementsBoard() {

        gameBoard = new JPanel(new GridLayout(10, 10));
        gameBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        gameBoard.isFontSet();
        Border border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(5, 5, 5, 5));
        int numerator = boardSquares.length * boardSquares[0].length;
        for (int i = 0; i < boardSquares[0].length; i++) {
            for (int j = boardSquares.length - 1; j >= 0; j--) {
                int r = (int) (Math.random() * 256);
                int g = (int) (Math.random() * 256);
                int b = (int) (Math.random() * 256);
                Color background = new Color(r, g, b);
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.RIGHT);
                label.setVerticalAlignment(SwingConstants.TOP);
                label.setOpaque(true);
                label.setBackground(background);
                label.setBorder(border);
                label.setText(String.valueOf(numerator - i * boardSquares.length - j));
                boardSquares[j][i] = label;
                gameBoard.add(boardSquares[j][i]);
            }
        }
        add(gameBoard);

    }
    /**
     * Prepare actions menu.
     */
    private void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                confirmClose();
            }
        });

        abrir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                openActiont();
            }
        });

        salvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                saveAction();
            }
        });

        nuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                newAction();
            }
        });

    }

    /**
     * Crea un dialogo para confirmar el reinicio del juego.
     */
    private void newAction() {
        int n = JOptionPane.showConfirmDialog(this, "¿Desea iniciar un nuevo juego?", "Nuevo Juego", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            juego = new POOBStairs();
            prepareElementsBoard();
            repaint();
        }
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


    /**
     * Accion que define el comportamiento al cerrar el juego.
     */
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
     * Accion que abre un archivo.
     */
    private void openActiont() {
        choose = new JFileChooser();
        int valor = choose.showOpenDialog(this);
        if (valor == JFileChooser.APPROVE_OPTION) {
            File file = choose.getSelectedFile();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String linea = br.readLine();
                while (linea != null) {
                    System.out.println(linea);
                    linea = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Accion que guarda un archivo.
     */
    private void saveAction() {
        chooseSave = new JFileChooser(new File("c:\\"));
        chooseSave.setDialogTitle("Save a File");
        chooseSave.showSaveDialog(null);
    }


    /**
     * Change turn.
     */
    public void changeTurn() {
        juego.changeTurn();
    }






}

