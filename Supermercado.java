import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Supermercado extends EstablecimientoPropio {
    //todo lo que es new arraylist hay que crearlo y meterlo en un archivo
    private ArrayList<Pedido> pedidos;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Producto> stock;
    private final Empresa empresa;
    private final Almacen almacen;
    private Horario horarioPublico;
    private ArrayList<HorarioPedido> horasDisParaPedidos;
    
    public Supermercado(Empresa empresa) {
        this.pedidos = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        if (agregarVehiculos()) {
            System.out.println("Vehículos agregados con éxito!");
        }else{
            System.out.println("No se han podido agregar los vehículos, contacte con el administrador");
        }
        this.stock = new ArrayList<>();
        this.empresa = empresa;
        this.almacen = empresa.devolverAlmacen();
        //a ver como hace esto jack
        this.horarioPublico = horarioPublico;
        //a ver como hace esto jack
        this.horasDisParaPedidos = new ArrayList<>();
    }
    //Hacer persistente
    public boolean agregarVehiculos(){
        try {
            System.out.println("Cuantos vehiculos desea agregar a este supermercado?");
            System.out.print("Respuesta: ");
            int cantidad = Herramientas.pedirEnteroPositivo();
            ArrayList<Vehiculo> vehiculosAagregar = empresa.darVehiculosASupermercados(cantidad);
            Iterator<Vehiculo> vehiculoIterador = vehiculosAagregar.iterator();
            while (vehiculoIterador.hasNext()) {
                vehiculos.add(vehiculoIterador.next());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Depende de como sea la clase vehiculo
    public boolean vehiculosDisponibles(){
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

   
    //depende de como sea la clase vehiculo
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

    //Añadido por adrian
  //Metodos para dar de alta un trabajador
   /*  private List<Trabajador> trabajadores;

    public Supermercado() {
        trabajadores = new ArrayList<>();
        horario = new HashMap<>();
        inicializarHorario();
    }
    

    public void darDeAltaTrabajador(String nombre, int edad, String puesto) {
        Trabajador nuevoTrabajador = new Trabajador(nombre, edad, puesto);
        trabajadores.add(nuevoTrabajador);
        guardarDatosTrabajadores();
    }

    
    public void darDeAltaTrabajador(String nombre, int edad, String puesto) {
        Trabajador nuevoTrabajador = new Trabajador(nombre, edad, puesto);
        trabajadores.add(nuevoTrabajador);
        guardarDatosTrabajadores();
    }

    //Metodos para Aplicar un horario laboral a los trabajadores
    
    private Map<String, String> horario;
    private void inicializarHorario() {
        horario.put(" - Lunes", "09:00 - 21:30");
        horario.put(" - Martes", "09:00 - 21:30");
        horario.put(" - Miércoles", "09:00 - 21:30");
        horario.put(" - Jueves", "09:00  - 21:30");
        horario.put(" - Viernes", "09:00  - 21:30");
        horario.put(" - Sábado", "09:00  - 13:00 ");
    }
    
    public void mostrarHorarioLaboral() {
        System.out.println("Horario Laboral:");
        for (Map.Entry<String, String> entry : horario.entrySet()) {
            String dia = entry.getKey();
            String horarioDia = entry.getValue();
            System.out.println(dia + ": " + horarioDia);
        } */
        
    public void eliminarProductoSupermercadoYEmpresa(Producto productoAEliminar){
        empresa.getStock().remove(productoAEliminar);
        stock.remove(productoAEliminar);
    }
    public void agregarProductoSupermercadoYEmpresa(Producto producto){
        empresa.getStock().add( producto);
        stock.add( producto);
    }
}

/*
 

* En empresa, vincular a esta clase los trabajadores
-Hecho-
* Hacer un horario laboral --- (Este ira en el main)--- HorarioLaboral horario = new HorarioLaboral();----- horario.mostrarHorarioLaboral();
 
      -Hecho-

* Hacer un horario de pedidos dentro de un array list para cada seman del ano

* Metodo para llamar al almacen para que me compruebe los productos y que se me guarden en un metodo stock

* metodo para preguntar a empresa que me de vehiculos dentro de un array List
 
*En herramientas se puede gastar de puente para hacer metodos repetitivos

*/