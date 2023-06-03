import java.io.Serializable;

public class Horario implements Serializable {
    private boolean[][] horarioSemana;
    private double horaEmpezar;
    private double horaTerminar;
    private double inicioDescanso;
    private double finDescanso;


    public Horario(double horaEmpezar, double horaTerminar) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        this.horaEmpezar = comprobacionHorasYMinutos(horaEmpezar);
        this.horaTerminar = comprobacionHorasYMinutos(horaTerminar);
        setHorario(horaEmpezar, horaTerminar);
    }

    public Horario(double horaEmpezar, double horaTerminar, double inicioDescanso, double finDescanso) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        this.horaEmpezar = comprobacionHorasYMinutos(horaEmpezar);
        this.horaTerminar = comprobacionHorasYMinutos(horaTerminar);
        this.inicioDescanso = comprobacionHorasYMinutos(inicioDescanso);
        this.finDescanso = comprobacionHorasYMinutos(finDescanso);
        setHorario(horaEmpezar, horaTerminar, inicioDescanso, finDescanso);
    }

    public Horario(){
        horarioSemana = new boolean[7][24 * 60];
        limpiarHorario();
    }

    public boolean[][] getHorarioSemana() {
        return horarioSemana;
    }


    /*public boolean isDisponible(int diaSemana, int hora, int minuto) {
        comprobacionInts(diaSemana, hora, minuto);
        if (!horarioSemana[diaSemana][hora * 60 + minuto]) {
            return true;
        }
        return false;
    }*/

    private void limpiarHorario() {
        for (int i = 0; i < horarioSemana.length; i++) {
            for (int j = 0; j < horarioSemana[0].length; j++) {
                horarioSemana[i][j]=false;
            }
        }
    }
    public void setHorarioSemana(boolean[][] horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public void setHorario(double horaEmpezar, double horaTerminar) {
        limpiarHorario();
        int horaEmpezarEntero = (int) horaEmpezar;
        int minutosHoraEmpezar = (int) ((horaEmpezar - horaEmpezarEntero) * 100);
        int horaEmpezarTotal = (horaEmpezarEntero * 60) + minutosHoraEmpezar;

        int horaTerminarEntero = (int) horaTerminar;
        int minutosHoraTerminar = (int) ((horaTerminar - horaTerminarEntero) * 100);
        int horaTerminarTotal = (horaTerminarEntero * 60) + minutosHoraTerminar;

        for (int i = 0; i < 7; i++) {
            for (int j = horaEmpezarTotal; j < horaTerminarTotal; j++) {
                horarioSemana[i][j] = true;
            }
        }
    }

    public void setHorario(double horaEmpezar, double horaTerminar, double inicioDescanso, double finDescanso) {
        limpiarHorario();
        int horaEmpezarEntero = (int) horaEmpezar;
        int minutosHoraEmpezar = (int) ((horaEmpezar - horaEmpezarEntero) * 100);
        int horaEmpezarTotal = (horaEmpezarEntero * 60) + minutosHoraEmpezar;

        int horaTerminarEntero = (int) horaTerminar;
        int minutosHoraTerminar = (int) ((horaTerminar - horaTerminarEntero) * 100);
        int horaTerminarTotal = (horaTerminarEntero * 60) + minutosHoraTerminar;

        for (int i = 0; i < 7; i++) {
            for (int j = horaEmpezarTotal; j < horaTerminarTotal; j++) {
                horarioSemana[i][j] = true;
            }
        }

        int horaEmpezarDescansoEntero = (int) inicioDescanso;
        int minutosHoraEmpezarDescanso = (int) ((inicioDescanso - horaEmpezarDescansoEntero) * 100);
        int horaEmpezarDescansoTotal = (horaEmpezarDescansoEntero * 60) + minutosHoraEmpezarDescanso;

        int horaTerminarDescansoEntero = (int) finDescanso;
        int minutosHoraTerminarDescanso = (int) ((finDescanso - horaTerminarDescansoEntero) * 100);
        int horaTerminarDescansoTotal = (horaTerminarDescansoEntero * 60) + minutosHoraTerminarDescanso;

        for (int i = 0; i < 7; i++) {
            for (int j = horaEmpezarDescansoTotal; j < horaTerminarDescansoTotal; j++) {
                horarioSemana[i][j] = false;
            }
        }
    }

    public double comprobacionHorasYMinutos(double hora) {
        int horaEntero = (int) Math.floor(hora);
        int minutosEntero = (int)((hora-horaEntero)*100);
        if (horaEntero > 23 || horaEntero < 0) {
            System.out.println("La hora " + (int)hora + " introducida no es correcta");
            System.out.print("Introduzca una hora válida: ");
            System.out.print("Nueva hora: ");
            double nuevaHora = Herramientas.pedirDoublePositivo();
            return comprobacionHorasYMinutos(nuevaHora);
        }

        if (minutosEntero > 59 || minutosEntero < 0) {
            System.out.println("Los minutos " + minutosEntero + " introducidos no son correctos");
            System.out.print("Introduzca una hora válida: ");
            System.out.print("Nueva hora: ");
            double nuevaHora = Herramientas.pedirDoublePositivo();
            return comprobacionHorasYMinutos(nuevaHora);
        }

        return hora;
    }
}
