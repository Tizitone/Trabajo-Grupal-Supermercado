package empleados;

import java.util.LinkedHashMap;
import java.util.Map;

import registros.Entrevista;

public class RRHH extends Administrativo {

	private LinkedHashMap<Integer,Entrevista> entrevistasAgendadas;

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
        this.entrevistasAgendadas = new LinkedHashMap<>();
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
        this.entrevistasAgendadas = new LinkedHashMap<>();
    }
    public void asignarEntrevistas() {
        // simplemente apunta a la misma lista
        if (Secretario.entrevistas != null) {
            this.entrevistasAgendadas = new LinkedHashMap<>();
            for (Entrevista e : Secretario.entrevistas) {
                this.entrevistasAgendadas.put(e.getId(), e);
            }
        } else {
            System.out.println("No hay entrevistas registradas");
        }
    }
    public String listarEntrevistas()
    {
    	StringBuilder sb = new StringBuilder();

	    	for(Map.Entry<Integer, Entrevista> entry : entrevistasAgendadas.entrySet())
	    	{
	    		sb.append(entry.getValue().toString());
	    	}
    	
    	return sb.toString();
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