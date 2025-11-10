package main;

import almacenamiento.Mostrador;
import empleados.*;
import registros.CV;
import registros.Entrevista;

public class Main {

	public static void main(String[] args) {
		RRHH test1 = new RRHH("Pedro", 47839859, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");
		Secretario test2 = new Secretario("Juan", 32685938, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");

		Mostrador comp = new Mostrador();
		Cajero test3 = new Cajero(comp, "Juan", 32685938, 'h', 127000, true, 5);
		Limpiador test4 = new Limpiador("Carla", 44575848, 'm', 57000, true, 3);
		Repositor test5 = new Repositor("juanita", 36888888, 'm', 32000, true, 5);
		
		CV cv = new CV("Raul", "Perez", 24, "2236837702", "raulperez@gmail.com", 'h');
		Entrevista e = new Entrevista(9, 12, 2026, 15, 30, cv, "");
		CV cv2 = new CV("Mario", "Rodriguez", 23, "2236837702", "marioRodriguez@gmail.com", 'h');
		Entrevista e2 = new Entrevista(9, 12, 2026, 15, 45, cv2, "");
		CV cv3 = new CV("Mario", "Rodriguez", 23, "2236837702", "marioRodriguez@gmail.com", 'h');
		Entrevista e3 = new Entrevista(9, 12, 2026, 16, 00, cv3, "");
		
		test2.agendarEntrevista(e2);
		test2.agendarEntrevista(e);
		test2.agendarEntrevista(e3);
		test1.asignarEntrevistas();
		System.out.println(test1.listarEntrevistas());
		
		System.out.println(test1.toString());
		System.out.println(test2.toString());
		System.out.println(test3.toString());
		System.out.println(test4.toString());
		System.out.println(test5.toString());
	}
}