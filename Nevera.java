import java.util.ArrayList;

public class Nevera {
    private int capacidad;
    private int temperatura;
    private ArrayList<Producto> productos;
    
    public Nevera(int capacidad, int temperatura) {
        this.capacidad = capacidad;
        this.temperatura = temperatura;
        this.productos = new ArrayList<>();
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    public int getTemperatura() {
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
}
