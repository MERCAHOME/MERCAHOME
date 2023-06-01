import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Empresa extends EstablecimientoPropio {
    //Necesito un método que me muestre los proveedores y productos que distribuyen(Almacén)boolean, si hay almenos uno true, si se da error o no hay false;
    //Necesito un método Proveedor que me retorne un proveedor(Almacén),si se da algun error o no existen proveedores o lo que sea devolver null;
    private final String nombre = "MERCAHOME";
    private ArrayList<Supermercado> supermercados;
    private ArrayList<Empleado> trabajadores;
    private ArrayList<Distribuidor> distribuidores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Almacen> almacenes;
    private ArrayList<Factura> facturas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Descuento> descuentos;
    private ArrayList<Producto> stock;
    
    public Empresa() {
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
        return new Distribuidor();
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
