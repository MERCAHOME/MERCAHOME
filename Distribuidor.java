import java.util.ArrayList;

public class Distribuidor extends Establecimiento {
    private String email;
    private ArrayList<Producto> productosQueDistribuye;
    
    public Distribuidor(String CIF, int numeroDeTelefono, Ubicacion ubicacion, Horario horarioPublico, String email) {
        super(CIF, numeroDeTelefono, ubicacion, horarioPublico);
        this.email = email;
        this.productosQueDistribuye = new ArrayList<>();
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public ArrayList<Producto> getProductosQueDistribuye() {
        return productosQueDistribuye;
    }
    
    public void setProductosQueDistribuye(ArrayList<Producto> productosQueDistribuye) {
        this.productosQueDistribuye = productosQueDistribuye;
    }
    
    public void agregarProducto(Producto producto) {
        productosQueDistribuye.add(producto);
    }
    
    public void eliminarProducto(Producto producto) {
        productosQueDistribuye.remove(producto);
    }
}
