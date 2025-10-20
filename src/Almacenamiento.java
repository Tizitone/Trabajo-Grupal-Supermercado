import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Almacenamiento {
    private UUID id;
    private String direccion;
    private ArrayList<Estanteria>estanterias;
    private int capacidadEstanterias;

    public Almacenamiento() {
        this.id = null;
        this.direccion = "";
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = 0;
    }
    public Almacenamiento(String direccion, ArrayList<Estanteria> estanterias, int capacidadEstanterias) {
        this.id = UUID.fromString(direccion.toLowerCase());
        this.direccion = direccion;
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = capacidadEstanterias;
    }

    public UUID getId() {
        return id;
    }

    private void setId(String direccion) {
        this.id = UUID.fromString(direccion.toLowerCase());
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
        setId(direccion);
    }

    public ArrayList<Estanteria> getEstanterias() {
        return estanterias;
    }

    public void setEstanterias(ArrayList<Estanteria> estanterias) {
        this.estanterias = estanterias;
    }

    public int getCapacidadEstanterias() {
        return capacidadEstanterias;
    }

    public void setCapacidadEstanterias(int capacidadEstanterias) {
        this.capacidadEstanterias = capacidadEstanterias;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Almacenamiento that = (Almacenamiento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Almacenamiento{" +
                "id=" + id +
                ", direccion='" + direccion + '\'' +
                ", estanterias=" + estanterias +
                ", capacidadEstanterias=" + capacidadEstanterias +
                '}';
    }
}
