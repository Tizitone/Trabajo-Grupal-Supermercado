import java.util.HashMap;

public class ClaseGestora {
    private HashMap<Integer, Cliente> clientes;


    public ClaseGestora(){
        clientes = new HashMap<>();
    }

    public HashMap<Integer, Cliente> getClientes() {
        return clientes;
    }


}
