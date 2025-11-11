package registros;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

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
	
	protected void setFecha(String fecha) {
		this.fecha = fecha;
	}


	protected void setHora(String hora) {
		this.hora = hora;
	}


	protected void setId(UUID id) {
		this.id = id;
	}


	public JSONArray toJson() 
	{
		JSONObject jb = new JSONObject();
		JSONArray jArray = new JSONArray();
		
		jb.put("fecha", getFecha());
		jb.put("hora", getHora());
		jb.put("id", getId().toString());
		
		jArray.put(jb);
		
		return jArray;
	}
	public void toObject(JSONObject jb)
	{
		this.setFecha(jb.getString("fecha"));
		this.setHora(jb.getString("hora"));
		this.setId(UUID.fromString(jb.getString("id")));
	}


	@Override
	public String toString() {
		return "Venta [id=" + id + ", getFechaLocal()=" + fecha + ", getHorarioLocal()=" + hora
				+ "]";
	}
	
	
	
}
