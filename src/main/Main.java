package main;

import almacenamiento.Mostrador;
import empleados.*; // ELIMINAR CUANDO SE CAMBIE EL USO
import gestion.Gestion;

public class Main {

	public static void main(String[] args) {
		Gestion sisgest = new Gestion();

		System.out.println(sisgest.verDia());

		Mostrador mostrador = new Mostrador();

		RRHH temp1 = new RRHH("Julio", 12345678, 'h', 120000, true, 15, "JulioTorrado@hotmail.com","ascuas123");
		Secretario temp2 = new Secretario("Amanda", 87654321, 'm', 55000, true, 20, "CameliaAmanda@gmail.com", "pepero456");
		Cajero temp3 = new Cajero(mostrador, "Richtofen", 13579246, 'h', 20000, true, 17);
		Limpiador temp4 = new Limpiador("Pablo", 93718573, 'o', 15000, true, 25);
		Repositor temp5 = new Repositor("Pabla", 74829105, 'o', 25000, true, 20);

		// Ejemplo de uso: mostrar y añadir empleados
		sisgest.addEmpleado(temp1);
		sisgest.addEmpleado(temp2);
		sisgest.addEmpleado(temp3);
		sisgest.addEmpleado(temp4);
		sisgest.addEmpleado(temp5);
		sisgest.addEmpleado(temp1);

		System.out.println(sisgest.verEmpleados());

		// Ejemplo de uso: usar empleado variable para funciones especificas del empleado. no hay nada que mostrar ahora
		if (sisgest.getEAdministrativos().get(0) instanceof Secretario trabajo){
            trabajo.listarEntrevistas();
		}

		// Ejemplo de uso: usar empleado variable para modificar cosas de la clase gestora misma. por ahora no funciona :/
		System.out.println(sisgest.verEmpleados(0));

		if (sisgest.getEAdministrativos().get(1) instanceof RRHH trabajo){
			trabajo.darAumento(sisgest.getEPersonal().get(1), 10000);
		}

		System.out.println(sisgest.verEmpleados(0));





	}

}
