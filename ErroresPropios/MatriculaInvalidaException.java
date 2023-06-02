package ErroresPropios;

public class MatriculaInvalidaException extends Exception {
    public MatriculaInvalidaException(String mensaje){
        super(mensaje);
    }
}
