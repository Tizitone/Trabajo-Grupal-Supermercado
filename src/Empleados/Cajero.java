package Empleados;

import Almacenamiento.Mostrador;
import Almacenamiento.Producto;
import Almacenamiento.Venta;
import Externos.Cliente;

import java.util.*;

public class Cajero extends Personal {

    /*use linkedhashmap para mantener por orden de insercion las ventas, cree tambien el
    arreglo auxVentas para tener una lista de los productos vendidos y una clase venta
    para mantener registrado la fecha actual en la que se realizo la venta
    */
    private static LinkedHashMap<Venta, ArrayList<Producto>> ventas = new LinkedHashMap<>();
    private ArrayList<Producto>auxVentas = new ArrayList<>();

    //cada cajero tendra un mostrador asignado, que puede ser equivalente a una tienda
    //por ejemplo tengo 2 tiendas separadas, cada una tendra un mostrador con articulos que
    //se repondran segun correspondan
    private Mostrador mostradorAsignado;

    public Cajero(Mostrador mostrador,String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
        this.mostradorAsignado = mostrador;
    }

    public Cajero(Mostrador mostrador,String nombre, int DNI, char genero, boolean activo, int antiguedad){
        super(nombre, DNI, genero, activo, antiguedad);
        this.mostradorAsignado = mostrador;
    }

    //funcion que recibira por parametro una id y una cantidad, seria como registrar un codigo de barras y una cantidad
    public boolean venderProducto(String id, int cant)
    {
        boolean exito = false;
        exito = mostradorAsignado.venderArticulo(id, cant); //del mostrador asignado, le resta valor a la variable cantEnVenta del producto y si se pudo vender, se agrega al aux
        if(exito)
        {
            auxVentas.add(mostradorAsignado.buscarProducto(id)); //en el aux ventas registra los productos que se van a vender, buscandolos por id
        }

        return exito; // devuelve true si no hubo problemas
    }
    //metodo para registrar las compras de un cliente,y devuelve un string como si fuera un ticket
    public String registrarCompras(String idProducto)
    {
        StringBuilder sb = new StringBuilder();

        ventas.put(new Venta(),auxVentas); //crea una instancia de venta con la fecha actual, y como valor le pasa el arreglo de productos
        auxVentas.clear(); //limpia el auxiliar una vez que se agrego a las ventas

        Map.Entry<Venta, ArrayList<Producto>> auxVentas = ventas.entrySet().iterator().next(); // variable de tipo entrymap para obtener la clave y el valor que sean las ultimas en la lista

        sb.append(auxVentas.getKey()) //obtiene la clave de las ventas
                .append("\n")
                .append(auxVentas.getValue()); // obtiene el valor de las ventas

        return sb.toString();
    }


    public void atenderMiembro(Cliente cliente){
        cliente.setTotalCompra(cliente.getTotalCompra() - cliente.getDescuento());

        setProductividad(getProductividad() + 1 );
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

    /// Modificar cuando tengamos mejor la clase gestora
    public boolean agregarCliente(Cliente cliente){
        return true;
    }

    @Override
    public String toString() {
        return "Gestion.Empleados.Cajero[" +
                " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Genero: " + getGenero() +
                ", Salario: " + getSalario() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad() +
                ", Informe: " + getInforme() +
                ", Productividad: " + getProductividad() +
                ']';
    }

    /*
    public org.json.JSONObject serializar(){

    }

     */
}