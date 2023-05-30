package domain;

/**
 * Clase POOBStairsException.
 *
 * @author Santiago Parra / Juan Vizcaino
 * @version v1.0
 */
public class POOBStairsException extends Exception {

    /**
     * The No puede tener mismo turno.
     */
    static String NO_PUEDE_TENER_MISMO_TURNO = "Error en los turnos";
    /**
     * The No puede tener mismo color.
     */
    static String NO_PUEDE_TENER_MISMO_COLOR = "Error en los colores, no pueden existir dos iguales";
    /**
     * The Jugador debe tener nombre.
     */
    static String JUGADOR_DEBE_TENER_NOMBRE = "El jugador debe de poseer nombre";
    /**
     * The D ebe existir 2 jugadores.
     */
    static String dEBE_EXISTIR_2JUGADORES = "No puedes colocar 0 jugadores";
    /**
     * The Ganador.
     */
    static String GANADOR = "Ha ganado!";

    /**
     * Instantiates a new Poob stairs exception.
     *
     * @param message the message
     */
    public POOBStairsException(String message) {
        super(message);
    }
}
