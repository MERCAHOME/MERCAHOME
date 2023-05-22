public class DescuentoCantidad extends Descuento {
    private double importeMinimo;
    private double cantidadDescuento;
    
    public DescuentoCantidad(boolean activo, double importeMinimo, double cantidadDescuento) {
        super(activo);
        this.importeMinimo = importeMinimo;
        this.cantidadDescuento = cantidadDescuento;
    }
    
    public double getImporteMinimo() {
        return importeMinimo;
    }
    
    public void setImporteMinimo(double importeMinimo) {
        this.importeMinimo = importeMinimo;
    }
    
    public double getCantidadDescuento() {
        return cantidadDescuento;
    }
    
    public void setCantidadDescuento(double cantidadDescuento) {
        this.cantidadDescuento = cantidadDescuento;
    }
}
