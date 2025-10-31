package registros;

import java.util.UUID;

public class Venta {

	private String fecha, hora;
	private UUID id;
	
	public Venta() {
		FechaActualFormato f = new FechaActualFormato();
		this.fecha = f.getFecha();
		this.hora = f.getHorario();
		this.id = UUID.randomUUID();
	}
	

	public String getFecha() {
		return fecha;
	}


	public String getHora() {
		return hora;
	}


	public UUID getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Venta [id=" + id + ", getFechaLocal()=" + fecha + ", getHorarioLocal()=" + hora
				+ "]";
	}
	
	
	
}
