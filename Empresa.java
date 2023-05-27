import java.util.ArrayList;

public class Empresa extends EstablecimientoPropio {
    //Necesito un método que me muestre los proveedores y productos que distribuyen(Almacén)boolean, si hay almenos uno true, si se da error o no hay false;
    //Necesito un método Proveedor que me retorne un proveedor(Almacén),si se da algun error o no existen proveedores o lo que sea devolver null;
    private final String nombre;
    private ArrayList<Supermercado> supermercados;
    private ArrayList<Empleado> trabajadores;
    private ArrayList<Distribuidor> distribuidores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Almacen> almacenes;
    private ArrayList<Factura> facturas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Descuento> descuentos;
    private ArrayList<Producto> stock;
    
    public Empresa(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico,
                   Empleado gerente, ArrayList<Empleado> encargados, ArrayList<Empleado> trabajadores,
                   String nombre) {
        this.nombre = nombre;
        this.supermercados = new ArrayList<>();
        this.trabajadores = new ArrayList<>();
        this.distribuidores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.almacenes = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.descuentos = new ArrayList<>();
        this.stock = new ArrayList<>();
    }
    //No está hecho, solo está para que no de error
    public boolean mostrarProveedoresYProductoQueDistribuye(){
        return true;
    }
    //No está hecho tampoco
    public Distribuidor devolverProveedor(){
        return new Distribuidor(nombre, nombre, getNumeroDeTelefono(), getUbicacion(), getHorarioPublico(), nombre);
    }
    //No está hecho, preguntar al usuario cual es el almacen que quiere para su supermercado
    public Almacen devolverAlmacen(){
        return new Almacen(this);
    }
    //no hecho
    public ArrayList<Vehiculo> darVehiculosASupermercados(int cantidad){
        //crea tantos vehiculos como te pidan los añades a tu arraylist y me los devuelves en un arraylist
        return new ArrayList<>(null);
    }

    //fin
    
    public String getNombre() {
        return nombre;
    }
    
    public ArrayList<Supermercado> getSupermercados() {
        return supermercados;
    }
    
    public void setSupermercados(ArrayList<Supermercado> supermercados) {
        this.supermercados = supermercados;
    }
    
    public ArrayList<Empleado> getTrabajadores() {
        return trabajadores;
    }
    
    public void setTrabajadores(ArrayList<Empleado> trabajadores) {
        this.trabajadores = trabajadores;
    }
    
    public ArrayList<Distribuidor> getDistribuidores() {
        return distribuidores;
    }
    
    public void setDistribuidores(ArrayList<Distribuidor> distribuidores) {
        this.distribuidores = distribuidores;
    }
    
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }
    
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public ArrayList<Almacen> getAlmacenes() {
        return almacenes;
    }
    
    public void setAlmacenes(ArrayList<Almacen> almacenes) {
        this.almacenes = almacenes;
    }
    
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    
    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public ArrayList<Descuento> getDescuentos() {
        return descuentos;
    }
    
    public void setDescuentos(ArrayList<Descuento> descuentos) {
        this.descuentos = descuentos;
    }
    
    public ArrayList<Producto> getStock() {
        return stock;
    }
    
    public void setStock(ArrayList<Producto> stock) {
        this.stock = stock;
    }
}
