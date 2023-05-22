public class Establecimiento {
    private final String CIF;
    private int numeroDeTelefono;
    private Ubicacion ubicacion;
    private Horario horarioPublico;
    
    public Establecimiento(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico) {
        this.CIF = CIF;
        this.numeroDeTelefono = numeroDeTelefono;
        this.ubicacion = ubicacion;
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
