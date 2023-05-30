public class Horario {
    private boolean[][] horarioSemana;

    // SUPERMERCASDO, EMPLEADO....  GENERAL
//Metodos
// 2 COSNTRUCOTS 
    // 1 sin descansos (2 parametros comencar i acabar)
    // 1 con descansos ( 4 parametros comencar acabar, inicio descanso, fin descanso)

    //assesguear que decimal DOUBLE no passa de 60
    //que no pase de 24 


    public Horario() {
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
}

