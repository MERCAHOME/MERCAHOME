import java.time.LocalDate;
import java.util.ArrayList;

public class Empleado extends Persona {
    //Comprobar que no exista un trabajador con el mismo dni ya dado de alta en empresa
    //crear método mostrar
    //Se puede poner plus de salario por tiempo dado de alta
    //Dar de alta vehiculos y gente a su cargo en el metodo establecerTipoDeEmpleado();
    //ImplementarHorarios

    // Metodo si existe cliene (dato clau igual (ex. DNI))

    private LocalDate fechaDeAlta;
    private Horario horariotrabajador;
    private TipoDeEmpledo tipoDeEmpleado;
    private double salario;
    private EstablecimientoPropio establecimientodeEmpleado;
    private Vehiculo vehiculo;
    private ArrayList<Empleado> empleadosASuCargo = new ArrayList<>();
    private Empleado encargado;
    private Empleado gerente;

    public Empleado(EstablecimientoPropio establecimiento) {
        if (altaEmpleado(establecimiento)) {
            System.out.println("El empleado "+ super.getNombre() + " " + super.getApellidos()+" ha sido dado de alta correctamente.");
        } else {
            System.out.println("No se ha podido dar de alta al cliente "+ super.getNombre() + " " + super.getApellidos()+".\nContacte con el administrador");
        }
    }
    //crear un horario para cada empleado
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
                this.tipoDeEmpleado = TipoDeEmpledo.GERENTE;
                this.salario = this.tipoDeEmpleado.salario;
                break;
            case 2:
                this.tipoDeEmpleado = TipoDeEmpledo.ENCARGADO;
                this.salario = this.tipoDeEmpleado.salario;
                //poner empleados a su cargo
                break;
            case 3:
                this.tipoDeEmpleado = TipoDeEmpledo.CONDUCTOR;
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
                                //Implementar Horario mañanas
                            }else if (vehiculoDisponible.asignarHorario(this) == 1) {
                                //implementar Horario tardes
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
                this.tipoDeEmpleado = TipoDeEmpledo.MOZODEALMACEN;
                this.salario = this.tipoDeEmpleado.salario;
                break;
            case 5:
                this.tipoDeEmpleado = TipoDeEmpledo.CAJERODESUPERMERCADO;
                this.salario = this.tipoDeEmpleado.salario;
                break;
            case 6:
                this.tipoDeEmpleado = TipoDeEmpledo.REPONEDORSUPERMERCADO;
                this.salario = this.tipoDeEmpleado.salario;
                break;
            default:
                break;
        }
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

    public enum TipoDeEmpledo {
        GERENTE(3000.00),
        ENCARGADO(2800.00),
        CONDUCTOR(1700.00),
        MOZODEALMACEN(1400.00),
        CAJERODESUPERMERCADO(1300.70),
        REPONEDORSUPERMERCADO(1200.00);

        private double salario;

        TipoDeEmpledo(double salario) {
            this.salario = salario;
        }

        double getSalario() {
            return salario;
        }

    }
}
