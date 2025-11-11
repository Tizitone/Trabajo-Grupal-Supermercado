package empleados;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.IGestionable;

public abstract class Personal extends Empleado implements IGestionable<Integer>{

    // Atributos
    private int productividad;
    private String informe;

    public Personal()
    {
    	super();
    	productividad = 0;
        informe = "";
    }
    
    // Constructores
    public Personal(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
        productividad = 0;
        informe = "";
    }

    public Personal(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        super(nombre, DNI, genero, salario, activo, antiguedad);
        productividad = 0;
        informe = "";
    }

    // Getters y Setters
    public int getProductividad() {
        return productividad;
    }

    public String getInforme() {
        return informe;
    }

    public void setProductividad(int productividad) {
        this.productividad = productividad;
    }

    public void modificarInforme(String informe) {
        this.informe = informe;
    }

	public JSONArray toJsonPersonal() {
		JSONArray jArray = new JSONArray();
		JSONObject jb = new JSONObject();
		
		jb.put("nombre", this.getNombre());
		jb.put("dni",this.getDNI());
		jb.put("genero",String.valueOf(this.getGenero()));
		jb.put("salario", this.getSalario());
		jb.put("activo", this.isActivo());
		jb.put("antiguedad", this.getAntiguedad());
		jb.put("productividad",this.getProductividad());
		jb.put("informe", this.getInforme());
		
		jArray.put(jb);
		
		return jArray;
	}
	
	public void toObject(JSONObject jb) {
		this.setNombre(jb.getString("nombre"));
		this.setDNI(jb.getInt("dni"));
		this.setGenero(jb.getString("genero").charAt(0));
		this.setSalario(jb.getInt("salario"));
		this.setActivo(jb.getBoolean("activo"));
		this.setAntiguedad(jb.getInt("antiguedad"));
		this.setProductividad(jb.getInt("productividad"));
		this.modificarInforme(jb.getString("informe"));
	}
   

    // Metodo toString
    @Override
    public String toString() {
        return super.toString()+", Productividad: " + getProductividad() + ", Informe: " + getInforme();
       
    }



}