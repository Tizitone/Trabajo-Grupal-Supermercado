public abstract class Personal extends Empleado {

    private int productividad;
    private String informe;

    public Personal(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
        productividad = 0;
        informe = "";
    }

    public Personal(String nombre, int DNI, char genero, boolean activo, int antiguedad){
        super(nombre, DNI, genero, activo, antiguedad);
        productividad = 0;
        informe = "";
    }

    public int getProductividad() {
        return productividad;
    }

    public String getInforme() {
        return informe;
    }

    public void setProductividad(int productividad) {
        this.productividad = productividad;
    }

    public void modificarInforme(String informe) {
        this.informe = informe;
    }

    @Override
    public String toString() {
        return "Personal[" +
                " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Genero: " + getGenero() +
                ", Salario: " + getSalario() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad() +
                ", Informe: " + getInforme() +
                ", Productividad: " + getProductividad() +
                ']';
    }


}
