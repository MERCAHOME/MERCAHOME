import java.util.ArrayList;

public class ProvesJAVA {
    public static void main(String[] args) {
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


        System.out.println("*****************************************************************************************\n\n");

        */

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
}
