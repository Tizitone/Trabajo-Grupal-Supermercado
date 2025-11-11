package clientes;

import org.json.JSONArray;
import org.json.JSONObject;

import enumerators.Membresia;

public class Cliente {

    private String nombre;
    private int DNI;
    private double consumosTotales;
    private TarjetaMembresia membresia;

    public Cliente(){
        nombre = "";
        this.DNI = 0;
        consumosTotales = 0;
        this.membresia = null;
    }
    
    public Cliente(String nombre, int DNI, TarjetaMembresia membresia){
        this.nombre = nombre;
        this.DNI = DNI;
        this.membresia = membresia;
    }

    public Cliente(int dni ,TarjetaMembresia membresia){
        nombre = "";
        this.DNI = dni;
        consumosTotales = 0;
        this.membresia = membresia;
    }

    public String getNombre() {
        return nombre;
    }

    public double getConsumosTotales() {
        return consumosTotales;
    }

    public int getDNI() {
        return DNI;
    }

    public TarjetaMembresia getMembresia() {
        return membresia;
    }

    public double getDescuento(){
        return membresia.membresia.getDescuento();
    }
    
    
    
    protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected void setDNI(int dNI) {
		DNI = dNI;
	}

	public void setConsumosTotales(double consumosTotales) {
		this.consumosTotales = consumosTotales;
	}

	protected void setMembresia(TarjetaMembresia membresia) {
		this.membresia = membresia;
	}

	public JSONArray toJson()
    {
    	JSONArray jArray = new JSONArray();
    	JSONArray jArrayTarjeta = new JSONArray();
    	JSONObject jb = new JSONObject();
    	JSONObject jbTarjeta = new JSONObject();
    	
    	jb.put("nombre", getNombre());
    	jb.put("consumosTotales", getConsumosTotales());
    	jb.put("dni", getDNI());
    	
    	jbTarjeta.put("codigo", getMembresia().codigo.toString());
    	jbTarjeta.put("membresia", getMembresia().membresia.name());
    	
    	jb.put("TarjetaMembresia", jArrayTarjeta);
    	
    	jArray.put(jb);
    	
    	return jArray;
    }
    public void toObject(JSONObject jb)
    {
    	setNombre(jb.getString("nombre"));
    	setConsumosTotales(jb.getDouble("consumosTotales"));
    	setDNI(jb.getInt("dni"));
    	JSONArray jArray = jb.getJSONArray("TarjetaMembresia");
    	for(int i = 0; i<jArray.length();i++)
    	{
    		JSONObject jbTarjeta = jArray.getJSONObject(0);
    		
    		String codigo = jbTarjeta.getString("codigo");
    		TarjetaMembresia t = new TarjetaMembresia();
    		t.setCodigo(codigo);
    		t.setMembresia(Membresia.valueOf(jbTarjeta.getString("membresia")));
    		setMembresia(t);
    	}
    	
    }
}


