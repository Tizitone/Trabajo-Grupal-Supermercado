package gestion;

import java.util.Date;

public class Gestion {
    private Date dia;
    private double ctaCte;
    //hashmap<int,Administrativo>EAdministrativos();
    //EPersonal<int,Gestion.Empleados.Personal>: hashMap();


    public Gestion() {
        this.dia = null;
        this.ctaCte = 0;
    }

    public Gestion(Date dia, double ctaCte) {
        this.dia = dia;
        this.ctaCte = ctaCte;
    }

}
