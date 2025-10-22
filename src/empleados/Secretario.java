package empleados;

import interfaces.ISalario;
import registros.CV;
import registros.Entrevista;

import java.util.ArrayList;
import java.util.TreeMap;

public class Secretario extends Administrativo implements ISalario {

    protected static TreeMap<Integer, CV> curriculums;
    protected static ArrayList<Entrevista> entrevistas;

    public Secretario(String nombre, int DNI, char genero, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
        curriculums = new TreeMap<>();
        entrevistas = new ArrayList<>();
    }

    public Secretario(String nombre, int DNI, char genero, boolean activo, int antiguedad, String correo, String contrasenia){
        super(nombre, DNI, genero, correo, contrasenia);
        curriculums = new TreeMap<>();
        entrevistas = new ArrayList<>();
    }

    public boolean recibirCV(CV curri){
        curriculums.put(curri.getAu_ID(), curri);
        return true;
    }

    public boolean descartarCV(CV curri){
        return curriculums.remove(curri.getAu_ID(), curri);
    }

    public String listarEntrevistas(){
        StringBuilder lista = new StringBuilder();

        for (Entrevista aVer : entrevistas){
            lista.append(aVer.toString());
        }
        return lista.toString();
    }

	@Override
	public int calcularSalario() {
		// TODO Auto-generated method stub
		return 0;
	}
}
