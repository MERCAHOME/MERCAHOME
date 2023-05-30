import java.time.LocalDate;

public class HorarioPedido {
    private boolean[][] horarioSemana;
    private LocalDate diaInicioSemana;

    // 
//Metodos
// 2 CONSTRUCTORS 
    // 1 sin descansos (3 parametros comencar i acabar, localdate dia semana)
    // 1 con descansos ( 4 parametros comencar acabar, inicio descanso, fin descanso)

    //assesguear que decimal DOUBLE no passa de 60
    //que no pase de 24

    public HorarioPedido(LocalDate diaInicioSemana) {
        this.diaInicioSemana = diaInicioSemana;
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minutos
    }

    public boolean[][] getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(boolean[][] horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public boolean isDisponible(int diaSemana, int hora, int minuto) {
        return horarioSemana[diaSemana][hora * 4 + minuto / 15];
    }

    public void setDisponible(int diaSemana, int hora, int minuto, boolean disponible) {
        horarioSemana[diaSemana][hora * 4 + minuto / 15] = disponible;
    }

    public LocalDate getDiaInicioSemana() {
        return diaInicioSemana;
    }

    public void setDiaInicioSemana(LocalDate diaInicioSemana) {
        this.diaInicioSemana = diaInicioSemana;
    }
}
