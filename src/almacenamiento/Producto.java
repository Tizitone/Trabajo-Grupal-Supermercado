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
        jb.put("id", id.toString());
        jb.put("nombre", nombre);
        jb.put("marca", marca);
        jb.put("descripcionAdicional", descripcionAdicional);
        jb.put("precioUnitario", precioUnitario);
        jb.put("peso", peso);
        jb.put("stock", stock);
        jb.put("medida", medida.name());
        jb.put("cantEnVenta", cantEnVenta);
        jb.put("vendidos", vendidos);
        return jb;
    }
    public Producto toObject(JSONObject jb) {
        Producto p = new Producto();

        // Asignar ID
        p.setId(UUID.fromString(jb.getString("id")));

        // Asignar atributos básicos
        p.setNombre(jb.getString("nombre"));
        p.setMarca(jb.getString("marca"));
        p.setDescripcionAdicional(jb.getString("descripcionAdicional"));
        p.setPrecioUnitario(jb.getDouble("precioUnitario"));
        p.setPeso(jb.getDouble("peso"));
        p.setStock(jb.getInt("stock"));
        p.setCantEnVenta(jb.getInt("cantEnVenta"));
        p.setVendidos(jb.getInt("vendidos"));

        // Enum ETipoMedida
        String medidaStr = jb.getString("medida");
        if (medidaStr != null && !medidaStr.equals("null")) {
            try {
                p.setMedida(ETipoMedida.valueOf(medidaStr));
            } catch (IllegalArgumentException e) {
                p.setMedida(null);
            }
        }

        return p;
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
		return "Producto [\n id=" + id + ",\n nombre=" + nombre + ",\n marca=" + marca + ",\n descripcion="
				+ descripcionAdicional + ",\n precioUnitario=" + precioUnitario + ",\n stock=" + stock + ",\n peso=" + peso
				+ medida.getMedida() + "]\n";
	}

	@Override
	public int compareTo(Producto o) {
		return this.id.compareTo(o.id);
	}
}
