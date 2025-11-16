package almacenamiento;
import enumerators.ETipoMedida;

import java.util.Objects;
import java.util.UUID;

import org.json.JSONObject;

public class Producto implements Comparable<Producto>{
    private UUID id;
    private String nombre,marca,descripcionAdicional;
    private double precioUnitario, peso;
    private int stock;
    private ETipoMedida medida;
    private int cantEnVenta, vendidos;

    public Producto() {
        this.id = UUID.randomUUID();
        this.nombre = "";
        this.marca = "";
        this.descripcionAdicional = "";
        this.precioUnitario = 0;
        this.peso = 0;
        this.stock = 0;
        this.cantEnVenta=0;
		this.vendidos = 0;
    }

    public Producto(String nombre,String marca,ETipoMedida medida,double peso, String descripcionAdicional ,double precioUnitario, int stock) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.marca = marca;
        this.precioUnitario = precioUnitario;
        this.descripcionAdicional = descripcionAdicional;
        this.medida = medida;
        this.peso = peso;
        this.stock = stock;
        this.cantEnVenta=0;
		this.vendidos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public String getDescripcionAdicional() {
		return descripcionAdicional;
	}

	public void setDescripcionAdicional(String descripcionAdicional) {
		this.descripcionAdicional = descripcionAdicional;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public ETipoMedida getMedida() {
		return medida;
	}

	public void setMedida(ETipoMedida medida) {
		this.medida = medida;
	}

	public UUID getId() {
        return id;
    }
	
    protected void setId(UUID id) {
		this.id = id;
	}

	public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getCantEnVenta() {
        return cantEnVenta;
    }

    public void setCantEnVenta(int cantEnVenta) {
        this.cantEnVenta = cantEnVenta;
    }

	public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }

    public JSONObject toJSON() {
        JSONObject jb = new JSONObject();
        jb.put("tipo", "producto");
        jb.put("id", id.toString());
        jb.put("nombre", nombre);
        jb.put("marca", marca);
        jb.put("descripcionAdicional", descripcionAdicional);
        jb.put("precioUnitario", precioUnitario);
        jb.put("peso", peso);
        jb.put("stock", stock);
        jb.put("medida", medida);
        jb.put("cantEnVenta", cantEnVenta);
        jb.put("vendidos", vendidos);
        return jb;
    }
    public void toObject(JSONObject jb) {

        setId(UUID.fromString(jb.getString("id")));
        setNombre(jb.getString("nombre"));
        setMarca(jb.getString("marca"));
        setDescripcionAdicional(jb.getString("descripcionAdicional"));
        setPrecioUnitario(jb.getDouble("precioUnitario"));
        setPeso(jb.getDouble("peso"));
        setStock(jb.getInt("stock"));
        setCantEnVenta(jb.getInt("cantEnVenta"));
        setVendidos(jb.getInt("vendidos"));
        setMedida(ETipoMedida.valueOf(jb.getString("medida")));
    }
    @Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Producto [ id=" + id + ", nombre=" + nombre + ", marca=" + marca + ", descripcion="
				+ descripcionAdicional + ", precioUnitario=" + precioUnitario + ", stock=" + stock + ", peso=" + peso
				+ medida.getMedida() + "]\n";
	}

	@Override
	public int compareTo(Producto o) {
		return this.id.compareTo(o.id);
	}
}
