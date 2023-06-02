import java.io.Serializable;
import java.util.ArrayList;

public class EstablecimientoPropio extends Establecimiento {
    private Empleado gerente;
    private ArrayList<Empleado> encargados;
    
    public EstablecimientoPropio() {
        this.gerente = new Empleado(null);
        this.encargados = null;
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
    
    public void setEncargados(ArrayList<Empleado> encargados) {
        this.encargados = encargados;
    }
    
 
}
