package gestion;

import empleados.Administrativo;
import empleados.Personal;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Gestion {
    private LocalDate dia;
    private double ctaCte;
    private HashMap <Integer, Administrativo> EAdministrativos;
    private HashMap <Integer,Personal> EPersonal;


    public Gestion() {
        this.dia = LocalDate.now();
        this.ctaCte = 0;
        EAdministrativos = new HashMap<>();
        EPersonal = new HashMap<>();
    }

    // para crear la clase gestora cuando recuperemos información de un archivo.
    public Gestion(LocalDate dia, double ctaCte, HashMap <Integer, Administrativo> EAdministrativos, HashMap <Integer, Personal> EPersonal) {
        this.dia = dia;
        this.ctaCte = ctaCte;
        this.EAdministrativos = EAdministrativos;
        this.EPersonal = EPersonal;
    }

    public LocalDate getDia() {
        return dia;
    }

    public double getCtaCte() {
        return ctaCte;
    }

    public HashMap <Integer, Administrativo> getEAdministrativos() {
        return EAdministrativos;
    }

    public HashMap <Integer, Personal> getEPersonal() {
        return EPersonal;
    }

    public String verDia(){
        return dia.getDayOfMonth() + "/" + dia.getMonthValue() + "/" + dia.getYear();
    }

    public String verEmpleados(){
        StringBuilder Empleados = new StringBuilder();

        for (int key : EAdministrativos.keySet() ){
            Empleados.append(EAdministrativos.get(key).toString()).append("\n");
        }

        for (int key : EPersonal.keySet() ){
            Empleados.append(EPersonal.get(key).toString()).append("\n");
        }
        return Empleados.toString();
    }

    public String verEmpleados(int id){
        for (int key : EPersonal.keySet()){
            return EPersonal.get(key).toString();
        }
        return "";
    }

    // metodo temporal solo para probar otras funciones
    public void addEmpleado(Personal empleado){
        EPersonal.put(empleado.getDNI(), empleado);
    }

    // metodo temporal solo para probar otras funciones
    public void addEmpleado(Administrativo empleado){
        EAdministrativos.put(empleado.getDNI(), empleado);
    }


}
