package Gestion.Clientes;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private int DNI;
    private double consumosTotales;
    private TarjetaMembresia Membresia; // no es de este tipo, solo para probar funciones de cliente en empleados.
    private ArrayList<String> cupones = new ArrayList<>();
    private double totalCompra;

    public String getNombre() {
        return nombre;
    }

    public double getConsumosTotales() {
        return consumosTotales;
    }

    public int getDNI() {
        return DNI;
    }

    public TarjetaMembresia getMembresia() {
        return Membresia;
    }

    public ArrayList<String> getCupones() {
        return cupones;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }
}


