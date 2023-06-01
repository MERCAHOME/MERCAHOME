import java.time.LocalDate;
import java.util.ArrayList;

public class HorarioPedido {
    private boolean[][][] horarioSemana;
    private LocalDate diaInicioSemana;
    private double horaEmpezar;
    private double horaTerminar;

    // 
//Metodos
// 2 CONSTRUCTORS 
    // 1 sin descansos (3 parametros comencar i acabar, localdate dia semana)
    // 1 con descansos ( 4 parametros comencar acabar, inicio descanso, fin descanso)

    //assesguear que decimal DOUBLE no passa de 60
    //que no pase de 24

    public HorarioPedido(LocalDate diaInicioSemana, ArrayList<Empleado> trabajadores) {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for (Empleado empleado : trabajadores) {
            if (empleado.getTipoDeEmpleado() == TipoDeEmpleado.CONDUCTOR) {
                if (!vehiculos.contains(empleado.getVehiculo())) {
                    vehiculos.add( empleado.getVehiculo());
                }
            }
        }

        //per a cada vehicul, la disponibilitat del seu/s conductor

        this.diaInicioSemana = diaInicioSemana;
        horarioSemana = new boolean[7][24 * 60][vehiculos.size()]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        for (boolean[] bs : horarioSemana) {
            for (boolean b : bs) {
                b = false;
            }
        }
        this.horaEmpezar = horaEmpezar;
        this.horaTerminar = horaTerminar;
    }

    public boolean[][] getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(boolean[][] horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public boolean isDisponible(int diaSemana, int hora, int minuto) {
        return horarioSemana[diaSemana][hora * 60 + minuto];
    }

    public void setDisponible(int diaSemana, int hora, int minuto, boolean disponible) {
        horarioSemana[diaSemana][hora * 60 + minuto] = disponible;
    }

    public LocalDate getDiaInicioSemana() {
        return diaInicioSemana;
    }

    public void setDiaInicioSemana(LocalDate diaInicioSemana) {
        this.diaInicioSemana = diaInicioSemana;
    }
}


