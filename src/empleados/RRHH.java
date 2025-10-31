package empleados;

public class RRHH extends Administrativo {

    public RRHH(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
    }

    public RRHH(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario, activo, antiguedad, correo, contrasenia);
    }

    @Override
    public String toString() {
        return super.toString(); // con super toString llamas al toString de empleados, que devuelven su dni,nombre,etc
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
