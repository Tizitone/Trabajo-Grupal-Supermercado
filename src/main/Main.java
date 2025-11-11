package main;

import almacenamiento.Estanteria;
import almacenamiento.Mostrador;
import clientes.Cliente;
import clientes.TarjetaMembresia;
import empleados.*;
import enumerators.Membresia;
import registros.CV;
import registros.Entrevista;

public class Main {

	public static void main(String[] args) {
		// Administrativos
		RRHH recursos = new RRHH("Pedro", 47839859, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");
		Secretario secretario = new Secretario("Juan", 32685938, 'h', 127000, true, 5, "popcap@gmail.com", "popcap27");

		// Personal
		Mostrador mostrador = new Mostrador();
		Estanteria estanteria = new Estanteria(120);
		Cajero cajero = new Cajero(mostrador, "Juan", 32685938, 'h', 100000, true, 5);
		Limpiador limpiador = new Limpiador("Carla", 44575848, 'm', 50000, true, 3);
		Repositor repositor = new Repositor("juanita", 36888888, 'm', 90000, true, 5);

		// Entrevistas y Curriculums
		CV cv = new CV("Raul", "Perez", 24, 25879312, "2236837702", "raulperez@gmail.com", 'h');
		Entrevista e = new Entrevista(9, 12, 2026, 15, 30, cv, "");
		CV cv2 = new CV("Mario", "Rodriguez", 23,25879354, "2236778090", "marioRodriguez@gmail.com", 'h');
		Entrevista e2 = new Entrevista(9, 12, 2026, 15, 45, cv2, "");
		CV cv3 = new CV("Geremias", "Rodriguez", 23, 25123354, "2234568890", "gereRodriguez@gmail.com", 'h');
		Entrevista e3 = new Entrevista(9, 12, 2026, 16, 0, cv3, "");

		// Agendar y ver entrevistas
		secretario.agendarEntrevista(e2);
		secretario.agendarEntrevista(e);
		secretario.agendarEntrevista(e3);
		recursos.asignarEntrevistas();
		System.out.println(recursos.listarEntrevistas());

		recursos.asignarEntrevistas();
		System.out.println(recursos.listarEntrevistas());

		// Ver empleados
		System.out.println(recursos.toString() + "\n");
		System.out.println(secretario.toString() + "\n");
		System.out.println(cajero.toString() + "\n");
		System.out.println(limpiador.toString() + "\n");
		System.out.println(repositor.toString() + "\n");

		/// simular rendimiento de empleados personales.

		cajero.setProductividad(35); // 35 personas atendidas.
		repositor.setProductividad(40); // 40 veces que conto stock y repuso estanterias.
		limpiador.confirmarTiendaLimpiada(mostrador);
		limpiador.confirmarEstanteriaLimpiada(estanteria);

		/// Pruebas: GENERAL EMPLEADO.
		System.out.println("\n\n\t\u001B[32mPruebas Generales: Empleado. \u001B[0m\n");

		System.out.println("Salario empleado RRHH: " + recursos.calcularSalario());
		System.out.println("Salario empleado secretario: " + secretario.calcularSalario());
		System.out.println("Salario empleado Cajero: " + cajero.calcularSalario());
		System.out.println("Salario empleado Limpiador: " + limpiador.getSalario());
		System.out.println("Salario empleado Repositor: " + repositor.calcularSalario());

		/// Pruebas: GENERAL ADMINISTRATIVO.
		System.out.println("\n\n\t\u001B[32mPruebas Generales: Administrativo. \u001B[0m\n");

		secretario.cambiarContrasenia("popcap28", "papafrita");
		recursos.cambiarContrasenia("popcap27", "papafrita22");

		String mailMal = "malingresado@gmail.com";
		String mailBien = "popcap@gmail.com";
		String contrasenia = "popcap27";
		if (secretario.verificarCorreo(mailMal) && secretario.verificarContrasenia(contrasenia)){
			secretario.recuperarContrasenia(mailMal, 32685938, "papafrita22");
			secretario.recuperarContrasenia(mailBien, 32685938, "papafrita22");
		}

		if (recursos.verificarCorreo(mailMal) && recursos.verificarContrasenia(contrasenia)){
			recursos.recuperarContrasenia(mailBien, 32685938, "papafrita22");
			recursos.recuperarContrasenia(mailBien, 47839859, "papafrita22");
		}

		/// Pruebas: RRHH.
		System.out.println("\n\n\t\u001B[32mPruebas: RRHH. \u001B[0m\n");

		recursos.asignarEntrevistas();
		recursos.listarEntrevistas();

		System.out.println("Salario empleado secretario: " + secretario.calcularSalario());
		recursos.darAumentoPorcentaje(secretario, 10);
		System.out.println("Salario empleado secretario: " + secretario.calcularSalario());

		System.out.println("Salario empleado secretario: " + secretario.calcularSalario());
		recursos.darAumento(secretario, 300);
		System.out.println("Salario empleado secretario: " + secretario.calcularSalario());

		/// Pruebas: Secretario.
		System.out.println("\n\n\t\u001B[32mPruebas: Secretario. \u001B[0m\n");

		System.out.println(secretario.listarEntrevistas());
		if (secretario.agendarEntrevista(e))
			System.out.println("Entrevista agendada correctamente.");
		else System.out.println("\u001B[31mEntrevista ya existe.\u001B[0m");
		System.out.println(secretario.listarEntrevistas());

		/// Puebas: Cajero.
		System.out.println("\n\n\t\u001B[32mPruebas: Cajero. \u001B[0m\n");

		TarjetaMembresia miembro = new TarjetaMembresia(Membresia.cobre);
		Cliente cliente = new Cliente(15000, miembro);

		cajero.atenderCliente();
		cajero.atenderMiembro(cliente);

		System.out.println(cajero.listarVentas());

		// TODO: comprobar el resto de metodos.

		/// Pruebas: Limpiador.
		System.out.println("\n\n\t\u001B[32mPruebas: Limpiador. \u001B[0m\n");

		// TODO: comprobar metodos limpiador.

		/// Pruebas: Repositor.
		System.out.println("\n\n\t\u001B[32mPruebas: Repositor. \u001B[0m\n");

		// TODO: comprobar metodos Repositor.

	}
}