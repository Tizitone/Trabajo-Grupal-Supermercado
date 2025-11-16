package empleados;

import excepciones.InvalidDNIException;
import excepciones.InvalidLengthException;

import java.util.Objects;

public abstract class Empleado {

    // Atributos
    private String nombre;
    private int DNI; // unico y no modificable.
    private char genero; // (m)ujer, (h)ombre u (o)tros
    private int salario;
    private boolean activo; // significa si está trabajando en este momento.
    private int antiguedad; // años de trabajo.

    
    public Empleado()
    {
    	 this.nombre = "";
         this.DNI = 0;
         this.genero = 'h';
         salario = 0;
         activo = false;
         antiguedad = 0;
    }
    /**
     * Constructor de empleado general como si fuera un nuevo empleado
     *
     * @param nombre Nombre del empleado.
     * @param DNI DNI único del empleado.
     * @param genero Género del empleado.
     */
    public Empleado(String nombre, int DNI, char genero){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = Character.toLowerCase(genero);
        salario = 0;
        activo = false;
        antiguedad = 0;
    }


    /**
     * Constructor de empleado general para cuando lo ingresemos por JSON
     *
     * @param nombre Nombre del empleado.
     * @param DNI DNI unico del empleado.
     * @param genero Género del empleado.
     * @param salario Salario del empleado.
     * @param activo Si esta trabajando o no.
     * @param antiguedad Cuantos años lleva con nosotros el empleado.
     */
    public Empleado(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        this.nombre = nombre;
        this.DNI = DNI;
        this.genero = Character.toLowerCase(genero);
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
    
    protected void setDNI(int dNI) {
		DNI = dNI;
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
        return  " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Genero: " + getGenero() +
                ", Salario: " + getSalario() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad();
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

    // Verificaciones
    public boolean verificarNombre(String verificar){
        try{
            if (verificar.length() > 12)
                throw new InvalidLengthException("\u001B[31mEl nombre excede el limite de caracteres (12).\u001B[0m");
            if (verificar.length() < 3)
            throw new InvalidLengthException("\u001B[31mEl nombre es demasiado corto, debe superar los 3 caracteres.\u001B[0m");

        }catch(InvalidLengthException ile){
            ile.getMessage();
        }
        return true;
    }

    public boolean verificarGenero(char verificar){
        try{
            if (verificar != 'h' && verificar != 'm' && verificar != 'o')
                throw new IllegalArgumentException("\u001B[31mEl valor ingresado no coincide a una de las opciones posibles.\u001B[0m");
        } catch (IllegalArgumentException iae) {
            System.out.println(iae.getMessage());
        }

        return true;
    }

    public boolean verificarDNI(){
        try{
            if (getDNI() >= 60000000 && getDNI() <= 69999999)
                throw new InvalidDNIException("\u001B[31mEl numero del DNI coincide con los reservados para CUIT y CUIL extranjero.\u001B[0m");
            if (getDNI() < 10000000)
                throw new InvalidDNIException("\u001B[31mEl numero del DNI es demasiado pequeño como para ser de una edad valida.\u001B[0m");
        }catch (InvalidDNIException idnie){
            System.out.println(idnie.getMessage());
        }

        return true;
    }
}