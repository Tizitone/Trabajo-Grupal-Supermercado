import java.util.Objects;
import java.util.UUID;

public class Producto {
    private UUID id;
    private String nombre,marca,descripcion;
    private double precioUnitario;
    private int stock;

    public Producto() {
        this.id = null;
        this.nombre = "";
        this.marca = "";
        this.descripcion = "";
        this.precioUnitario = 0;
        this.stock = 0;
    }

    public Producto(String nombre,String marca, String descripcion,double precioUnitario, int stock) {
        this.id = UUID.fromString(nombre.toLowerCase());
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        setId();
    }

    public UUID getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    private void setId() {
        this.id = UUID.fromString(nombre.toLowerCase());
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

    @Override
    public boolean equals(Object o) {
        if(o == this) return  true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return Objects.equals(id, producto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Producto{\n" +
                "id=" + id +
                "\n, nombre='" + nombre + '\'' +
                "\n, marca='" + marca + '\'' +
                "\n, descripcion='" + descripcion + '\'' +
                "\n, precioUnitario=" + precioUnitario +
                "\n, stock=" + stock +
                '}';
    }
}
