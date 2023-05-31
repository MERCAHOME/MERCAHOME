public class Horario {
    private boolean[][] horarioSemana;
    double horaEmpezar;
    double horaTerminar;
    double inicioDescanso = -1;
    double finDescanso = -1;

    // SUPERMERCASDO, EMPLEADO.... GENERAL
    // Metodos
    // 2 COSNTRUCOTS
    // 1 sin descansos (2 parametros comencar i acabar)
    // 1 con descansos ( 4 parametros comencar acabar, inicio descanso, fin
    // descanso)

    // assesguear que decimal DOUBLE no passa de 60
    // que no pase de 24

    public Horario(double horaEmpezar, double horaTerminar) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        for (boolean[] bs : horarioSemana) {
            for (boolean b : bs) {
                b = false;
            }
        }
        this.horaEmpezar = horaEmpezar;
        this.horaTerminar = horaTerminar;
    }

    public Horario(double horaEmpezar, double horaTerminar, double inicioDescanso, double finDescanso) {
        horarioSemana = new boolean[7][24 * 60]; // 7 días de la semana y 24 horas * 60 intervalos de 1 minuto
        for (boolean[] bs : horarioSemana) {
            for (boolean b : bs) {
                b = false;
            }
        }
        this.horaEmpezar = horaEmpezar;
        this.horaTerminar = horaTerminar;
        this.inicioDescanso = inicioDescanso;
        this.finDescanso = finDescanso;
    }

    public boolean[][] getHorarioSemana() {
        return horarioSemana;
    }

    public void setHorarioSemana(boolean[][] horarioSemana) {
        this.horarioSemana = horarioSemana;
    }

    public boolean isDisponible(int diaSemana, int hora, int minuto) {
        comprobacionInts(diaSemana, hora, minuto);
        if (!horarioSemana[diaSemana][hora * 60 + minuto]) {
            return true;
        }
        return false;
    }

    public void setHorario(int diaSemana, int horaEmpezar, int minutoEmpezar, int horaTerminar, int minutoTerminar) {
        System.out.println("Comprobando hora empezar...");
        comprobacionInts(diaSemana, horaEmpezar, minutoEmpezar);
        System.out.println("Hora empezar valida.");
        System.out.println("Comprobando hora terminar...");
        comprobacionInts(diaSemana, horaTerminar, minutoTerminar);
        System.out.println("Hora termianar valida.\n");
        int posicionEmpezar = horaEmpezar * 60 + minutoEmpezar;
        int posicionTerminar = horaTerminar * 60 + minutoTerminar;
        int comprobador = 0;
        for (int i = posicionEmpezar; i < posicionTerminar; i++) {
            if (horarioSemana[diaSemana][i]) {
                comprobador++;
            }
        }

        if (comprobador != 0) {
            System.out.println("No se ha podido asignar ese horario... Ya esta ocupado.");
        } else {
            for (int i = posicionEmpezar; i < posicionTerminar; i++) {
                horarioSemana[diaSemana][i] = true;
            }
        }
    }

    public void setHorario(int diaSemana, int horaEmpezar, int minutoEmpezar, int horaTerminar, int minutoTerminar,
            int horaInicioDescanso, int minutoInicioDescanso, int horaFinDecanso, int minutoFinDescanso) {
        System.out.println("Comprobando hora empezar...");
        comprobacionInts(diaSemana, horaEmpezar, minutoEmpezar);
        System.out.println("Hora empezar valida.");
        System.out.println("Comprobando hora terminar...");
        comprobacionInts(diaSemana, horaTerminar, minutoTerminar);
        System.out.println("Hora terminar valida.\n");
        int posicionEmpezar = horaEmpezar * 60 + minutoEmpezar;
        int posicionTerminar = horaTerminar * 60 + minutoTerminar;
        int comprobador = 0;
        for (int i = posicionEmpezar; i < posicionTerminar; i++) {
            if (horarioSemana[diaSemana][i]) {
                comprobador++;
            }
        }

        if (comprobador != 0) {
            System.out.println("No se ha podido asignar ese horario... Ya esta ocupado.");
        } else {
            for (int i = posicionEmpezar; i < posicionTerminar; i++) {
                horarioSemana[diaSemana][i] = true;
            }
        }

        System.out.println("Comprobando hora empezar descanso...");
        comprobacionInts(diaSemana, horaInicioDescanso, minutoInicioDescanso);
        System.out.println("Hora inicio descanso valida.");
        System.out.println("Comprobando hora terminar...");
        comprobacionInts(diaSemana, horaFinDecanso, minutoFinDescanso);
        System.out.println("Hora terminar descanso valida.\n");

        int posicionInicioDescanso = horaInicioDescanso * 60 + minutoInicioDescanso;
        int posicionFinDescanso = horaFinDecanso * 60 + minutoFinDescanso;
        int comprobadorD = 0;

        for (int i = posicionInicioDescanso; i < posicionFinDescanso; i++) {
            if (!horarioSemana[diaSemana][i]) {
                comprobadorD++;
            }
        }

        if (comprobadorD != 0) {
            System.out.println("No se ha podido asignar un descanso esas horas... No estan ocupadas...");
        } else {
            for (int i = posicionInicioDescanso; i < posicionFinDescanso; i++) {
                horarioSemana[diaSemana][i] = false;
            }
        }

    }

    public void comprobacionInts(int diaSemana, int hora, int minuto) {
        while (diaSemana < 0 || diaSemana > 7) {
            System.out.println("Dia de la semana entre 1 y 7: ");
            diaSemana = Herramientas.pedirEnteroPositivo();
        }
        while (hora < 0 || hora > 23) {
            System.out.println("Hora entre 0 y 23: ");
            hora = Herramientas.pedirEnteroPositivo();
        }
        while (minuto < 0 || minuto > 59) {
            System.out.println("Minuto entre 0 y 59: ");
            minuto = Herramientas.pedirEnteroPositivo();
        }
    }
}
