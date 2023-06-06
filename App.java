import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class App implements Herramientas {
    // crear empresa.dat, on es guardaran els datos de empresa la primera vegada.
    // LEs altres vegades traura els datos guardats del fitxer
    static Empresa empresa;
    static File ficheroEmpresa = new File("./datos/empresa.dat");

    public static void main(String[] args) {
        definirDatosEmpresa();
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "       MENU PRINCIPAL"
            };
            String[] opciones = {
                    "1- Gestiones de empresa.",
                    "2- Gestiones de cliente",
                    "0- Salir"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    menuEmpresa();
                    break;
                case 2:
                    menuCliente();
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("Gracias por confiar en MERCAHOME.\n");
                    System.out.println("¡Hasta pronto!\n\n");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 2");
                    break;
            }

        } while (respuesta != 0);

    }

    private static void menuEmpresa() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "     GESTION DE EMPRESA"

            };
            String[] opciones = {
                    "1- Gestiones de empresa.",
                    "2- Gestiones de almacenes",
                    "3- Gestiones de supermercados",
                    "4- Gestiones de distribuidores",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    menuGestionesEmpresa();
                    break;
                case 2:
                    menuGestionesAlmacenes();
                    break;
                case 3:
                    menuGestionesSupermercados();
                    break;
                case 4:
                    menuGestionesDistribuidores();
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 4");
                    break;
            }

        } while (respuesta != 0);

    }

    private static void menuGestionesEmpresa() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "    GESTIONES DE EMPRESA"

            };
            String[] opciones = {
                    "1- Mostrar todos los clientes",
                    "2- Mostrar todos los trabajadores",
                    "3- Mostrar todas las facturas",
                    "4- Mostrar todos los vehiculos",
                    "5- Mostrar todos los pedidos",
                    "6- Mostrar todos los productos",
                    "7- Mostrar todos los descuentos",
                    "8- Mostrar todos los distribuidores",
                    "",
                    "9- Agregar descuento",
                    "10- Modificar estado descuento",
                    "",
                    "11- Agregar supermercado",
                    "12- Agregar Almacén",
                    "13- Agregar distribuidor",
                    "",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    if (empresa.getClientes().size() > 0) {
                        empresa.mostrarClientes();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen clientes en la empresa");
                        System.out.println("Volviendo...");
                    }
                    break;
                case 2:
                    if (empresa.getTrabajadores().size() > 0) {
                        empresa.mostrarTrabajadores();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen trabajadores en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 3:
                    if (empresa.getFacturas().size() > 0) {
                        empresa.mostrarFacturas();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen facturas en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 4:
                    if (empresa.getVehiculos().size() > 0) {
                        empresa.mostrarVehiculos();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen vehículos en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 5:
                    if (empresa.getPedidos().size() > 0) {
                        empresa.mostrarPedidos();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen pedidos en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 6:
                    if (empresa.getStock().size() > 0) {
                        System.out.println("Método no implementado todavía");

                        empresa.mostrarProductos();

                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen productos en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 7:
                    if (empresa.getDescuentos().size() > 0) {
                        empresa.mostrarDescuentos();
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("No existen descuentos en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 8:
                    if (empresa.getDistribuidores().size() > 0) {
                        empresa.mostrarProveedores();
                    } else {
                        System.out.println("No existen distribuidores en la empresa");
                        System.out.println("Volviendo...");

                    }
                    break;
                case 9:
                    int respuestaNueva = 0;
                    do {
                        System.out.println("Que tipo de descuento quieres agregar?");
                        System.out.println("1- Descuento cantidad");
                        System.out.println("2- Descuento de porcentaje");
                        respuestaNueva = Herramientas.pedirEnteroPositivo();
                        if (respuestaNueva < 1 || respuestaNueva > 2) {
                            System.out.println("Error, solo puedes introducir 1 o 2");
                        }
                    } while (respuestaNueva < 1 || respuestaNueva > 2);

                    if (respuestaNueva == 1) {
                        System.out.println(
                                "Indique el importe mínimo que ha de gastar el cliente para poder aplicar este descuento");
                        System.out.print("Importe mínimo: ");
                        double minimo = Herramientas.pedirDoublePositivo();
                        System.out.println("Cuál va a ser la cantidad que va a restar este descuento?");
                        System.out.print("Cantidad: ");
                        double cantidad = Herramientas.pedirDoublePositivo();
                        empresa.getDescuentos().add(new DescuentoCantidad(true, minimo, cantidad));
                        System.out.println("¡Hecho!");
                    } else {
                        System.out.println("Cuál va a ser la cantidad porcentual a descontar?");
                        System.out.print("Porcentaje: ");
                        int porcentaje = Herramientas.pedirEnteroPositivo();
                        System.out.println("Cuál será la cantidad máxima que se podrá descontar con este descuento?");
                        System.out.print("Cantidad máxima: ");
                        double cantidadMax = Herramientas.pedirDoublePositivo();
                        empresa.getDescuentos().add(new DescuentoPorcentual(true, porcentaje, cantidadMax));
                        System.out.println("¡Hecho!");
                    }
                    System.out.println("Volviendo...");

                    break;
                case 10:
                    if (empresa.getDescuentos().size() > 0) {
                        Descuento desc = empresa.devolverDescuento();
                        if (desc.isActivo()) {
                            desc.setActivo(false);
                        } else {
                            desc.setActivo(true);
                        }
                        empresa.mostrarDescuentos();
                        System.out.println("¡Hecho!");
                        System.out.println("Volviendo...");
                    } else {
                        System.out.println("Todavía no hay descuentos dados de alta");
                    }

                    break;
                case 11:
                    System.out.println("Vamos a agregar un supermercado");
                    System.out.println();
                    empresa.getSupermercados().add(new Supermercado(empresa));

                    System.out.println("¡Hecho!");
                    System.out.println("Volviendo...");
                    break;
                case 12:

                    System.out.println("Vamos a agregar un almacén");
                    System.out.println();
                    empresa.getAlmacenes().add(new Almacen(empresa));
                    System.out.println("¡Hecho!");
                    System.out.println("Volviendo...");
                    break;
                case 13:

                    System.out.println("Vamos a agregar un distribuidor");
                    System.out.println();
                    empresa.getDistribuidores().add(new Distribuidor());
                    System.out.println("¡Hecho!");
                    System.out.println("Volviendo...");
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 13");
                    break;
            }

        } while (respuesta != 0);
    };

    private static void menuGestionesAlmacenes() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "    GESTION DE ALMACENES"

            };
            String[] opciones = {
                    "1- Mostrar productos de un almacen", // done
                    "2- Agregar producto a un almacen", // done
                    "3- Eliminar producto de un almacen", // done
                    "4- Agregar empleado a un almacén", // done
                    "5- Eliminar empleado a un almacén",
                    "6- Agregar estantería a un almacén", // done
                    "7- Agregar nevera a un almacén", // done
                    "8- Eliminar estanteria de un almacén",
                    "9- Eliminar nevera de un almacén",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);
            Almacen almacenM = null;
            int cantidad = 0;
            int capacidad = 0;
            int niveles = 0;
            if (respuesta!=0) {
                while (almacenM == null) {
                    almacenM = empresa.devolverAlmacen();
                }
            }
            switch (respuesta) {
                case 1:

                    almacenM.mostrarProductos();

                    /*
                     * if (empresa.getDistribuidores().size()>0) {
                     * Distribuidor proveedor = null;
                     * 
                     * do {
                     * empresa.mostrarProveedores();
                     * String cif = Herramientas.pedirString();
                     * proveedor = empresa.devolverProveedor(cif);
                     * if (proveedor == null) {
                     * System.out.println("No existe ningún proveedor con este cif");
                     * }
                     * } while (proveedor == null);
                     * 
                     * proveedor.mostrarProductosQueDistribuye();
                     * 
                     * }else{
                     * System.out.println("No existe nigún proveedor");
                     * System.out.println("Volviendo...");
                     * }
                     * break;
                     */
                    break;
                case 2:
                    almacenM.agregarProducto();

                    break;
                case 3:
                    Producto productoE = null;
                    while (productoE == null) {
                        almacenM.mostrarProductos();
                        System.out.println("Dime la ID del producto que quieres eliminar.");
                        System.out.print("ID: ");
                        int id = Herramientas.pedirEnteroPositivo();
                        for (Producto producto : almacenM.getStock()) {
                            if (producto.getId() == id) {
                                productoE = producto;
                                break;
                            }
                        }
                    }

                    almacenM.eliminarProducto(productoE);
                    break;
                case 4:
                    almacenM.agregarTrabajador();
                    break;
                case 5:
                    // eliminar empleado
                    // Tiene que haber mínimo un encargado, un gerente, y dos mozos
                    // de almacén, si se elimina un encargado, todos los empleados
                    // a su cargo se tienen que asignar a otro encargado

                    /* Empleado empleadoE = null;
                    int numEncargado = 0;
                    int numGerente = 0;
                    int numMozos = 0;
                    while (empleadoE == null) {
                        almacenM.mostrarTrabajadores();
                        System.out.println("Qué trabajador quiere eliminar? ");
                        System.out.print("DNI: ");
                        String dniE = Herramientas.crearDNI();
                        for (Empleado empleado : empresa.getTrabajadores()) {
                            if (empleado.getDNI().equals(dniE)) {
                                empleadoE = empleado;
                            }
                            if (empleado.getTipoDeEmpleado() == TipoDeEmpleado.GERENTE) {
                                numGerente++;
                            }
                            if (empleado.getTipoDeEmpleado() == TipoDeEmpleado.ENCARGADO) {
                                numEncargado++;
                            }
                            if (empleado.getTipoDeEmpleado() == TipoDeEmpleado.MOZODEALMACEN) {
                                numMozos++;
                            }
                        }
                    }

                    if (empleadoE.getTipoDeEmpleado() == TipoDeEmpleado.ENCARGADO
                            || empleadoE.getTipoDeEmpleado() == TipoDeEmpleado.GERENTE
                            || empleadoE.getTipoDeEmpleado() == TipoDeEmpleado.MOZODEALMACEN) {
                        if (numEncargado < 1 || numGerente < 1 || numMozos < 2) {
                            System.out.println("No se puede eliminar un " + empleadoE.getTipoDeEmpleado().name()
                                    + ", no hay suficientes...");
                        }
                    } else {
                        almacenM.eliminarEmpleado(empleadoE);
                    } */

                    if (almacenM.eliminarTrabajador()) {
                        System.out.println("Empleado eliminado con éxito");
                    }
                    

                    break;
                case 6:

                    while (cantidad == 0) {
                        cantidad = Herramientas.pedirEnteroPositivo();
                    }

                    while (capacidad == 0) {
                        capacidad = Herramientas.pedirEnteroPositivo();
                    }

                    while (niveles == 0) {
                        niveles = Herramientas.pedirEnteroPositivo();
                    }

                    almacenM.agregarEstanterias(cantidad, capacidad, niveles);
                    break;
                case 7:
                    while (cantidad == 0) {
                        cantidad = Herramientas.pedirEnteroPositivo();
                    }

                    while (capacidad == 0) {
                        capacidad = Herramientas.pedirEnteroPositivo();
                    }
                    almacenM.agregarNeveras(cantidad, capacidad);
                    break;
                case 8:
                    // eliminar estanteria
                    // Tiene que estar vacía o hay que reubicar productos

                    /* 
                    int numeroEstanteria = 0;
                    Estanteria estanteriaE = null;
                    while (estanteriaE == null) {
                        almacenM.mostrarEstanterias();
                        System.out.println(
                                "Indica la estantería que quiere eliminar. (Ten en cuenta que si no esta vacía habrá que reubicar los productos).");
                        System.out.print("Número: ");
                        numeroEstanteria = Herramientas.pedirEnteroPositivo();
                        for (Estanteria estanteria : almacenM.getEstanterias()) {
                            if (estanteria.getNumeroEstanteria() == numeroEstanteria) {
                                estanteriaE = estanteria;
                            }
                        }
                    }

                    if (estanteriaE.getProductos().isEmpty()) {
                        almacenM.eliminarEstanteria(estanteriaE);
                    } else {



                        Estanteria estanteriaMoverProductos = null;
                        while (estanteriaMoverProductos == null) {
                            almacenM.mostrarEstanterias();
                            System.out.println("A qué estantería quieres mover los productos?");
                            System.out.print("Número: ");
                            int estanteriaNumeroMover = Herramientas.pedirEnteroPositivo();
                            if (estanteriaNumeroMover == numeroEstanteria) {
                                System.out.println("No puedes mover los productos a la misma estantería...");
                            } else {


                                for (Estanteria estanteria2 : almacenM.getEstanterias()) {
                                    if (estanteria2.getNumeroEstanteria() == estanteriaNumeroMover) {
                                        estanteriaMoverProductos = estanteria2;
                                    }
                                }
                            }
                        }



                        // MOVER PRODUCTOS DE estanteriaE a estanteriaMoverProductos, luego eliminar
                        // estanteriaE
                        for (Producto producto : estanteriaE.getProductos()) {
                            estanteriaMoverProductos.agregarProducto(producto);
                            estanteriaE.eliminarProducto(producto);

                        }
                        almacenM.eliminarEstanteria(estanteriaE);
                    } */

                    almacenM.eliminarEstanteria();
                    break;
                case 9:
                    // eliminar nevera
                    // Tiene que estar vacía o hay que reubicar productos



                    /* 
                    int idNevera = 0;
                    Nevera neveraE = null;
                    while (neveraE == null) {
                        almacenM.mostrarNeveras();
                        System.out.println(
                                "Indica la nevera que quiere eliminar. (Ten en cuenta que si no esta vacía habrá que reubicar los productos).");
                        System.out.print("ID: ");
                        idNevera = Herramientas.pedirEnteroPositivo();
                        for (Nevera nevera : almacenM.getNeveras()) {
                            if (nevera.getId() == idNevera) {
                                neveraE = nevera;
                            }
                        }
                    }

                    if (neveraE.getProductos().isEmpty()) {
                        almacenM.eliminarNevera(neveraE);
                    } else {
                        Nevera neveraMoverProductos = null;
                        while (neveraMoverProductos == null) {
                            almacenM.mostrarNeveras();
                            System.out.println("A qué nevera quieres mover los productos?");
                            System.out.print("ID: ");
                            int neveraIdMover = Herramientas.pedirEnteroPositivo();
                            if (neveraIdMover == idNevera) {
                                System.out.println("No puedes mover los productos a la misma nevera...");
                            } else {
                                for (Nevera nevera2 : almacenM.getNeveras()) {
                                    if (nevera2.getId() == neveraIdMover) {
                                        neveraMoverProductos = nevera2;
                                    }
                                }
                            }
                        }

                        // MOVER PRODUCTOS DE neveraE a neveraMoverProductos, luego eliminar estanteriaE
                        for (Producto producto : neveraE.getProductos()) {
                            neveraMoverProductos.agregarproducto(producto);
                            neveraE.eliminarProducto(producto);
                        }
                        almacenM.eliminarNevera(neveraE);
                    } */

                    almacenM.eliminarNevera();
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 9");
                    break;
            }

        } while (respuesta != 0);
    };

    private static void menuGestionesSupermercados() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "  GESTION DE SUPERMERCADOS"

            };
            String[] opciones = {
                    "1- Mostrar productos de un supermercado",
                    "2- Agregar producto a un supermercado",
                    "3- Eliminar producto de un supermercado",
                    "4- Agregar empleado a un supermercado",
                    "5- Eliminar empleado a un supermercado", // Tiene que haber mínimo un encargado, un gerente, un
                                                              // conductor, y un cajero, si se elimina un encargado,
                                                              // todos los empleados a su cargo se tienen que asignar a
                                                              // otro encargado
                    "6- Agregar estantería a un supermercado",
                    "7- Agregar nevera a un supermercado",
                    "8- Eliminar estanteria de un supermercado", // Tiene que estar vacía o hay que reubicar productos
                    "9- Eliminar nevera de un supermercado", // Tiene que estar vacía o hay que reubicar productos
                    "10- Mostrar pedidos de un supermercado",
                    "11- Mostrar facturas de un supermercado",
                    "12- Mostrar vehículos de un supermercado",
                    "13- Mostrar trabajadores",
                    "14- Mostrar gerente",
                    "15- Mostrar encargado(s)",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            Supermercado spmcd = null;

            if (respuesta!=0) {
                if (empresa.getSupermercados().size()>0) {
                    do {
                        spmcd = empresa.devolverSupermercado();
                    } while (spmcd==null);
                    
                }else{
                    System.out.println("No puede realizar ninguna operación, ya que no hay supermercados dados de alta");
                    respuesta = 0;
                }
                
            }

            switch (respuesta) {
                case 1:
                    spmcd.mostrarProductos();
                    break;
                case 2:
                    spmcd.agregarProductos();

                    break;
                case 3:
                    spmcd.eliminarProducto();
                    break;
                case 4:
                    spmcd.agregarEmpleado();
                    break;
                case 5:
                    if (spmcd.eliminarTrabajador()) {
                        System.out.println("Empleado eliminado con éxito");
                    }
                    break;
                case 6:
                    System.out.println("Cuantas estanterias quieres añadir a tu supermercado?");
                    System.out.print("Cantidad: ");
                    int estanterias = Herramientas.pedirEnteroPositivo();
                    System.out.println("De cuantos niveles van a ser estas estanterias?");
                    System.out.print("Niveles: ");
                    int niveles = Herramientas.pedirEnteroPositivo();
                    System.out.println("Cual va a ser la capacidad total de esta estantería?");
                    System.out.print("Capacidad: ");
                    int capacidad = Herramientas.pedirEnteroPositivo();
                    if (spmcd.agregarEstanterias(estanterias, capacidad, niveles)) {
                        System.out.println("Estanteria(s) agregada(s) con éxito");
                    }

                    break;
                case 7:
                    System.out.println("Cuantas neveras quieres añadir a tu supermercado?");
                    System.out.print("Cantidad: ");
                    int neveras = Herramientas.pedirEnteroPositivo();
                    System.out.println("Que capacidad va(n) a tener la(s) nevera(s)?");
                    System.out.print("Capacidad: ");
                    int capacidad2 = Herramientas.pedirEnteroPositivo();
                    if (spmcd.agregarNeveras(neveras, capacidad2)) {
                        System.out.println("Nevera(s) agregada(s) con éxito");
                    }
                    break;
                case 8:
                    spmcd.eliminarEstanteria();
                    break;
                case 9:
                    spmcd.eliminarNevera();
                    break;
                case 10:
                    spmcd.mostrarPedidos();
                    break;
                case 11:
                    spmcd.mostrarFacturas();
                    break;
                case 12:
                    spmcd.mostrarVehiculos();
                    break;
                case 13:
                    spmcd.mostrarTrabajadores();
                    
                    break;
                case 14:
                    spmcd.mostrarGerente();
                      
                    break;
                case 15:
                    spmcd.mostrarEncargados();
                            
                            break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 15");
                    break;
            }

        } while (respuesta != 0);
    };

    private static void menuGestionesDistribuidores() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "    MENU DISTRIBUIDORES"
            };
            String[] opciones = {
                    "1- Mostrar productos que vende un distribuidor",
                    "2- Agregar producto que vendre un distribuidor",
                    "3- Eliminar producto que vendre un distribuidor",
                    "0- Salir"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    if (empresa.getDistribuidores().size() > 0) {
                        Distribuidor proveedor = null;

                        do {
                            empresa.mostrarProveedores();
                            String cif = Herramientas.pedirString();
                            proveedor = empresa.devolverProveedor(cif);
                            if (proveedor == null) {
                                System.out.println("No existe ningún proveedor con este cif");
                            }
                        } while (proveedor == null);

                        proveedor.mostrarProductosQueDistribuye();

                    } else {
                        System.out.println("No existe nigún proveedor");
                        System.out.println("Volviendo...");
                    }
                    break;
                case 2:
                    if (empresa.getDistribuidores().size() > 0) {
                        Distribuidor proveedor = null;

                        do {
                            empresa.mostrarProveedores();
                            String cif = Herramientas.pedirString();
                            proveedor = empresa.devolverProveedor(cif);
                            if (proveedor == null) {
                                System.out.println("No existe ningún proveedor con este cif");
                            }
                        } while (proveedor == null);
                        proveedor.darDeAltaUnproducto();
                    } else {
                        System.out.println("No existe nigún proveedor");
                        System.out.println("Volviendo...");
                    }
                    break;
                case 3:
                    if (empresa.getDistribuidores().size() > 0) {
                        Distribuidor proveedor = null;

                        do {
                            empresa.mostrarProveedores();
                            String cif = Herramientas.pedirString();
                            proveedor = empresa.devolverProveedor(cif);
                            if (proveedor == null) {
                                System.out.println("No existe ningún proveedor con este cif");
                            }
                        } while (proveedor == null);

                        proveedor.eliminarProductoQueDistribuye();
                    } else {
                        System.out.println("No existe nigún proveedor");
                        System.out.println("Volviendo...");
                    }
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("Volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 3");
                    break;
            }

        } while (respuesta != 0);
    };

    private static void menuCliente() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "     GESTION DE CLIENTE"

            };
            String[] opciones = {
                    "1- Alta nueva.",
                    "2- Identificarse como cliente",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    Cliente cliente = new Cliente(empresa);

                    if (cliente != null) {
                        empresa.getClientes().add(cliente);
                        System.out.println("Cliente añadido con éxito");
                        menuClienteIdentificado(cliente);
                    } else {
                        System.out.println("No se ha podido dar de alta el cliente");
                        System.out.println("Volviendo...");
                    }
                    break;
                case 2:
                    if (empresa.getClientes().size() > 0) {
                        Cliente cliente2 = empresa.devolverCliente();
                        Herramientas.limpiarPantalla();
                        menuClienteIdentificado(cliente2);
                    } else {
                        System.out.println("No se puede identificar como cliente porque no hay clientes dados de alta");
                        System.out.println("Volviendo...");
                    }
                    break;

                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 2");
                    break;
            }

        } while (respuesta != 0);

    }

    private static void menuClienteIdentificado(Cliente cliente) {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "     GESTION DE CLIENTE"

            };
            String[] opciones = {
                    "1- Consultar pedidos",
                    "2- Consultar facturas",
                    "3- Realizar pedido",
                    "4- Consultar categoria de cliente",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    if (cliente.getPedidos().size() > 0) {

                        cliente.mostrarPedidos();
                    } else {
                        System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos()
                                + " no tiene ningun pedido todavía");
                    }

                case 2:

                    if (cliente.getFacturas().size() > 0) {

                        cliente.mostrarFacturas();
                    } else {
                        System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos()
                                + " no tiene ninguna factura todavía");
                    }
                    break;

                case 3:
                    cliente.realizarPedido();

                    break;
                case 4:
                    System.out.println("La categoría del cliente " + cliente.getNombre() + " " + cliente.getApellidos()
                            + " es '" + cliente.getTipoDeCliente().toString() + "'");
                    break;

                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 4");
                    break;
            }

        } while (respuesta != 0);

    }

    private static void guardarDatosEmpresa() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheroEmpresa))) {

            out.writeObject(empresa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void leerDatosEmpresa() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheroEmpresa))) {
            Empresa empresaTemporal = (Empresa) in.readObject();
            if (empresaTemporal != null) {
                empresa = empresaTemporal;
            } else {
                empresa = new Empresa();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer los datos de la empresa:");
            e.printStackTrace();
        }
    }

    public static boolean definirDatosEmpresa() {
        if (ficheroEmpresa.exists()) {
            try {
                leerDatosEmpresa();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Como se ha producido un error al leer la empresa la vamos a crear de nuevo");
                empresa = new Empresa();
                guardarDatosEmpresa();
            }
        } else {
            Herramientas.limpiarPantalla();
            System.out.println(
                    "Ya que es la primera vez que se inicia la aplicación es necesario que de de alta la empresa.");
            System.out.println("Vamos allá!");
            System.out.println("");
            empresa = new Empresa();
            guardarDatosEmpresa();
        }
        return true;
    }

}
