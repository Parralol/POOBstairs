package domain;

/**
 * Clase POOBStairsException.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBStairsException extends Exception {

    static String NO_PUEDE_TENER_MISMO_TURNO = "Error en los turnos";
    static String NO_PUEDE_TENER_MISMO_COLOR = "Error en los colores, no pueden existir dos iguales";
    static String JUGADOR_DEBE_TENER_NOMBRE = "El jugador debe de poseer nombre";
    static String dEBE_EXISTIR_2JUGADORES = "No puedes colocar 0 jugadores";

    public POOBStairsException(String message) {
        super(message);
    }
}
