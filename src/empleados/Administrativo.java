package empleados;

import interfaces.ISalario;

public abstract class Administrativo extends Empleado implements ISalario {

    protected String correo;
    protected String contrasenia;

    public Administrativo(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public Administrativo(String nombre, int DNI, char genero, int salario , boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario, activo, antiguedad);
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    // TODO: capaz se podria hacer un metodo recuperarContrasenia() al que se le ingresen
    //  varios datos unicos de la persona y devuelva la contraseña si es correcto

    // TODO: se puede complejizar más el metodo, hice esto mas que nada
    //  para mostrar como funcionaria para ambos empleados administrativos
    public int calcularSalario(){
        return getSalario();
    }

    /**
     * AÑADÍ VERIFICACIÓN YA HECHA DE CORREO.
     */
    // Verificaciones
    public boolean verificarCorreo(String verificar) throws IllegalArgumentException {
        if (!verificar.contains("@gmail.com") || !verificar.contains("@hotmail.com") || !verificar.contains("@yahoo.com"))
            throw new IllegalArgumentException("El valor ingresado no se detecto como una direccion de correo electronico.");

        return true;
    }




}
