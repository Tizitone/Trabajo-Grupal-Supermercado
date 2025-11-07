package empleados;

import interfaces.IGestionable;

public abstract class Personal extends Empleado implements IGestionable<Integer>{

    // Atributos
    private int productividad;
    private String informe;

    // Constructores
    public Personal(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
        productividad = 0;
        informe = "";
    }

    public Personal(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        super(nombre, DNI, genero, salario, activo, antiguedad);
        productividad = 0;
        informe = "";
    }

    // Getters y Setters
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

    // Metodo toString
    @Override
    public String toString() {
        return super.toString()+", Productividad: " + getProductividad() + ", Informe: " + getInforme() + " ]";
    }



}