import java.util.ArrayList;

public class Cliente extends Persona {
    private TipoDeCliente tipoDeCliente;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Factura> facturas;
    private int pedidoEnCurso;
    
    public Cliente() {
        this.tipoDeCliente = TipoDeCliente.STANDARD;
        this.pedidos = new ArrayList<Pedido>();
        this.facturas = new ArrayList<Factura>();
        this.pedidoEnCurso = 0;
    }

    public void mostrarFacturas(){
        if (facturas.size()>0) {
            
        }else{
            System.out.println(super.getNombre()+" "+super.getApellidos()+" no tiene ninguna factura asociada todavía");
        }
    }
    public void mostrarPedidos(){
        if (pedidos.size()>0) {
            
        }else{
            System.out.println(super.getNombre()+" "+super.getApellidos()+" no tiene ningun pedido asociado todavía");
        }
    }
    
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

    public enum TipoDeCliente {
        STANDARD,
        PREMIUM,
        VIP
    }
}
