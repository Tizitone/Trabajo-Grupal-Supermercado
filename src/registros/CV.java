package registros;

import org.json.JSONArray;
import org.json.JSONObject;

import excepciones.InvalidAgeException;
import excepciones.InvalidDNIException;
import excepciones.InvalidLengthException;

public class CV implements Comparable<CV> {

    // Atributos
    protected String nombre;
    protected String apellido;
    protected int edad,dni;
    protected String correo, telefono;
    protected char genero;
    protected static int Au_ID;
    private int contador;

    // Constructor
    public CV(String nombre, String apellido,int dni, int edad, String telefono, String correo, char genero) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.genero = Character.toLowerCase(genero);
        Au_ID++;
        this.contador = Au_ID;
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

    public String getTelefono() {
        return telefono;
    }

    public char getGenero() {
        return genero;
    }

    public String getCorreo() {
        return correo;
    }

    public int getDni() {
		return dni;
	}

	protected void setDni(int dni) {
		this.dni = dni;
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

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    

    public int getContador() {
		return contador;
	}
    
    public JSONArray toJson()
    {
    	JSONArray jArray = new JSONArray();
    	JSONObject jb = new JSONObject();
    	
    	jb.put("nombre", getNombre());
    	jb.put("apellido", getApellido());
    	jb.put("edad", getEdad());
    	jb.put("dni", getDni());
    	jb.put("correo", getCorreo());
    	jb.put("genero", String.valueOf(getGenero()).charAt(0));
    	jb.put("telefono", getTelefono());
    	
    	jArray.put(jb);
    	
    	return jArray;
    }
    
    public void toObject(JSONObject jb)
    {
    	setNombre(jb.getString("nombre"));
    	setApellido(jb.getString("apellido"));
    	setEdad(jb.getInt("edad"));
    	setDni(jb.getInt("dni"));
    	setCorreo(jb.getString("correo"));
    	setGenero((char)jb.getString("genero").charAt(0));
    	setTelefono(jb.getString("telefono"));
    }

	// Verificaciones
    public boolean verificarNombreYApellido(String verificar) throws InvalidLengthException {
        if (verificar.length() > 12)
            throw new InvalidLengthException("El nombre excede el limite de caracteres (12).");

        return true;
    }
    
    public boolean verificarDNI(){
        try{
            if (getDni() >= 60000000 && getDni() <= 69999999)
                throw new InvalidDNIException("\u001B[31mEl numero del DNI coincide con los reservados para CUIT y CUIL extranjero.\u001B[0m");
            if (getDni() < 10000000)
                throw new InvalidDNIException("\u001B[31mEl numero del DNI es demasiado pequeño como para ser de una edad valida.\u001B[0m");
        }catch (InvalidDNIException idnie){
            System.out.println(idnie.getMessage());
        }

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
        return contador - o.getContador();
    }

    @Override
    public String toString() {
        return "| Curriculum Vitae |\n" +
                nombre + " " + apellido +
                "\n" + edad + " años" +
                "\nGenero: " + genero +
                "\ntelefono: " + telefono +
                "\ncorreo: " + correo;
    }
}
