package almacenamiento;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import empleados.Limpiador;
import interfaces.IEnsuciable;
import main.JsonUtiles;

public class Mostrador implements IEnsuciable{

    private static TreeMap<Producto,Integer> articulos;
    private final int limiteArticulos;
    private static int suciedad=0;

    public Mostrador() {
        articulos = new TreeMap<Producto,Integer>();
        limiteArticulos = 1200;
    }  
    
    public static TreeMap<Producto, Integer> getArticulos() {
		return articulos;
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
    public static String listarArticulos()
    {
    	StringBuilder sb = new StringBuilder();
    	for (Map.Entry<Producto,Integer> entry : articulos.entrySet())
        {
            sb.append(entry.getKey().toString()).append("\n");
        }
    	
    	return sb.toString();
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
        System.out.println("No se ha podido encontrar el objeto");
        return new Producto();
    }

    //le resta valor a la cantEnVenta de un producto
    public static boolean venderArticulo(String id,int cant)
    {
        boolean exito = false;
        if(buscarProducto(id)==null) return false;
        Producto p = buscarProducto(id);
        if(p.getCantEnVenta()>cant)
        {
            p.setCantEnVenta(p.getCantEnVenta()-cant);
			 p.setVendidos(p.getVendidos()+cant);
            exito = true;
        }

        return exito;
    }
    
    public void toJson(String archivo)
    {
        JSONObject jb = new JSONObject();
        JSONArray jArray = new JSONArray();
        jb.put("suciedad", getSuciedad());
        
        JSONArray jsonArticulosArray = new JSONArray();
        for (Map.Entry<Producto, Integer> entry : articulos.entrySet()) {
            JSONObject articuloJson = new JSONObject();
            articuloJson.put("producto", entry.getKey().toJSON());
            articuloJson.put("cantidad", entry.getValue());
            jsonArticulosArray.put(articuloJson);
        }
        jb.put("articulos", jsonArticulosArray);
        jArray.put(jb);
        // Si solo quieres guardar un objeto
        JsonUtiles.grabarUnJson(jArray, archivo);
    }
    public void toObject(String archivo)
    {
    	Mostrador.articulos.clear();
    	
    	JSONArray jArray = new JSONArray(JsonUtiles.leerUnJson(archivo));
    	
    	for(int i = 0; i < jArray.length(); i++)
    	{
    		JSONObject jb = jArray.getJSONObject(i);
    		JSONArray jArrayAux = jb.getJSONArray("articulos");
    		for(int j = 0 ; j<jArrayAux.length() ; j++)
        	{
        		Producto p = new Producto();
        		JSONObject jArticulo = jArrayAux.getJSONObject(j);
        		
        		p.toObject(jArticulo.getJSONObject("producto"));
        		
        		int cantidad = jArticulo.getInt("cantidad");
        		articulos.put(p, cantidad);
        	}
    		Mostrador.setSuciedad(jb.getInt("suciedad"));
    	}
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
