import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Factura {
    private static ArrayList<Producto> productos;
    private double totalSinIva;
    private double totalConIva;
    private double totalConIvaYTransporte;
    private final Cliente cliente;
    private static LocalDate fecha;
    private static int IDgenerator = 0;
    private static int id;
    private Establecimiento emisor;
    private int cantidad;
    
    public Factura(ArrayList<Producto> productos, double totalSinIva, double totalConIva, double totalConIvaYTransporte,
                   Cliente cliente, LocalDate fecha, Establecimiento emisor) {
        this.productos = productos;
        this.totalSinIva = totalSinIva;
        this.totalConIva = totalConIva;
        this.totalConIvaYTransporte = totalConIvaYTransporte;
        this.cliente = cliente;
        this.fecha = fecha;
        this.emisor = emisor;
        this.id = generateID();
    }

    public void mostrarFactura(){
        System.out.println("FACTURA\n");
        System.out.println("NÚMERO DE PEDIDO: " + getId());
        System.out.println("\nFECHA: " + getFecha());
        System.out.println("*****************************************************************************************\n\n");
        System.out.println("De "+ emisor.getUbicacion() + "\nCIF Establecimiento: " + emisor.getCIF() + "\nTelefono: " + emisor.getNumeroDeTelefono());
        System.out.println("Para " + cliente.getNombre() + " " + cliente.getApellidos());
        System.out.println("*****************************************************************************************\n\n");
        System.out.print("Descripcio                          Cantidad          Precio unidad          Importe");
        for (Producto producto : productos) {
            System.out.println(producto.getNombre() + "        " + cantidad + "          PRECIO           PRECIO TOTAL");
        }
        System.out.println("*****************************************************************************************\n\n");

    }
    
    public static void main(String[] args) {
        productos.add(new Producto(null, "ye", false, false, id));
        productos.add(new Producto(null, "ye", false, false, id));
        productos.add(new Producto(null, "ye", false, false, id));

        /*System.out.println("FACTURA\n");
        System.out.println("NÚMERO DE PEDIDO: 4");
        System.out.println("\nFECHA: 24/04/2023");
        System.out.println("*****************************************************************************************\n\n");
        System.out.println("De València, 54\nCIF Establecimiento: 876543fFfg\nTelefono: 948763477");
        System.out.println("Para Erick Ramsdale ");
        System.out.println("*****************************************************************************************\n\n");
        System.out.println("DESCRIPCION                          CANTIDAD          PRECIO UNIDAD          IMPORTE\n");
        System.out.println("Camiseta                                 4                 8.00                24.00€");
        System.out.println("Chaqueta                                 2                12.00                24.00€");
        System.out.println("Abrigo                                   5                40.00               200.00€");
        System.out.println("Calcetines                               10                2.00                20.00€");


        System.out.println("*****************************************************************************************\n\n");*/
        
        ArrayList<String> nombresVistos = new ArrayList<>(); // Para evitar mostrar duplicados

        System.out.println("*********************************************************************");
        System.out.println("                             FACTURA");
        System.out.println("*********************************************************************");
        System.out.println("NÚMERO DE PEDIDO: " + getId());
        System.out.println("\nFECHA: " + getFecha());

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
                int cantidadProducto = cantidad(productos, producto);
                //System.out.println(nombreProducto + precioProducto + idProducto + cantidadProducto);
                System.out.printf("| %-24s | %-14.2f | %-10d | %-8d |%n", nombreProducto, precioProducto, idProducto, cantidadProducto);
            }
        }

        System.out.println("*********************************************************************");
    }

    public static int cantidad(ArrayList productos, Producto producto){
        return Collections.frequency(productos, producto);

    }

    public void anyadirProducto(){
        
    }

    public void eliminarProducto(){
        
    }

    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public double getTotalSinIva() {
        return totalSinIva;
    }
    
    public void setTotalSinIva(double totalSinIva) {
        this.totalSinIva = totalSinIva;
    }
    
    public double getTotalConIva() {
        return totalConIva;
    }
    
    public void setTotalConIva(double totalConIva) {
        this.totalConIva = totalConIva;
    }
    
    public double getTotalConIvaYTransporte() {
        return totalConIvaYTransporte;
    }
    
    public void setTotalConIvaYTransporte(double totalConIvaYTransporte) {
        this.totalConIvaYTransporte = totalConIvaYTransporte;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public static LocalDate getFecha() {
        return fecha;
    }
    
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    
    public static int getId() {
        return id;
    }
    
    public Establecimiento getEmisor() {
        return emisor;
    }
    
    public void setEmisor(Establecimiento emisor) {
        this.emisor = emisor;
    }
    
    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }
}
