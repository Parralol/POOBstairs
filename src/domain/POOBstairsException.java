package domain;

/**
 * Clase POOBstairsException.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBstairsException extends Exception {

    static String NO_PUEDE_TENER_MISMO_TURNO = "Error en los turnos";
    static String NO_PUEDE_TENER_MISMO_COLOR = "Error en los colores, no pueden existir dos iguales";

    public POOBstairsException(String message) {
        super(message);
    }
}
