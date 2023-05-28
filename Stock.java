public interface Stock {
    boolean agregarProducto(Producto producto);
    boolean eliminarProducto(Producto producto);
    int obtenerCantidadProductos();
}
