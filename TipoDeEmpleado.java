public enum TipoDeEmpleado {
    GERENTE(3000.00),
    ENCARGADO(2800.00),
    CONDUCTOR(1700.00),
    MOZODEALMACEN(1400.00),
    CAJERODESUPERMERCADO(1300.70),
    REPONEDORSUPERMERCADO(1200.00);

    public double salario;

    TipoDeEmpleado(double salario) {
        this.salario = salario;
    }

    double getSalario() {
        return salario;
    }

}