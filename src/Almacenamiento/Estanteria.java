package Almacenamiento;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

public class Estanteria {
    private final UUID id;
    private final ArrayList<Producto>productos;
    private int capacidadProductos;

    public Estanteria() {
        this.id = UUID.randomUUID();;
        this.productos = new ArrayList<Producto>();
        this.capacidadProductos = 0;
    }

    public Estanteria(int capacidadProductos) {
        this.id = UUID.randomUUID();
        this.productos = new ArrayList<Producto>();
        this.capacidadProductos = capacidadProductos;
    }

    public UUID getId() {
        return id;
    }

    public int getCapacidadProductos() {
        return capacidadProductos;
    }

    public void setCapacidadProductos(int capacidadProductos) {
        this.capacidadProductos = capacidadProductos;
    }

    //metodos

    //metodo que agrega un producto a la estanteria
    public boolean agregarProducto(Producto p)
    {
        boolean exito = false;
        int cantidadProductos=0, auxCapacidad=0;

        for(Producto o : productos)
        {
            cantidadProductos+=o.getStock(); //obtiene el stock que hay actualmente en la estanteria
        }
        auxCapacidad = capacidadProductos - cantidadProductos; // esto devuelve la capacidad

        if(auxCapacidad>p.getStock()) // si hay suficiente capacidad como para agregar stock entonces permite quitar esa cantidad de capacidad a la estanteria
        {
            capacidadProductos-=p.getStock();
            exito = productos.add(p);
        }
        return exito;
    }
    public boolean eliminarProducto(String nombre)
    {
        boolean exito = false;
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext())
        {
            Producto p = it.next();
            if(p.getNombre().equals(nombre))
            {
                this.capacidadProductos+=p.getStock();
                it.remove();
                exito = true;
            }
        }

        return exito;
    }
    //metodo que resta al stock la cantidad ingresada, y esta misma cantidad se suma a la variable en venta
    public Producto venderProductos(String id,int cant)
    {
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext())
        {
            Producto p = it.next();
            if(p.getId().equals(UUID.fromString(id)) && p.getStock()>cant)
            {
                p.setStock((p.getStock()-cant));
                if(capacidadProductos<1500)
                {capacidadProductos+=cant;} else if (capacidadProductos>=1500) { //verifica que no se sobrepase de la capacidad limite
                    capacidadProductos=1500;
                }
                p.setCantEnVenta(cant);
                return p;
            }
        }

        return null;
    }
    public String listarProductos()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(Producto p:productos)
    	{
    		sb.append(p.toString()).append("\n");
    	}
    	
    	return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if(o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estanteria that = (Estanteria) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Estanteria{" +
                "id=" + id +
                ", limiteProductos=" + capacidadProductos +
                '}';
    }
}
