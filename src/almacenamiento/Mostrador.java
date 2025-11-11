package almacenamiento;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import empleados.Limpiador;
import interfaces.IEnsuciable;

public class Mostrador implements IEnsuciable{

    private static TreeMap<Producto,Integer> articulos;
    private final int limiteArticulos;
    private static int suciedad=0;

    public Mostrador() {
        articulos = new TreeMap<Producto,Integer>();
        limiteArticulos = 1200;
    }  
    
    public int getLimiteArticulos() {
		return limiteArticulos;
	}

	public int getSuciedad() {
		return suciedad;
	}
	
	public static void setSuciedad(int suciedad) {
		Mostrador.suciedad = suciedad;
	}

	//agrega un producto al mostrador(es decir que ese articulo se encuentra en venta)
    public boolean agregarArticulos(Producto p)
    {
        boolean exito = false;
        int cantidadTotalVenta=0;

        for (Map.Entry<Producto,Integer> entry : articulos.entrySet())
        {
            cantidadTotalVenta+= entry.getKey().getCantEnVenta(); //obtiene la cantidad todal de articulos agregados que estan en venta
        }
        if(limiteArticulos>cantidadTotalVenta) // verifica que no se rebase el limite
        {
        	calcularIndiceSuciedad();
            articulos.put(p,p.getCantEnVenta()); //si se puede agregar, se agrega el producto como clave y la cantidad de articulos en venta de ese producto
            exito = true;
        }
        return exito;
    }
    //busca un producto por uuid
    public static Producto buscarProducto(String id)
    {
        for (Map.Entry<Producto,Integer> entry : articulos.entrySet())
        {
            if(entry.getKey().getId().equals(UUID.fromString(id)))
            {
                return entry.getKey();
            }
        }

        return null;
    }

    //le resta valor a la cantEnVenta de un producto
    public  boolean venderArticulo(String id,int cant)
    {
        boolean exito = false;
        if(buscarProducto(id)==null) return false;
        Producto p = buscarProducto(id);
        if(p.getCantEnVenta()>cant)
        {
            p.setCantEnVenta(p.getCantEnVenta()-cant);
			 p.setVendidos(p.getVendidos()+cant);
            exito = true;
            calcularIndiceSuciedad();
        }

        return exito;
    }

	@Override
	public  int calcularIndiceSuciedad() {
		Mostrador.suciedad++;
		if(Mostrador.suciedad>50)
		{
			Limpiador.setTiendaLimpia(false);
		}
		if(suciedad>100)
		{
			Mostrador.suciedad=100;
		}
		return suciedad;
	}

	@Override
	public boolean verificarSuciedad() {
		return suciedad<50;
	}
}
