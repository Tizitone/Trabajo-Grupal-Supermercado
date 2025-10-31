package empleados;

import registros.CV;
import registros.Entrevista;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Secretario extends Administrativo {

    protected static TreeMap<Integer, CV> curriculums;
    protected static ArrayList<Entrevista> entrevistas;

    public Secretario(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
        curriculums = new TreeMap<>();
        entrevistas = new ArrayList<>();
    }

    public Secretario(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, salario , activo, antiguedad, correo, contrasenia);
        curriculums = new TreeMap<>();
        entrevistas = new ArrayList<>();
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
        return  super.toString();
    }

	@Override
	public Integer getIdentificador() {
		// TODO Auto-generated method stub
		return getDNI();
	}
}
