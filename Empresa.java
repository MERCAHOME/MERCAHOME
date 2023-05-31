import java.util.ArrayList;

public class Empresa extends EstablecimientoPropio {
    // Necesito un método que me muestre los proveedores y productos que
    // distribuyen(Almacén)boolean, si hay almenos uno true, si se da error o no hay
    // false;
    // Necesito un método Proveedor que me retorne un proveedor(Almacén),si se da
    // algun error o no existen proveedores o lo que sea devolver null;

    // METODOS
    // Metodos comprobar si existe cliente y trabajador (dato clau igual (ex. DNI)) PASSANT UN CLIENT O TREBALLADOR COM A PARAMETRE
    // AGREGAR supermecado i almacen mentres no coincidixa la ubicacio.
    // AGREGAR distribuidor sempre q no existixca amb el mateix cif
    // AGREGAR descuento
    //

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

    public boolean existe(Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getDNI().equals(cliente.getDNI())) {
                return true;
            }
        }
        return false;
    }

    public boolean existe(Empleado empleado) {
        for (Empleado t : trabajadores) {
            if (t.getDNI().equals(t.getDNI())) {
                return true;
            }
        }
        return false;
    }

    public boolean anyadirEstablecimiento(Supermercado supermercado) {
        int contador = 0;
        for (Supermercado s : supermercados) {
            if (s.getUbicacion().equals(supermercado.getUbicacion())) {
                contador++;
            }
        }
        if (contador != 0) {
            return false;
        } else {
            supermercados.add(supermercado);
            return true;
        }
    }

    public boolean anyadirEstablecimiento(Almacen almacen){
        int contador = 0;
        for (Almacen a : almacenes) {
            if (a.getUbicacion().equals(almacen.getUbicacion())) {
                contador++;
            }
        }
        if(contador!=0){
        return false;
         }else{
        almacenes.add(almacen);
        return true;
        }
    }

    public boolean agregarDistribuidor(Distribuidor distribuidor){
        int contador = 0;
        for (Distribuidor d : distribuidores) {
            if (d.getCIF().equals(distribuidor.getCIF())) {
                contador++;
            }
        }
        if (contador != 0) {
            return false;
        } else{
            distribuidores.add(distribuidor);
            return true;
        }
    }

    public boolean agregarDescuento(Descuento descuento){
        int contador = 0;
        for (Descuento descuento2 : descuentos) {
            if (descuento2.equals(descuento)) {
                contador++;
            }
        }
        if (contador != 0) {
            return false;
        } else {
            descuentos.add(descuento);
            return true;
        }
    }

    
    // llistat proveedors i tots els productes q distribuix cada proveedor
    public boolean mostrarProveedoresYProductoQueDistribuye(){
        for (Distribuidor d : distribuidores) {
        System.out.println("*********************************************************************");
        System.out.println("                            PROVEEDOR");
        System.out.println("*********************************************************************");
        System.out.println("NOMBRE PROVEEDOR: " + d.getNombre());
        System.out.println("\nCIF: " + d.getCIF());

        System.out.println("\n*********************************************************************");
        System.out.println("                            PRODUCTOS");
        System.out.println("*********************************************************************");
        System.out.println("*********************************************************************");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", "PRODUCTO", "PRECIO", "CANTIDAD", "TOTAL");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", " ", " ", " ", " ");
        ArrayList<Producto> productos = d.getProductosQueDistribuye();
        for (Producto producto : productos) {
            if (!nombresVistos.contains(producto.getNombre())) {
                nombresVistos.add(producto.getNombre());
                String nombreProducto = producto.getNombre();
                double precioProducto = producto.getPrecioVentaPublico();
                int idProducto = producto.getId();
                int cantidadProducto = cantidad(productos, producto);
                double totalProducto = cantidadProducto * precioProducto;
                totalSinIva = totalSinIva + totalProducto;
                System.out.printf("| %-24s | %-14.2f | %-10d | %-8.2f |%n", nombreProducto, precioProducto,
                        cantidadProducto, totalProducto);
            }
        }
        return true;
    }
    }

    // indicar CIF y retornar distribuidor en ixe cif
    public Distribuidor devolverProveedor(){
        return new Distribuidor();
    }

    // reguntar al usuario cual es el almacen que quiere para su supermercado (demanant cif)
    public Almacen devolverAlmacen(){
        return new Almacen(this);
    }
        
    //crea tantos vehiculos como te pidan los añades a tu arraylist y me los devuelves en un arraylist
    public ArrayList<Vehiculo> darVehiculosASupermercados(int cantidad){
        return new ArrayList<>(null);
    }

    // fin

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
