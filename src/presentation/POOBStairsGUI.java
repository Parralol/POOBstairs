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
public class POOBStairsGUI extends javax.swing.JFrame {

    private POOBStairs juego;

    private Color background;
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
    private JMenuItem cambiarColorTablero;
    private JMenuItem cambiarColorFichas1;
    private JMenuItem cambiarColorFichas2;
    private JPanel gameBoard;
    private JMenuItem resizeButton;
    private JFileChooser choose;
    private JFileChooser chooseSave;


    /**
     * Instantiates a new Conecta 4 gui.
     */
    public POOBStairsGUI() {
        setTitle("POOBStairs");
        prepareElements();
        prepareElementsMenu();
        prepareActions();
        prepareElementsBoard();

    }

    /**
     * Prepare elements.
     */
    public void prepareElements() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width / 2, screenSize.height / 2);
        setLocationRelativeTo(null);
        juego = new POOBStairs();
        background = Color.WHITE;
        background = new Color(255, 255, 255);

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

        // cambiarColor = new JMenuItem("Cambiar Color");
        cambiarColorTablero = new JMenuItem("Color del Tablero");
        cambiarColorFichas1 = new JMenuItem("Color de las fichas del jugador 1");
        cambiarColorFichas2 = new JMenuItem("Color de las fichas del jugador 2");
        resizeButton = new JMenuItem("Cambiar el tamaño del tablero");

        opciones.add(nuevo);
        opciones.add(abrir);
        opciones.add(salvar);
        opciones.add(salir);
        menuB.add(opciones);

        edit.add(resizeButton);
        edit.add(cambiarColorFichas1);
        edit.add(cambiarColorFichas2);
        edit.add(cambiarColorTablero);

        menuB.add(edit);

        setJMenuBar(menuB);
    }

    /**
     *  Prepare actions menu.
     */
    private void prepareActionsMenu() {
        salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                confirmClose();
            }
        });

        resizeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int newRows = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo numero de filas:"));
                int newCols = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo numero de columnas:"));
                resizeBoard(newRows, newCols);
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

        cambiarColorTablero.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                changeColorAction();
            }
        });

    }

    /**
     * Crea un dialogo para confirmar el reinicio del juego.
     */
    private void newAction() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea crear un nuevo juego?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            gameBoard.removeAll();
            refresh();
            prepareElementsBoard();
        }

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
     * Cambia el tamaño.
     */
    private void resizeBoard(int rows, int cols) {
        gameBoard.setLayout(new GridLayout(rows, cols));
        gameBoard.removeAll();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                JButton button = new JButton();
                button.setBackground(Color.WHITE);
                gameBoard.add(button);
            }
        }
        gameBoard.revalidate();
    }
    /**
     * Change turn.
     */
    public void changeTurn() {
        juego.changeTurn();
    }

    /**
     * Revalida.
     */
    public void revalida() {
        revalidate();
        refresh();
    }





    /**
     * Accion que genera un mensaje de confirmacion para cerrar ka ventana.
     */
    private void confirmClose() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la apliacion?", "Advertencia",
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

    private void refresh() {
        this.repaint();
    }


    /**
     * Accion que cambia el color.
     */
    private void changeColorAction() {
        Color color = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        background = color;
        if (juego.getAllTurns() > 1) {
            JOptionPane.showMessageDialog(null, "No es posible cambiar de color, solo puedes antes del primer turno");
        } else {
            Component[] components = gameBoard.getComponents();
            for (Component component : components) {
                if (component instanceof JButton) {
                    JButton button = (JButton) component;
                    button.setBackground(background);
                    refresh();
                }

            }
        }
    }



    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        POOBStairsGUI POOBStairsGUI = new POOBStairsGUI();
        POOBStairsGUI.setVisible(true);
    }
}
