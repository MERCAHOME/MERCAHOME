public class Horario {
    private boolean[][] horarioSemana;

    public Horario() {
        horarioSemana = new boolean[7][24 * 4]; // 7 días de la semana y 24 horas * 4 intervalos de 15 minutos
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

