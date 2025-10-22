package almacenamiento;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class Almacenamiento {
    private final UUID id;
    private ArrayList<Estanteria>estanterias;
    private int capacidadEstanterias;

    public Almacenamiento() {
        this.id = UUID.randomUUID();
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = 0;
    }
    public Almacenamiento(String direccion, int capacidadEstanterias) {
        this.id = UUID.randomUUID();
        this.estanterias = new ArrayList<Estanteria>();
        this.capacidadEstanterias = capacidadEstanterias;
    }

    public UUID getId() {
        return id;
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
    public boolean agregarEstanteria(Estanteria e)
    {
        boolean exito = false;

        if(capacidadEstanterias>0)
        {
        	capacidadEstanterias--;
            exito = estanterias.add(e);
        }
        return exito;
    }
    
    public String listarEstanterias()
    {
    	StringBuilder sb = new StringBuilder();
    	
    	for(Estanteria e : estanterias)
    	{
    		sb.append(e.toString()).append("\n");
    	}
    	
    	return sb.toString();
    }
    public String buscarEstanteriaPorID(String id)
    {
    	StringBuilder sb = new StringBuilder();
    	UUID uuidbuscado = UUID.fromString(id);
    	
    	for(Estanteria e : estanterias)
    	{
    		if(e.getId().equals(uuidbuscado))
    		{
    			sb.append(e.toString());
    		}
    	}
    	
    	return sb.toString();
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
                ", capacidadEstanterias=" + capacidadEstanterias +
                '}';
    }
}
