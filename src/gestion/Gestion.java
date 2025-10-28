package gestion;

import almacenamiento.Almacenamiento;
import almacenamiento.Producto;
import empleados.Administrativo;
import empleados.Cajero;
import empleados.Personal;
import registros.Venta;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Gestion {
    private LocalDate dia;
    private double ctaCte;
    HashMap<Integer, Administrativo> eAdministrativos;//dni, Personal
    HashMap<Integer, Personal> ePersonal; //dni,Personal
    HashMap<String, Almacenamiento> deposito;

    public Gestion() {
        this.eAdministrativos = new HashMap<>();
        this.ePersonal = new HashMap<>();
        this.deposito = new HashMap<>();
        this.dia = LocalDate.now();
        this.ctaCte = 0;
    }

    public Gestion(double ctaCte) {
        this.eAdministrativos = new HashMap<>();
        this.ePersonal = new HashMap<>();
        this.deposito = new HashMap<>();
        this.dia = LocalDate.now();
        this.ctaCte = ctaCte;
    }
    public void agregarAlmacenamiento(Almacenamiento a)
    {
        deposito.put(a.getId().toString(),a);
    }
    public boolean removerAlmacenamiento(String id)
    {
        boolean exito = false;
        Iterator<Map.Entry<String,Almacenamiento>> it = deposito.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<String,Almacenamiento> hm = it.next();
            if(id.equals(hm.getKey()))
            {
                it.remove();
                exito = true;
            }
        }

        return exito;
    }
    public String listarAlmacenamiento()
    {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String,Almacenamiento> entry : deposito.entrySet())
        {
            sb.append(entry.toString()).append("\n");
        }

        return sb.toString();
    }
    public double verGanancias()
    {
        double ganancias = 0;
        for (Map.Entry<Venta, ArrayList<Producto>> entry : Cajero.getVentas().entrySet())
        {
            for (Producto p : entry.getValue())
            {
                ganancias += p.getPrecioUnitario()*(p.getVendidos());
            }
        }
        return ganancias;
    }

    public void agregarPersonal(Personal p)
    {
        ePersonal.put(p.getDNI(),p);
    }
    public void removerPersonal(String dni)
    {
        boolean exito = false;
        Iterator<Map.Entry<Integer,Personal>> it = ePersonal.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer,Personal> hm = it.next();
            if(dni.equals(hm.getKey().toString()))
            {
                it.remove();
                exito = true;
            }
        }
    }
    public String listarPersonal()
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Personal> entry : ePersonal.entrySet())
        {
            sb.append(entry.toString()).append("\n");
        }

        return sb.toString();
    }
    public void agregarAdministrativos(Administrativo a)
    {
        eAdministrativos.put(a.getDNI(),a);
    }
    public void removerAdministrativo(String dni)
    {
        boolean exito = false;
        Iterator<Map.Entry<Integer,Administrativo>> it = eAdministrativos.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry<Integer,Administrativo> hm = it.next();
            if(dni.equals(hm.getKey().toString()))
            {
                it.remove();
                exito = true;
            }
        }
    }
    public String listarAdministrativos()
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Administrativo> entry : eAdministrativos.entrySet())
        {
            sb.append(entry.toString()).append("\n");
        }

        return sb.toString();
    }

}
