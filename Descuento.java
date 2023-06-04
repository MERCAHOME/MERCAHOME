import java.io.Serializable;

public class Descuento implements Serializable{
    private static int IDgenerator = 0;
    private int id;
    private boolean activo;
    
    public Descuento(boolean activo) {
        this.id = generateID();
        this.activo = activo;
    }
    
    public int getId() {
        return id;
    }
    
    public boolean isActivo() {
        return activo;
    }
    
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    private static int generateID() {
        IDgenerator++;
        return IDgenerator;
    }
}
