package empleados;



import almacenamiento.Estanteria;
import almacenamiento.Mostrador;
import interfaces.ISalario;

public class Repositor extends Personal implements ISalario{

    public Repositor(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
    }

    public Repositor(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad){
        super(nombre, DNI, genero, salario, activo, antiguedad);
    }

    public int contarStock(){
        setProductividad(getProductividad() + 1 );
        return 1;
    }

    //metodo para agregar articulos(productos) al mostrador
    public boolean reponerProducto(Mostrador mostrador,Estanteria estanteria,String id, int cant){
        boolean exito;

        exito = mostrador.agregarArticulos(estanteria.venderProductos(id,cant));// mostrador devuelve un booleano si se pudo agregar, y venderProductos devuelve un producto si todavia hay la cantidad que se solicita

        return exito;
    }


    

	@Override
	public String toString() {
		return super.toString();
	}

	@Override
	public int calcularSalario() {
		// TODO Auto-generated method stub
		return 0;
	}

}

