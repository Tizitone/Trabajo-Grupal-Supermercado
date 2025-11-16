package gestion;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import empleados.*;
import almacenamiento.*;
import clientes.*;

/**
 * Clase genérica para gestionar lectura y escritura de objetos JSON.
 */
public class JsonGestor {


    public static <T> void guardarListaJSON(ArrayList<T> lista, String rutaArchivo) {
        JSONArray jArray = new JSONArray();
        File archivo = new File(rutaArchivo);
        for (T obj : lista) {
            if (obj instanceof Personal) {
                JSONArray arr = ((Personal) obj).toJsonPersonal();
                jArray.put(arr.getJSONObject(0));
            } else if (obj instanceof Administrativo) {
                JSONArray arr = ((Administrativo) obj).toJsonAdministrativo();
                jArray.put(arr.getJSONObject(0));
            } else if (obj instanceof Almacenamiento) {
                jArray.put(((Almacenamiento) obj).toJson());
            } else {
                System.out.println("Tipo no soportado: " + obj.getClass().getSimpleName());
            }
        }

        try (PrintWriter file = new PrintWriter(archivo)) {
            file.println(jArray.toString(4)); // con sangría
            file.close();
            System.out.println("Datos guardados en " + rutaArchivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Object> cargarListaJSON(String rutaArchivo) {
        ArrayList<Object> lista = new ArrayList<>();

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            JSONArray jArray = new JSONArray(contenido);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jb = jArray.getJSONObject(i);
                String tipo = jb.optString("tipo");

                Object obj = null;

                // EMPLEADOS
                switch (tipo) {
                    case "Cajero":
                        obj = new Cajero();
                        ((Cajero) obj).toObject(jb);
                        break;
                    case "Repositor":
                        obj = new Repositor();
                        ((Repositor) obj).toObject(jb);
                        break;
                    case "Limpiador":
                        obj = new Limpiador();
                        ((Limpiador) obj).toObject(jb);
                        break;
                    case "Secretario":
                        obj = new Secretario();
                        ((Secretario) obj).toObject(jb);
                        break;
                    case "RRHH":
                        obj = new RRHH();
                        ((RRHH) obj).toObject(jb);
                        break;

                    // ALMACENAMIENTO
                    case "Producto":
                        obj = new Producto();
                        ((Producto) obj).toObject(jb);
                        break;
                    case "Estanteria":
                        obj = new Estanteria();
                        ((Estanteria) obj).toObject(jb);
                        break;

                    // CLIENTES
                    case "Cliente":
                        obj = new Cliente();
                        ((Cliente) obj).toObject(jb);
                        break;

                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                        continue;
                }

                lista.add(obj);
            }

        } catch (IOException e) {
            System.out.println(" Error leyendo " + rutaArchivo + ": " + e.getMessage());
        }

        return lista;
    }

}
