package empleados;

import excepciones.InvalidIDException;
import excepciones.InvalidLengthException;

import java.util.Objects;

public abstract class Empleado {

    // Atributos
    private String nombre;
    private int DNI; // unico y no modificable
    private char genero;
    private int salario; // calculado por varias caracteristicas del empleado.
    private boolean activo; // significa si esta trabajando en este momento.
    private int antiguedad; // años de trabajo.

    public Empleado(String nombre, int DNI, char genero){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = genero;
        salario = 0;
        activo = false;
        antiguedad = 0;
    }


    // Este constructor probablemente se use mas que nada para probar empleados hardcodeados, no dentro del sistema.
    public Empleado(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = genero;
        this.salario = salario;
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

    public int getSalario() {
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

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Empleado[ " +
                " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Genero: " + getDNI() +
                ", Salario: " + getGenero() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad() +
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

    /**
     * AÑADÍ LAS VERIFICACIONES YA HECHAS E HICE UNA PARA EL DNI
    */
    // Verificaciones
    public boolean verificarNombre(String verificar) throws InvalidLengthException {
        if (verificar.length() > 12)
            throw new InvalidLengthException("El nombre excede el limite de caracteres (12).");

        return true;
    }

    public boolean verificarGenero(char verificar) throws IllegalArgumentException{
        if (verificar != 'h' && verificar != 'm' && verificar != 'o')
            throw new IllegalArgumentException("El valor ingresado no coincide a una de las opciones posibles");

        return true;
    }

    public boolean verificarDNI() throws InvalidIDException {
        if (getDNI() >= 60000000 && getDNI() <= 69999999)
            throw new InvalidIDException("El numero del DNI coincide con los reservados para CUIT y CUIL extranjero.");
        if (getDNI() < 10000000)
            throw new InvalidIDException("El numero del DNI es demasiado pequeño como para ser de una edad valida.");
        return true;
    }




}
