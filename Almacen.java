import java.util.ArrayList;
import java.util.Iterator;

public class Almacen extends EstablecimientoPropio implements Stock {
    //Se puede crear un método para eliminar estanterias y neveras teniendo en cuenta que etén vacias, cambiando los productos de sitio en caso de que no
    private ArrayList<Producto> stock;
    private final Empresa empresa;
    private ArrayList<Estanteria> estanterias;
    private ArrayList<Nevera> neveras;
    public int capacidadAlmacen;
    
    public Almacen(Empresa empresa) {
        this.stock = new ArrayList<>();
        this.empresa = empresa;
        this.estanterias = new ArrayList<>();
        this.neveras = new ArrayList<>();
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
            System.out.println("Estantería(s) agregada(s) con éxito!");
        } else {
            System.out.println("No se ha agregado ninguna estantería, contacte con el administrador si no era su intención");
        }
        System.out.println("Cuantas neveras quieres añadir a tu almacén?");
        System.out.print("Cantidad: ");
        int neveras = Herramientas.pedirEnteroPositivo();
        System.out.println("Que capacidad va(n) a tener la(s) nevera(s)?");
        System.out.print("Capacidad: ");
        capacidad = Herramientas.pedirEnteroPositivo();
        if (agregarNeveras(neveras, capacidad)) {
            System.out.println("Nevera(s) agregada(s) con éxito!");
        } else {
            System.out.println("No se ha agregado ninguna nevera, contacte con el administrador si no era su intención");
        }

    }

    //Falta la persistencia
    public boolean agregarNeveras(int cantidad, int capacidad){
        try {
            for (int i = 0; i < cantidad; i++) {
                neveras.add(new Nevera(capacidad));
                capacidadAlmacen = capacidadAlmacen+capacidad;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Se ha producido un error inesperado, contacte con el administrador");
            return false;
        }

    }
    //Falta la persistencia
    public boolean agregarEstanterias(int cantidad, int capacidad, int niveles){

        try {
            for (int i = 0; i < cantidad; i++) {
                estanterias.add(new Estanteria(capacidad, niveles));
                capacidadAlmacen = capacidadAlmacen+capacidad;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Se ha producido un error inesperado, contacte con el administrador");
            return false;
        }
        
    }
    //Falta persistencia
    public boolean agregarProducto(){
        if (empresa.mostrarProveedoresYProductoQueDistribuye()) {
            Distribuidor distribuidor = empresa.devolverProveedor();
            if (distribuidor!=null) {
                distribuidor.mostrarProductosQueDistribuye();
                System.out.println("De el producto que va a agregar cuantos va a necesitar?");
                System.out.println("Cantidad: ");
                int cantidad = Herramientas.pedirEnteroPositivo();
                int tipoDeProducto =0;
                do {
                    System.out.println("Va a ser este producto refrigerado?");
                    System.out.println("1- Si");
                    System.out.println("2- No");
                    System.out.print("Respuesta: ");
                    tipoDeProducto = Herramientas.pedirEnteroPositivo();
                    if (tipoDeProducto<1||tipoDeProducto>2) {
                        System.out.println("Error, solo puede responder '1' o '2'.");
                    }
                } while (tipoDeProducto<1||tipoDeProducto>2);
                int espacioDisponible =0;
                if (tipoDeProducto==1) {
                    for (Nevera frigo : neveras) {
                        espacioDisponible = espacioDisponible+ frigo.getespacioDisponible();
                    }
                }else{
                    for (Estanteria estanteria : estanterias) {
                        espacioDisponible = espacioDisponible + estanteria.getespacioDisponible();
                    }
                }
                if (espacioDisponible>=cantidad) {
                    System.out.println("PONIENDO EN CONTACTO CON EL DISTRIBUIDOR");
                    ArrayList<Producto> productosComprados = distribuidor.comprarMasDeUnProducto();
                    Iterator<Producto> iteradorDeProducto = productosComprados.iterator();
                    while (iteradorDeProducto.hasNext()) {
                        if (!agregarProducto(iteradorDeProducto.next())) {
                            System.out.println("Se ha producido un error agregando un producto");
                        }
                    }
                    
                    return true;

                }else{
                    System.out.println("No hay suficiente espacio para tantos productos");
                    System.out.println("Cancelando compra...");
                    return false;
                }
                
            } else {
                System.out.println("No se ha podido contactar con el proveedor, contacte con el administrador.");
                return false;
            }
        }else{
            System.out.println("Parece que la empresa no tiene ningún distribuidor o no se ha podido acceder a ellos");
            System.out.println("No se va a poder agregar ningún producto, contacte con el administrador");
            return false;
        }
        
    }
    //Hacer el método persistente
    public boolean agregarProducto(Producto producto){
        try {
            if (producto.isRefrigerado()) {
                agregarProductoANevera(producto);
            }else{
                agregarProductoAEstanteria(producto);
            }
            agregarProductoAquíYEnEmpresa(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //Hacer método persistente
    public boolean agregarProductoANevera(Producto producto){
        Iterator <Nevera> controlNeveras = neveras.iterator();
        while (controlNeveras.hasNext()) {
            Nevera mercNevera = controlNeveras.next();
            if (mercNevera.getCapacidad() >= 1) {
                mercNevera.agregarproducto(producto);
                return true;
            }
        }
        return false;
    }
     //Hacer método persistente
    public boolean agregarProductoAEstanteria(Producto producto){
        Iterator <Estanteria> controlEstanterias = estanterias.iterator();
        while (controlEstanterias.hasNext()) {
          Estanteria mercEstanteria = controlEstanterias.next();
            if (mercEstanteria.getCapacidad() >= 1) {
                mercEstanteria.agregarProducto(producto);
                return true;
            }
        }
        return false;
    }
      //Hacer método persistente
    //Método no hecho
    public boolean agregarProductoAquíYEnEmpresa(Producto producto){
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
    //Hacer el método persistente
    public boolean eliminarProducto(Producto producto){
        try {
            if (producto.isRefrigerado()) {
                eliminarProductoANevera(producto);
            }else{
                eliminarProductoAEstanteria(producto);
            }
            eliminarProductoAquíYEnEmpresa(producto);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //Hacer método persistente
    public boolean eliminarProductoANevera(Producto producto){
        
        Iterator <Nevera> controlNeveras = neveras.iterator();
        while (controlNeveras.hasNext()) {
          Nevera mercNevera = controlNeveras.next();
            if (mercNevera.contieneProducto(producto)) {
                mercNevera.eliminarProducto(producto);
                return true;
            }
        }
        return false;
    }
     //Hacer método persistente
    public boolean eliminarProductoAEstanteria(Producto producto){
        
        Iterator <Estanteria> controlEstanterias = estanterias.iterator();
        while (controlEstanterias.hasNext()) {
          Estanteria mercEstanteria = controlEstanterias.next();
            if (mercEstanteria.contieneProducto(producto)) {
                mercEstanteria.eliminarProducto(producto);
                return true;
            }
        }
        return false;
    }
      //Hacer método persistente
      
    public boolean eliminarProductoAquíYEnEmpresa(Producto producto){
        
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
    
    @Override
    public int obtenerCantidadProductos() {
        return stock.size();
    }
}
