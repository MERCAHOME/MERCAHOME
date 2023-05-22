import java.util.ArrayList;

public class EstablecimientoPropio extends Establecimiento {
    private Empleado gerente;
    private ArrayList<Empleado> encargados;
    private ArrayList<Empleado> trabajadores;
    
    public EstablecimientoPropio(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico,
                                 Empleado gerente, ArrayList<Empleado> encargados, ArrayList<Empleado> trabajadores) {
        super(CIF, numeroDeTelefono, ubicacion, horarioPublico);
        this.gerente = gerente;
        this.encargados = encargados;
        this.trabajadores = trabajadores;
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
    
    public ArrayList<Empleado> getTrabajadores() {
        return trabajadores;
    }
    
    public void setTrabajadores(ArrayList<Empleado> trabajadores) {
        this.trabajadores = trabajadores;
    }
}
