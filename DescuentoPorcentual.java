public class DescuentoPorcentual extends Descuento {
    private double cantidadMaximaDescuento;
    private int porcentajeDescuento;
    

    
    public DescuentoPorcentual(boolean activo, int porcentaje, double cantidadMax) {
        super(activo);
        this.cantidadMaximaDescuento = cantidadMax;
        this.porcentajeDescuento = porcentaje;
    }

    public double getCantidadMaximaDescuento() {
        return cantidadMaximaDescuento;
    }
    
    public void setCantidadMaximaDescuento(double cantidadMaximaDescuento) {
        this.cantidadMaximaDescuento = cantidadMaximaDescuento;
    }
    
    public int getPorcentajeDescuento() {
        return porcentajeDescuento;
    }
    
    public void setPorcentajeDescuento(int porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
