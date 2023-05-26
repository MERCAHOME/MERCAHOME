import java.util.ArrayList;

public class Supermercado extends EstablecimientoPropio {

    private ArrayList<Pedido> pedidos;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Producto> stock;
    private final Empresa empresa;
    private final Almacen almacen;
    private Horario horarioPublico;
    private ArrayList<HorarioPedido> horasDisParaPedidos;
    
    public Supermercado(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico,
                        Empleado gerente, ArrayList<Empleado> encargados, ArrayList<Empleado> trabajadores,
                        Empresa empresa, Almacen almacen) {
        super(CIF, numeroDeTelefono, ubicacion, horarioPublico, gerente, encargados, trabajadores);
        this.pedidos = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.stock = new ArrayList<>();
        this.empresa = empresa;
        this.almacen = almacen;
        this.horarioPublico = horarioPublico;
        this.horasDisParaPedidos = new ArrayList<>();
    }

    public boolean vehiculosDisponibles(){
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    

    public Vehiculo devolverVehiculoDisponible(){
        //método no creado, solo para no dar error en otras clases
        return new Vehiculo(getCIF());
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }
    
    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
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
    
    public Almacen getAlmacen() {
        return almacen;
    }
    
    public Horario getHorarioPublico() {
        return horarioPublico;
    }
    
    public void setHorarioPublico(Horario horarioPublico) {
        this.horarioPublico = horarioPublico;
    }
    
    public ArrayList<HorarioPedido> getHorasDisParaPedidos() {
        return horasDisParaPedidos;
    }
    
    public void setHorasDisParaPedidos(ArrayList<HorarioPedido> horasDisParaPedidos) {
        this.horasDisParaPedidos = horasDisParaPedidos;
    }
    public void eliminarProductoSupermercadoYEmpresa(Producto productoAEliminar){
        empresa.getStock().remove(productoAEliminar);
        stock.remove(productoAEliminar);
    }
    public void agregarProductoSupermercadoYEmpresa(Producto producto){
        empresa.getStock().add( producto);
        stock.add( producto);
    }
}
