package registros;

import excepciones.InvalidAgeException;
import excepciones.InvalidLengthException;

public class CV implements Comparable<CV> {

    // Atributos
    protected String nombre;
    protected String apellido;
    protected int edad;
    protected long telefono;
    protected String correo;
    protected char genero;
    protected static int Au_ID;
    private int contador;

    // Constructor
    public CV(String nombre, String apellido, int edad, long telefono, String correo, char genero) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = Character.toLowerCase(genero);
        Au_ID = contador;
        contador++;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }

    public long getTelefono() {
        return telefono;
    }

    public char getGenero() {
        return genero;
    }

    public String getCorreo() {
        return correo;
    }

    public int getAu_ID() {
        return Au_ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    // Verificaciones
    public boolean verificarNombreYApellido(String verificar) throws InvalidLengthException {
        if (verificar.length() > 12)
            throw new InvalidLengthException("El nombre excede el limite de caracteres (12).");

        return true;
    }

    public boolean verificarEdad(int verificar) throws InvalidAgeException{
        if (verificar < 1 || verificar > 118)
            throw new InvalidAgeException("La edad ingresada representa un valor imposible.");

        return true;
    }

    public boolean verificarTelefono(long telefono) throws InvalidLengthException {
        String verificar = Long.toString(telefono);

        if (verificar.length() < 10 || verificar.length() > 15)
            throw new InvalidLengthException("La cantidad de caracteres en su número telefonico es invalida.");

        return true;
    }

    public boolean verificarCorreo(String verificar) throws IllegalArgumentException {
        if (!verificar.contains("@gmail.com") || !verificar.contains("@hotmail.com") || !verificar.contains("@yahoo.com"))
            throw new IllegalArgumentException("El valor ingresado no se detecto como una direccion de correo electronico.");

        return true;
    }

    public boolean verificarGenero(char verificar) throws IllegalArgumentException{
        if (verificar != 'h' && verificar != 'm' && verificar != 'o')
            throw new IllegalArgumentException("El valor ingresado no coincide a una de las opciones posibles");

        return true;
    }

    @Override
    public int compareTo(CV o) {
        return Au_ID - o.getAu_ID();
    }

    @Override
    public String toString() {
        return "| Curriculum Vitae |\n" +
                nombre + " " + apellido +
                "\n" + edad + " años" +
                "Genero: " + genero +
                "\ntelefono: " + telefono +
                "\ncorreo: " + correo;
    }
}
