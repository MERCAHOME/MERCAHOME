import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Supermercado extends EstablecimientoPropio {
    //creaerMétodo que al mostrarPedidos los actualize, si la fecha de entrega ya ha sido que lo modifique
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    private ArrayList<Producto> stock = new ArrayList<>();
    private ArrayList<Factura> facturas = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private ArrayList<HorarioPedido> horasDisParaPedidos = new ArrayList<>();
    private final Empresa empresa;
    private final Almacen almacen;
    //Asegurarse de crear un almacen al arrancar la app minimo, no se puede crear un supermercado si no existe almenos un almacen
    //Asegurarse que existe siempre almenos un conductor, un encargado, un repartidor, y un trabajador randomX
    public Supermercado(Empresa empresa) {
        System.out.println("Vamos a dar de alta un supermercado");
        this.empresa = empresa;
        Herramientas.limpiarPantalla();
        if (agregarVehiculos()) {
            Herramientas.limpiarPantalla();
            System.out.println("Vehículos agregados con éxito!");
        }else{
            Herramientas.limpiarPantalla();
            System.out.println("No se han podido agregar los vehículos, contacte con el administrador");
        }

        this.almacen = empresa.devolverAlmacen();

        if (agregar4Trabajadores()) {
            Herramientas.limpiarPantalla();
            System.out.println("Se han agregado los trabajadores con éxito");
        }else{
            Herramientas.limpiarPantalla();
            System.out.println("Se ha producido un error agregando los trabajadores contacte con el administrador");
        }
        //a ver como hace esto jack
        if (crearHorarioPedido()) {
            Herramientas.limpiarPantalla();
            System.out.println("Los horarios de pedidos han sido añadidos correctamente");
        }else{
            Herramientas.limpiarPantalla();
            System.out.println("No se han podido crear los horarios para los pedidos, contacte con el administrador");
        }
    }

    public boolean crearHorarioPedido(){

        try {
            int anios = 0;
            do {
                System.out.println("Para cuantos años desea crear la posibilidad de hacer pedidos?");
                System.out.print("Años: ");
                anios = Herramientas.pedirEnteroPositivo();
                if (anios<2) {
                    System.out.println("Error: el mínimo son 2 años");
                    System.out.println("Introduzca de nuevo la cantidad de años a dar de alta");
                }
            } while (anios<2);
    
            for (int i = 0; i < anios; i++) {
                if (i == 0) {
                    int cantidadDeSemanas = cantidadDeSemanasDeAnioPrimeraVez(LocalDate.now().getYear());
                    LocalDate primerLunes = retornarLunesPriveraVez();
                    for (int j = 0; j < cantidadDeSemanas; j++) {
                        horasDisParaPedidos.add(new HorarioPedido(primerLunes, empleados));
                        primerLunes = primerLunes.plusDays(7);
                    }
    
                }else{
                    int cantidadDeSemanas = cantidadDeSemanasDeAnioSegundaVez(LocalDate.now().getYear()+i);
                    LocalDate primerLunes = retornarLunesSegundaVez(i);
                    for (int j = 0; j < cantidadDeSemanas; j++) {
                        horasDisParaPedidos.add(new HorarioPedido(primerLunes, empleados));
                        primerLunes = primerLunes.plusDays(7);
                    }
                }
            }
    
            return true;
            
        } catch (Exception e) {
            return false;
        }
    }
    public LocalDate retornarLunesPriveraVez(){
        LocalDate dia1 = LocalDate.of(LocalDate.now().getYear(), 1, 1);

        while (dia1.getDayOfWeek()!= DayOfWeek.MONDAY) {
            dia1 = dia1.plusDays(-1);
        }
        
        return dia1;



    }

    public LocalDate retornarLunesSegundaVez(int i){
        LocalDate dia1 = LocalDate.of(LocalDate.now().getYear()+i, 1, 1);

        while (dia1.getDayOfWeek()!= DayOfWeek.MONDAY) {
            dia1 = dia1.plusDays(1);
        }
        
        return dia1;

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
        //berywam
        
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

    public void mostrarPedidos(){
        if (pedidos.size()>0) {
            System.out.println("*********************************************************************");
            System.out.println("                            PEDIDOS");
            System.out.println("*********************************************************************");
            System.out.println();
            for (Pedido p : pedidos) {
               p.mostrarProductosPedidoConTotal();

            }
        }else{
            System.out.println("Todavía no hay pedidos dados de alta");
        }
    }

    public void mostrarVehiculos(){
        if (vehiculos.size()>0) {
            
            System.out.println("*****************************");
            System.out.println("         VEHICULOS");
            System.out.println("*****************************");
            System.out.println();

            for (Vehiculo v : vehiculos) {
                System.out.println("Matrícula: "+v.getMatricula());
                if (v.getConductorManyana()!=null) {
                    System.out.println("Conductor mañanas:"+v.getConductorManyana().getNombre()+" "+v.getConductorManyana().getApellidos());
                }
                if (v.getConductorTarde()!=null) {
                    System.out.println("Conductor tardes:"+v.getConductorTarde().getNombre()+" "+v.getConductorTarde().getApellidos());
                }
                System.out.println("*****************************");

            }
        }else{
            System.out.println("Todavía no hay vehículos dadas de alta");
        }
    }


    public void mostrarFacturas(){
        if (facturas.size()>0) {
            System.out.println("*********************************************************************");  
            System.out.println("                            FACTURAS");
            System.out.println("*********************************************************************");
            System.out.println();

            for (Factura f : facturas) {
                f.mostrar();

            }
        }else{
            System.out.println("Todavía no hay facturas dadas de alta");
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


    public boolean agregar4Trabajadores(){

        try {
            
            boolean gerente = false;
            boolean conductor = false;
            boolean cajero = false;
            boolean encargado = false;
            Empleado empleadoGerente = null;
            Empleado empleadoConductor = null;
            Empleado empleadoCajero = null;
            Empleado empleadoEncargado = null;
    
            do {
                if (!gerente) {
                    System.out.println("Primero ha de dar de alta a un gerente");
                    do {
                        empleadoGerente = new Empleado(this,empresa);
                        if (empleadoGerente.getTipoDeEmpleado() != TipoDeEmpleado.GERENTE) {
                            empleadoGerente = null;
                            System.out.println("El empleado generado no es válido");
                            System.out.println("El empleado que se solicitaba era un gerente");
                            System.out.println("Introduzca un empleado que si sea gerente");
                        }
                    } while (empleadoGerente == null);
                    gerente = true;
                }
                if (!encargado) {
                    Herramientas.limpiarPantalla();
                    System.out.println("Es necesario dar de alta a un encargado");  
                    do {
                        empleadoEncargado = new Empleado(this,empresa);
                        if (empleadoEncargado.getTipoDeEmpleado() != TipoDeEmpleado.ENCARGADO) {
                            empleadoEncargado = null;
                            System.out.println("El empleado generado no es válido");
                            System.out.println("El empleado que se solicitaba era un encargado");
                            System.out.println("Introduzca un empleado que si sea encargado");
                        }
                    } while (empleadoEncargado == null);
                    encargado = true;
                }
                if (!conductor) {
                    Herramientas.limpiarPantalla();
                    System.out.println("Es necesariotambién dar de alta a un conductor");
                    do {
                        empleadoConductor = new Empleado(this,empresa);
                        if (empleadoConductor.getTipoDeEmpleado() != TipoDeEmpleado.CONDUCTOR) {
                            empleadoConductor = null;
                            System.out.println("El empleado generado no es válido");
                            System.out.println("El empleado que se solicitaba era un conductor");
                            System.out.println("Introduzca un empleado que si sea conductor");
                        }
                    } while (empleadoConductor == null);
                    conductor = true;
                }
                if (!cajero) {
                    Herramientas.limpiarPantalla();
                    System.out.println("También necesitarás un cajero");
                    do {
                        empleadoCajero = new Empleado(this,empresa);
                        if (empleadoCajero.getTipoDeEmpleado() != TipoDeEmpleado.CAJERODESUPERMERCADO) {
                            empleadoCajero = null;
                            System.out.println("El empleado generado no es válido");
                            System.out.println("El empleado que se solicitaba era un cajero");
                            System.out.println("Introduzca un empleado que si sea cajero");
                        }
                    } while (empleadoCajero == null);
                    cajero = true;
                }
                          
            } while (!gerente&&!conductor&&!encargado&&!cajero);
            empleadoCajero.agregarEncargado();
            empleadoConductor.agregarEncargado();
            empresa.getTrabajadores().add(empleadoEncargado);
            empresa.getTrabajadores().add(empleadoCajero);
            empresa.getTrabajadores().add(empleadoConductor);
            empresa.getTrabajadores().add(empleadoGerente);
            empleados.add(empleadoEncargado);
            empleados.add(empleadoCajero);
            empleados.add(empleadoConductor);
            empleados.add(empleadoGerente);
            this.setGerente(empleadoGerente);
            this.agregarEncargado(empleadoEncargado);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    

    public boolean altaTrabajador(){
        Empleado empleado = new Empleado(this,empresa);
        try {
            empresa.getTrabajadores().add(empleado);;
            empleados.add(empleado);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
   //actualizar horariopedido aqui
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

    public boolean mostrarProductos() {
        if (stock.size() > 0) {
            System.out.println("*****************************");
            System.out.println("          PRODUCTOS");
            System.out.println("*****************************");
            ArrayList<String> nombresVistos = new ArrayList<>();
            System.out.printf("| %-28s | %-16s | %-12s |%n", "PRODUCTO", "CANTIDAD", "PRECIO/u");
            System.out.printf("| %-28s | %-16s | %-12s |%n", " ", " ", " ", " ");
            for (Producto producto : stock) {
                if (!nombresVistos.contains(producto.getNombre())) {
                    nombresVistos.add(producto.getNombre());
                    String nombreProducto = producto.getNombre();
                    double precioProducto = producto.getPrecioVentaPublico();
                    int cantidadProducto = cantidad(stock, producto);
                    System.out.printf("| %-28s | %-16.2f | %-12.2f |%n", nombreProducto, cantidadProducto,
                            precioProducto);
                }
            }
            return true;
        }
        return false;
    }
    public int cantidad(ArrayList<Producto> productos, Producto producto) {
        return Collections.frequency(productos, producto);
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
