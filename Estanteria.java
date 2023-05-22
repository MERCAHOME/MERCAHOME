import java.util.ArrayList;

public class Estanteria {
    private int numeroEstanteria;
    private int capacidad;
    private ArrayList<Producto> productos;
    private int niveles;
    
    public Estanteria(int numeroEstanteria, int capacidad, int niveles) {
        this.numeroEstanteria = numeroEstanteria;
        this.capacidad = capacidad;
        this.niveles = niveles;
        this.productos = new ArrayList<>();
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
