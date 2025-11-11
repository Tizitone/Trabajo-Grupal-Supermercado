package empleados;

import registros.CV;
import registros.Entrevista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class Secretario extends Administrativo {

    protected static TreeMap<Integer, CV> curriculums = new TreeMap<>();;
    protected static ArrayList<Entrevista> entrevistas = new ArrayList<>();

    public Secretario(){
        super();
    }
    /**
     * Construye un nuevo {@code Secretario} como si fuera un nuevo empleado, pidiendo solo información obligatoria.
     *
     * @param nombre Nombre del empleado.
     * @param DNI DNI del empleado.
     * @param genero Género del empleado.
     * @param correo Correo del empleado.
     * @param contrasenia Contraseña de la cuenta del empleado.
     */
    public Secretario(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
    }

    /**
     * Construye un nuevo {@code Secretario} como si fuera un empleado ya establecido con toda la información posible.
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
    public Secretario(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario , activo, antiguedad, correo, contrasenia);
    }
   
    public static ArrayList<Entrevista> getEntrevistas() {
        return entrevistas;
    }

    public static TreeMap<Integer, CV> getCurriculums() {
        return curriculums;
    }
    
    public boolean agendarEntrevista(Entrevista e)
    {
        LocalDateTime hoy = LocalDateTime.now(); // obtiene la fecha y hora de hoy
        LocalDateTime fechaHoraEntrevista = LocalDateTime.of(e.getAnio(), e.getMes(), e.getDia(), e.getHora(), e.getMinuto()); // guarda la fecha y hora de la entrevista ingresada, y la guarda en una variable tipo localDateTime
        if(fechaHoraEntrevista.isBefore(hoy)) return false; // si la fecha y hora ya paso, entonces devuelve false
        //verifica que la fecha que contenga la entrevista no sea identica a alguna ingresada
        for(Entrevista entry : entrevistas) {
            LocalDateTime fechaHoraExistente = LocalDateTime.of( // guarda la fecha y hora de cada una de las entrevistas ya guardadas en una variable
                    entry.getAnio(), entry.getMes(), entry.getDia(),
                    entry.getHora(), entry.getMinuto()
            );
            if(fechaHoraExistente.equals(fechaHoraEntrevista)) { //compara la fecha de las entrevistas ingresadas, con la que quiere ingresar, si es igual a alguna corta el metodo
                return false;
            }
        }
        entrevistas.add(e); // si todo fue bien, agrega la entrevista al arraylist y devuelve true;
        return true;

    }
    public boolean darDeBajaEntrevista(int dia,int mes,int anio, int hora, int minuto)
    {
        LocalDateTime fechaHoraEntrevista = LocalDateTime.of(anio,mes,dia,hora,minuto);
        for(Entrevista entry : entrevistas) {
            LocalDateTime fechaHoraExistente = LocalDateTime.of( // guarda la fecha y hora de cada una de las entrevistas ya guardadas en una variable
                    entry.getAnio(), entry.getMes(), entry.getDia(),
                    entry.getHora(), entry.getMinuto()
            );
            if(fechaHoraExistente.equals(fechaHoraEntrevista)) { //compara la fecha de las entrevistas ingresadas, con la que quiere ingresar, si es igual a alguna corta el metodo
                return entrevistas.remove(entry);
            }
        }
        return false;
    }

    public boolean recibirCV(CV curri){
        curriculums.put(curri.getContador(), curri);
        return true;
    }

    public boolean descartarCV(CV curri){
        return curriculums.remove(curri.getContador(), curri);
    }
    public CV buscarCv(int dni)
    {
    	for(Map.Entry<Integer, CV> entry : curriculums.entrySet())
    	{
    		if(entry.getValue().getDni() == dni)
    		{
    			return entry.getValue();
    		}
    	}
    	
    	return null;
    }

    public String listarEntrevistas(){
        StringBuilder lista = new StringBuilder();

        for (Entrevista a : entrevistas){
            lista.append(a.toString()).append("\n");
        }
        return lista.toString();
    }
    public String listarCV(){
        StringBuilder lista = new StringBuilder();

        for (Map.Entry<Integer, CV> entry : curriculums.entrySet()){
            lista.append(entry.getValue().toString()).append("\n");
        }
        return lista.toString();
    }

    @Override
    public String toString() {
        return "Secretario[" + super.toString();
    }
    
    @Override
	public JSONArray toJsonAdministrativo() {
		// TODO Auto-generated method stub
    	JSONArray jArray = super.toJsonAdministrativo();
    	JSONObject jb = jArray.getJSONObject(0);
    	jb.put("tipo", "Secretario");
		return jArray;
	}

    @Override
    public Integer getIdentificador() {
        // TODO Auto-generated method stub
        return getDNI();
    }
}