import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Empresa extends EstablecimientoPropio {

    private final String nombre = "MERCAHOME";
    private ArrayList<Supermercado> supermercados = new ArrayList<>();
    private ArrayList<Empleado> trabajadores = new ArrayList<>();
    private ArrayList<Distribuidor> distribuidores = new ArrayList<>();
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Almacen> almacenes = new ArrayList<>();
    private ArrayList<Factura> facturas = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Descuento> descuentos = new ArrayList<>();
    private ArrayList<Producto> stock = new ArrayList<>();
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();
    
    public Empresa() {
       
    }

    public void puestaEnMarcha(){
        Herramientas.limpiarPantalla();
        System.out.println("Vamos a dar de alta el primer almacén");
        almacenes.add(new Almacen(this));
        Herramientas.limpiarPantalla();
        System.out.println("Vamos a dar de alta el primer supermercado");
        supermercados.add(new Supermercado(this));
        Herramientas.limpiarPantalla();
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
                System.out.println("                                PROVEEDOR");
                System.out.println("*****************************************************************************");
                System.out.println("NOMBRE PROVEEDOR: " + d.getNombre());
                System.out.println("CIF: " + d.getCIF());

                System.out.println("\n*****************************************************************************");
                System.out.println("                                PRODUCTOS");
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
            boolean matriculaValida = true;
            do {
                matricula = Herramientas.crearMatricula();
                for (Vehiculo cochVehiculo : vehiculos) {
                    if (cochVehiculo.getMatricula().equalsIgnoreCase(matricula)) {
                        matriculaValida = false;
                        System.out.println("Ya existe un vehiculo con esta matricula dado de alta");
                        System.out.println("Introduzca la matrícula de nuevo");
                    }
                }
            } while (!matriculaValida);
            Vehiculo vehiculo = new Vehiculo(matricula);
            vehiculosSupermercado.add(vehiculo);
            vehiculos.add(vehiculo);
        }
        return vehiculosSupermercado;
    }

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























































































































































    public void IniciarApp(){
        leerDatosSupermercados();
        leerDatosTrabajadores();
        leerDatosDistribuidores();
        leerDatosClientes();
        leerDatosAlmacenes();
        leerDatosFacturas();
        leerDatosPedidos();
        leerDatosDescuentos();
        leerDatosStock();
    }

    private void leerDatosSupermercados() {
        File file = new File("./datos/supermercados.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                supermercados = (ArrayList<Supermercado>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } 
    }

    private void leerDatosTrabajadores() {
        File file = new File("./datos/trabajadores.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                trabajadores = (ArrayList<Empleado>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosDistribuidores() {
        File file = new File("./datos/distribuidores.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                distribuidores = (ArrayList<Distribuidor>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    private void leerDatosClientes() {
        File file = new File("./datos/clientes.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                clientes = (ArrayList<Cliente>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosAlmacenes() {
        File file = new File("./datos/almacenes.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                almacenes = (ArrayList<Almacen>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosFacturas() {
        File file = new File("./datos/facturas.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                facturas = (ArrayList<Factura>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosPedidos() {
        File file = new File("./datos/pedidos.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                pedidos = (ArrayList<Pedido>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosDescuentos() {
        File file = new File("./datos/descuentos.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                descuentos = (ArrayList<Descuento>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    
    private void leerDatosStock() {
        File file = new File("./datos/stock.dat");
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                stock = (ArrayList<Producto>) in.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
        
    public void guardarCambios() {
        guardarDatosSupermercados();
        guardarDatosTrabajadores();
        guardarDatosDistribuidores();
        guardarDatosClientes();
        guardarDatosAlmacenes();
        guardarDatosFacturas();
        guardarDatosPedidos();
        guardarDatosDescuentos();
        guardarDatosStock();
    }

    private void guardarDatosSupermercados() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/supermercados.dat"))) {
            out.writeObject(supermercados);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    private void guardarDatosTrabajadores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/trabajadores.dat"))) {
            out.writeObject(trabajadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosDistribuidores() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/distribuidores.dat"))) {
            out.writeObject(distribuidores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosClientes() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/clientes.dat"))) {
            out.writeObject(clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosAlmacenes() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/almacenes.dat"))) {
            out.writeObject(almacenes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosFacturas() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/facturas.dat"))) {
            out.writeObject(facturas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosPedidos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/pedidos.dat"))) {
            out.writeObject(pedidos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosDescuentos() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/descuentos.dat"))) {
            out.writeObject(descuentos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void guardarDatosStock() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./datos/stock.dat"))) {
            out.writeObject(stock);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
