package domain;

import java.util.ArrayList;

/**
 * Clase Tablero
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class Tablero {
    private ArrayList<Casilla> casillas;

    /**
     * Constructor para tablero
     */
    public Tablero() {
        casillas = new ArrayList<Casilla>();
        generateCasillasNormal();

    }

    // METODOS PARA CREAR O ASIGNAR DATOS

    /**
     * Realiza una jugada dado el turno y la posicion donde debe moverse
     * 
     * @param ficha
     * @param pos
     */
    public void jugar(Ficha ficha, int[] pos) {
        for (Casilla a : casillas) {
            if (a.getColor() == ficha.getColor()) {
                a.removeFicha();
            }
        }
        int a = Integer.parseInt(pos[0] + "" + pos[1]);
        ficha.changePos(pos);
        casillas.get(a).setFicha(ficha);
    }
    // METODOS PARA RETORNAR DATOS

    /**
     * Obtiene todas las casillas
     * 
     * @return
     */
    public ArrayList<Casilla> getCasillas() {
        return casillas;
    }

    // METODOS PRIVADOS

    /**
     * genera las casillas del tablero
     */
    private void generateCasillasNormal() {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= 10; j++) {
                int[] pos = { i, j };
                casillas.add(new Cnormal(pos));
            }
        }
    }

}
