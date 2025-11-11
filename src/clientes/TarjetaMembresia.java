package clientes;

import java.util.UUID;

import enumerators.Membresia;

public class TarjetaMembresia {

    protected String codigo;
    protected Membresia membresia;

    public TarjetaMembresia()
    {
    	codigo = "";
    	membresia = null;
    }
    
    public TarjetaMembresia(Membresia membresia){
        codigo = UUID.randomUUID().toString();
        this.membresia = membresia;
    }
    
	protected String getCodigo() {
		return codigo;
	}

	protected void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	protected Membresia getMembresia() {
		return membresia;
	}

	protected void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}
    
    

}
