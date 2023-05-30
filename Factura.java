import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class Factura {
    private ArrayList<Producto> productos = new ArrayList<>();
    private double totalSinIva;
    private double totalConIva;
    private double totalConTransporte;
    private final Cliente cliente;
    private LocalDate fecha;
    private static int IDgenerator = 0;
    private int id;
    private Establecimiento emisor;
    private Descuento descuento = null;
    private double costoTransporte;

    public Factura(ArrayList<Producto> productos, Cliente cliente, LocalDate fecha, Establecimiento emisor,
            double precioTransporte, double costoTransporte) {
        this.productos = productos;
        this.cliente = cliente;
        this.fecha = fecha;
        this.emisor = emisor;
        this.id = generateID();
    }

    public Factura(ArrayList<Producto> productos, Cliente cliente, LocalDate fecha, Establecimiento emisor,
            double precioTransporte, Descuento descuento, double costoTransporte) {
        this.productos = productos;
        this.cliente = cliente;
        this.fecha = fecha;
        this.emisor = emisor;
        this.id = generateID();
        this.descuento = descuento;
    }

    public static void mostrar() {
        ArrayList<String> nombresVistos = new ArrayList<>(); // Para evitar mostrar duplicados

        System.out.println("*********************************************************************");
        System.out.println("                             FACTURA");
        System.out.println("*********************************************************************");
        System.out.println("NÚMERO DE PEDIDO: " + getId());
        System.out.println("\nFECHA: " + getFecha());

        System.out.println("*********************************************************************");
        // System.out.println("PRODUCTO PRECIO ID CANTIDAD");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", "PRODUCTO", "PRECIO", "CANTIDAD", "TOTAL");
        System.out.printf("| %-24s | %-14s | %-10s | %-8s |%n", " ", " ", " ", " ");
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
        totalConTransporte = totalSinIva + costoTransporte;
        totalConIva = totalConTransporte + (totalConTransporte * 0.21);
        System.out.println("*********************************************************************");

        System.out.printf("| %-26s  %-14.2s  %-10s | %-8.2f |%n", "TOTAL SIN IVA ", " ", " ", totalSinIva);
        System.out.printf("| %-26s  %-14.2s  %-10s | %-8.2f |%n", "TOTAL CON TRANSPORTE ", " ", " ",
                totalConTransporte);
        double totalConDescuento;
        if (descuento != null) {
            if (descuento instanceof DescuentoPorcentual) {
                totalConDescuento = totalConTransporte
                        - (totalConTransporte * ((DescuentoPorcentual) descuento).getPorcentajeDescuento());
            } else {
                totalConDescuento = totalConTransporte - ((DescuentoCantidad) descuento).getCantidadDescuento();
            }
            System.out.printf("| %-26s  %-14.2s  %-10s | %-8.2f |%n", "TOTAL CON TRANSPORTE ", " ", " ",
                    totalConDescuento);
        }
        System.out.printf("| %-26s  %-14.2s  %-10s | %-8.2f |%n", "TOTAL CON IVA ", " ", " ", totalConIva);

        System.out.println("*********************************************************************");
    }

    public static int cantidad(ArrayList productos, Producto producto) {
        return Collections.frequency(productos, producto);
    }

    public boolean anyadirProducto(Producto producto) {
        if (!productos.contains(producto)) {
            if (productos.add(producto)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public boolean eliminarProducto(Producto producto) {
        if (!productos.contains(producto)) {
            if (productos.remove(producto)) {
                return true;
            }
            return false;
        } else {
            return false;
        }
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double getCostoTransporte() {
        return costoTransporte;
    }

    public void setCostoTransporte(double costoTransporte) {
        this.costoTransporte = costoTransporte;
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

    public double getTotalConTransporte() {
        return totalConTransporte;
    }

    public void setTotalConTransporte(double totalConTransporte) {
        this.totalConTransporte = totalConTransporte;
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
