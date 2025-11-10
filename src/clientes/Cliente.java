package clientes;

import java.util.ArrayList;

public class Cliente {

    private String nombre;
    private int DNI;
    private double consumosTotales;
    private TarjetaMembresia membresia;
    private ArrayList<String> cupones;
    private double totalCompra;

    public Cliente(String nombre, int DNI, double consumosTotales, TarjetaMembresia membresia, double totalCompra){
        this.nombre = nombre;
        this.DNI = DNI;
        this.consumosTotales = consumosTotales;
        this.membresia = membresia;
        cupones = new ArrayList<>();
        this.totalCompra = totalCompra;
    }

    public Cliente(double totalCompra, TarjetaMembresia membresia){
        nombre = "";
        DNI = 0;
        consumosTotales = 0;
        this.membresia = membresia;
        cupones = new ArrayList<>();
        this.totalCompra = totalCompra;
    }

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
        return membresia;
    }

    public double getDescuento(){
        return membresia.membresia.getDescuento();
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


