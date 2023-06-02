import java.time.LocalDate;
import java.util.ArrayList;

public class Empleado extends Persona {
    //Comprobar que no exista un trabajador con el mismo dni ya dado de alta en empresa
    //crear método mostrar
    //Se puede poner plus de salario por tiempo dado de alta
    //Dar de alta vehiculos y gente a su cargo en el metodo establecerTipoDeEmpleado();
    //ImplementarHorarios

    // Metodo si existe cliene (dato clau igual (ex. DNI))

    //Un conductor no puede tener un horario antes o después de las horas del supermercado disponible

    private LocalDate fechaDeAlta;
    private Horario horariotrabajador;
    private TipoDeEmpleado tipoDeEmpleado;
    private double salario;
    private EstablecimientoPropio establecimientodeEmpleado;
    private Vehiculo vehiculo;
    private ArrayList<Empleado> empleadosASuCargo = new ArrayList<>();
    private Empleado encargado;
    private Empleado gerente;

    public Empleado(EstablecimientoPropio establecimiento, Empresa empresaMercahome) {
        super(empresaMercahome);
        if (altaEmpleado(establecimiento)) {
            System.out.println("El empleado "+ super.getNombre() + " " + super.getApellidos()+" ha sido dado de alta correctamente.");
        } else {
            System.out.println("No se ha podido dar de alta al cliente "+ super.getNombre() + " " + super.getApellidos()+".\nContacte con el administrador");
        }
    }
    //para dar de alta a cualquier empleado ha de existir antes un Encargado y un gerente, al momento de crear al empleado hay que asignarselo
    public boolean altaEmpleado(EstablecimientoPropio establecimiento) {
        try {
            this.fechaDeAlta = LocalDate.now();
            this.establecimientodeEmpleado = establecimiento;
            establecerTipoDeEmpleado();
            return true;
        } catch (Exception e) {
            System.out.println("No se ha podido dar de alta al empleado " + super.getNombre() + " " + super.getApellidos());
            return false;
        }
    }

    private void establecerTipoDeEmpleado() {
        int respuesta = 0;
        do {
            String[] titulos = {
                    "Cual va a ser el cargo de " + super.getNombre() + " " + super.getApellidos() + "?",
            };
            String[] opciones = {
                    "1- Gerente.",
                    "2- Encargado.",
                    "3- Conductor.",
                    "4- Mozo de almacén.",
                    "5- Cajero en supermercado.",
                    "6- Reponedor en supermercado"
            };
            respuesta = Herramientas.crearMenu(titulos, opciones);
            if (respuesta < 1 || respuesta > 6) {
                System.out.println("Error, solo puedes introducir un número entre 1 y 6");
                System.out.println(
                        "Seleccione el cargo de " + super.getNombre() + " " + super.getApellidos() + " de nuevo.");
            }
        } while (respuesta < 1 || respuesta > 6);
        switch (respuesta) {
            case 1:
                this.tipoDeEmpleado = TipoDeEmpleado.GERENTE;
                this.salario = this.tipoDeEmpleado.salario;
                this.horariotrabajador = crearHorario();
                break;
            case 2:
                this.tipoDeEmpleado = TipoDeEmpleado.ENCARGADO;
                this.salario = this.tipoDeEmpleado.salario;
                this.horariotrabajador = crearHorario();
                //poner empleados a su cargo
                break;
            case 3:
                this.tipoDeEmpleado = TipoDeEmpleado.CONDUCTOR;
                this.salario = this.tipoDeEmpleado.salario;
                if(establecimientodeEmpleado instanceof Supermercado){
                    Supermercado supermercado = (Supermercado)establecimientodeEmpleado;
                    ArrayList<Vehiculo> vehiculosEnSuperMercado = supermercado.getVehiculos();
                    if (vehiculosEnSuperMercado.size()<1) {
                        System.out.println("No hay vehiculos disponibles en el supermercado");
                        System.out.println("Va a tener que establecer otro cargo a "+super.getNombre() + " " + super.getApellidos()+", almenos hasta que exista un vehiculo disponible, entonces ya podrá asignar a este trabajador como conductor.");
                        System.out.println("Establezca otro cargo para "+super.getNombre() + " " + super.getApellidos());
                        altaEmpleado(establecimientodeEmpleado);
                    } else {
                        if (supermercado.vehiculosDisponibles()) {
                            Vehiculo vehiculoDisponible = supermercado.devolverVehiculoDisponible();
                            if(vehiculoDisponible.asignarHorario(this) == 0){
                                this.horariotrabajador = new Horario(8, 13.59);
                            }else if (vehiculoDisponible.asignarHorario(this) == 1) {
                                this.horariotrabajador = new Horario(14, 20);
                            }else{
                                System.out.println("Se ha producido un error al asignar el vehiculo a "+super.getNombre() + " " + super.getApellidos()+" intentelo de nuevo, si sigue teniendo problemas asigne otro cargo al empleado y contacte con el administrador para que lo solucione cuanto antes.");
                                System.out.println("Establezca otro cargo para "+super.getNombre() + " " + super.getApellidos());
                                altaEmpleado(establecimientodeEmpleado);
                            }
                        }else{
                            System.out.println("No hay más vehiculos disponibles en el supermercado");
                            System.out.println("Va a tener que establecer otro cargo a "+super.getNombre() + " " + super.getApellidos()+", almenos hasta que exista un vehiculo disponible, entonces ya podrá asignar a este trabajador como conductor.");
                            System.out.println("Establezca otro cargo para "+super.getNombre() + " " + super.getApellidos());
                            altaEmpleado(establecimientodeEmpleado);
                        }
                    }
                }else{
                    
                    System.out.println("No se puede dar de alta a este trabajador como conductor.");
                    System.out.println("Solo se pueden agregar conductores a los supermercados");
                    System.out.println("Eliga otro cargo para "+ super.getNombre() + " " + super.getApellidos()+".");
                    altaEmpleado(establecimientodeEmpleado);
                }
                break;
            case 4:
                this.tipoDeEmpleado = TipoDeEmpleado.MOZODEALMACEN;
                this.salario = this.tipoDeEmpleado.salario;
                this.horariotrabajador = crearHorario();
                break;
            case 5:
                this.tipoDeEmpleado = TipoDeEmpleado.CAJERODESUPERMERCADO;
                this.salario = this.tipoDeEmpleado.salario;
                this.horariotrabajador = crearHorario();
                break;
            case 6:
                this.tipoDeEmpleado = TipoDeEmpleado.REPONEDORSUPERMERCADO;
                this.salario = this.tipoDeEmpleado.salario;
                this.horariotrabajador = crearHorario();
                break;
            default:
                break;
        }
    }

    public Horario crearHorario(){
        System.out.println("Vamos a establecer el horario de este trabajador");
        int respuesta = 0;
        do {
            System.out.println("Va a tener descanso este trabajador?");
            System.out.println("1- Si");
            System.out.println("2- No");
            System.out.print("Respuesta: ");
            respuesta = Herramientas.pedirEnteroPositivo();
            if (respuesta<1||respuesta>2) {
                System.out.println("Error, solo puede introducir 1 o 2");
            }
        } while (respuesta<1||respuesta>2);
        System.out.println("A que hora iniciará su jornada laboral "+this.getNombre()+" "+this.getApellidos()+"?");
        System.out.print("Hora: ");
        double horarInicio = Herramientas.pedirDoublePositivo();
        double horaFinal = 0;
        do {
            System.out.println("A que hora terminará su jornada laboral "+this.getNombre()+" "+this.getApellidos()+"?");
            System.out.print("Hora: ");
            horaFinal = Herramientas.pedirDoublePositivo();
            if (horaFinal<horarInicio) {
                System.out.println("El trabajador no puede terminar antes de las "+horarInicio);
                System.out.println("Introduzca otra hora");
            }
        } while (horaFinal<horarInicio);

        if (respuesta == 1) {
            double horaInicioDescanso = 0;
            double horaFinDescanso = 0;
            do {
                System.out.println("A que hora iniciará su descanso "+this.getNombre()+" "+this.getApellidos()+"?");
                horaInicioDescanso = Herramientas.pedirDoublePositivo();
                if (horaInicioDescanso>horarInicio||horaInicioDescanso>horaFinal) {
                    System.out.println("Error, el descanso no puede empezar antes o después de su jornada laboral");
                }
                
            } while (horaInicioDescanso<horarInicio||horaInicioDescanso>horaFinal);
            do {
                System.out.println("A que hora terminará su descanso "+this.getNombre()+" "+this.getApellidos()+"?");
                horaFinDescanso = Herramientas.pedirDoublePositivo();
                if (horaFinDescanso>horaInicioDescanso||horaFinDescanso>horaFinal) {
                    System.out.println("Error, el descanso no puede terminar antes de su descanso o después de su jornada laboral");
                }
                
            } while (horaFinDescanso<horaInicioDescanso||horaFinDescanso>horaFinal);
            return new Horario(horarInicio, horaFinal, horaInicioDescanso, horaFinDescanso);
        }
        return new Horario(horarInicio, horaFinal);
    }

    public void mostrarNombreYDNI(){
        System.out.println("Nombre: " +this.getNombre());
        System.out.println("Dni: "+this.getDNI());
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public boolean cambiarCategoriaProfesional() {
        try {
            establecerTipoDeEmpleado();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }

    public Horario getHorario() {
        return horariotrabajador;
    }

    public void setHorario(Horario horario) {
        this.horariotrabajador = horario;
    }
    
    public TipoDeEmpleado getTipoDeEmpleado() {
        return tipoDeEmpleado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /* public enum TipoDeEmpleado {
        GERENTE(3000.00),
        ENCARGADO(2800.00),
        CONDUCTOR(1700.00),
        MOZODEALMACEN(1400.00),
        CAJERODESUPERMERCADO(1300.70),
        REPONEDORSUPERMERCADO(1200.00);

        private double salario;

        TipoDeEmpleado(double salario) {
            this.salario = salario;
        }

        double getSalario() {
            return salario;
        }

    } */
}
