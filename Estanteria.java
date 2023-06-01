import java.io.Serializable;
import java.util.ArrayList;
//Crear una interface para estanteria y nevera
public class Estanteria implements Herramientas, Serializable{
    private int numeroEstanteria;
    private int capacidad;
    private ArrayList<Producto> productos;
    private int niveles;
    private static int generadordenumerodeestanteria = 0;
    
    public Estanteria() {
        generadordenumerodeestanteria++;
        this.numeroEstanteria = generadordenumerodeestanteria;
        System.out.println("Cuantos productos caben en esta estantería?");
        System.out.print("Cantidad: ");
        this.capacidad = Herramientas.pedirEnteroPositivo();
        System.out.println("Cuantos niveles tiene esta estantería?");
         System.out.print("Niveles: ");
        this.niveles = niveles;
        this.productos = new ArrayList<>();
    }
    
    public Estanteria(int capacidad, int niveles){
        generadordenumerodeestanteria++;
        this.numeroEstanteria = generadordenumerodeestanteria;
        this.capacidad = capacidad;
        this.niveles = niveles;
        this.productos = new ArrayList<>();
    }
    
    public int getespacioDisponible(){
        return capacidad - productos.size();
    }
    
    public boolean agregarProducto(Producto producto){
        if (productos.size() < capacidad) {
            productos.add(producto);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean eliminarProducto(Producto producto){
        try {
            productos.remove(producto);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("se ha producido un error al eliminar el producto de la estantería, contacte con el administrador");
            return false;
        }  
    }
    public boolean contieneProducto(Producto producto){
        if (productos.contains(producto)) {
            return true;
        } else {
            return false;
        }        
    }
    public int getNumeroEstanteria() {
        return numeroEstanteria;
    }
    
    public void setNumeroEstanteria(int numeroEstanteria) {
        this.numeroEstanteria = numeroEstanteria;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public ArrayList<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }
    
    public int getNiveles() {
        return niveles;
    }
    
    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }
}

