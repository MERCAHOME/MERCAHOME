public class Persona {
    private final String DNI;
    private int numeroDeTelefono;
    
    public Persona(String DNI, int numeroDeTelefono) {
        this.DNI = DNI;
        this.numeroDeTelefono = numeroDeTelefono;
    }
    
    public String getDNI() {
        return DNI;
    }
    
    public int getNumeroDeTelefono() {
        return numeroDeTelefono;
    }
    
    public void setNumeroDeTelefono(int numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }
}
