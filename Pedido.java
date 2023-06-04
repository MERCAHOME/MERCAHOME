import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
//metodo que talle el nom a maxim 24 caracters
//agregar factura y pedido a el super empresa y cliente
//agregar descuento
//al consultar el estado de el pedido d¡se ha de modificar su estado dependiendo de la fecha actual
public class Pedido implements Herramientas {
    private ArrayList<Producto> productos = new ArrayList<>();
    private double total;
    private double totalConTransporte;
    private double totalConTransporteYDescuento;
    private double totalConTransporteDescuentoEIVA;
    private double precioTransporte;
    private Factura factura;
    private static int IDgenerator = 0;
    private int id;
    private LocalDateTime fechaRealizacion;
    private LocalDateTime horaDeEntrega;
    private EstadoDePedido estadoDePedido;
    private int distanciaEnKmHastaSupermercado;
    private Descuento descuento;

   
    public Pedido(Supermercado supermercado) {
        this.id = generateID();
        this.fechaRealizacion = LocalDateTime.now();
        System.out.println("Cuantos Km hay desde el supermercado hasta la ubicación de entrega de el pedido?");
        System.out.print("Km: ");
        this.distanciaEnKmHastaSupermercado = Herramientas.pedirEnteroPositivo();
        int respuesta =0;
        do {
            System.out.println("Tiene algún código de descuento ?");
            System.out.println("1- Si");
            System.out.println("2- No");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta<1||respuesta>2) {
                System.out.println("Error, solo puede introducir 1 o 2");
                System.out.println("Introduzca un valor válido");
            }
        } while (respuesta<1||respuesta>2);
        if (respuesta==1) {
            this.descuento = supermercado.getEmpresa().devolverDescuento();
        }

        if (realizarPedido(supermercado)) {
            System.out.println("Pedido realizado con éxito");
            this.estadoDePedido = EstadoDePedido.ACEPTADO;
        } else {
            System.out.println("No se ha podido realizar el pedido, contacte con soporte");
            this.estadoDePedido = EstadoDePedido.RECHAZADO;
        }
    }

    private boolean realizarPedido(Supermercado supermercado) {

        if (supermercado.getStock().size() > 0) {
            menuPedido(supermercado);
            return true;
        } else {
            System.out.println(
                    "No se puede realizar un pedido desde este supermercado ya que no tiene productos dados de alta");
            return false;
        }

    }

    private void menuPedido(Supermercado supermercado) {
        int respuesta = 0;
        do {
            String[] titulos = {
                    "MENU REALIZACIÓN PEDIDO"
            };
            String[] opciones = {
                    "1- Agregar producto",
                    "2- Eliminar producto",
                    "3- Mostrar carrito",
                    "0- Finalizar pedido"
            };

            respuesta = Herramientas.crearMenu(titulos, opciones);

            switch (respuesta) {
                case 1:
                    if (agregarProductoAPedido(supermercado)) {
                        System.out.println("Producto agregado con éxito");
                    } else {
                        System.out.println("No se ha podido agregar el producto");
                    }
                    break;
                case 2:
                    if (eliminarProductoDePedido(supermercado)) {
                        System.out.println("Producto eliminado con éxito");
                    } else {
                        System.out.println("No se ha podido eliminar el producto del pedido");
                    }
                    break;
                case 3:
                    if (productos.size()>0) {
                        mostrarProductosPedidoConTotal();
                    } else {
                        System.out.println("Todavía no hay productos en su pedido");
                    }

                    break;
                case 0:
                    //preguntar por descuento
                    break;
                default:
                    System.out.println("Error, no puede introducir un número menor que 0 o mayor que 3");
                    break;
            }

        } while (respuesta != 0);
    }

    private boolean agregarProductoAPedido(Supermercado supermercado) {
        try {
            mostrarProductosSupermercado(supermercado);
            System.out.println("Que producto quiere agregar al pedido?");
            ArrayList<Producto> productosAOperar = seleccionarProductoARetornar(supermercado.getStock());
            Iterator<Producto> iterator = productosAOperar.iterator();
            while (iterator.hasNext()) {
                Producto productoAOperar = iterator.next();
                productos.add(productoAOperar);
                supermercado.eliminarProductoSupermercadoYEmpresa(productoAOperar);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean eliminarProductoDePedido(Supermercado supermercado) {
        if (productos.size() > 0) {
            try {
                mostrarProductosPedido();
                System.out.println("Que producto quiere eliminar al pedido?");
                ArrayList<Producto> productosAOperar = seleccionarProductoARetornar(productos);
                Iterator<Producto> iterator = productosAOperar.iterator();
                while (iterator.hasNext()) {
                    Producto productoAOperar = iterator.next();
                    supermercado.agregarProductoSupermercadoYEmpresa(productoAOperar);
                    productos.remove(productoAOperar);
                }
                return true;
            } catch (Exception e) {
                return false;
            }

        } else {
            System.out.println("Todavia no hay productos en su pedido");
            System.out.println("Volviendo...");
            return false;
        }

    }

    private ArrayList<Producto> seleccionarProductoARetornar(ArrayList<Producto> productosOperar) {
        ArrayList<Producto> productosARetornar = new ArrayList<>();
        int respuesta = 0;
        do {
            System.out.println("Seleccione uno de los productos mostrados anteriormente");
            System.out.println("como desea seleccionar el producto?");
            System.out.println("1- por su ID");
            System.out.println("2- Por su nombre");
            System.out.print("Respuesta: ");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta < 1 || respuesta > 2) {
                System.out.println("\n\nError, número invalido\n\n");
            }
        } while (respuesta < 1 || respuesta > 2);
        int idProducto = -1;
        String nombreDeProducto = null;
        if (respuesta == 1) {
            System.out.println("Cual es el ID de el producto a seleccionar?");
            System.out.print("ID: ");
            idProducto = Herramientas.pedirEnteroPositivo();
        } else {
            System.out.println("Cual es el nombre de el producto a seleccionar?");
            System.out.print("Nombre: ");
            nombreDeProducto = Herramientas.pedirString();
        }
        System.out.println("Cuantas unidades de este producto quiere seleccionar?");
        int cantidad = Herramientas.pedirEnteroPositivo();
        Producto productoOperar = null;
        if (idProducto != -1) {
            productoOperar = retornarProducto(idProducto, productosOperar);
            nombreDeProducto = productoOperar.getNombre();
        } else {
            productoOperar = retornarProducto(nombreDeProducto, productosOperar);
        }
        // Comprobar si existen suficientes productos en stock
        // Si existen los devolvemos y sino devuelve null
        if (obtenerCantidadProducto(productoOperar, productosOperar) >= cantidad) {
            for (int i = 0; i < cantidad; i++) {
                productosARetornar
                        .add(retornarProductoNoagregado(nombreDeProducto, productosOperar, productosARetornar));
            }
            return productosARetornar;
        } else {
            System.out.println("No hay bastante cantidad de " + productoOperar.getNombre());
            return null;
        }

    }

    private void mostrarProductosSupermercado(Supermercado supermercado) {
        ArrayList<Producto> stock = supermercado.getStock();
        ArrayList<String> nombresVistos = new ArrayList<>(); // Para evitar mostrar duplicados

        System.out.println("*********************************************************************");
        System.out.println("                       PRODUCTOS DISPONIBLES");
        System.out.println("*********************************************************************");
        //System.out.println("PRODUCTO                PRECIO        ID        CANTIDAD");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", "PRODUCTO", "PRECIO", "ID", "CANTIDAD");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", " ", " ", " ", " ");
        for (Producto producto : stock) {
            if (!nombresVistos.contains(producto.getNombre())) {
                nombresVistos.add(producto.getNombre());
                String nombreProducto = producto.getNombre();
                double precioProducto = producto.getPrecioVentaPublico();
                int idProducto = producto.getId();
                int cantidadProducto = obtenerCantidadProducto(producto, stock);
                //System.out.println(nombreProducto + precioProducto + idProducto + cantidadProducto);
                System.out.printf("| %-24s | %-14.2f | %-10d | %-8d |%n", nombreProducto, precioProducto, idProducto, cantidadProducto);

            }
        }

        System.out.println("*********************************************************************");
    }

    private void mostrarProductosPedido() {

        ArrayList<String> nombresVistos = new ArrayList<>(); // Para evitar mostrar duplicados

        System.out.println("*********************************************************************");
        System.out.println("                          PRODUCTOS PEDIDO");
        System.out.println("*********************************************************************");
        //System.out.println("PRODUCTO                PRECIO        ID        CANTIDAD");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", "PRODUCTO", "PRECIO", "ID", "CANTIDAD");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", " ", " ", " ", " ");
        for (Producto producto : productos) {
            if (!nombresVistos.contains(producto.getNombre())) {
                nombresVistos.add(producto.getNombre());
                String nombreProducto = producto.getNombre();
                double precioProducto = producto.getPrecioVentaPublico();
                int idProducto = producto.getId();
                int cantidadProducto = obtenerCantidadProducto(producto, productos);
                //System.out.println(nombreProducto + precioProducto + idProducto + cantidadProducto);
                System.out.printf("| %-24s | %-14.2f | %-10d | %-8d |%n", nombreProducto, precioProducto, idProducto, cantidadProducto);
            }
        }

        System.out.println("*********************************************************************");
    }
    public void mostrarProductosPedidoConTotal(){
        String stringVacio = " ";
        mostrarProductosPedido();
        total = carcularTotal();
        totalConTransporte = carcularTotal()+calcularPrecioTransporte();
        totalConTransporteYDescuento = calcularTotalConDescuento();
        double iva = totalConTransporteYDescuento/100*21;
        totalConTransporteDescuentoEIVA = totalConTransporteYDescuento+iva;
        System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", "TOTAL", " ", " ", total);
        System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", "TOTAL CON TRANSPORTE", " ", " ",totalConTransporte );
        System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", "TOTAL CON DESCUENTO", " ", " ", totalConTransporteYDescuento);
        System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", "IVA", " ", " ",iva );
        System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", "TOTAL CON IVA", " ", " ",totalConTransporteDescuentoEIVA );
        System.out.println("*********************************************************************");

    }

    private double calcularTotalConDescuento(){
        if (this.descuento!=null) {
            if (descuento instanceof DescuentoCantidad) {
                DescuentoCantidad descuentoCant = (DescuentoCantidad)descuento;
                double importeMinimo =  descuentoCant.getImporteMinimo();
                double cantidadDescuento = descuentoCant.getCantidadDescuento();
                if (carcularTotal()>=importeMinimo) {
                    return (carcularTotal()+calcularPrecioTransporte()) - cantidadDescuento;
                } else {
                    return (carcularTotal()+calcularPrecioTransporte());
                }
            }
            if (descuento instanceof DescuentoPorcentual) {
                DescuentoPorcentual descuentoPorc = (DescuentoPorcentual)descuento;
                double cantidadMaxima = descuentoPorc.getCantidadMaximaDescuento();
                int porcentaje = descuentoPorc.getPorcentajeDescuento();
                double cantidadDescuento = (carcularTotal()+calcularPrecioTransporte())/100*porcentaje;
                if (cantidadDescuento > cantidadMaxima) {
                    return (carcularTotal()+calcularPrecioTransporte())- cantidadMaxima;
                } else {
                    return (carcularTotal()+calcularPrecioTransporte()) - cantidadDescuento;
                }

            }
        }
        return (carcularTotal()+calcularPrecioTransporte()); 
    }

    private double calcularPrecioTransporte(){
        precioTransporte = (double)(distanciaEnKmHastaSupermercado)*0.8;
        return precioTransporte;
    }
    private double carcularTotal(){
        double total = 0;
        for (Producto producto : productos) {
            total = total+ producto.getPrecioVentaPublico();
        }
        return total;
    }
 

    private int obtenerCantidadProducto(Producto producto, ArrayList<Producto> stock) {
        int cantidad = 0;
        for (Producto p : stock) {
            if (p.getNombre().equals(producto.getNombre())) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private void finalizarPedido() {

    }

    private Producto retornarProducto(int id, ArrayList<Producto> productos) {
        Iterator<Producto> productosIterator = productos.iterator();
        while (productosIterator.hasNext()) {
            Producto productoIterado = productosIterator.next();
            if (productoIterado.getId() == id) {
                return productoIterado;
            }
        }
        System.out.println("No existe ningún producto con id " + id);
        return null;
    }

    private Producto retornarProducto(String nombre, ArrayList<Producto> productos) {
        Iterator<Producto> productosIterator = productos.iterator();
        while (productosIterator.hasNext()) {
            Producto productoIterado = productosIterator.next();
            if (productoIterado.getNombre().equalsIgnoreCase(nombre)) {
                return productoIterado;
            }
        }
        System.out.println("No existe ningún producto con nombre " + nombre);
        return null;
    }

    private Producto retornarProductoNoagregado(String nombre, ArrayList<Producto> productos,
            ArrayList<Producto> productosYaAñadidos) {
        Iterator<Producto> productosIterator = productos.iterator();
        while (productosIterator.hasNext()) {
            Producto productoIterado = productosIterator.next();
            if (productoIterado.getNombre().equalsIgnoreCase(nombre)
                    && !productoYaExiste(productoIterado, productosYaAñadidos)) {
                return productoIterado;
            }
        }
        System.out.println("No existe ningún producto con nombre " + nombre);
        return null;
    }

    public boolean productoYaExiste(Producto producto, ArrayList<Producto> productoacomprobar) {
        for (Producto producto2 : productoacomprobar) {
            if (producto2.equals(producto)) {
                return true;
            }
        }
        return false;
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

    public double getTotalConTransporteDescuentoEIVA() {
        return totalConTransporteDescuentoEIVA;
    }

    public double getPrecioTransporte() {
        return precioTransporte;
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
    public Descuento getDescuento() {
        return descuento;
    }

}
