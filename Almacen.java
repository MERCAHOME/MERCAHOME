import java.util.ArrayList;

public class Almacen extends EstablecimientoPropio implements Stock, Herramientas {
    //ctrl+z de nevera, estanteria,establecimiento, y establecimientopropio y empresa

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
    public void agregarProducto(Producto producto) {
        stock.add(producto);
    }
    
    @Override
    public void eliminarProducto(Producto producto) {
        stock.remove(producto);
    }
    
    @Override
    public int obtenerCantidadProductos() {
        return stock.size();
    }
}
