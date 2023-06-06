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

    //desde super o almacen hay que asignar un nuevo encargado
    public boolean eliminarEncargado(Empleado empleado){

        if (encargados.size()>1) {
            if (encargados.contains(empleado)) {
                encargados.remove(empleado);
                return true;
            } else {
                System.out.println("Este empleado no era un encargado");
                return false;
            }
            
        }else{
            System.out.println("No se puede eliminar el encargado, ya que solo existe un encargado");
            return false;
        }

    }
    
    public void setEncargados(ArrayList<Empleado> encargados) {
        this.encargados = encargados;
    }
    
    public void mostrarEncargados(){
        if (encargados.size()!=0) {
            System.out.println("*****************************");
            System.out.println("          ENCARGADOS");
            System.out.println("*****************************");
            for (Empleado empleado : encargados) {
                System.out.println("Nombre: "+empleado.getNombre()+" "+empleado.getApellidos());
                System.out.println("DNI: "+empleado.getDNI());
                System.out.println("*****************************");
            }

        }else{
            System.out.println("Este establecimiento no tiene ningún encargado dado de alta");
        }
    }

    public void mostrarGerente(){
        if (this.gerente!=null) {
            
            System.out.println("*****************************");
            System.out.println("          GERENTE");
            System.out.println("*****************************");
            System.out.println("Nombre: "+this.gerente.getNombre()+" "+this.gerente.getApellidos());
            System.out.println("DNI: "+this.gerente.getDNI());
            System.out.println("*****************************");

        } else {
            
        }
    }
 
}
