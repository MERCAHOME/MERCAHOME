import java.time.LocalDate;

public class Producto {
    private static int IDgenerator = 0;
    private int id;
    private Distribuidor proveedor;
    private String nombre;
    private boolean perecedero;
    private boolean refrigerado;
    private LocalDate fechaCaducidad;
    
    public Producto(Distribuidor proveedor, String nombre, boolean perecedero, boolean refrigerado, LocalDate fechaCaducidad) {
        this.id = generateID();
        this.proveedor = proveedor;
        this.nombre = nombre;
        this.perecedero = perecedero;
        this.refrigerado = refrigerado;
        this.fechaCaducidad = fechaCaducidad;
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
    
    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }
}
