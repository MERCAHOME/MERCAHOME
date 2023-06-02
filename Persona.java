import java.io.Serializable;

public class Persona implements Herramientas, Serializable {
    private final String DNI;
    private int numeroDeTelefono;
    private String nombre;
    private String apellidos;

    public Persona() {
        System.out.println("Indique su nombre.");
        System.out.print("Nombre: ");
        this.nombre = Herramientas.pedirString();
        System.out.println("Indique su(s) apellido(s).");
        System.out.print("Apellido(s): ");
        this.apellidos = Herramientas.pedirString();
        this.DNI = Herramientas.crearDNI();
        this.numeroDeTelefono = Herramientas.crearNumeroDeTelefono();
    }
    
    public String getDNI() {
        return DNI;
    }
    
    public int getNumeroDeTelefono() {
        return numeroDeTelefono;
    }
    
    public void setNumeroDeTelefono() {
        this.numeroDeTelefono = Herramientas.crearNumeroDeTelefono();
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
        
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    
}
