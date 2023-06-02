import java.io.Serializable;
import java.util.ArrayList;

public class EstablecimientoPropio extends Establecimiento {
    private Empleado gerente;
    private ArrayList<Empleado> encargados = new ArrayList<>();
    
    public EstablecimientoPropio() {
        
    }
    
    public Empleado getGerente() {
        return gerente;
    }
    
    public void setGerente(Empleado gerente) {
        this.gerente = gerente;
    }
    
    public ArrayList<Empleado> getEncargados() {
        return encargados;
    }

    public void agregarEncargado(Empleado empleado){
        encargados.add( empleado);
    }

    public boolean eliminarEncargado(Empleado empleado){
        if (encargados.contains(empleado)) {
            encargados.remove(empleado);
            return true;
        } else {
            System.out.println("Este empleado no era un encargado");
            return false;
        }
    }
    
    public void setEncargados(ArrayList<Empleado> encargados) {
        this.encargados = encargados;
    }
    
 
}
