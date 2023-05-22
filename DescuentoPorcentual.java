public class DescuentoPorcentual extends Descuento {
    private double cantidadMaximaDescuento;
    private int porcentajeDescuento;
    
    public DescuentoPorcentual(boolean activo, double cantidadMaximaDescuento, int porcentajeDescuento) {
        super(activo);
        this.cantidadMaximaDescuento = cantidadMaximaDescuento;
        this.porcentajeDescuento = porcentajeDescuento;
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
