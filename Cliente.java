import java.util.ArrayList;

public class Cliente extends Persona {
    private TipoDeCliente tipoDeCliente;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Factura> facturas;
    private int pedidoEnCurso;
    private int distanciaEnKmHastaSupermercado;
    
    public Cliente(String DNI, int numeroDeTelefono, TipoDeCliente tipoDeCliente,
                   int distanciaEnKmHastaSupermercado) {
        super(DNI, numeroDeTelefono);
        this.tipoDeCliente = tipoDeCliente;
        this.pedidos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.pedidoEnCurso = 0;
        this.distanciaEnKmHastaSupermercado = distanciaEnKmHastaSupermercado;
    }

    // JACK OA
    
    public TipoDeCliente getTipoDeCliente() {
        return tipoDeCliente;
    }
    
    public void setTipoDeCliente(TipoDeCliente tipoDeCliente) {
        this.tipoDeCliente = tipoDeCliente;
    }
    
    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
    
    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
    
    public ArrayList<Factura> getFacturas() {
        return facturas;
    }
    
    public void setFacturas(ArrayList<Factura> facturas) {
        this.facturas = facturas;
    }
    
    public int getPedidoEnCurso() {
        return pedidoEnCurso;
    }
    
    public void setPedidoEnCurso(int pedidoEnCurso) {
        this.pedidoEnCurso = pedidoEnCurso;
    }
    
    public int getDistanciaEnKmHastaSupermercado() {
        return distanciaEnKmHastaSupermercado;
    }
    
    public void setDistanciaEnKmHastaSupermercado(int distanciaEnKmHastaSupermercado) {
        this.distanciaEnKmHastaSupermercado = distanciaEnKmHastaSupermercado;
    }
}
