import java.io.Serializable;
import java.util.ArrayList;

public class Persona implements Herramientas, Serializable {
    private final String DNI;
    private int numeroDeTelefono;
    private String nombre;
    private String apellidos;
    private Empresa empresa;

    public Persona(Empresa empresaMercahome) {
        this.empresa = empresa;
        System.out.println("Indique su nombre.");
        System.out.print("Nombre: ");
        this.nombre = Herramientas.pedirString();
        System.out.println("Indique su(s) apellido(s).");
        System.out.print("Apellido(s): ");
        this.apellidos = Herramientas.pedirString();
        this.DNI = crearDni();
        this.numeroDeTelefono = Herramientas.crearNumeroDeTelefono();
    }

    public String crearDni(){
        String dni = Herramientas.crearDNI();
        ArrayList<Empleado> empleados = empresa.getTrabajadores();
        ArrayList<Cliente> clientes = empresa.getClientes();
        
        for (Cliente cliente : clientes) {
            if (cliente.getDNI().equalsIgnoreCase(dni)) {
                System.out.println("Ya existe un cliente con este dni");
                System.out.println("Inserte un dni distinto");
                dni = crearDni();
            }
        }
        for (Empleado empleado : empleados) {
            if (empleado.getDNI().equalsIgnoreCase(dni)) {
                System.out.println("Ya existe un empleado con este dni");
                System.out.println("Inserte un dni distinto");
                dni = crearDni();
            }
        }

        return dni;

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
