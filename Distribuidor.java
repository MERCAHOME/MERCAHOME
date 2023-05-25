import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Distribuidor extends Establecimiento implements Herramientas {
    //Si cambia jack el ID de producto cambiar el método de eliminar producto/retornarUnProducto
    private String nombre;
    private String email;
    private ArrayList<Producto> productosQueDistribuye = new ArrayList<>();
    
    public Distribuidor(String nombreDistribuidor, String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico, String email) {
        super(CIF, numeroDeTelefono, ubicacion, horarioPublico);
        this.email = email;
        this.nombre = nombreDistribuidor;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Producto> getProductosQueDistribuye() {
        return productosQueDistribuye;
    }
    public void darDeAltaUnproducto(){
        System.out.println("Indique el nombre de el producto a dar de alta.");
        System.out.print("Nobre: ");
        String nombreDelProducto = Herramientas.pedirString();
        int respuesta = 0;
        do {
            System.out.println("El producto '"+nombreDelProducto+"' es perecedero?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.print("Respuesta: ");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta<1||respuesta>2) {
                System.out.println("\nError: solo puede introducir 1 o 2");
                System.out.println("Introduzca su respuesta de nuevo\n");
            }
        } while (respuesta<1||respuesta>2);
        boolean perecedero = respuesta == 1?true:false;
        do {
            System.out.println("El producto '"+nombreDelProducto+"' es refrigerado?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.print("Respuesta: ");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta<1||respuesta>2) {
                System.out.println("\nError: solo puede introducir 1 o 2");
                System.out.println("Introduzca su respuesta de nuevo\n");
            }
        } while (respuesta<1||respuesta>2);
        boolean refrigerado = respuesta == 1?true:false;
        do {
            System.out.println("El producto '"+nombreDelProducto+"' caduca?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.print("Respuesta: ");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta<1||respuesta>2) {
                System.out.println("\nError: solo puede introducir 1 o 2");
                System.out.println("Introduzca su respuesta de nuevo\n");
            }
        } while (respuesta<1||respuesta>2);
        boolean caduca = respuesta == 1?true:false;
        int diasQueTardaEnCaducar=0;
        if (caduca) {
            System.out.println("Cuantos días tarda en caducar el producto '"+nombreDelProducto+"'?");
            System.out.print("Nº de dias: ");
            diasQueTardaEnCaducar = Herramientas.pedirEnteroPositivo();
        }
        System.out.println("Por cuantos euros vende "+this.nombre+" el producto '"+nombreDelProducto+"'?");
        System.out.print("Precio: ");
        double precioDistribuidor = Herramientas.pedirDoublePositivo();
        Producto creandoProducto;

        if (caduca) {
            creandoProducto = new Producto(this,  nombre,  perecedero,  refrigerado, precioDistribuidor,  diasQueTardaEnCaducar);
        } else {
            creandoProducto = new Producto(this,  nombre,  perecedero,  refrigerado, precioDistribuidor);
        }

        if (productoYaExiste(creandoProducto)) {
            System.out.println("\n\nEste producto ya existe, por lo que no se ha añadido, si quieres puedes modificar su información.");
        }else{
            agregarProducto(creandoProducto);
            System.out.println("\n\nEl producto '"+nombreDelProducto+"' se ha dado de alta correctamente para el distrubuidor "+this.nombre+".");
        }

    }
    private void agregarProducto(Producto producto) {
        productosQueDistribuye.add(producto);
    }
    //devuelve true si ya existe el producto
    private boolean productoYaExiste(Producto productoADarDeAlta){
        if (productosQueDistribuye.size()>0) {
            Iterator<Producto> iteradorDeProductos = productosQueDistribuye.iterator();
            while (iteradorDeProductos.hasNext()) {
                Producto productoAComprobar = iteradorDeProductos.next();
                if (productoADarDeAlta.getNombre().equalsIgnoreCase(productoAComprobar.getNombre())) {
                    return true;
                }
            }
            return false;
        }else{
            return false;
        }
    }
    public void mostrarProductosQueDistribuye(){
        if (productosQueDistribuye.size()>0) {
            System.out.println("*****************************");
            System.out.println("          PRODUCTOS");
            System.out.println("*****************************");
            Iterator<Producto> iteradorDeProductos = productosQueDistribuye.iterator();
            while (iteradorDeProductos.hasNext()) {
                System.out.println("");
                iteradorDeProductos.next().mostrarParaDistribuidor();
                System.out.println("");
            }
            System.out.println("*****************************");
        }else{
            System.out.println("El proveedor "+this.nombre+" todavía no tiene productos dados de alta");
        }

    }
    public void eliminarProductoQueDistribuye() {
        if (productosQueDistribuye.size()>0) {      
            System.out.println("Idique el producto a eliminar de el listado de "+this.nombre+".");
            productosQueDistribuye.remove(retornarUnProducto());
            System.out.println("Producto eliminado con éxito!");
        }else{
            System.out.println("El proveedor "+this.nombre+" todavía no tiene productos dados de alta");
        }
       
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Producto comprarUnProducto(){
        System.out.println("Seleccione el producto a comprar");
        Producto productoBase = retornarUnProducto();
        System.out.println("Que ganancia en euros quieres tener de este producto?");
        System.out.println("Es decir, cual va a ser la diferencia entre el precio del producto de el distribuidor, y el precio de venta al público (sin I.V.A)");
        System.out.print("Incremento: ");
        double incremento = Herramientas.pedirDoublePositivo();
        return crearUnProducto(productoBase,incremento);

    }
    public ArrayList<Producto> comprarMasDeUnProducto(){
        ArrayList<Producto> productos = new ArrayList<Producto>();
        System.out.println("Seleccione el producto a comprar");
        Producto productoBase = retornarUnProducto();
        System.out.println("Que ganancia en euros quieres tener de este producto?");
        System.out.println("Es decir, cual va a ser la diferencia entre el precio del producto de el distribuidor, y el precio de venta al público (sin I.V.A)");
        System.out.print("Incremento: ");
        double incremento = Herramientas.pedirDoublePositivo();
        System.out.println("Cual es la cantidad de productos que va a necesitar?");
        System.out.print("Cantidad: ");
        int cantidad = Herramientas.pedirEnteroPositivo();
        for (int i = 0; i < cantidad; i++) {
            productos.add(crearUnProducto(productoBase, incremento));
        }
        return productos;
    }
    private Producto retornarUnProducto(){
        if (productosQueDistribuye.size()>0) {
            
                do {
                    mostrarProductosQueDistribuye();
                    System.out.println("Indique el id del producto");
                    System.out.print("ID: ");
                    int idDeProducto = Herramientas.pedirEnteroPositivo();
                    Iterator<Producto> iteradorDeProductos = productosQueDistribuye.iterator();
                    while (iteradorDeProductos.hasNext()) {
                        Producto productoARetornar = iteradorDeProductos.next();
                        if (productoARetornar.getId()==idDeProducto) {
                            return productoARetornar;
                        }
                    }
                    System.out.println("No se ha encontrado ningún producto con este ID, indique el ID nuevamente.");
                    
                } while (true);
        } else {
            System.out.println("El proveedor "+this.nombre+" todavía no tiene productos dados de alta");
            return null;
        }
    }
    private Producto crearUnProducto(Producto producto, double gananciasMERCAHOME){
        String nombre = producto.getNombre();
        boolean perecedero = producto.isPerecedero();
        boolean refrigerado = producto.isRefrigerado();
        double precioDistribuidor = producto.getPrecioDistribuidor();
        double precioVentaPublico = precioDistribuidor+gananciasMERCAHOME;
        if (producto.getDiasQueTardaEnCaducar()>=0) {
            int diasQueTardaEnCaducar = producto.getDiasQueTardaEnCaducar();
            LocalDate fechaDeCaducidad = LocalDate.now().plusDays(diasQueTardaEnCaducar);
            return new Producto(this,  nombre,  perecedero,  refrigerado, fechaDeCaducidad,  precioDistribuidor,  precioVentaPublico,  diasQueTardaEnCaducar);
        }else{
            return new Producto(this,  nombre,  perecedero,  refrigerado, precioDistribuidor,  precioVentaPublico);
        }
    }
}
