package gestion;

import almacenamiento.Producto;
import empleados.Cajero;
import interfaces.IGestionable;
import registros.Venta;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class Gestion <T extends IGestionable<?>>{ // clase generica que recibe una clase que implemente IGestionable 
    private String dia;
    LinkedHashSet<T> listaGestora;//Administrativos, Personal, Almacenamiento
    private final String cuenta = "admin";
    private final String contrasenia = "admin";

    public Gestion() {
        this.listaGestora = new LinkedHashSet<>();
        this.dia = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // inicializa el dia con el tiempo actual en un formato tipo String
    }
    
    public String getDia() {
		return dia;
	}

	protected String getCuenta() {
		return cuenta;
	}

	protected String getContrasenia() {
		return contrasenia;
	}

	public void agregar(T t)
    {
        listaGestora.add(t);
    }
    public boolean removerPorIdentificador(String id)
    {
        Iterator<T> it = listaGestora.iterator();
        while (it.hasNext())
        {
            T hm = it.next();
            if(id.equals(hm.getIdentificador()))
            {
                it.remove();
                return true;
            }
        }
        return false;
    }
    public String listar()
    {
        StringBuilder sb = new StringBuilder();

        Iterator<T> it = listaGestora.iterator();
        while (it.hasNext())
        {
            T hm = it.next();
            
            sb.append(hm.toString()).append("\n");
        }
        return sb.toString();
    }
    
    
    public double verGanancias()
    {
        double ganancias = 0;
        for (Map.Entry<Venta, ArrayList<Producto>> entry : Cajero.getVentas().entrySet())
        {
            for (Producto p : entry.getValue())
            {
                ganancias += p.getPrecioUnitario()*(p.getVendidos());
            }
        }
        return ganancias;
    }
    
    

}
