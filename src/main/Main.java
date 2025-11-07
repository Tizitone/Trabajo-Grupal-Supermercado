package main;

import almacenamiento.Mostrador;
import empleados.*;

public class Main {

	public static void main(String[] args) {
		RRHH test1 = new RRHH("Pedro", 47839859, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");
		Secretario test2 = new Secretario("Juan", 32685938, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");

		Mostrador comp = new Mostrador();
		Cajero test3 = new Cajero(comp, "Juan", 32685938, 'h', 127000, true, 5);
		Limpiador test4 = new Limpiador("Carla", 44575848, 'm', 57000, true, 3);
		Repositor test5 = new Repositor("juanita", 36888888, 'm', 32000, true, 5);

		System.out.println(test1.toString() + "\n");
		System.out.println(test2.toString() + "\n");
		System.out.println(test3.toString() + "\n");
		System.out.println(test4.toString() + "\n");
		System.out.println(test5.toString() + "\n");
	}
}