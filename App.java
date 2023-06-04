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
                    "",
                    "8- Agregar descuento",
                    "9- Modificar estado descuento",
                    "",
                    "10- Agregar supermercado",
                    "11- Agregar Almacén",
                    "12- Agregar distribuidor",
                    "",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:

                    break;
                case 6:

                    break;
                case 7:

                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
                case 12:

                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 12");
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
                    "1- Agregar almacén",
                    "2- Mostrar productos de un almacen",
                    "3- Agregar producto a un almacen",
                    "4- Eliminar producto de un almacen",
                    "5- Agregar empleado a un almacén",
                    "6- Eliminar empleado a un almacén",//Tiene que haber mínimo un encargado, un gerente, y dos mozos de almacén, si se elimina un encargado, todos los empleados a su cargo se tienen que asignar a otro encargado
                    "7- Agregar estantería a un almacén",
                    "8- Agregar nevera a un almacén",
                    "9- Eliminar estanteria de un almacén",//Tiene que estar vacía o hay que reubicar productos
                    "10- Eliminar nevera de un almacén",//Tiene que estar vacía o hay que reubicar productos
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 0:
                    guardarDatosEmpresa();
                    System.out.println("volviendo...");
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 10");
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
                    "1- Agregar supermercado",
                    "2- Mostrar productos de un supermercado",
                    "3- Agregar producto a un supermercado",
                    "4- Eliminar producto de un supermercado",
                    "5- Agregar empleado a un supermercado",
                    "6- Eliminar empleado a un supermercado",//Tiene que haber mínimo un encargado, un gerente, un conductor, y un cajero, si se elimina un encargado, todos los empleados a su cargo se tienen que asignar a otro encargado
                    "7- Agregar estantería a un supermercado",
                    "8- Agregar nevera a un supermercado",
                    "9- Eliminar estanteria de un supermercado",//Tiene que estar vacía o hay que reubicar productos
                    "10- Eliminar nevera de un supermercado",//Tiene que estar vacía o hay que reubicar productos
                    "11- Mostrar pedidos de un supermercado",
                    "12- Mostrar facturas de un supermercado",
                    "13- Mostrar vehículos de un supermercado",
                    "0- Volver al menú principal"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    
                    break;
                case 6:
                    
                    break;
                case 7:
                    
                    break;
                case 8:
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                    
                    break;
                case 11:
                    
                    break;
                case 12:
                    
                    break;
                case 13:
                    
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
                    
                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 0:
                    guardarDatosEmpresa();
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
