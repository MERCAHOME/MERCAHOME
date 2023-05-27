import java.util.ArrayList;

public class Nevera {
    //Cuando se agrega o elimina un producto se modifica su cantidad de espacio disponible
    private int capacidad;
    private double temperatura;
    private ArrayList<Producto> productos;

    private static int generadordenumerodeneveras = 0;

    public Nevera() {
        generadordenumerodeneveras++;
        System.out.println("Cuantos productos caben en esta nevera?");
        System.out.print("Cantidad: ");
        this.capacidad = generadordenumerodeneveras;
        this.temperatura = 2;
        this.productos = new ArrayList<>();
    }


    public boolean agregarproducto(Producto producto){
     
        if(productos.size < capacidad){
            productos.add(producto);
            sout
            return true;

        }else{

            return false;
        }


   
    }
    
    //necesito lo siguiente:
    public Nevera(int capacidad){
        //realizar gestiones de añadir capacidad
    }
    
    //No está implementado
    public int getespacioDisponible(){
        return 1;
    }
    //fin de lo que necesito
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
