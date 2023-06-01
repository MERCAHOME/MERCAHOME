import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Producto implements Serializable {
    private static int IDgenerator = 0; 
    private int id;
    private Distribuidor proveedor;
    private String nombre;
    private boolean perecedero;
    private boolean refrigerado;
    private LocalDate fechaCaducidad;
    private double precioDistribuidor;
    private double precioVentaPublico;
    private int diasQueTardaEnCaducar = -1;

    // Constructor para MERCAHOME de producto que SI caduca
    public Producto(Distribuidor proveedor, String nombre, boolean perecedero, boolean refrigerado,
            LocalDate fechaCaducidad, double precioDistribuidor, double precioVentaPublico, int diasQueTardaEnCaducar) {
        this.id = generateID();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.perecedero = perecedero;
        this.refrigerado = refrigerado;
        this.fechaCaducidad = fechaCaducidad;
        this.precioDistribuidor = precioDistribuidor;
        this.precioVentaPublico = precioVentaPublico;
        this.diasQueTardaEnCaducar = diasQueTardaEnCaducar;
    }

    // Constructor para MERCAHOME de producto que NO caduca
    public Producto(Distribuidor proveedor, String nombre, boolean perecedero, boolean refrigerado,
            double precioDistribuidor, double precioVentaPublico) {
        this.id = generateID();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.perecedero = perecedero;
        this.refrigerado = refrigerado;
        this.precioDistribuidor = precioDistribuidor;
        this.precioVentaPublico = precioVentaPublico;
    }

    // constructor para distribuidor en el caso de que SI tenga fecha de caducidad
    public Producto(Distribuidor proveedor, String nombre, boolean perecedero, boolean refrigerado,
            double precioDistribuidor, int diasQueTardaEnCaducar) {
        this.id = generateID();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.perecedero = perecedero;
        this.refrigerado = refrigerado;
        this.precioDistribuidor = precioDistribuidor;
        this.diasQueTardaEnCaducar = diasQueTardaEnCaducar;
    }

    // constructor para distribuidor en el caso de que NO tenga fecha de caducidad
    public Producto(Distribuidor proveedor, String nombre, boolean perecedero, boolean refrigerado,
            double precioDistribuidor) {
        this.id = generateID();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.perecedero = perecedero;
        this.refrigerado = refrigerado;
        this.precioDistribuidor = precioDistribuidor;
    }

    public void mostrarParaDistribuidor() { // mostrar id, precioDistribuidor, nombre, if perecedero si o no, if
                                            // refrigerado si o no
        System.out.println("***************************************************************************");
        System.out.println("                                PRODUCTO");
        System.out.println("***************************************************************************");
        System.out.printf("| %-4s | %-24s | %-10s | %-10s | %-11s |%n", "ID", "NOMBRE", "PRECIO", "PERECEDERO",
                "REFRIGERADO");
        System.out.printf("| %-4s | %-24s | %-10s | %-10s | %-11s |%n", id, nombre, precioDistribuidor, perecedero,
                refrigerado);
        System.out.println("***************************************************************************");

    }

    public void mostrarParaCliente() {
        if (perecedero) {
            System.out.println("********************************************************************************");
            System.out.println("                                    PRODUCTO");
            System.out.println("********************************************************************************");
            System.out.printf("| %-4s | %-24s | %-10s | %-15s | %-11s |%n", "ID", "NOMBRE", "PRECIO", "FECHA CADUCIDAD",
                    "REFRIGERADO");
            System.out.printf("| %-4s | %-24s | %-10s | %-15s | %-11s |%n", id, nombre, precioDistribuidor,
                    fechaCaducidad, refrigerado);
            System.out.println("********************************************************************************");
        } else {
            System.out.println("*************************************************************");
            System.out.println("                          PRODUCTO");
            System.out.println("*************************************************************");
            System.out.printf("| %-4s | %-24s | %-10s | %-10s |%n", "ID", "NOMBRE", "PRECIO", "REFRIGERADO",
                    "REFRIGERADO");
            System.out.printf("| %-4s | %-24s | %-10s | %-10s |%n", id, nombre, precioDistribuidor, refrigerado);
            System.out.println("*************************************************************");
        }
    }

    public int getId() {
        return id;
    }

    public Distribuidor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Distribuidor proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPerecedero() {
        return perecedero;
    }

    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public double getPrecioDistribuidor() {
        return precioDistribuidor;
    }

    public void setPrecioDistribuidor(double precioDistribuidor) {
        this.precioDistribuidor = precioDistribuidor;
    }

    public double getPrecioVentaPublico() {
        return precioVentaPublico;
    }

    public void setPrecioVentaPublico(double precioVentaPublico) {
        this.precioVentaPublico = precioVentaPublico;
    }

    public int getDiasQueTardaEnCaducar() {
        return diasQueTardaEnCaducar;
    }

    public void setDiasQueTardaEnCaducar(int diasQueTardaEnCaducar) {
        this.diasQueTardaEnCaducar = diasQueTardaEnCaducar;
    }

    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return nombre.equals(producto.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
}
