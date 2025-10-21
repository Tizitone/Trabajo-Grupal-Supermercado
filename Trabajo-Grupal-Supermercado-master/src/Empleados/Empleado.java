package Gestion.Empleados;

import java.util.Objects;

public abstract class Empleado {

    // Atributos
    private String nombre;
    private int DNI; // unico y no modificable
    private char genero;
    private double salario; // calculado por varias caracteristicas del empleado.
    private boolean activo; // significa si esta trabajando en este momento.
    private int antiguedad; // años de trabajo.

    public Empleado(String nombre, int DNI, char genero){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = genero;
        activo = false;
        antiguedad = 0;
    }

    // Este constructor probablemente se use mas que nada para probar empleados hardcodeados, no dentro del sistema.
    public Empleado(String nombre, int DNI, char genero, boolean activo, int antiguedad){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = genero;
        this.activo = activo;
        this.antiguedad = antiguedad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public char getGenero() {
        return genero;
    }

    public double getSalario() {
        return salario;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Gestion.Empleados.Empleado[ " +
                " Nombre: " + nombre +
                ", DNI: " + DNI +
                ", Genero: " + genero +
                ", Salario: " + salario +
                ", Activo: " + activo +
                ", Antiguedad: " + antiguedad +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return getDNI() == empleado.getDNI() && getGenero() == empleado.getGenero() && Objects.equals(getNombre(), empleado.getNombre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombre(), getDNI(), getGenero());
    }


}
