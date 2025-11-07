package empleados;

public class RRHH extends Administrativo {

    /**
     * Construye un nuevo empleado de {@code RRHH} como si fuera un nuevo empleado, pidiendo solo información obligatoria.
     *
     * @param nombre Nombre del empleado.
     * @param DNI DNI del empleado.
     * @param genero Género del empleado.
     * @param correo Correo del empleado.
     * @param contrasenia Contraseña de la cuenta del empleado.
     */
    public RRHH(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
    }

    /**
     * Construye un nuevo empleado de {@code RRHH} como si fuera un empleado ya establecido con toda la información posible.
     *
     * @param nombre Nombre del empleado.
     * @param DNI DNI del empleado.
     * @param genero Género del empleado.
     * @param salario Salario del empleado.
     * @param activo Si el empleado esta trabajando.
     * @param antiguedad Cuantos años lleva trabajando con nosotros el empleado.
     * @param correo Correo del empleado.
     * @param contrasenia Contraseña de la cuenta del empleado.
     */
    public RRHH(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario, activo, antiguedad, correo, contrasenia);
    }

    @Override
    public String toString() {
        return "RRHH[" + super.toString();
    }

    public void darAumento(Empleado empleado, int aumento){
        empleado.setSalario(empleado.getSalario() + empleado.getSalario() * aumento /100);
    }

    @Override
    public Integer getIdentificador() {
        // TODO Auto-generated method stub
        return getDNI();
    }
}