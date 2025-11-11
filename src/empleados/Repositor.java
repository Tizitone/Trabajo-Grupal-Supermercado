package empleados;



import org.json.JSONArray;
import org.json.JSONObject;

import almacenamiento.Almacenamiento;
import almacenamiento.Estanteria;
import almacenamiento.Mostrador;
import interfaces.IRendimiento;
import interfaces.ISalario;

public class Repositor extends Personal implements ISalario, IRendimiento{

	public Repositor(){
        super();
    }

	
    public Repositor(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
    }

    public Repositor(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        super(nombre, DNI, genero, salario, activo, antiguedad);
    }

    public boolean contarStock(){
        setProductividad(getProductividad() + 1 );
        return true;
    }
    
    public String listarEstantes(Almacenamiento a)
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(Estanteria e : a.getEstanterias())
    	{
    		sb.append(e.toString()).append("\n");
    	}
    	return sb.toString();
    }

    //metodo para agregar articulos(productos) al mostrador
    public boolean reponerProducto(Mostrador mostrador,Estanteria estanteria,String id, int cant){
        boolean exito;

        exito = mostrador.agregarArticulos(estanteria.venderProductos(id,cant));// mostrador devuelve un booleano si se pudo agregar, y venderProductos devuelve un producto si todavia hay la cantidad que se solicita
        setProductividad(getProductividad() + 1 );
        return exito;
    }

    @Override
    public String toString() {
        return "Repositor ["+super.toString()+"]";
    }

    /**
     * Calcula el salario que recibe este empleado según su rendimiento.
     * @return {@code int} representando el sueldo del empleado.
     */
    @Override
    public int calcularSalario() {
        return (int) (getSalario() + calcularRendimiento());
    }

    /**
     * Calcula el rendimiento del empleado para calcular su salario.
     * @return {@code float} representando el rendimiento que tuvo el empleado.
     */
    @Override
    public float calcularRendimiento() {
        return 1000 * getProductividad();
    }

    @Override
    public Integer getIdentificador() {
        return getDNI();
    }

    @Override
	public JSONArray toJsonPersonal() {
		// TODO Auto-generated method stub
    	JSONArray jArray = super.toJsonPersonal();
    	JSONObject jb = jArray.getJSONObject(0);
    	jb.put("tipo", "Repositor");
		return jArray;
    }

}