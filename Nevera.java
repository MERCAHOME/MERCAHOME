import java.io.Serializable;
import java.util.ArrayList;

public class Nevera implements Serializable {
    // Cuando se agrega o elimina un producto se modifica su cantidad de espacio disponible
    private int capacidad;
    private double temperatura;
    private ArrayList<Producto> productos = new ArrayList<>();
    private int id;

    private static int generadordenumerodeneveras = 0;
    public Nevera(int capacidad) {
        generadordenumerodeneveras++;
        this.id=generadordenumerodeneveras;
        this.capacidad=capacidad;
        this.temperatura = 2;
    }
    public int getespacioDisponible(){
        
        return capacidad - productos.size();
    }

    public boolean agregarproducto(Producto producto) {

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
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }
}
