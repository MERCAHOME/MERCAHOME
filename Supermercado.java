import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Iterator;

public class Supermercado extends EstablecimientoPropio {
    //creaerMétodo que al mostrarPedidos los actualize, si la fecha de entrega ya ha sido que lo modifique
    private ArrayList<Pedido> pedidos;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Producto> stock;
    private ArrayList<Factura> facturas = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<HorarioPedido> horasDisParaPedidos;
    private final Empresa empresa;
    private final Almacen almacen;
    private Horario horarioPublico;
    
    public Supermercado(Empresa empresa) {
        this.empresa = empresa;
        this.pedidos = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        if (agregarVehiculos()) {
            System.out.println("Vehículos agregados con éxito!");
        }else{
            System.out.println("No se han podido agregar los vehículos, contacte con el administrador");
        }
        this.stock = new ArrayList<>();
        this.almacen = empresa.devolverAlmacen();
        int cantidadTrabajadores = 0;
        do {
            System.out.println("Cuantos empleados desea dar de alta al Supermercado?");
            System.out.print("Cantidad: ");
            cantidadTrabajadores = Herramientas.pedirEnteroPositivo();
            if (cantidadTrabajadores<4) {
                System.out.println("Tienes que dar de alta un mínimo de 4 trabajadores por supermercado");
            }
        } while (cantidadTrabajadores<4);
        darDeAltaVariosTrabajadores(cantidadTrabajadores);
        //a ver como hace esto jack
        this.horarioPublico = horarioPublico;
        //a ver como hace esto jack
        this.horasDisParaPedidos = new ArrayList<>();
    }

    //Este método solo se puede utilizar la primera vez, ya que la segunda, a menos que empieze en lunes, ya tendrá esa semana añadida
    public int cantidadDeSemanasDeAnioPrimeraVez(int anio){
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        return fin.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) - inicio.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) + 1;
    }
    //Este método retorna la cantidad de semanas teniendo en cuenta que solo retorna la primera semana si el año empieza un Lunes
    public static int cantidadDeSemanasDeAnioSegundaVez(int anio) {
        LocalDate inicio = LocalDate.of(anio, 1, 1);
        LocalDate fin = LocalDate.of(anio, 12, 31);
        
        // Si el primer día del año no es lunes, se resta una semana
        if (inicio.getDayOfWeek() != DayOfWeek.MONDAY) {
            inicio = inicio.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        
        int semanas = fin.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) - inicio.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR) + 1;
        
        // Si el año tiene 53 semanas y la última semana comienza en el próximo año, se resta una semana
        if (semanas == 53 && fin.getDayOfWeek() != DayOfWeek.SUNDAY) {
            semanas--;
        }
        
        return semanas;
    }

    public boolean agregarFactura(Factura factura){
        try {
            empresa.getFacturas().add( factura);
            facturas.add( factura);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean agregarPedido(Pedido pedido){
        try {
            empresa.getPedidos().add( pedido);
            pedidos.add( pedido);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean eliminarTrabajador(){
        if (empleados.size()>0) {
            Empleado empleadoAEliminar = devolverTrabajador();
            empresa.getTrabajadores().remove(empleadoAEliminar);
            empleados.remove(empleadoAEliminar);
            return true;
        }else{
            System.out.println("No hay trabajadores en este supermercado");
            return false;
        }
    }
    public void mostrarTrabajadores(){
        System.out.println("*****************************");
        System.out.println("          EMPLEADOS");
        System.out.println("*****************************");

        if (empleados.size()>0) {
            for (Empleado empleado : empleados) {
                empleado.mostrarNombreYDNI();
                System.out.println("*****************************");
            }
        }else{
            System.out.println("Este supermercado no tiene trabajadores dados de alta");
        }
    }
    public Empleado devolverTrabajador(){
        String dni = "";
        boolean trabajadorEncontrado = true;
        Empleado trabajadorARetornar=null;
        do {
            mostrarTrabajadores();
            if (!trabajadorEncontrado) {
                System.out.println("No se ha encontrado ningún trabajador con el DNI: "+dni);
            }
            System.out.println("Indique el DNI de el trabajador");
            System.out.print("DNI: ");
            dni = Herramientas.pedirString();
            Iterator<Empleado> iteradorDeEmpleados = empleados.iterator();
            while (iteradorDeEmpleados.hasNext()) {
                Empleado trabajadorIterado  = iteradorDeEmpleados.next();
                if (trabajadorIterado.getDNI().equalsIgnoreCase(dni)) {
                    trabajadorEncontrado=true;
                    trabajadorARetornar = trabajadorIterado;
                }
            }
        } while (!trabajadorEncontrado);
        return trabajadorARetornar;
    }

    public boolean darDeAltaVariosTrabajadores(int cantidad){
        try {
            for (int i = 0; i < cantidad; i++) {
                altaTrabajador();
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean altaTrabajador(){
        Empleado empleado = new Empleado(this);
        try {
            empresa.getTrabajadores();
            empleados.add(empleado);
            return true;
        } catch (Exception e) {
            return false;
        }
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

    public void eliminarProductoSupermercadoYEmpresa(Producto productoAEliminar){
        empresa.getStock().remove(productoAEliminar);
        stock.remove(productoAEliminar);
    }
    public void agregarProductoSupermercadoYEmpresa(Producto producto){
        empresa.getStock().add( producto);
        stock.add( producto);
    }
}
