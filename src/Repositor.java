import java.util.HashMap;

public class Repositor extends Personal {

    public Repositor(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
    }

    public Repositor(String nombre, int DNI, char genero, boolean activo, int antiguedad){
        super(nombre, DNI, genero, activo, antiguedad);
    }

    public int contarStock(){
        setProductividad(getProductividad() + 1 );
        return 1;
    }

    public boolean reponerProducto(Almacenamiento){

    }

    @Override
    public String toString() {
        return "Repositor[" +
                " Nombre: " + getNombre() +
                ", DNI: " + getDNI() +
                ", Genero: " + getGenero() +
                ", Salario: " + getSalario() +
                ", Activo: " + isActivo() +
                ", Antiguedad: " + getAntiguedad() +
                ", Informe: " + getInforme() +
                ", Productividad: " + getProductividad() +
                ']';
    }

}
