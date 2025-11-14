package empleados;

import almacenamiento.Mostrador;
import almacenamiento.Producto;
import interfaces.IRendimiento;
import interfaces.ISalario;
import registros.Venta;
import clientes.Cliente;
import excepciones.InvalidDNIException;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Cajero extends Personal implements ISalario, IRendimiento {

    /*use linkedhashmap para mantener por orden de insercion las ventas, cree tambien el
    arreglo auxVentas para tener una lista de los productos vendidos y una clase venta
    para mantener registrado la fecha actual en la que se realizo la venta
    */
    private static final LinkedHashMap<Venta, ArrayList<Producto>> ventas = new LinkedHashMap<>();
    private static HashSet<Cliente> clientes = new HashSet<>();
    private final ArrayList<Producto>auxVentas = new ArrayList<>();
    private double totalVenta;

    //cada cajero tendra un mostrador asignado, que puede ser equivalente a una tienda
    //por ejemplo tengo 2 tiendas separadas, cada una tendra un mostrador con articulos que
    //se repondran segun correspondan
    private Mostrador mostradorAsignado;

    // TODO: es necesario este constructor?
    public Cajero(){
        super("", 0, 'n');
        this.mostradorAsignado = null;
    }
    public Cajero(Mostrador m){
        super("", 0, 'n');
        this.mostradorAsignado = null;
    }

    /**
     * Construye un nuevo {@code Cajero} como si fuera un nuevo empleado, pidiendo solo información obligatoria.
     *
     * @param mostrador Mostrador en el que trabaja.
     * @param nombre Nombre del empleado.
     * @param DNI DNI del empleado.
     * @param genero Género del empleado.
     */
    public Cajero(Mostrador mostrador,String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
        this.mostradorAsignado = mostrador;
    }

    /**
     * Construye un nuevo {@code Cajero} como si fuera un empleado ya establecido con toda la información posible.
     *
     * @param mostrador Mostrador en donde trabaja.
     * @param nombre Nombre del empleado.
     * @param DNI DNI del empleado.
     * @param genero Género del empleado.
     * @param salario Salario del empleado.
     * @param activo Si el empleado esta trabajando.
     * @param antiguedad Cuantos años lleva trabajando con nosotros el empleado.
     */
    public Cajero(Mostrador mostrador,String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        super(nombre, DNI, genero, salario, activo, antiguedad);
        this.mostradorAsignado = mostrador;
    }

    // getters y setters
    public static LinkedHashMap<Venta, ArrayList<Producto>> getVentas() {
        return ventas;
    }

    // metodos
    //primer metodo para venta
    /**
     * Función que recibe por parámetro un ID y una cantidad, sería como registrar un código de barras
     * y una cantidad añade productos a una lista aux para luego usar la función registrar compras
     *
     * @param id Identificador del producto.
     * @param cant Cantidad a vender.
     * @return {@code true} si no hubo problemas.
     */
    public boolean venderProducto(String id, int cant)
    {
        boolean exito = false;
        exito = mostradorAsignado.venderArticulo(id, cant); //del mostrador asignado, le resta valor a la variable cantEnVenta del producto y si se pudo vender, se agrega al aux
        if(exito)
        {
            auxVentas.add(Mostrador.buscarProducto(id)); //en el aux ventas registra los productos que se van a vender, buscandolos por id
        }

        return exito;
    }
    //segundo metodo para venta
    /**
     * Registra las compras de un cliente y devuelve un string como si fuera un ticket.
     *
     * @return {@code String} que representa el ticket de la venta.
     */
    public String registrarCompras()
    {
        StringBuilder sb = new StringBuilder();
        this.totalVenta = 0;
        ventas.put(new Venta(),auxVentas); //crea una instancia de venta con la fecha actual, y como valor le pasa el arreglo de productos.
        auxVentas.clear(); //limpia el auxiliar una vez que se agrego a las ventas.

        Map.Entry<Venta, ArrayList<Producto>> auxVentas = ventas.entrySet().iterator().next(); // variable de tipo entrymap para obtener la clave y el valor que sean las ultimas en la lista
        
        for(Producto p : auxVentas.getValue())
    	{
    		totalVenta += p.getPrecioUnitario();
    	}
        
        sb.append(auxVentas.getKey()) //obtiene la clave de las ventas
                .append("\n")
                .append(auxVentas.getValue())
                .append(this.totalVenta); // obtiene el valor de las ventas

        return sb.toString();
    }   

    public String listarVentas(){
        return ventas.toString();
    }
    
    public Cliente buscarMiembro(int dni)
    {
    	for(Cliente e : clientes)
    	{
    		if(dni == e.getDNI())
    		{
    			return e;
    		}
    	}
    	return null;
    }
    
    public void atenderMiembro(Cliente cliente){
        try{
            if(buscarMiembro(cliente.getDNI())==null) throw new InvalidDNIException("No se ha encontrado al cliente");
    		
            cliente.setConsumosTotales(cliente.getConsumosTotales()+(totalVenta * (1-cliente.getDescuento())));

            setProductividad(getProductividad() + 1 );
        } catch (InvalidDNIException idnie) {
            idnie.getMessage();
        }
    }

    public void atenderCliente(){
        setProductividad(getProductividad() + 1 );
    }

    public void validarCliente(Cliente cliente){
        if (cliente.getMembresia() != null) atenderMiembro(cliente);

        else if (preguntarACliente()) agregarCliente(cliente);

        else atenderCliente();
    }

    public boolean preguntarACliente(){
        Random pregunta = new Random();
        return pregunta.nextBoolean();
    }

    // TODO: Modificar cuando tengamos mejor la clase gestora.
    public boolean agregarCliente(Cliente cliente){ 	
        return clientes.add(cliente);
    }


	@Override
	public String toString() {
		return "Cajero[ "+super.toString()+",mostradorAsignado=" + mostradorAsignado + "]";
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
    	jb.put("tipo", "Cajero");
		return jArray;
	}

	@Override
	public void toObject(JSONObject jb) {
		// TODO Auto-generated method stub
		super.toObject(jb);
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

}
