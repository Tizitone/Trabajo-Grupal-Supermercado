import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonUtiles {

    public static void grabarUnJson(JSONArray jsonArray, String archivo){
        try {
            FileWriter file = new FileWriter(archivo);
            file.write(jsonArray.toString(4));
            file.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static JSONTokener leerUnJson(String archivo) {
        JSONTokener tokener = null;

        try{
            tokener = new JSONTokener(new FileReader(archivo));

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        return tokener;
    }

    //Otra forma
    public static String leer(String archivo)
    {
        String contenido = "";
        try
        {
            contenido = new String(Files.readAllBytes(Paths.get(archivo+".json")));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contenido;
    }
}