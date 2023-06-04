import java.io.Serializable;

public class Horario implements Serializable {
    private boolean[][] horarioSemana;
    private String horaEmpezar;
    private String horaTerminar;
    private String inicioDescanso;
    private String finDescanso;


    public Horario(String horaEmpezar, String horaTerminar) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        this.horaEmpezar =horaEmpezar;
        this.horaTerminar =horaTerminar;
        setHorario(horaEmpezar, horaTerminar);
    }

    public Horario(String horaEmpezar, String horaTerminar, String inicioDescanso, String finDescanso) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        this.horaEmpezar = horaEmpezar;
        this.horaTerminar = horaTerminar;
        this.inicioDescanso = inicioDescanso;
        this.finDescanso = finDescanso;
        setHorario(horaEmpezar, horaTerminar, inicioDescanso, finDescanso);
    }
    
    public Horario(){
        horarioSemana = new boolean[7][24 * 60];
        limpiarHorario();
    }

    public boolean[][] getHorarioSemana() {
        return horarioSemana;
    }



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

    public void setHorario(String horaEmpezar, String horaTerminar) {
        limpiarHorario();
        String[] partes1 = horaEmpezar.split(":");
        int hora1Dividida = Integer.parseInt(partes1[0]);
        int min1Divididos = Integer.parseInt(partes1[1]); 
        String[] partes2 = horaTerminar.split(":");
        int hora2Dividida = Integer.parseInt(partes2[0]);
        int min2Divididos = Integer.parseInt(partes2[1]); 
        
        int horaEmpezarTotal = (hora1Dividida * 60) + min1Divididos;

        int horaTerminarTotal = (hora2Dividida * 60) + min2Divididos;

        for (int i = 0; i < 7; i++) {
            for (int j = horaEmpezarTotal; j < horaTerminarTotal; j++) {
                horarioSemana[i][j] = true;
            }
        }
    }

    public void setHorario(String horaEmpezar, String horaTerminar, String inicioDescanso, String finDescanso) {
        limpiarHorario();
        String[] partes1 = horaEmpezar.split(":");
        int hora1Dividida = Integer.parseInt(partes1[0]);
        int min1Divididos = Integer.parseInt(partes1[1]); 
        String[] partes2 = horaTerminar.split(":");
        int hora2Dividida = Integer.parseInt(partes2[0]);
        int min2Divididos = Integer.parseInt(partes2[1]); 
        String[] descanso1 = inicioDescanso.split(":");
        int horadescanso1Dividida = Integer.parseInt(descanso1[0]);
        int mindescanso1Divididos = Integer.parseInt(descanso1[1]); 
        String[] descanso2 = finDescanso.split(":");
        int horadescanso2Dividida = Integer.parseInt(descanso2[0]);
        int min2descansoDivididos = Integer.parseInt(descanso2[1]); 
        
        int horaEmpezarTotal = (hora1Dividida * 60) + min1Divididos;

        int horaTerminarTotal = (hora2Dividida * 60) + min2Divididos;

        int horaIniciarDescansoTotal = (horadescanso1Dividida * 60) + mindescanso1Divididos;

        int horaTerminarDescansoTotal = (horadescanso2Dividida * 60) + min2descansoDivididos;

        for (int i = 0; i < 7; i++) {
            for (int j = horaEmpezarTotal; j < horaTerminarTotal; j++) {
                horarioSemana[i][j] = true;
            }
        }


        for (int i = 0; i < 7; i++) {
            for (int j = horaIniciarDescansoTotal; j < horaTerminarDescansoTotal; j++) {
                horarioSemana[i][j] = false;
            }
        }
    }


}
