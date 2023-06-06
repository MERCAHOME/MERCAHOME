import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Almacen extends EstablecimientoPropio implements Stock {
    // Se puede crear un método para eliminar estanterias y neveras teniendo en
    // cuenta que etén vacias, cambiando los productos de sitio en caso de que no
    // La capacidad ha decambiar tanto al eliminarlo como al agregar el
    // producto(debería estar ya)
    private ArrayList<Producto> stock = new ArrayList<>();
    private final Empresa empresa;
    private ArrayList<Estanteria> estanterias = new ArrayList<>();
    private ArrayList<Nevera> neveras = new ArrayList<>();
    private ArrayList<Empleado> empleados = new ArrayList<>();

    public int capacidadAlmacen;

    public Almacen(Empresa empresa) {
        this.empresa = empresa;
        Herramientas.limpiarPantalla();
        System.out.println("La capacidad del almacen va a depender de sus estantenrias y neveras");
        System.out.println("Vamos a añadir estanterias y neveras a tu almacén");
        System.out.println("Cuantas estanterias quieres añadir a tu almacén?");
        System.out.print("Cantidad: ");
        int estanterias = Herramientas.pedirEnteroPositivo();
        System.out.println("De cuantos niveles van a ser estas estanterias?");
        System.out.print("Niveles: ");
        int niveles = Herramientas.pedirEnteroPositivo();
        System.out.println("Cual va a ser la capacidad total de esta estantería?");
        System.out.print("Capacidad: ");
        int capacidad = Herramientas.pedirEnteroPositivo();
        if (agregarEstanterias(estanterias, capacidad, niveles)) {
            Herramientas.limpiarPantalla();
            System.out.println("Estantería(s) agregada(s) con éxito!");
        } else {
            Herramientas.limpiarPantalla();
            System.out.println(

                    "No se ha agregado ninguna estantería, contacte con el administrador si no era su intención");
        }
        System.out.println("Cuantas neveras quieres añadir a tu almacén?");
        System.out.print("Cantidad: ");
        int neveras = Herramientas.pedirEnteroPositivo();
        System.out.println("Que capacidad va(n) a tener la(s) nevera(s)?");
        System.out.print("Capacidad: ");
        capacidad = Herramientas.pedirEnteroPositivo();
        if (agregarNeveras(neveras, capacidad)) {
            Herramientas.limpiarPantalla();
            System.out.println("Nevera(s) agregada(s) con éxito!");
        } else {
            Herramientas.limpiarPantalla();
            System.out
                    .println("No se ha agregado ninguna nevera, contacte con el administrador si no era su intención");
        }
        // Herramientas.limpiarPantalla();
        System.out.println("Antes de finalizar, tiene que dar de alta algun trabajador");
        System.out.println("Vamos allá!");
        agregar4Trabajadores();

    }

    public boolean agregarNeveras(int cantidad, int capacidad) {
        try {
            for (int i = 0; i < cantidad; i++) {
                neveras.add(new Nevera(capacidad));
                capacidadAlmacen = capacidadAlmacen + capacidad;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Se ha producido un error inesperado, contacte con el administrador");
            return false;
        }

    }

    public boolean agregarEstanterias(int cantidad, int capacidad, int niveles) {

        try {
            for (int i = 0; i < cantidad; i++) {
                estanterias.add(new Estanteria(capacidad, niveles));
                capacidadAlmacen = capacidadAlmacen + capacidad;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Se ha producido un error inesperado, contacte con el administrador");
            return false;
        }

    }

    public boolean agregarProducto() {
        if (empresa.mostrarProveedoresYProductoQueDistribuye()) {
            boolean NoRepetir = false;
            String cif = "";
            do {
                System.out.println("Indique el CIF de uno de los proveedores mostrados previamente.");
                System.out.print("CIF: ");
                cif = Herramientas.pedirString();
                NoRepetir = empresa.existeProveedorConCifQueContieneAlmenos1Producto(cif);
                if (!NoRepetir) {
                    System.out.println(
                            "Parece que no existe ningún distribuidor con este CIF o este proveedor no tiene ningún producto");
                    System.out.println("Introduzca un CIF válido porfavor");
                }
            } while (!NoRepetir);

            Distribuidor distribuidor = empresa.devolverProveedor(cif);
            if (distribuidor != null) {
                distribuidor.mostrarProductosQueDistribuye();
                System.out.println("De el producto que va a agregar cuantos va a necesitar?");
                System.out.println("Cantidad: ");
                int cantidad = Herramientas.pedirEnteroPositivo();
                int tipoDeProducto = 0;
                do {
                    System.out.println("Va a ser este producto refrigerado?");
                    System.out.println("1- Si");
                    System.out.println("2- No");
                    System.out.print("Respuesta: ");
                    tipoDeProducto = Herramientas.pedirEnteroPositivo();
                    if (tipoDeProducto < 1 || tipoDeProducto > 2) {
                        System.out.println("Error, solo puede responder '1' o '2'.");
                    }
                } while (tipoDeProducto < 1 || tipoDeProducto > 2);
                int espacioDisponible = 0;
                if (tipoDeProducto == 1) {
                    for (Nevera frigo : neveras) {
                        espacioDisponible = espacioDisponible + frigo.getespacioDisponible();
                    }
                } else {
                    for (Estanteria estanteria : estanterias) {
                        espacioDisponible = espacioDisponible + estanteria.getespacioDisponible();
                    }
                }
                if (espacioDisponible >= cantidad) {
                    System.out.println("PONIENDO EN CONTACTO CON EL DISTRIBUIDOR");
                    ArrayList<Producto> productosComprados = distribuidor.comprarMasDeUnProducto();
                    Iterator<Producto> iteradorDeProducto = productosComprados.iterator();
                    while (iteradorDeProducto.hasNext()) {
                        if (!agregarProducto(iteradorDeProducto.next())) {
                            System.out.println("Se ha producido un error agregando un producto");
                        }
                    }

                    return true;

                } else {
                    System.out.println("No hay suficiente espacio para tantos productos");
                    System.out.println("Cancelando compra...");
                    return false;
                }

            } else {
                System.out.println("No se ha podido contactar con el proveedor, contacte con el administrador.");
                return false;
            }
        } else {
            System.out.println(
                    "Parece que la empresa no tiene ningún distribuidor o ninguno de ellos contiene ningún producto.");
            System.out.println("No se va a poder agregar ningún producto, contacte con el administrador");
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

    public boolean mostrarTrabajadores() {
        if (empleados.size() > 0) {
            System.out.println("*****************************");
            System.out.println("          EMPLEADOS");
            System.out.println("*****************************");
            System.out.printf("| %-28s | %-16s | %-12s |%n", "NOMBRE", "DNI", "CARGO");
            System.out.printf("| %-28s | %-16s | %-12s |%n", " ", " ", " ", " ");
            for (Empleado empleado : empleados) {
                String nombreEmpleado = empleado.getNombre();
                String dniEmpleado = empleado.getDNI();
                TipoDeEmpleado cargoEmpleado = empleado.getTipoDeEmpleado();
                System.out.printf("| %-28s | %-16.2f | %-12.2f |%n", nombreEmpleado, dniEmpleado,
                        cargoEmpleado.name());
            }

            return true;
        }
        return false;
    }

    public boolean eliminarEmpleado(Empleado empleado) {
        if (empleados.size() > 0) {
            empleados.remove(empleado);
            return true;
        } else
            return false;
    }
    

    public boolean mostrarEstanterias() {
        if (estanterias.size() > 0) {
            System.out.println("*****************************");
            System.out.println("         ESTANTERIAS");
            System.out.println("*****************************");
            System.out.printf("| %-28s | %-16s | %-12s |%n", "NUMERO", "CAPACIDAD", "PRODUCTOS");
            System.out.printf("| %-28s | %-16s | %-12s |%n", " ", " ", " ", " ");
            for (Estanteria estanteria : estanterias) {
                int numeroEstanteria = estanteria.getNumeroEstanteria();
                int numProductos = estanteria.getProductos().size();
                int capacidadEstanteria = estanteria.getCapacidad();
                System.out.printf("| %-28s | %-16d | %-12d |%n", numeroEstanteria,
                        capacidadEstanteria, numProductos);
            }

            return true;
        }
        return false;
    }

    public boolean eliminarEstanteria(Estanteria estanteria) {
        if (estanterias.remove(estanteria)) {
            return true;
        } else
            return false;
    }

    public boolean mostrarNeveras() {
        if (estanterias.size() > 0) {
            System.out.println("*****************************");
            System.out.println("         ESTANTERIAS");
            System.out.println("*****************************");
            System.out.printf("| %-28s | %-16s | %-12s |%n", "NUMERO", "CAPACIDAD", "PRODUCTOS");
            System.out.printf("| %-28s | %-16s | %-12s |%n", " ", " ", " ", " ");
            for (Estanteria estanteria : estanterias) {
                int numeroEstanteria = estanteria.getNumeroEstanteria();
                int numProductos = estanteria.getProductos().size();
                int capacidadEstanteria = estanteria.getCapacidad();
                System.out.printf("| %-28s | %-16d | %-12d |%n", numeroEstanteria,
                        capacidadEstanteria, numProductos);
            }

            return true;
        }
        return false;
    }

    public boolean eliminarNevera(Nevera nevera) {
        if (neveras.remove(nevera)) {
            return true;
        } else
            return false;
    }

    public int cantidad(ArrayList<Producto> productos, Producto producto) {
        return Collections.frequency(productos, producto);
    }

    public boolean agregar4Trabajadores() {

        // try {

        boolean gerente = false;
        boolean mozo1 = false;
        boolean mozo2 = false;
        boolean encargado = false;
        Empleado empleadoGerente = null;
        Empleado empleadoMozo1 = null;
        Empleado empleadoMozo2 = null;
        Empleado empleadoEncargado = null;

        do {
            if (!gerente) {
                System.out.println("Para un funcionamiento óptimo primero ha de dar de alta a un gerente");
                do {
                    empleadoGerente = new Empleado(this, empresa);
                    if (empleadoGerente.getTipoDeEmpleado() != TipoDeEmpleado.GERENTE) {
                        empleadoGerente = null;
                        Herramientas.limpiarPantalla();
                        System.out.println("El empleado generado no es válido");
                        System.out.println("El empleado que se solicitaba era un gerente");
                        System.out.println("Introduzca un empleado que si sea gerente");
                    }
                } while (empleadoGerente == null);
                gerente = true;
            }
            if (!encargado) {
                Herramientas.limpiarPantalla();
                System.out.println("Es necesario dar de alta también a un encargado");
                do {
                    empleadoEncargado = new Empleado(this, empresa);
                    if (empleadoEncargado.getTipoDeEmpleado() != TipoDeEmpleado.ENCARGADO) {
                        empleadoEncargado = null;
                        Herramientas.limpiarPantalla();
                        System.out.println("El empleado generado no es válido");
                        System.out.println("El empleado que se solicitaba era un encargado");
                        System.out.println("Introduzca un empleado que si sea encargado");
                    }
                } while (empleadoEncargado == null);
                encargado = true;
            }
            if (!mozo1) {
                Herramientas.limpiarPantalla();
                System.out.println("Es necesario también dar de alta a un mozo de almacén");
                do {
                    empleadoMozo1 = new Empleado(this, empresa);
                    if (empleadoMozo1.getTipoDeEmpleado() != TipoDeEmpleado.MOZODEALMACEN) {
                        empleadoMozo1 = null;
                        Herramientas.limpiarPantalla();
                        System.out.println("El empleado generado no es válido");
                        System.out.println("El empleado que se solicitaba era un mozo de almacén");
                        System.out.println("Introduzca un empleado que si sea mozo de almacén");
                    }
                } while (empleadoMozo1 == null);
                mozo1 = true;
            }
            if (!mozo2) {
                Herramientas.limpiarPantalla();
                System.out.println("Para finalizar hay que dar de alta porlomenos otro mozo de almacén");
                do {
                    empleadoMozo2 = new Empleado(this, empresa);
                    if (empleadoMozo2.getTipoDeEmpleado() != TipoDeEmpleado.MOZODEALMACEN) {
                        empleadoMozo2 = null;
                        Herramientas.limpiarPantalla();
                        System.out.println("El empleado generado no es válido");
                        System.out.println("El empleado que se solicitaba era un mozo de almacén");
                        System.out.println("Introduzca un empleado que si sea mozo de almacén");
                    }
                } while (empleadoMozo2 == null);
                mozo2 = true;
            }

        } while (!gerente && !mozo1 && !encargado && !mozo2);
        empleadoMozo1.agregarEncargado();
        empleadoMozo2.agregarEncargado();
        empresa.getTrabajadores().add(empleadoEncargado);
        empresa.getTrabajadores().add(empleadoMozo1);
        empresa.getTrabajadores().add(empleadoMozo2);
        empresa.getTrabajadores().add(empleadoGerente);
        empleados.add(empleadoEncargado);
        empleados.add(empleadoMozo1);
        empleados.add(empleadoMozo2);
        empleados.add(empleadoGerente);
        this.setGerente(empleadoGerente);
        this.agregarEncargado(empleadoEncargado);
        return true;
        // } catch (Exception e) {
        // return false;
        // }

    }

    public void agregarTrabajador() {
        int respuesta = 0;
        do {
            String[] titulos = {
                    "Trabajadores a agregar"
            };
            String[] opciones = {
                    "1- Encargado",
                    "2- Mozo de almacén"
            };
            respuesta = Herramientas.crearMenu(titulos, opciones);
            if (respuesta < 1 || respuesta > 2) {
                System.out.println("Error: solo puede introducir 1 o 2");
            }
        } while (respuesta < 1 || respuesta > 2);
        if (respuesta == 1) {
            Empleado empleadoEncargado = null;
            do {
                empleadoEncargado = new Empleado(this, empresa);
                if (empleadoEncargado.getTipoDeEmpleado() != TipoDeEmpleado.ENCARGADO) {
                    empleadoEncargado = null;
                    System.out.println("El empleado generado no es válido");
                    System.out.println("El empleado que se solicitaba era un encargado");
                    System.out.println("Introduzca un empleado que si sea encargado");
                }
            } while (empleadoEncargado == null);
            empresa.getTrabajadores().add(empleadoEncargado);
            empleados.add(empleadoEncargado);
        } else {
            Empleado empleadoMozo = null;
            do {
                empleadoMozo = new Empleado(this, empresa);
                if (empleadoMozo.getTipoDeEmpleado() != TipoDeEmpleado.MOZODEALMACEN) {
                    empleadoMozo = null;
                    System.out.println("El empleado generado no es válido");
                    System.out.println("El empleado que se solicitaba era un mozo de almacén");
                    System.out.println("Introduzca un empleado que si sea mozo de almacén");
                }
            } while (empleadoMozo == null);
            empleadoMozo.agregarEncargado();
            empresa.getTrabajadores().add(empleadoMozo);
            empleados.add(empleadoMozo);
        }
    }

    // Hacer el método persistente
    public boolean agregarProducto(Producto producto) {
        try {
            if (producto.isRefrigerado()) {
                agregarProductoANevera(producto);
            } else {
                agregarProductoAEstanteria(producto);
            }
            agregarProductoAquíYEnEmpresa(producto);
            capacidadAlmacen--;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hacer método persistente
    public boolean agregarProductoANevera(Producto producto) {
        Iterator<Nevera> controlNeveras = neveras.iterator();
        while (controlNeveras.hasNext()) {
            Nevera mercNevera = controlNeveras.next();
            if (mercNevera.getCapacidad() >= 1) {
                mercNevera.agregarproducto(producto);
                return true;
            }
        }
        return false;
    }

    // Hacer método persistente
    public boolean agregarProductoAEstanteria(Producto producto) {
        Iterator<Estanteria> controlEstanterias = estanterias.iterator();
        while (controlEstanterias.hasNext()) {
            Estanteria mercEstanteria = controlEstanterias.next();
            if (mercEstanteria.getCapacidad() >= 1) {
                mercEstanteria.agregarProducto(producto);
                return true;
            }
        }
        return false;
    }

    // Hacer método persistente
    // Método no hecho
    public boolean agregarProductoAquíYEnEmpresa(Producto producto) {
        try {
            stock.add(producto);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Se ha producido un error al agregar el producto al stock del supermercado");
            return false;
        }
        try {
            empresa.getStock().add(producto);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Se ha producido un error al agregar el producto al stock de la empresa");
            return false;
        }
        return true;
    }
    //al llamar este metodo ha de existir almenos un producto
    public ArrayList<Producto> darProducto(){
      
        if (stock.size()>0) {
            ArrayList<Producto> productos = devolverProductos();
            stock.removeAll(productos);
            return productos;
        }else{
            System.out.println("No hay productos en este almacén");
            return null;
        }

    }

    private ArrayList<Producto> devolverProductos(){

        do {
                    mostrarProductos();
                    ArrayList<Producto> prodADevolver = new ArrayList<>();
                    int cantidad = 0;
                    int cantidadEncontrados =0;
                    String nombre= "";
                    boolean nombreValido = false;
            
                    System.out.println("Indique el nombre de el producto a ceder al supermercado");
                    System.out.print("Nombre: ");
                    nombre = Herramientas.pedirString();
                    System.out.println("Que cantidad de este producto necesita?");
                    System.out.print("Cantidad: ");
                    cantidad=Herramientas.pedirEnteroPositivo();
            
                    for (Producto prod : stock) {
                        if (prod.getNombre().equalsIgnoreCase(nombre)) {
                            nombreValido=true;
                            if (prodADevolver.size()<cantidad) {
                                prodADevolver.add(prod);
                                cantidadEncontrados++;
                            }
                        }
                    }
                    if (nombreValido) {
                        if (prodADevolver.size()==cantidad) {

                            for (Producto producto : prodADevolver) {
                                
                                for (Nevera nev : neveras) {
                                    if (nev.contieneProducto(producto)) {
                                        nev.eliminarProducto(producto);
                                    }
                                }
                                for (Estanteria estant : estanterias) {
                                    if (estant.contieneProducto(producto)) {
                                        estant.eliminarProducto(producto);
                                    }
                                }
                            }

                            return prodADevolver;
                        }else{
                            System.out.println("Existen "+cantidadEncontrados+ " productos llamados "+nombre);
                            System.out.println("Seleccione una cantidad válida");
                        }
                        
                    }else{
                        System.out.println("No existe ningun producto con este nombre en el almacén");
                    }
                    cantidadEncontrados = 0;
            
        } while (true);
       
    }

    // Hacer el método persistente
    public boolean eliminarProducto(Producto producto) {
        try {
            if (producto.isRefrigerado()) {
                eliminarProductoANevera(producto);
            } else {
                eliminarProductoAEstanteria(producto);
            }
            eliminarProductoAquíYEnEmpresa(producto);
            capacidadAlmacen++;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Hacer método persistente
    public boolean eliminarProductoANevera(Producto producto) {

        Iterator<Nevera> controlNeveras = neveras.iterator();
        while (controlNeveras.hasNext()) {
            Nevera mercNevera = controlNeveras.next();
            if (mercNevera.contieneProducto(producto)) {
                mercNevera.eliminarProducto(producto);
                return true;
            }
        }
        return false;
    }

    // Hacer método persistente
    public boolean eliminarProductoAEstanteria(Producto producto) {

        Iterator<Estanteria> controlEstanterias = estanterias.iterator();
        while (controlEstanterias.hasNext()) {
            Estanteria mercEstanteria = controlEstanterias.next();
            if (mercEstanteria.contieneProducto(producto)) {
                mercEstanteria.eliminarProducto(producto);
                return true;
            }
        }
        return false;
    }

    // Hacer método persistente
    // Método no hecho
    public boolean eliminarProductoAquíYEnEmpresa(Producto producto) {

        try {
            stock.remove(producto);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Se ha producido un error al agregar el producto al stock del supermercado");
            return false;
        }
        try {
            empresa.getStock().remove(producto);

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Se ha producido un error al agregar el producto al stock de la empresa");
            return false;
        }
        return true;
    }


    public void eliminarEstanteria(){
        
        if (estanterias.size()>0) {
            Estanteria estante = null;
            do {
                int id = 0;
                mostrarEstanterias2();
                System.out.println("Indique el número que identifica la estantería a eliminar");
                System.out.print("ID: ");
                id = Herramientas.pedirEnteroPositivo();
                for (Estanteria esta : estanterias) {
                    if (esta.getNumeroEstanteria() == id) {
                        estante = esta;
                        
                    }
                }
                if (estante!=null) {
                    break;
                }else{
                    System.out.println("No se ha encontrado una estantería con el id seleccionado, introsuzca un id valido");
                }
                
            } while (true);

            if (estante.getCapacidad()==estante.getespacioDisponible()) {
                estanterias.remove(estante);
                System.out.println("estantería eliminada con éxito");
            }else{
                int cantidadDeProductos = estante.getCapacidad()-estante.getespacioDisponible();
                int espacioDisponible = 0;
                for (Estanteria estanteriaX : estanterias) {
                    if (estanteriaX != estante) {
                        espacioDisponible = espacioDisponible+estanteriaX.getespacioDisponible();
                    }
                }
                if (espacioDisponible>=cantidadDeProductos) {
                    ArrayList<Producto> productosAMover =  estante.getProductos();

                    for (Producto producto : productosAMover) {
                        for (Estanteria estant : estanterias) {
                            if (estant != estante) {
                                if (estant.getespacioDisponible()>0) {
                                    estant.agregarProducto(producto);
                                    break;
                                }
                            }
                        }
                    }

                    estanterias.remove(estante);
                    System.out.println("estantería eliminada con éxito");

                } else {
                    System.out.println("No se puede eliminar la estantería porque no hay espacio suficiente en el establecimiento");
                }
                
            }
        }else{
            System.out.println("No hay estanterias que eliminar en este establecimiento");
        }
    }


    public void eliminarNevera(){
        
        if (neveras.size()>0) {
            Nevera nevera = null;
            do {
                int id = 0;
                mostrarNeveras2();
                System.out.println("Indique el número que identifica la nevera a eliminar");
                System.out.print("ID: ");
                id = Herramientas.pedirEnteroPositivo();
                for (Nevera neve : neveras) {
                    if (neve.getId() == id) {
                        nevera = neve;
                        
                    }
                }
                if (nevera!=null) {
                    break;
                }else{
                    System.out.println("No se ha encontrado una nevera con el id seleccionado, introsuzca un id valido");
                }
                
            } while (true);

            if (nevera.getCapacidad()==nevera.getespacioDisponible()) {
                neveras.remove(nevera);
            }else{
                int cantidadDeProductos = nevera.getCapacidad()-nevera.getespacioDisponible();
                int espacioDisponible = 0;
                for (Nevera neve : neveras) {
                    if (neve != nevera) {
                        espacioDisponible = espacioDisponible+neve.getespacioDisponible();
                    }
                }
                if (espacioDisponible>=cantidadDeProductos) {
                    ArrayList<Producto> productosAMover =  nevera.getProductos();

                    for (Producto producto : productosAMover) {
                        for (Nevera neveraa : neveras) {
                            if (neveraa != nevera) {
                                if (neveraa.getespacioDisponible()>0) {
                                    neveraa.agregarproducto(producto);
                                    break;
                                }
                            }
                        }
                    }

                    neveras.remove(nevera);
                } else {
                    System.out.println("No se puede eliminar la nevera porque no hay espacio suficiente en el establecimiento");
                }
                
            }
        }else{
            System.out.println("No hay neveras que eliminar en este establecimiento");
        }
    }

    public void mostrarEstanterias2(){
        if (estanterias.size()>0) {
            System.out.println("*****************************");
            System.out.println("         ESTANTERIAS");
            System.out.println("*****************************");
            for (Estanteria est : estanterias) {
                System.out.println("Numero de estantería: "+est.getNumeroEstanteria());
                System.out.println("Capacidad: "+est.getCapacidad());
                System.out.println("Espacio disponible: "+est.getespacioDisponible());
                System.out.println("*****************************");
            }
            
        }else{
            System.out.println("No hay estanterias en este establecimiento");
        }
    }

    public void mostrarNeveras2(){
        if (neveras.size()>0) {
            System.out.println("*****************************");
            System.out.println("          NEVERAS");
            System.out.println("*****************************");
            for (Nevera nev : neveras) {
                System.out.println("Numero de estantería: "+nev.getId());
                System.out.println("Capacidad: "+nev.getCapacidad());
                System.out.println("Espacio disponible: "+nev.getespacioDisponible());
                System.out.println("*****************************");
            }
            
        }else{
            System.out.println("No hay neveras en este establecimiento");
        }
    }

    public boolean eliminarTrabajador(){
        if (empleados.size()>0) {
            Empleado empleadoAEliminar = devolverTrabajador();
            if (empleadoAEliminar.getTipoDeEmpleado()!=TipoDeEmpleado.GERENTE||getEncargados().size()>1) {
                boolean reasignarEncargado = false;
                if (empleadoAEliminar.getTipoDeEmpleado() == TipoDeEmpleado.ENCARGADO) {
                    reasignarEncargado =true;
                }
                empresa.getTrabajadores().remove(empleadoAEliminar);
                empleados.remove(empleadoAEliminar);
                if (reasignarEncargado) {
                    if (getEncargados().size()>1) {
                        System.out.println("Vamos a tener que reasignar un encargado para cada trabajador");
                    }
                    for (Empleado empleado : empleados) {
                        if (empleado.getTipoDeEmpleado()!=TipoDeEmpleado.GERENTE&&empleado.getTipoDeEmpleado()!=TipoDeEmpleado.ENCARGADO) {
                            empleado.agregarEncargado();
                        }
                    }
                }
                return true;
                
            }else{
                System.out.println("No se puede eliminar un gerente, o un encargado si solo hay uno");
                return false;
            }
        }else{
            System.out.println("No hay trabajadores en este almacen");
            return false;
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

    public ArrayList<Producto> getStock() {
        return stock;
    }

    public void setStock(ArrayList<Producto> stock) {
        this.stock = stock;
    }

    public Empresa getEmpresa() {
        return empresa;
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

    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    @Override
    public int obtenerCantidadProductos() {
        return stock.size();
    }

}
