import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Producto> productos;
    private double total;
    private double totalConTransporte;
    private double totalConTransporteEIVA;
    private Factura factura;
    private static int IDgenerator = 0;
    private int id;
    private LocalDateTime fechaRealizacion;
    private LocalDateTime horaDeEntrega;
    private EstadoDePedido estadoDePedido;
    
    public Pedido(ArrayList<Producto> productos, double total, double totalConTransporte, double totalConTransporteEIVA,
                  Factura factura, LocalDateTime fechaRealizacion, LocalDateTime horaDeEntrega, EstadoDePedido estadoDePedido) {
        this.productos = productos;
        this.total = total;
        this.totalConTransporte = totalConTransporte;
        this.totalConTransporteEIVA = totalConTransporteEIVA;
        this.factura = factura;
        this.id = generateID();
        this.fechaRealizacion = fechaRealizacion;
        this.horaDeEntrega = horaDeEntrega;
        this.estadoDePedido = estadoDePedido;
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public double getTotalConTransporte() {
        return totalConTransporte;
    }
    
    public void setTotalConTransporte(double totalConTransporte) {
        this.totalConTransporte = totalConTransporte;
    }
    
    public double getTotalConTransporteEIVA() {
        return totalConTransporteEIVA;
    }
    
    public void setTotalConTransporteEIVA(double totalConTransporteEIVA) {
        this.totalConTransporteEIVA = totalConTransporteEIVA;
    }
    
    public Factura getFactura() {
        return factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    public int getId() {
        return id;
    }
    
    public LocalDateTime getFechaRealizacion() {
        return fechaRealizacion;
    }
    
    public void setFechaRealizacion(LocalDateTime fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }
    
    public LocalDateTime getHoraDeEntrega() {
        return horaDeEntrega;
    }
    
    public void setHoraDeEntrega(LocalDateTime horaDeEntrega) {
        this.horaDeEntrega = horaDeEntrega;
    }
    
    public EstadoDePedido getEstadoDePedido() {
        return estadoDePedido;
    }
    
    public void setEstadoDePedido(EstadoDePedido estadoDePedido) {
        this.estadoDePedido = estadoDePedido;
    }
    
    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }
}
