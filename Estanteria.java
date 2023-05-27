import java.util.ArrayList;

public class Estanteria {
        //Cuando se agrega o elimina un producto se modifica su cantidad de espacioDisponible

    private int numeroEstanteria;
    private int capacidad;
    private ArrayList<Producto> productos;
    private int niveles;
    
    public Estanteria() {
        this.numeroEstanteria = 1;
        this.capacidad = 1;
        this.niveles = 1;
        this.productos = new ArrayList<>();
    }
    //necesito esto:
    public Estanteria(int capacidad, int niveles){
        //gestion de añadir niveles y capacidad
    }
    //no implementado
    public int getespacioDisponible(){
        return 1;
    }

    //fin de lo que necesito
    
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
