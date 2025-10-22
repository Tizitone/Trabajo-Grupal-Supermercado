package almacenamiento;
import enumerators.ETipoMedida;

import java.util.Objects;
import java.util.UUID;

public class Producto implements Comparable<Producto>{
    private final UUID id;
    private String nombre,marca,descripcionAdicional;
    private double precioUnitario, peso;
    private int stock;
    private ETipoMedida medida;
    private int cantEnVenta;

    public Producto() {
        this.id = UUID.randomUUID();
        this.nombre = "";
        this.marca = "";
        this.descripcionAdicional = "";
        this.precioUnitario = 0;
        this.peso = 0;
        this.stock = 0;
        this.cantEnVenta=0;
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
