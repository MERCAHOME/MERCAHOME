import java.util.ArrayList;

public class Estanteria implements Herramientas{
        //Cuando se agrega o elimina un producto se modifica su cantidad de espacioDisponible

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

