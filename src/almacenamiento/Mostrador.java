package almacenamiento;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class Mostrador {

    private TreeMap<Producto,Integer> articulos;
    private final int limiteArticulos;

    public Mostrador() {
        articulos = new TreeMap<Producto,Integer>();
        limiteArticulos = 1200;
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
            articulos.put(p,p.getCantEnVenta()); //si se puede agregar, se agrega el producto como clave y la cantidad de articulos en venta de ese producto
            exito = true;
        }
        return exito;
    }
    //busca un producto por uuid
    public Producto buscarProducto(String id)
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
    public boolean venderArticulo(String id,int cant)
    {
        boolean exito = false;
        if(buscarProducto(id)==null) return false;
        Producto p = buscarProducto(id);
        if(p.getCantEnVenta()>cant)
        {
            p.setCantEnVenta(p.getCantEnVenta()-cant);
            exito = true;
        }

        return exito;
    }
}
