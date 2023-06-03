import java.io.Serializable;

public class Establecimiento implements Herramientas, Serializable{
    private final String CIF;
    private int numeroDeTelefono;
    private Ubicacion ubicacion;
    private Horario horarioPublico;
    private static int cifGnerator = 12345670;
    
    public Establecimiento() {
        cifGnerator++;
        this.CIF ="A"+cifGnerator;
        this.numeroDeTelefono = Herramientas.crearNumeroDeTelefono();
        this.ubicacion = Herramientas.crearUbicacion();
        Herramientas.limpiarPantalla();
        System.out.println("A que hora abre este establecimiento?");
        System.out.print("Hora: ");
        double horaApertura = Herramientas.pedirDoublePositivo();
        double horaCierre = 0;
        do {
            System.out.println("A que hora cierra este establecimiento?");
            System.out.print("Hora: ");
            horaCierre = Herramientas.pedirDoublePositivo();
            if (horaCierre<horaApertura) {
                System.out.println("Este establecimiento no puede cerrar antes de abrir, indique una nueva hora de cierre");
            }
        } while (horaCierre<horaApertura);
        this.horarioPublico = new Horario(horaApertura,horaCierre);
    }
    
    public String getCIF() {
       
        return CIF;
    
    }
    
    public int getNumeroDeTelefono() {
    
        return numeroDeTelefono;
    
    }
    
    public void setNumeroDeTelefono(int numeroDeTelefono) {
    
        this.numeroDeTelefono = numeroDeTelefono;
    
    }
    
    public Ubicacion getUbicacion() {
    
        return ubicacion;
    
    }
    
    public void setUbicacion(Ubicacion ubicacion) {
    
        this.ubicacion = ubicacion;
    
    }
    
    public Horario getHorarioPublico() {
    
        return horarioPublico;
    
    }
    
    public void setHorarioPublico(Horario horarioPublico) {
    
        this.horarioPublico = horarioPublico;
    
    }

}
