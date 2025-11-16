package gestion;

import interfaces.IGestionable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Gestion <T extends IGestionable<?>>{ // clase generica que recibe una clase que implemente IGestionable 
    private String dia;
    private ArrayList<T> listaGestora = new ArrayList<>();;//Administrativos, Personal, Almacenamiento
    private final static String cuenta = "admin";
    private final static String contrasenia = "admin";

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
	public static boolean validadCuenta(String correo,String contrasenia)
	{
		boolean cuentaCorrecta= false;
		if(correo.equals(cuenta) && contrasenia.equals(contrasenia))
		{
			cuentaCorrecta = true;
		}
		
		return cuentaCorrecta;
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
        for(T t : listaGestora)
        {
            if(id.equals(t.getIdentificador().toString()))
            {
                return listaGestora.remove(t);
            }
        }
        return false;
    }
    public String listar()
    {
        StringBuilder sb = new StringBuilder();

        for(T t : listaGestora)
        {     
            sb.append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}
