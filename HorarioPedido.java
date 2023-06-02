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
        this.diaInicioSemana = diaInicioSemana;
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        for (Empleado empleado : trabajadores) {
            if (empleado.getTipoDeEmpleado() == TipoDeEmpleado.CONDUCTOR) {
                if (!vehiculos.contains(empleado.getVehiculo())) {
                    vehiculos.add( empleado.getVehiculo());
                }
            }
        }

        //per a cada vehicul, la disponibilitat del seu/s conductor

        horarioSemana = new boolean[7][24 * 60][vehiculos.size()]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        int primerTamanio = horarioSemana.length;
        int segundoTamanio = horarioSemana[0].length;
        int tercerTamanio = horarioSemana[0][0].length;
        //Establece el horario en false;
        for (int i = 0; i < primerTamanio; i++) {
            for (int j = 0; j < segundoTamanio; j++) {
                for (int k = 0; k < tercerTamanio; k++) {
                    
                    horarioSemana[i][j][k]=false;
                   
                }
            }
        }
        for (int i = 0; i < primerTamanio; i++) {
            for (int j = 0; j < segundoTamanio; j++) {
                for (int k = 0; k < tercerTamanio; k++) {
                    Horario horarioARecorrer = crearHorarioSegunVehiculo(vehiculos.get(k));
                    boolean[][] horarioVehiculoBoleano = horarioARecorrer.getHorarioSemana();
                    if (horarioVehiculoBoleano[i][j]==true) {
                        horarioSemana[i][j][k]=true;
                    }
                }
            }
        }
        this.horaEmpezar = horaEmpezar;
        this.horaTerminar = horaTerminar;
    }

    private Horario crearHorarioSegunVehiculo(Vehiculo vehiculo){
        Empleado empleado1 = vehiculo.getConductorManyana()==null? null: vehiculo.getConductorManyana();
        Empleado empleado2 = vehiculo.getConductorTarde()==null? null: vehiculo.getConductorTarde();
        Horario horario = new Horario();
        boolean[][] horarioBoleano = horario.getHorarioSemana();
        boolean[][] horarioBoleanoEmp1 = empleado1 == null? null: empleado1.getHorario().getHorarioSemana();
        boolean[][] horarioBoleanoEmp2 = empleado2 == null? null: empleado2.getHorario().getHorarioSemana();
        horarioBoleano = modificarHorario(horarioBoleano, horarioBoleanoEmp1);
        horarioBoleano = modificarHorario(horarioBoleano, horarioBoleanoEmp2);
        horario.setHorarioSemana(horarioBoleano);
        return horario;
    }

    private boolean[][] modificarHorario(boolean[][] horarioAModificar, boolean[][] horarioModificador){
        if (horarioModificador != null) {
            for (int i = 0; i < horarioAModificar.length; i++) {
                for (int j = 0; j < horarioAModificar[0].length; j++) {
                    if (horarioModificador[i][j]==true) {
                        horarioAModificar[i][j]=true;
                    }
                }
            }
        } 
        return horarioAModificar;
    }

    public boolean[][][] getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(boolean[][][] horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public boolean isDisponible(int diaSemana, int hora, int minuto) {
        for (int i = 0; i < horarioSemana.length; i++) {
            for (int j = 0; j < horarioSemana[0].length; j++) {
                for (int j2 = 0; j2 < horarioSemana[0][0].length; j2++) {
                    if (horarioSemana[i][j][j2]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void setDisponible(int diaSemana, int hora, int minuto, boolean disponible) {
        //int dia semana??????
        int vehiculo = 0;
        do {
            System.out.println("Existen "+horarioSemana[0][0].length+" vehiculos");

            for (int i = 0; i < horarioSemana.length; i++) {
                for (int j = 0; j < horarioSemana[0].length; j++) {
                    for (int j2 = 0; j2 < horarioSemana[0][0].length; j2++) {
                        if (horarioSemana[i][j][j2]== true) {
                            System.out.println("El "+j2+"º vehiculo está disponible a las "+hora+":"+minuto);
                        }else{
                            System.out.println("El "+j2+"º vehiculo está disponible a las "+hora+":"+minuto);
                        }
                    }
                }
            }

            if (disponible) {
                System.out.println("Que vehiculo quieres establecer como disponible?");
                
            } else {
                System.out.println("Que vehiculo quieres establecer como no disponible?");
            }
            System.out.print("Vehiculo: ");
            vehiculo = Herramientas.pedirEnteroPositivo();
            vehiculo --;
            if (vehiculo<0||vehiculo<horarioSemana[0][0].length-1) {
                System.out.println();
            }
        } while (vehiculo<0||vehiculo<horarioSemana[0][0].length-1);
        horarioSemana[diaSemana][hora * 60 + minuto][vehiculo] = disponible;
    }

    public LocalDate getDiaInicioSemana() {
        return diaInicioSemana;
    }

    public void setDiaInicioSemana(LocalDate diaInicioSemana) {
        this.diaInicioSemana = diaInicioSemana;
    }
}


