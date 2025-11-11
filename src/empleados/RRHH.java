package empleados;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.ISalario;
import registros.Entrevista;

public class RRHH extends Administrativo implements ISalario {

	private LinkedHashMap<Integer,Entrevista> entrevistasAgendadas;

	
	public RRHH(){
        super();
        this.entrevistasAgendadas = new LinkedHashMap<>();
    }
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

    /**
     * Agenda todas las entrevistas no agendadas que posea el arraylist estatico de los secretarios en el LinkedHashMap de este empleado.
     *
     */
    public void asignarEntrevistas() {

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
    public String listarEntrevistasPendientes()
    {
    	StringBuilder sb = new StringBuilder();

	    	for(Map.Entry<Integer, Entrevista> entry : entrevistasAgendadas.entrySet())
	    	{
	    		if(entry.getValue().getDia() >= LocalDate.now().getDayOfMonth()
	    				&& entry.getValue().getMes() >= LocalDate.now().getMonthValue()
	    				&& entry.getValue().getAnio() >= LocalDate.now().getYear())
	    		{
	    			sb.append(entry.getValue().getFecha().toString());
	    		}
	    		
	    	}
    	
    	return sb.toString();
    }
    @Override
    public String toString() {
        return "RRHH[" + super.toString();
    }

    /**
     * Aumenta el salario de un empleado sobre un porcentaje de su salario actual.
     *
     * @param empleado a quien se le da el aumento.
     * @param aumento porcentaje de aumento de salario.
     */
    public void darAumentoPorcentaje(Empleado empleado, int aumento){
        empleado.setSalario(empleado.getSalario() + (empleado.getSalario() /100 * aumento));
    }

    /**
     * Aumenta el salario de un empleado sobre un monto fijo.
     *
     * @param empleado a quien se le da el aumento
     * @param aumento aumento a salario en pesos.
     */
    public void darAumento(Empleado empleado, int aumento){
        empleado.setSalario(empleado.getSalario() + aumento);
    }
    
    @Override
	public JSONArray toJsonAdministrativo() {
		// TODO Auto-generated method stub
    	JSONArray jArray = super.toJsonAdministrativo();
    	JSONObject jb = jArray.getJSONObject(0);
    	jb.put("tipo", "RRHH");
		return jArray;
	}

    @Override
    public Integer getIdentificador() {
        // TODO Auto-generated method stub
        return getDNI();
    }
}