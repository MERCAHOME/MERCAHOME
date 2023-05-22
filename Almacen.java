import java.util.ArrayList;

public class Almacen extends EstablecimientoPropio implements Stock {
    private ArrayList<Producto> stock;
    private final Empresa empresa;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Estanteria> estanterias;
    private ArrayList<Nevera> neveras;
    
    public Almacen(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico,
                   Empleado gerente, ArrayList<Empleado> encargados, ArrayList<Empleado> trabajadores,
                   Empresa empresa) {
        super(CIF, numeroDeTelefono, ubicacion, horarioPublico, gerente, encargados, trabajadores);
        this.stock = new ArrayList<>();
        this.empresa = empresa;
        this.vehiculos = new ArrayList<>();
        this.estanterias = new ArrayList<>();
        this.neveras = new ArrayList<>();
    }
    
    public ArrayList<Producto> getStock() {
        return stock;
    }
    
    public void setStock(ArrayList<Producto> stock) {
        this.stock = stock;
    }
    
    public Empresa getEmpresa() {
        return empresa;
    }
    
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public ArrayList<Estanteria> getEstanterias() {
        return estanterias;
    }
    
    public void setEstanterias(ArrayList<Estanteria> estanterias) {
        this.estanterias = estanterias;
    }
    
    public ArrayList<Nevera> getNeveras() {
        return neveras;
    }
    
    public void setNeveras(ArrayList<Nevera> neveras) {
        this.neveras = neveras;
    }
    
    @Override
    public void agregarProducto(Producto producto) {
        stock.add(producto);
    }
    
    @Override
    public void eliminarProducto(Producto producto) {
        stock.remove(producto);
    }
    
    @Override
    public int obtenerCantidadProductos() {
        return stock.size();
    }
}
