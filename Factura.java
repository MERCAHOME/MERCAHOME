import java.time.LocalDate;
import java.util.ArrayList;

public class Factura {
    private ArrayList<Producto> productos;
    private double totalSinIva;
    private double totalConIva;
    private double totalConIvaYTransporte;
    private final Cliente cliente;
    private LocalDate fecha;
    private static int IDgenerator = 0;
    private final int id;
    private Establecimiento emisor;
    private int cantidad;
    
    public Factura(ArrayList<Producto> productos, double totalSinIva, double totalConIva, double totalConIvaYTransporte,
                   Cliente cliente, LocalDate fecha, Establecimiento emisor) {
        this.productos = productos;
        this.totalSinIva = totalSinIva;
        this.totalConIva = totalConIva;
        this.totalConIvaYTransporte = totalConIvaYTransporte;
        this.cliente = cliente;
        this.fecha = fecha;
        this.emisor = emisor;
        this.id = generateID();
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public double getTotalSinIva() {
        return totalSinIva;
    }
    
    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }
    
    public double getTotalConIva() {
        return totalConIva;
    }
    
    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }
    
    public double getTotalConIvaYTransporte() {
        return totalConIvaYTransporte;
    }
    
    public void setTotalConIvaYTransporte(double totalConIvaYTransporte) {
        this.totalConIvaYTransporte = totalConIvaYTransporte;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public int getId() {
        return id;
    }
    
    public Establecimiento getEmisor() {
        return emisor;
    }
    
    public void setEmisor(Establecimiento emisor) {
        this.emisor = emisor;
    }
    
    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }
}
