public class Establecimiento implements Herramientas{
    private final String CIF;
    private int numeroDeTelefono;
    private Ubicacion ubicacion;
    private Horario horarioPublico;
    
    public Establecimiento() {
       
        System.out.println("Indica el CIF del Establecimiento");
        System.out.print("CIF: ");
        this.CIF = Herramientas.pedirString();
        this.numeroDeTelefono = Herramientas.crearNumeroDeTelefono();
        this.ubicacion = Herramientas.crearUbicacion();
        //Cuando Jack haga el horario publico
        this.horarioPublico = horarioPublico;
    
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
