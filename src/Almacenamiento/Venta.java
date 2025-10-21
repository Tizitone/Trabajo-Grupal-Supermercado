package Almacenamiento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.UUID;
/*
 clase que registra la fecha y hora actual cuando se instancia. Ademas de una id;
 */
public class Venta {
    private UUID id;
    private String fecha;
    private String hora;
    private ArrayList<Producto>productos;

    public Venta() {
        this.productos = new ArrayList<Producto>();
        this.fecha = devolverFechaLocal();
        this.hora = devolverHoraLocal();
        this.id = UUID.randomUUID();
    }

    private String devolverFechaLocal()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDate.now().getDayOfMonth())
                .append("/")
                .append(LocalDate.now().getMonthValue())
                .append("/")
                .append(LocalDate.now().getYear());
        return sb.toString();
    }
    private String devolverHoraLocal()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(LocalDateTime.now().getHour())
                .append(":")
                .append(LocalDateTime.now().getMinute())
                .append(":")
                .append(LocalDateTime.now().getSecond());

        return sb.toString();
    }
}
