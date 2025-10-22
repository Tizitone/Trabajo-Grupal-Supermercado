package empleados;

public abstract class Administrativo extends Empleado{

    protected String correo;
    protected String contrasenia;

    public Administrativo(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Administrativo(String nombre, int DNI, char genero, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, activo, antiguedad);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }






}
