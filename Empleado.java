import java.time.LocalDate;

public class Empleado extends Persona {
    private LocalDate fechaDeAlta;
    private boolean esGerente;
    private boolean esEncargado;
    private Horario horariotrabajador;
    
    public Empleado(String DNI, int numeroDeTelefono, LocalDate fechaDeAlta, boolean esGerente, boolean esEncargado, Horario horario) {
        super(DNI, numeroDeTelefono);
        this.fechaDeAlta = fechaDeAlta;
        this.esGerente = esGerente;
        this.esEncargado = esEncargado;
        this.horariotrabajador = horario;
    }
    
    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }
    
    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }
    
    public boolean isEsGerente() {
        return esGerente;
    }
    
    public void setEsGerente(boolean esGerente) {
        this.esGerente = esGerente;
    }
    
    public boolean isEsEncargado() {
        return esEncargado;
    }
    
    public void setEsEncargado(boolean esEncargado) {
        this.esEncargado = esEncargado;
    }
    
    public Horario getHorario() {
        return horariotrabajador;
    }
    
    public void setHorario(Horario horario) {
        this.horariotrabajador = horario;
    }
}

