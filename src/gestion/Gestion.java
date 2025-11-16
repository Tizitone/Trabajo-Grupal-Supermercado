package gestion;

import interfaces.IGestionable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

public class Gestion <T extends IGestionable<?>>{ // clase generica que recibe una clase que implemente IGestionable 
    private String dia;
    private ArrayList<T> listaGestora = new ArrayList<>();;//Administrativos, Personal, Almacenamiento
    private final String cuenta = "admin";
    private final String contrasenia = "admin";

    public Gestion() {
        this.dia = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); // inicializa el dia con el tiempo actual en un formato tipo String
    }
    
    public String getDia() {
		return dia;
	}
	public ArrayList<T> getListaGestora() {
		return listaGestora;
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
	public T buscarObjeto(String id)
	{
		for(T t: listaGestora)
		{
			if(t.getIdentificador() == id)
			{
				return t;
			}
		}
		return null;
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
}
