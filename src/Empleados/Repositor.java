package Empleados;



import Almacenamiento.Estanteria;
import Almacenamiento.Mostrador;

import java.util.HashMap;

public class Repositor extends Personal {
    private Estanteria estanteria;

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

    //metodo para agregar articulos(productos) al mostrador
    public boolean reponerProducto(Mostrador mostrador,Estanteria estanteria,String id, int cant){
        boolean exito = false;

        exito = mostrador.agregarArticulos(estanteria.venderProductos(id,cant));// mostrador devuelve un booleano si se pudo agregar, y venderProductos devuelve un producto si todavia hay la cantidad que se solicita

        return exito;
    }


    @Override
    public String toString() {
        return "Gestion.Empleados.Repositor[" +
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