package empleados;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.IGestionable;
import interfaces.ISalario;

public abstract class Administrativo extends Empleado implements ISalario, IGestionable <Integer>{

    protected String correo;
    protected String contrasenia;

    public Administrativo(){
        super();
        this.correo = "";
        this.contrasenia = "";
    }
    
    public Administrativo(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Administrativo(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario, activo, antiguedad);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    // getters y setters
    public String getCorreo() {
        return correo;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    protected void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    protected void setCorreo(String correo) {
		this.correo = correo;
	}

	// metodo de moficacion de direccion de correo con verificacion de correo y contraseña
    public void setNuevoCorreo(String nuevoCorreo, String contrasenia) {
        if (contrasenia.equals(this.contrasenia) && verificarCorreo(correo)) this.correo = nuevoCorreo;
    }
    
    public JSONArray toJsonAdministrativo() {
		JSONArray jArray = new JSONArray();
		JSONObject jb = new JSONObject();
		jb.put("nombre", this.getNombre());
		jb.put("dni",this.getDNI());
		jb.put("genero",String.valueOf(this.getGenero()));
		jb.put("salario", this.getSalario());
		jb.put("activo", this.isActivo());
		jb.put("antiguedad", this.getAntiguedad());
		jb.put("correo", this.getCorreo());
		jb.put("contrasenia", this.getContrasenia());
		
		jArray.put(jb);
		return jArray;
	}
    public void toObject(JSONObject jb) {
		// TODO Auto-generated method stub
		this.setNombre(jb.getString("nombre"));
		this.setDNI(jb.getInt("dni"));
		this.setGenero(jb.getString("genero").charAt(0));
		this.setSalario(jb.getInt("salario"));
		this.setActivo(jb.getBoolean("activo"));
		this.setAntiguedad(jb.getInt("antiguedad"));
		this.setCorreo(jb.getString("correo"));
		this.setContrasenia(jb.getString("contrasenia"));
	}
    /**
     * Retorna el sueldo del empleado.
     * @return {@code int } que representa el salario del empleado.
     */
    @Override
    public int calcularSalario(){
        return getSalario();
    }

    /**
     * Si la contraseña dada en el primer parametro coincide con la del empleado,
     * entonces la cambia por la contraseña dada en el segundo parametro.
     *
     * @param antiguaContrasenia contraseña que debe ser la misma del empleado.
     * @param nuevaContrasenia nueva contraseña del empleado.
     */
    public void cambiarContrasenia(String antiguaContrasenia, String nuevaContrasenia){
        try{
            if (getContrasenia().equals(antiguaContrasenia))
                setContrasenia(nuevaContrasenia);
            else throw new IllegalArgumentException("\u001B[31mLa contraseña ingresada no es correcta.\u001B[0m"); // Está bien usar esta excepcion o deberiamos crear una personalizada.
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    /**
     * Si el correo y DNI proporcionados por parametro coinciden con los del
     * empleado reemplaza la contraseña con la nueva dada por parametro
     *
     * @param correo correo que debe coincidir con el del empleado.
     * @param DNI DNI que debe coincidir con el del empleado.
     * @param nuevaContrasenia Contraseña a cambiar por la anterior.
     */
    public void recuperarContrasenia(String correo, int DNI, String nuevaContrasenia){
        try{
            if (getCorreo().equals(correo) && getDNI() == DNI){
                setContrasenia(nuevaContrasenia);
            }else{
                throw new IllegalArgumentException("\u001B[31mEl correo o DNI ingresado no es correcto.\u001B[0m");
            }
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }
    }

    // Verificaciones
    public boolean verificarCorreo(String verificar){
        try{
            if (!verificar.contains("@gmail.com") || !verificar.contains("@hotmail.com") || !verificar.contains("@yahoo.com"))
                throw new IllegalArgumentException("\u001B[31mEl valor ingresado no se detecto como una direccion de correo electronico.\u001B[0m");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }

        return true;
    }

    public boolean verificarContrasenia(String verificar){
        try{
            if (verificar.length() < 8)
                throw new IllegalArgumentException("\u001B[31mLa Contraseña es demasiado corta, debe superar los 7 caracteres\u001B[0m");
        }catch (IllegalArgumentException iae){
            System.out.println(iae.getMessage());
        }

        return true;
    }
    
    

    // Metodo toString
    @Override
    public String toString() {
        return super.toString() +
                ", Correo:" + getCorreo() + " ]";
    }
}