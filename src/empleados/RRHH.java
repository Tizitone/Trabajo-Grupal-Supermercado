package empleados;

import interfaces.ISalario;

public class RRHH extends Administrativo implements ISalario {

    public RRHH(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
    }

    public RRHH(String nombre, int DNI, char genero, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, activo, antiguedad, correo, contrasenia);
    }

	@Override
	public int calcularSalario() {
		// TODO Auto-generated method stub
		return 0;
	}


}
