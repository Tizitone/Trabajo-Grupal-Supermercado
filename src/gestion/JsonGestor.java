package gestion;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import empleados.*;
import main.Menu;
import almacenamiento.*;
import clientes.*;

/**
 * Clase genérica para gestionar lectura y escritura de objetos JSON.
 */
public class JsonGestor {


    public static <T> void guardarListaJSON(ArrayList<T> lista, String rutaArchivo) {
        JSONArray jArray = new JSONArray();

        for (T obj : lista) {
            if (obj instanceof Personal) {
                JSONArray arr = ((Personal) obj).toJsonPersonal();
                jArray.put(arr.getJSONObject(0));
            } else if (obj instanceof Administrativo) {
                JSONArray arr = ((Administrativo) obj).toJsonAdministrativo();
                jArray.put(arr.getJSONObject(0));
            } else if (obj instanceof Producto) {
                jArray.put(((Producto) obj).toJSON());
            } else if (obj instanceof Estanteria) {
                jArray.put(((Estanteria) obj).toJson());
            } else if (obj instanceof Cliente) {
                jArray.put(((Cliente) obj).toJson());
            } else {
                System.out.println("Tipo no soportado: " + obj.getClass().getSimpleName());
            }
        }

        try (FileWriter file = new FileWriter(rutaArchivo)) {
            file.write(jArray.toString(4)); // con sangría
            System.out.println("✅ Datos guardados en " + rutaArchivo);
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

                // === EMPLEADOS ===
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

                    // === ALMACENAMIENTO ===
                    case "Producto":
                        obj = new Producto();
                        ((Producto) obj).toObject(jb);
                        break;
                    case "Estanteria":
                        obj = new Estanteria();
                        ((Estanteria) obj).toObject(jb);
                        break;

                    // === CLIENTES ===
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
            System.out.println(" rror leyendo " + rutaArchivo + ": " + e.getMessage());
        }

        return lista;
    }
    public static boolean agregarObjeto(Object obj) {
        if (obj == null) return false;

        if (obj instanceof Personal) {
            Menu.getPersonal().add((Personal) obj);
            System.out.println("Personal agregado correctamente.");
            return true;
        } 
        else if (obj instanceof Administrativo) {
            Menu.getAdministrativos().add((Administrativo) obj);
            System.out.println("Administrativo agregado correctamente.");
            return true;
        } 
        else if (obj instanceof Producto) {
            Menu.getProducto().add((Producto) obj);
            System.out.println("Producto agregado correctamente.");
            return true;
        } 
        else if (obj instanceof Estanteria) {
            Menu.getEstanterias().add((Estanteria) obj);
            System.out.println("Estantería agregada correctamente.");
            return true;
        }

        System.out.println("Tipo de objeto no reconocido: " + obj.getClass().getSimpleName());
        return false;
    }
}
