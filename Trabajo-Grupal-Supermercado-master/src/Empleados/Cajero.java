package Gestion.Empleados;

import Gestion.Empleados.Personal;

import java.util.Random;

public class Cajero extends Personal {

    public Cajero(String nombre, int DNI, char genero){
        super(nombre, DNI, genero);
    }

    public Cajero(String nombre, int DNI, char genero, boolean activo, int antiguedad){
        super(nombre, DNI, genero, activo, antiguedad);
    }

    public void atenderMiembro(Cliente cliente){
        cliente.setTotalCompra(cliente.getTotalCompra() * cliente.getMembresia().membresia.getDescuento());

        setProductividad(getProductividad() + 1 );
    }

    public void atenderCliente(){
        setProductividad(getProductividad() + 1 );
    }

    public void validarCliente(Cliente cliente){
        if (cliente.getMembresia() != null) atenderMiembro(cliente);

        else if (preguntarACliente()) agregarCliente(cliente);

        else atenderCliente();
    }

    public boolean preguntarACliente(){
        Random pregunta = new Random();
        return pregunta.nextBoolean();
    }

    /// Modificar cuando tengamos mejor la clase gestora
    public boolean agregarCliente(Cliente cliente){
        return true;
    }

    @Override
    public String toString() {
        return "Gestion.Empleados.Cajero[" +
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

    /*
    public org.json.JSONObject serializar(){

    }

     */
}