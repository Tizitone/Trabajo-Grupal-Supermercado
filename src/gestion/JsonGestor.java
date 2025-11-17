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
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


	public static <T> ArrayList<T> cargarListaJSON(String rutaArchivo) {
        ArrayList<T> lista = new ArrayList<>();

        try {
            String contenido = new String(Files.readAllBytes(Paths.get(rutaArchivo)));
            JSONArray jArray = new JSONArray(contenido);

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jb = jArray.getJSONObject(i);
                String tipo = jb.getString("tipo").toLowerCase();

                Object obj = null;

                // EMPLEADOS
                switch (tipo) {
                    case "cajero":
                        obj = new Cajero();
                        ((Cajero) obj).toObject(jb);
                        break;
                    case "repositor":
                        obj = new Repositor();
                        ((Repositor) obj).toObject(jb);
                        break;
                    case "limpiador":
                        obj = new Limpiador();
                        ((Limpiador) obj).toObject(jb);
                        break;
                    case "secretario":
                        obj = new Secretario();
                        ((Secretario) obj).toObject(jb);
                        break;
                    case "rrhh":
                        obj = new RRHH();
                        ((RRHH) obj).toObject(jb);
                        break;

                    // ALMACENAMIENTO
                    case "almacenamiento":
                        obj = new Almacenamiento(50);
                        ((Almacenamiento) obj).toObject(jb);
                        break;              

                    // CLIENTES
                    case "cliente":
                        obj = new Cliente();
                        ((Cliente) obj).toObject(jb);
                        break;

                    default:
                        System.out.println("Tipo desconocido: " + tipo);
                        continue;
                }

                lista.add((T)obj);
            }

        } catch (IOException e) {
            System.out.println(" Error leyendo " + rutaArchivo + ": " + e.getMessage());
        }

        return lista;
    }

}
