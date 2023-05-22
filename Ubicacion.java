public class Ubicacion {
    private String pais;
    private String provincia;
    private String localidad;
    private String calle;
    private String numero;
    private String masInformacion;
    
    public Ubicacion(String pais, String provincia, String localidad, String calle, String numero, String masInformacion) {
        this.pais = pais;
        this.provincia = provincia;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
        this.masInformacion = masInformacion;
    }
    
    public String getPais() {
        return pais;
    }
    
    public void setPais(String pais) {
        this.pais = pais;
    }
    
    public String getProvincia() {
        return provincia;
    }
    
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    public String getLocalidad() {
        return localidad;
    }
    
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
    
    public String getCalle() {
        return calle;
    }
    
    public void setCalle(String calle) {
        this.calle = calle;
    }
    
    public String getNumero() {
        return numero;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getMasInformacion() {
        return masInformacion;
    }
    
    public void setMasInformacion(String masInformacion) {
        this.masInformacion = masInformacion;
    }
}
