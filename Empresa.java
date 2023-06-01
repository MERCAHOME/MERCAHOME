import java.util.ArrayList;

public class Empresa extends EstablecimientoPropio {
    // Necesito un método que me muestre los proveedores y productos que
    // distribuyen(Almacén)boolean, si hay almenos uno true, si se da error o no hay
    // false;
    // Necesito un método Proveedor que me retorne un proveedor(Almacén),si se da
    // algun error o no existen proveedores o lo que sea devolver null;

    // METODOS
    // Metodos comprobar si existe cliente y trabajador (dato clau igual (ex. DNI))
    // PASSANT UN CLIENT O TREBALLADOR COM A PARAMETRE
    // AGREGAR supermecado i almacen mentres no coincidixa la ubicacio.
    // AGREGAR distribuidor sempre q no existixca amb el mateix cif
    // AGREGAR descuento
    //
    // NO PUEDEN HABER DOS ALMACENES EN LA MISMA LOCALIDAD

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

    public boolean anyadirEstablecimiento(Almacen almacen) {
        int contador = 0;
        for (Almacen a : almacenes) {
            if (a.getUbicacion().equals(almacen.getUbicacion())) {
                contador++;
            }
        }
        if (contador != 0) {
            return false;
        } else {
            almacenes.add(almacen);
            return true;
        }
    }

    public boolean agregarDistribuidor(Distribuidor distribuidor) {
        int contador = 0;
        for (Distribuidor d : distribuidores) {
            if (d.getCIF().equals(distribuidor.getCIF())) {
                contador++;
            }
        }
        if (contador != 0) {
            return false;
        } else {
            distribuidores.add(distribuidor);
            return true;
        }
    }

    public boolean agregarDescuento(Descuento descuento) {
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

    // TALLAR NOMS LLARGS D PRODUCTES
    public boolean mostrarProveedoresYProductoQueDistribuye() {
        ArrayList<String> nombresVistos = new ArrayList<>(); // Para evitar mostrar duplicados

        boolean existeAlmenosProductoEnUnProveedor = false;

        for (Distribuidor d : distribuidores) {
            if (d.getProductosQueDistribuye().size() > 0) {
                existeAlmenosProductoEnUnProveedor = true;

                System.out.println("*****************************************************************************");
                System.out.println("                            PROVEEDOR");
                System.out.println("*****************************************************************************");
                System.out.println("NOMBRE PROVEEDOR: " + d.getNombre());
                System.out.println("CIF: " + d.getCIF());

                System.out.println("\n*****************************************************************************");
                System.out.println("                            PRODUCTOS");
                System.out.println("*****************************************************************************");
                System.out.printf("| %-24s | %-14s | %-10s | %-16s |%n", "ID", "NOMBRE", "PRECIO", "PERECEDERO");
                System.out.printf("| %-24s | %-14s | %-10s | %-16s |%n", " ", " ", " ", " ");
                ArrayList<Producto> productos = d.getProductosQueDistribuye();
                for (Producto producto : productos) {
                    if (!nombresVistos.contains(producto.getNombre())) {
                        nombresVistos.add(producto.getNombre());
                        String nombreProducto = producto.getNombre();
                        double precioProducto = producto.getPrecioVentaPublico();
                        int idProducto = producto.getId();
                        boolean perecedero = producto.isPerecedero();
                        String perecederoS = perecedero ? "Es perecedero" : "No es perecedero";
                        System.out.printf("| %-24s | %-14s | %-10f | %-16s |%n", idProducto, nombreProducto,
                                precioProducto, perecederoS);
                    }
                }
            }

        }
        if (existeAlmenosProductoEnUnProveedor) {
            return true;
        } else {
            return false;
        }
    }

    public Distribuidor devolverProveedor(String cifDistribuidor) {
        for (Distribuidor distribuidor : distribuidores) {
            if (distribuidor.getCIF().equals(cifDistribuidor)) {
                return distribuidor;
            }
        }
        System.out.println("ERROR: No existe un distribuidor con ese CIF...");
        return null;
    }

    public boolean existeProveedorConCifQueContieneAlmenos1Producto(String cif) {

        for (Distribuidor distribuidor : distribuidores) {
            if (distribuidor.getCIF().equalsIgnoreCase(cif) && distribuidor.getProductosQueDistribuye().size() > 0) {
                return true;
            }
        }
        return false;

    }

    public Almacen devolverAlmacen() {
        // mostrar almacens i fer q trien un cif ASSEGURNANT Q EXISTIXQUEN ALMACENS
        // ANTES
        if (almacenes.size() > 0) {
            
            String cifAlmacen = "";
            do {
                mostrarAlmacenes();
                System.out.println("Indique el CIF del almacén a operar");
                System.out.print("CIF: ");
                cifAlmacen = Herramientas.pedirString();
                for (Almacen almacen : almacenes) {
                    if (almacen.getCIF().equalsIgnoreCase(cifAlmacen)) {
                        return almacen;
                    }
                }
                System.out.println("No existe ningún almacén con el CIF indicado... Indica un CIF válido.");
            } while (true);
        } else {
            System.out.println("No existen almacenes. Contacte con el administrador porfavor.");
            return null;
        }
    }

    private void mostrarAlmacenes() {
        if (almacenes.size() > 0) {
            System.out.println("*****************************");
            System.out.println("           ALMACENES");
            System.out.println("*****************************");
            for (Almacen almacen : almacenes) {
                System.out.println("Ubicacion: " + almacen.getUbicacion().toStringBasic());
                System.out.println("CIF: " + almacen.getCIF());
                System.out.println("*****************************");
            }
        } else {
            System.out.println("No existe ningun almacen...");
        }
    }

    public ArrayList<Vehiculo> darVehiculosASupermercados(int cantidad) {
        ArrayList<Vehiculo> vehiculosSupermercado = new ArrayList<>();
        String matricula = " ";
        for (int i = 0; i < cantidad; i++) {
            System.out.println("Dime la matricula del vehiculo " + i + ": ");
            matricula = Herramientas.pedirString();
            vehiculosSupermercado.add(new Vehiculo(matricula));
        }
        return vehiculosSupermercado;
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
