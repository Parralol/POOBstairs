package domain;

import java.awt.Color;


/**
 * Clase Maquina
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v2.3
 */
public class Maquina extends Jugador {

    /**
     * constructor para la clase maquina
     *
     * @param turno the turno
     */
    public Maquina(int turno) {
        super(false, turno);
    }

    @Override
    public void setName(String nombre) {
        super.nombre = "Maquina";
    }

    @Override
    public void setColor(Color color) {
        super.color = Color.BLACK;
        super.ficha = new Ficha(color, super.ficha.getPos());
    }
}
