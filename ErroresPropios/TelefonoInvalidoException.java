package ErroresPropios;

public class TelefonoInvalidoException extends Exception {
    public TelefonoInvalidoException(String mensaje){
        super(mensaje);
    }
}
