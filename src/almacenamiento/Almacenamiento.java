package almacenamiento;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.IGestionable;

public class Almacenamiento implements IGestionable<String>{
    private  UUID id;
    private ArrayList<Estanteria>estanterias;
    private int capacidadEstanterias;

    public Almacenamiento() {
        this.id = UUID.randomUUID();
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = 0;
    }

    public Almacenamiento(int capacidadEstanterias) {
        this.id = UUID.randomUUID();
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = capacidadEstanterias;
    }

    public UUID getId() {
        return id;
    }
    
    public void setId(UUID id) {
		this.id = id;
	}
	public ArrayList<Estanteria> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(ArrayList<Estanteria> estanterias) {
        this.estanterias = estanterias;
    }

    public int getCapacidadEstanterias() {
        return capacidadEstanterias;
    }

    public void setCapacidadEstanterias(int capacidadEstanterias) {
        this.capacidadEstanterias = capacidadEstanterias;
    }
    public boolean agregarEstanteria(Estanteria e)
    {
        boolean exito = false;

        if(capacidadEstanterias>0)
        {
        	capacidadEstanterias--;
            exito = estanterias.add(e);
        }
        return exito;
    }
    
    public String listarEstanterias()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(Estanteria e : estanterias)
    	{
    		sb.append(e.toString()).append("\n");
    	}
    	
    	return sb.toString();
    }
    public Estanteria buscarEstanteriaPorID(String id)
    {
    	UUID uuidbuscado = UUID.fromString(id);
    	
    	for(Estanteria e : estanterias)
    	{
    		if(e.getId().equals(uuidbuscado))
    		{
    			return e;
    		}
    	}
    	
    	return null;
    }
    public boolean removerEstanteria(String id)
    {

        for(Estanteria a : estanterias)
        {
            if(id.equals(a.getId().toString()))
             {
                return estanterias.remove(a);
             }
        }
       return false;
    }
    public JSONObject toJson()
    {
    	JSONObject jb = new JSONObject();
        JSONArray jEstanteria = new JSONArray();
        
        jb.put("tipo", "Almacenamiento");
        jb.put("id", getId().toString());
        jb.put("capacidad", getCapacidadEstanterias());
        
        for(Estanteria e : estanterias)
        {
        	jEstanteria.put(e.toJson()); 
        	
        }
        jb.put("estanterias", jEstanteria);
        
    	return jb;
    }
    public void toObject(JSONObject jb)
    {
    	this.setId(UUID.fromString(jb.getString("id")));
    	this.setCapacidadEstanterias(jb.getInt("capacidad"));
    	
    	 this.estanterias = new ArrayList<>();
    	
    	JSONArray jEstanterias = jb.getJSONArray("estanterias");
    	for(int i = 0 ; i<jEstanterias.length() ; i++)
    	{
    		Estanteria e = new Estanteria();
    		JSONObject jbObjEstanteria = jEstanterias.getJSONObject(i);
    		e.toObject(jbObjEstanteria);
    		this.estanterias.add(e);
    	}	
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Almacenamiento that = (Almacenamiento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Almacenamiento{" +
                "id=" + id +
                ", capacidadEstanterias=" + capacidadEstanterias +
                ",\n estanterias= "+estanterias+
                '}';
    }
	@Override
	public String getIdentificador() {
		return getId().toString();
	}
}
