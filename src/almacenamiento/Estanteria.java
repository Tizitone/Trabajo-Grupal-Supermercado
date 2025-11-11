package almacenamiento;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;

import interfaces.IEnsuciable;

public class Estanteria implements IEnsuciable{
    private UUID id;
    private ArrayList<Producto>productos;
    private int capacidadProductos;
    private int suciedad=0;

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
   
    protected void setId(UUID id) {
		this.id = id;
	}

	public int getCapacidadProductos() {
        return capacidadProductos;
    }

    public void setCapacidadProductos(int capacidadProductos) {
        this.capacidadProductos = capacidadProductos;
    }
    
    //metodos
    public int getSuciedad() {
		return suciedad;
	}

	public void setSuciedad(int suciedad) {
		this.suciedad = suciedad;
	}

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
            calcularIndiceSuciedad();
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
                calcularIndiceSuciedad();
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
                calcularIndiceSuciedad();
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
    public JSONObject toJson() {
        JSONObject jb = new JSONObject();
        JSONArray jProductos = new JSONArray();

        // Convertir productos dentro de la estantería
        for (Producto p : productos) {
            jProductos.put(p.toJSON());
        }

        jb.put("tipo", "Estanteria"); // importante para JsonGestor
        jb.put("id", id.toString());
        jb.put("capacidadProductos", capacidadProductos);
        jb.put("suciedad", suciedad);
        jb.put("productos", jProductos);

        return jb;
    }
    public void toObject(JSONObject jb) {
        // ID y propiedades básicas
        this.setId(UUID.fromString(jb.getString("id")));
        this.capacidadProductos = jb.getInt("capacidadProductos");
        this.suciedad = jb.getInt("suciedad");

        // Limpiar productos actuales
        this.productos.clear();

        // Cargar productos
        JSONArray jProductos = jb.getJSONArray("productos");
        for (int i = 0; i < jProductos.length(); i++) {
            JSONObject jProd = jProductos.getJSONObject(i);
            Producto p = new Producto();
            p.toObject(jProd);
            this.productos.add(p);
        }
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

	@Override
	public int calcularIndiceSuciedad() {
		this.suciedad++;
		if(suciedad>100)
		{
			this.suciedad=100;
		}
		return suciedad;
	}

	@Override
	public boolean verificarSuciedad() {
		return suciedad<50;
	}
}
