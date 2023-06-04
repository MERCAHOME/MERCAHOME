import java.util.ArrayList;

public class Cliente extends Persona {
    private TipoDeCliente tipoDeCliente;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Factura> facturas;
    private int pedidoEnCurso;
    //Implementar método mostrar
    //El tipo de cliente ha de cambiar dependiendo de el dinero que se gaste
    //implementar métodos de mostrar
    //crear método de realizar pedido
    public Cliente(Empresa empresaMercahome) {
        super(empresaMercahome);
        this.tipoDeCliente = TipoDeCliente.STANDARD;
        this.pedidos = new ArrayList<Pedido>();
        this.facturas = new ArrayList<Factura>();
        this.pedidoEnCurso = 0;
    }

    public void mostrarFacturas(){
        if (facturas.size()>0) {
            System.out.println("*********************************************************************");
            System.out.println("                       LISTADO DE FACTURAS");
            for (Factura factura : facturas) {
                factura.mostrar();
            }
        }else{
            System.out.println(super.getNombre()+" "+super.getApellidos()+" no tiene ninguna factura asociada todavía");
        }
    }
    public void mostrarPedidos(){
        if (pedidos.size()>0) {
            System.out.println("*********************************************************************");
            System.out.println("                            PEDIDOS");
            for (Pedido pedidoN : pedidos) {
                pedidoN.mostrarProductosPedidoConTotal();
            }
        }else{
            System.out.println(super.getNombre()+" "+super.getApellidos()+" no tiene ningun pedido asociado todavía");
        }
    }

    public void realizarPedido(){
        System.out.println("Seleccione un supermercado para realizar el pedido");
        Supermercado superm = this.getEmpresa().devolverSupermercado();
        if (superm!=null) {
            Pedido pedido = new Pedido(superm);
            getEmpresa().getPedidos().add( pedido);
            pedidos.add( pedido);
        } else {
            System.out.println("No se ha podido realizar el pedido, ya que se ha producido un error seleccionando el supermercado");
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
