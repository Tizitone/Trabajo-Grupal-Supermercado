package empleados;

import interfaces.ISalario;

public class RRHH extends Administrativo {

    public RRHH(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
    }

    public RRHH(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario, activo, antiguedad, correo, contrasenia);
    }

    @Override
    public String toString() {
        return "RRHH[ " +
                " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Correo: " + getCorreo() +
                ", Genero: " + getGenero() +
                ", Salario: " + getSalario() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad() +
                ']';
    }

    public void darAumento(Empleado empleado, int aumento){
        empleado.setSalario(getSalario() + getSalario() * aumento /100);
    }
}
