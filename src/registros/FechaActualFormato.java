package registros;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FechaActualFormato {
	
	private final String fecha;
	private final String horario;
	
	
	
	
	public FechaActualFormato() {
		this.fecha = getFechaLocal();
		this.horario = getHorarioLocal();
	}
	
	public  String getFecha() {
		return fecha;
	}

	public  String getHorario() {
		return horario;
	}

	private String getFechaLocal() {
	    return LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	private String getHorarioLocal() {
	    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}
	
	
}
