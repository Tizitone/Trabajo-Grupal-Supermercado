package registros;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.json.JSONArray;
import org.json.JSONObject;

import excepciones.InvalidDateException;

public class Entrevista {

    protected String fecha;
    protected String horario;
    protected int dia,mes,anio;
    protected int hora, minuto;
    protected CV curriculum;
    protected String informe;
    protected int id;
    protected static int AU_id=0;

    public Entrevista(int dia, int mes, int anio, int hora, int minuto, CV curriculum, String informe) throws InvalidDateException{
        this.fecha = fechaToString(dia,mes,anio);
        this.horario = horaToString(hora, minuto);
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
        this.curriculum = curriculum;
        this.informe = informe;
        this.id = AU_id;
        Entrevista.AU_id++;
    }  
    
    public int getId() {
		return id;
	}

	public int getDia() {
		return dia;
	}
    
	public int getMes() {
		return mes;
	}

	public int getAnio() {
		return anio;
	}
	
	public String getHorario() {
		return horario;
	}


	public int getHora() {
		return hora;
	}


	public int getMinuto() {
		return minuto;
	}


	public String getFecha() {
        return fecha;
    }

    public CV getCurriculum() {
        return curriculum;
    }

    public String getInforme() {
        return informe;
    }

    public void setCurriculum(CV curriculum) {
        this.curriculum = curriculum;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }
    
    
    
    public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public JSONArray toJson()
    {
    	JSONObject jb = new JSONObject();
    	JSONObject jbCv = new JSONObject();
    	JSONArray jarrayCv = new JSONArray();
    	JSONArray jarray = new JSONArray();
    	
    	jb.append("dia", getDia());
    	jb.append("mes", getMes());
    	jb.append("anio", getAnio());
    	jb.append("hora", getHora());
    	jb.append("minuto", getMinuto());
    	
    	jbCv.append("nombre", getCurriculum().nombre);
    	jbCv.append("apellido", getCurriculum().apellido);
    	jbCv.append("edad", getCurriculum().edad);
    	jbCv.append("telefono", getCurriculum().telefono);
    	jbCv.append("correo", getCurriculum().correo);
    	jarrayCv.put(jbCv);
    	
    	jb.append("Cv", jarrayCv);
    	
    	jarray.put(jb);
    	
    	return jarray;
    }
    public void toObject(JSONObject jb)
    {
    	JSONArray jArray = jb.getJSONArray("Cv");
    	this.setDia(jb.getInt("dia"));
    	this.setMes(jb.getInt("mes"));
    	this.setAnio(jb.getInt("anio"));
    	this.setHora(jb.getInt("hora"));
    	this.setMinuto(jb.getInt("minuto"));
    	
    }
    
    public String fechaToString(int dia,int mes,int anio) throws InvalidDateException
    {
    	StringBuilder sb = new StringBuilder();
    	if(dia>31 || dia<0)
    	{
    		throw new InvalidDateException("El numero ingresado para el dia no es valido o esta fuera de rango");
    	}
    	if(mes>12 || mes<0)
    	{
    		throw new InvalidDateException("El numero ingresado para el mes no es valido o esta fuera de rango");
    	}
    	if(anio<LocalDate.now().getYear())
    	{
    		throw new InvalidDateException("El numero ingresado para el año no es valido o esta fuera de rango");
    	}
    	try
    	{
    		sb.append(LocalDate.of(anio, mes, dia).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    	}catch(DateTimeException  o)
    	{
    		throw new InvalidDateException("La fecha ingresada no existe realmente.");
    	}
    	
    	return sb.toString();
    }
    public String horaToString(int hora, int minuto) throws InvalidDateException {
    	
    	StringBuilder sb = new StringBuilder();
        if (hora < 0 || hora > 23)
            throw new InvalidDateException("La hora ingresada no es válida (debe estar entre 0 y 23)");
        if (minuto < 0 || minuto > 59)
            throw new InvalidDateException("El minuto ingresado no es válido (debe estar entre 0 y 59)");
        sb.append(String.format("%02d", hora)).append(":").append(String.format("%02d", minuto));
        
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Entrevista dada el " + getFecha() + " a las " + getHorario() +
               "\n" + curriculum.toString() +
               "\nInforme: " + informe + "\n";
    }
}
