public class Vehiculo {
    private final String matricula;
    private boolean repartiendo;
    private boolean tieneConductorManyana = true;
    private boolean tieneConductorTarde = true;
    private Empleado conductorManyana;    
    private Empleado conductorTarde;

    //metodos
    //disponibilitat. Si un dels booleanos (tiene) esta en true, torna true. BOOLEAN
    //    

    public boolean isTieneConductorManyana() {
        return tieneConductorManyana;
    }

    public void setTieneConductorManyana(boolean tieneConductorManyana) {
        this.tieneConductorManyana = tieneConductorManyana;
    }
    public boolean isTieneConductorTarde() {
        return tieneConductorTarde;
    }

    public void setTieneConductorTarde(boolean tieneConductorTarde) {
        this.tieneConductorTarde = tieneConductorTarde;
    }

    public Empleado getConductorManyana() {
        return conductorManyana;
    }

    public void setConductorManyana(Empleado conductorManyana) {
        this.conductorManyana = conductorManyana;
    }
    
    public Empleado getConductorTarde() {
        return conductorTarde;
    }

    public void setConductorTarde(Empleado conductorTarde) {
        this.conductorTarde = conductorTarde;
    }

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.repartiendo = false;
    }

    public String getMatricula() {
        return matricula;
    }
    
    public boolean isRepartiendo() {
        return repartiendo;
    }
    
    public void setRepartiendo(boolean repartiendo) {
        this.repartiendo = repartiendo;
    }
    public int asignarHorario(){
       return 1;//o 0 mañana, 1 para tardes
    }
}
