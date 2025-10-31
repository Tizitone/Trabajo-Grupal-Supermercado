package registros;

import java.time.DateTimeException;
import java.time.LocalDate;

import excepciones.InvalidDateException;

public class Entrevista {

    protected String fecha;
    protected String horario;
    protected int dia,mes,anio;
    protected int hora, minuto;
    protected CV curriculum;
    protected String informe;

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
    		LocalDate.of(anio, mes, dia);
    	}catch(DateTimeException  o)
    	{
    		throw new InvalidDateException("La fecha ingresada no existe realmente.");
    	}
    	
    	sb.append(dia).append("/").append(mes).append("/").append(anio);
    	
    	return sb.toString();
    }
    public String horaToString(int hora, int minuto) throws InvalidDateException {
    	
    	StringBuilder sb = new StringBuilder();
        if (hora < 0 || hora > 23)
            throw new InvalidDateException("La hora ingresada no es válida (debe estar entre 0 y 23)");
        if (minuto < 0 || minuto > 59)
            throw new InvalidDateException("El minuto ingresado no es válido (debe estar entre 0 y 59)");
        sb.append(hora).append(":").append(minuto);
        
        return sb.toString();
    }
    @Override
    public String toString() {
        return "Entrevista dada el " + fecha + " a las " + horario +
               "\n" + curriculum.toString() +
               "\nInforme: " + informe + "\n";
    }
}
