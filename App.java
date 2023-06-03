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
       /* empresa.IniciarApp(); 
                               * Distribuidor d1 = new Distribuidor();
                               * empresa.agregarDistribuidor(d1);
                               * d1.agregarProducto(new Producto(d1, "Oklahomo", false, false, 4.16));
                               * empresa.mostrarProveedoresYProductoQueDistribuye();
                               * empresa.guardarCambios();
                               */
        menuPrincipal();
    }

    private static void menuPrincipal() {
        int respuesta = 0;
        do {
            String[] titulo = {
                    "MENU PRINCIPAL"
            };
            String[] opciones = {
                    "1- Gestiones de empresa.",
                    "2- Gestiones de cliente",
                    "0- Salir"
            };
            respuesta = Herramientas.crearMenu(titulo, opciones);

            switch (respuesta) {
                case 1:

                    break;
                case 2:

                    break;
                case 0:
                    empresa.guardarCambios();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Error, solo puedes introducir un número del 0 al 2");
                    break;
            }

        } while (respuesta != 0);

    }

    private static void guardarDatosEmpresa() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheroEmpresa))) {
            empresa = new Empresa();
            out.writeObject(empresa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void leerDatosEmpresa() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheroEmpresa))) {
            Empresa empresaTemporal = (Empresa) in.readObject();
            if (empresaTemporal!=null) {
                empresa = empresaTemporal;
            }else{
                empresa = new Empresa();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean definirDatosEmpresa() {
        if (ficheroEmpresa.exists()) {
            try {
                leerDatosEmpresa();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Herramientas.limpiarPantalla();
            System.out.println("Ya que es la primera vez que se inicia la aplicación es necesario que de de alta la empresa");
            System.out.println("Vamos allá!");
            System.out.println("");
            guardarDatosEmpresa();
        }
        return true;
    }

}
