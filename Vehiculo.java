public class Vehiculo {
    private final String matricula;
    private boolean repartiendo;
    
    
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
