import java.io.Serializable;

public class Vehiculo implements Serializable {
    private final String matricula;
    private boolean repartiendo;
    private boolean tieneConductorManyana = false;
    private boolean tieneConductorTarde = false;
    private Empleado conductorManyana;
    private Empleado conductorTarde;

    public Vehiculo(String matricula) {
        this.matricula = matricula;
        this.repartiendo = false;
    }

    public boolean estaDisponible() {
        if (!tieneConductorManyana || !tieneConductorTarde) {
            return true;
        }
        return false;
    }

    public boolean estaTodoDisponible() {
        if (!tieneConductorManyana && !tieneConductorTarde) {
            return true;
        }
        return false;
    }

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

    public String getMatricula() {
        return matricula;
    }

    public boolean isRepartiendo() {
        return repartiendo;
    }

    public void setRepartiendo(boolean repartiendo) {
        this.repartiendo = repartiendo;
    }

    public int asignarHorario(Empleado empleado) {
        if (estaDisponible()) {
            if (estaTodoDisponible()) {
                int opcion = 0;
                do {                
                    System.out.println("Quieres asignarle un horario de mañanas (1) o de tardes (2)?");
                    System.out.print("Respuesta: ");
                    opcion = Herramientas.pedirEnteroPositivo();
                    if (opcion == 1) {
                        tieneConductorManyana = true;
                        conductorManyana = empleado;
                        return 0;
                    } else if(opcion == 2){
                        tieneConductorTarde = true;
                        conductorTarde = empleado;
                        return 1;
                    }
                } while (opcion != 1 && opcion != 2);                
            } else {
                if (!tieneConductorManyana) {
                    tieneConductorManyana = true;
                    conductorManyana = empleado;
                    return 0;
                } else {
                    tieneConductorTarde = true;
                    conductorTarde = empleado;
                    return 1;
                }
            }
        }
        System.out.println("No se ha podido asignar un horario al conductor...");
        return -1;

        //return 1; o 0 mañana, 1 para tardes, -1 ERROR
    }
}
