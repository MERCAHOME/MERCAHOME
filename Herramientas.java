import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import ErroresPropios.*;

public interface Herramientas {
    public static Scanner sc = new Scanner(System.in); /*
                                                        * 
                                                        * _______________________________________________________________________________________________
                                                        * METODOS EXISTENTES METODOS EXISTENTES METODOS EXISTENTES
                                                        * METODOS EXISTENTES METODOS EXISTENTES|
                                                        * ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
                                                        * ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾
                                                        * 
                                                        * 
                                                        * crearDNI(): Solicita al usuario que ingrese un número de DNI y
                                                        * valida su formato.
                                                        * 
                                                        * crearNumeroDeTelefono(): Solicita al usuario que ingrese un
                                                        * número de teléfono y valida su formato y longitud.
                                                        * 
                                                        * crearMenu(String[] titulos, String[] opciones): Muestra un
                                                        * menú de opciones y solicita al usuario que elija una
                                                        * respuesta, devuelve un entero.
                                                        * 
                                                        * pedirEnteroPositivo(): Solicita al usuario que ingrese un
                                                        * número entero positivo y realiza validaciones en el formato y
                                                        * el rango del número.
                                                        * 
                                                        * localDateToStringSinCeros(LocalDate localDate): Convierte un
                                                        * objeto LocalDate en una cadena de texto que representa la
                                                        * fecha sin ceros a la izquierda.
                                                        * 
                                                        * localDateToStringConCeros(LocalDate localDate): Convierte un
                                                        * objeto LocalDate en una cadena de texto que representa la
                                                        * fecha con ceros a la izquierda si es necesario.
                                                        * 
                                                        * pedirString(): Solicita al usuario que ingrese una cadena de
                                                        * texto y realiza validaciones en la entrada.
                                                        * 
                                                        * pedirDoublePositivo(): Solicita al usuario un número double
                                                        * positivo y realiza sus validaciones.
                                                        * 
                                                        * limpiarPantalla(): Limpia la pantalla imprimiendo líneas en
                                                        * blanco.
                                                        */

    public static String crearDNI() {
        String dni = "";

        try {
            System.out.println("Indique el DNI a continuación.");
            System.out.print("DNI: ");
            dni = sc.nextLine();
            if (!dni.matches("^[0-9]{8}[a-zA-Z]{1}$")) {
                throw new DNIInvalidoException(
                        "El DNI Introducido no cumple con el patrón.\nEjemplo de DNI valido: '12345678X'");
            }
            ;
            return dni;
        } catch (DNIInvalidoException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Es necesario que introduzca su DNI de nuevo.");
            return crearDNI();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado.");
            System.out.println("Es necesario que introduzca su DNI de nuevo.");
            return crearDNI();
        }

    }

    public static String crearMatricula(){
        try {
            System.out.println("Indique la matricula del vehículo");
            System.out.print("Matrícula: ");
            String matricula = pedirString();
            if (!matricula.matches("^[0-9]{4}[A-Za-z]{3}$")) {
                throw new MatriculaInvalidaException("La matricula introducida no cumple el patrón");
            }
            return matricula;
        } catch (MatriculaInvalidaException e) {
            System.err.println("Error: "+e.getMessage());
            System.out.println("Introduzca una matrícula válida");
            return crearMatricula();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado");
            System.out.println("Introduzca una matrícula válida");
            return crearMatricula();
        }
    }

    public static  String crearEmail(){
        try {
            System.out.println("Indique el correo electrónico");
            System.out.println("Email: ");
            String email = pedirString();
            if (!email.matches("^[A-Za-z0-9+_.-]+@[a-zA-Z]+\\.[a-zA-Z]+$")) {
                throw new EmailInvalidoexception("El email introducido no cumple con el patrón de validación");
            }
            return email;
        } catch (EmailInvalidoexception e) {
            System.err.println("Error: "+e.getMessage());
            System.out.println("Introduzca un email válido");
            return crearEmail();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado");
            System.out.println("Introduzca un email válido");
            return crearEmail();
        }
    }

    public static int crearNumeroDeTelefono() {
        int numeroDeTelefono = 0;
        String telefonoString = "";
        try {
            System.out.println("Indique el número de teléfono a contniuación");
            System.out.print("Nº teléfono: ");
            telefonoString = sc.nextLine();
            if (telefonoString.length() != 9) {
                throw new TelefonoInvalidoException(
                        "El número de teléfono solo se puede componer por un total de 9 dígitos numéricos.");
            }
            for (int i = 0; i < telefonoString.length(); i++) {
                if (!Character.isDigit(telefonoString.charAt(i))) {
                    throw new TelefonoInvalidoException(
                            "El " + i + "º caracter: '" + telefonoString.charAt(i) + "' no es un digito numérico.");
                }
                if (i == 0 && (telefonoString.charAt(i) != '6' && telefonoString.charAt(i) != '7'
                        && telefonoString.charAt(i) != '9')) {
                    throw new TelefonoInvalidoException("El número de teléfono solo puede empezar por 6, 7 o 9");
                }
            }
            numeroDeTelefono = Integer.parseInt(telefonoString);
            return numeroDeTelefono;
        } catch (TelefonoInvalidoException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("El número de teléfono ha de contener 9 números, y ha de empezar por 9, 6 o 7");
            System.out.println("Introduzca el número de teléfono de nuevo.");
            return crearNumeroDeTelefono();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado.");
            System.out.println("Introduzca el número de teléfono de nuevo.");
            return crearNumeroDeTelefono();
        }

    }

    public static String ajustarTamanioString(String cadena, int tamanio) {
        if (cadena.length() < tamanio) {
            for (int i = 0; i < tamanio - cadena.length() / 2.7; i++) {
                cadena = cadena + "_";
            }
        } else {
            cadena = cadena.substring(0, tamanio - 3) + "...";
        }
        return cadena;
    }

    public static int crearMenu(String[] titulos, String[] opciones) {
        System.out.println("\n\n*****************************");
        for (int i = 0; i < titulos.length; i++) {
            System.out.println(titulos[i]);
        }
        System.out.println("*****************************");
        for (int i = 0; i < opciones.length; i++) {
            System.out.println(opciones[i]);
        }
        System.out.print("Respuesta: ");
        int respuesta = pedirEnteroPositivo();
        System.out.println("*****************************");
        limpiarPantalla();
        return respuesta;
    }

    public static int pedirEnteroPositivo() {
        try {
            String numero = sc.nextLine();
            for (int i = 0; i < numero.length(); i++) {
                if (!Character.isDigit(numero.charAt(i))) {
                    throw new NumeroInvalidoException(
                            "El " + i + "º caracter introducido: '" + numero.charAt(i) + "' no es un dígito numérico.");
                }
            }
            int numeroEntero = 0;
            numeroEntero = Integer.parseInt(numero);
            if (numeroEntero < 0) {
                throw new NumeroInvalidoException("El número introducido es menor de 0.");
            }
            return numeroEntero;
        } catch (NumeroInvalidoException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
            System.out.println("Introduzca el número de nuevo");
            System.out.print("Número: ");
            return pedirEnteroPositivo();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado");
            System.out.println("Introduzca el número de nuevo");
            System.out.print("Número: ");
            return pedirEnteroPositivo();
        }
    }

    public static String localDateToStringSinCeros(LocalDate localDate) {
        String fecha = "";
        fecha = localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear();
        return fecha;
    }

    public static String localDateToStringConCeros(LocalDate localDate) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return localDate.format(formateador);
    }

    public static String pedirString() {
        try {
            String cadena = sc.nextLine();
            if (cadena == null || cadena.isEmpty()) {
                throw new ErrorEnStringException("Se esperaba una entrada te texto por teclado");
            }

            if (cadena.isBlank()) {
                throw new ErrorEnStringException(
                        "Se esperaba un campo de texto que no fueran solo espacion en blanco.");
            }
            return cadena;

        } catch (ErrorEnStringException e) {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Introduzca el valo deseado de nuevo.");
            System.out.print("Nuevo campo de texto: ");
            return pedirString();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado");
            System.out.println("Introduzca el valo deseado de nuevo.");
            System.out.print("Nuevo campo de texto: ");
            return pedirString();
        }
    }

    public static double pedirDoublePositivo() {
        try {
            String numero = sc.nextLine();
            double numeroDouble = Double.parseDouble(numero);
            if (numeroDouble < 0) {
                throw new NumeroInvalidoException("El número introducido es menor que 0.");
            }
            return numeroDouble;
        } catch (NumberFormatException e) {
            System.err.println("Error: El valor introducido no es un número válido.");
            System.out.println("Introduzca el número de nuevo");
            System.out.print("Número: ");
            return pedirDoublePositivo();
        } catch (NumeroInvalidoException e) {
            System.err.println("Error: " + e.getLocalizedMessage());
            System.out.println("Introduzca el número de nuevo");
            System.out.print("Número: ");
            return pedirDoublePositivo();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inesperado");
            System.out.println("Introduzca el número de nuevo");
            System.out.print("Número: ");
            return pedirDoublePositivo();
        }
    }

    public static Ubicacion crearUbicacion() {

        System.out.println("Indicame el país");
        System.out.print("País: ");
        String pais = Herramientas.pedirString();
        System.out.println("Indicame la provincia");
        System.out.print("Provincia: ");
        String provincia = Herramientas.pedirString();
        System.out.println("Indicame la localidad");
        System.out.print("Localidad: ");
        String localidad = Herramientas.pedirString();
        System.out.println("Indicame la calle");
        System.out.print("Calle: ");
        String calle = Herramientas.pedirString();
        System.out.println("Indicame el numero");
        System.out.print("Número: ");
        String numero = Herramientas.pedirString();
        System.out.println("Si necesita introducir más información sobre la dirección, hagalo aquí");
        System.out.print("Mas info: ");
        String masinfo = sc.nextLine();
        return new Ubicacion(pais, provincia, localidad, calle, numero, masinfo);
    }

    public static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
            if (i==48) {
                logoMercahome();
            }
        }
    }

    public static void logoMercahome(){
        System.out.println("███╗░░░███╗███████╗██████╗░░█████╗░░█████╗░██╗░░██╗░█████╗░███╗░░░███╗███████╗");
        System.out.println("████╗░████║██╔════╝██╔══██╗██╔══██╗██╔══██╗██║░░██║██╔══██╗████╗░████║██╔════╝");
        System.out.println("██╔████╔██║█████╗░░██████╔╝██║░░╚═╝███████║███████║██║░░██║██╔████╔██║█████╗░░");
        System.out.println("██║╚██╔╝██║██╔══╝░░██╔══██╗██║░░██╗██╔══██║██╔══██║██║░░██║██║╚██╔╝██║██╔══╝░░");
        System.out.println("██║░╚═╝░██║███████╗██║░░██║╚█████╔╝██║░░██║██║░░██║╚█████╔╝██║░╚═╝░██║███████╗");
        System.out.println("╚═╝░░░░░╚═╝╚══════╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░░░░╚═╝╚══════╝");
        
    }

}
