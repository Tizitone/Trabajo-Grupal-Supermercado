package main;

import java.util.ArrayList;
import java.util.Scanner;

import enumerators.ETipoMedida;
import excepciones.InvalidDateException;
import org.json.JSONArray;
import org.json.JSONObject;

import almacenamiento.*;
import clientes.*;
import empleados.*;
import enumerators.Membresia;
import gestion.JsonGestor;
import registros.CV;
import registros.Entrevista;

public class Main {

	// Arreglos Estaticos
	private static ArrayList<Personal> personal = new ArrayList<>();
	private static ArrayList<Administrativo> administrativos = new ArrayList<>();
	private static ArrayList<Producto> producto = new ArrayList<>();
	private static ArrayList<Estanteria> estanterias = new ArrayList<>();
	private static Almacenamiento almacenamiento = new Almacenamiento("San Enrrique 1846",500);
	private static Mostrador m = new Mostrador();

	// Getters
	public static ArrayList<Personal> getPersonal() {
		return personal;
	}

	public static ArrayList<Administrativo> getAdministrativos() {
		return administrativos;
	}

	public static ArrayList<Producto> getProducto() {
		return producto;
	}

	public static ArrayList<Estanteria> getEstanterias() {
		return estanterias;
	}

	public static Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}

	public static Mostrador getM() {
		return m;
	}

	// Menu del sistema.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		final String archivoPersonal = "Trabajo-Grupal-Supermercado-master/personal.json";
		final String archivoAdministrativo = "Trabajo-Grupal-Supermercado-master/administrativo.json";
		final String archivoAlmacenamiento = "Trabajo-Grupal-Supermercado-master/almacenamiento.json";
		final String archivoProducto = "Trabajo-Grupal-Supermercado-master/productos.json";
		final String archivoEstanteria = "Trabajo-Grupal-Supermercado-master/estanteria.json";

		Estanteria estanteria = new Estanteria(120);
		Cajero cajero = new Cajero(m, "Juan", 32685938, 'h', 100000, true, 5);
		Limpiador limpiador = new Limpiador("Carla", 44575848, 'm', 50000, true, 3);
		Repositor repositor = new Repositor("juanita", 36888888, 'm', 90000, true, 5);
		RRHH recursos = new RRHH("Pedro", 47839859, 'h', 127000, true, 5, "popcap1@gmail.com", "popcap27");
		Secretario secretario = new Secretario("Juan", 32685938, 'h', 127000, true, 5, "popcap2@gmail.com", "popcap27");

		cargarJSONPersonal(personal,archivoPersonal);
		cargarJSONAdministrativo(administrativos,archivoAdministrativo);
		cargarJSONProducto(producto,archivoProducto);
		cargarJSONEstanteria(estanterias,archivoEstanteria);
		agregarAlMostrador();

		char continuar = 's';
		do {
			switch (bienvenida()) {
				case 1:
					menuPersonal();
					break;
				case 2:
					menuAdministrativo();
					break;
				case 3:
					menuGestor();
					JsonGestor.guardarListaJSON(producto,archivoProducto);
					JsonGestor.guardarListaJSON(personal,archivoPersonal);
					JsonGestor.guardarListaJSON(administrativos,archivoAdministrativo);
					JsonGestor.guardarListaJSON(estanterias,archivoEstanteria);
					break;
				case 0:
					System.out.println("Hasta pronto...");
					break;
				default:
					System.out.println("Opcion invalida cerrando...");
					break;
			}
			System.out.println("Desea continuar en el menu?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while (continuar == 's');
	}
	private static void cargarJSONPersonal(ArrayList<Personal> personal,String archivo) {
		// Leer el contenido JSON
		JSONArray jArray = new JSONArray(JsonUtiles.leerUnJson(archivo));

		// Recorrer los objetos
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb = jArray.getJSONObject(i);

			// Obtener tipo (por ejemplo: "Cajero", "Limpiador", etc.)
			String tipo = jb.getString("tipo");

			Personal p = null;

			// Crear el objeto correspondiente según el tipo
			switch (tipo) {
				case "Cajero":
					p = new Cajero(m);
					break;
				case "Limpiador":
					p = new Limpiador();
					break;
				case "Repositor":
					p = new Repositor();
					break;
				default:
					System.out.println("Tipo de empleado desconocido: " + tipo);
					continue;
			}
			// Llenar datos usando tu método toObject
			p.toObject(jb);

			// Agregar a la gestión
			personal.add(p);
		}
	}
	private static void cargarJSONAdministrativo(ArrayList<Administrativo> administrativo, String archivo) {
		// Leer el contenido JSON
		JSONArray jArray = new JSONArray(JsonUtiles.leerUnJson(archivo));

		// Recorre los objetos
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb = jArray.getJSONObject(i);

			// Obtiene tipo (por ejemplo: "Cajero", "Limpiador", etc.)
			String tipo = jb.getString("tipo");

			Administrativo a = null;

			// Crea el objeto correspondiente según el tipo
			switch (tipo) {
				case "Secretario":
					a = new Secretario();
					break;
				case "RRHH":
					a = new RRHH();
					break;

				default:
					System.out.println("Tipo de empleado desconocido: " + tipo);
					continue;
			}

			// Llenar datos usando el metodo toObject
			a.toObject(jb);

			// Agregar a la gestión
			administrativo.add(a);
		}
	}
	private static void cargarJSONProducto(ArrayList<Producto> producto, String archivo) {
		// Lee el contenido JSON
		JSONArray jArray = new JSONArray(JsonUtiles.leerUnJson(archivo));

		// Recorre los objetos
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb = jArray.getJSONObject(i);

			Producto a = new Producto();

			// Llena datos usando el metodo toObject
			a.toObject(jb);

			// Agrega a la gestión
			producto.add(a);
		}
	}
	private static void cargarJSONEstanteria(ArrayList<Estanteria> producto, String archivo) {
		// Lee el contenido JSON
		JSONArray jArray = new JSONArray(JsonUtiles.leerUnJson(archivo));

		// Recorre los objetos
		for (int i = 0; i < jArray.length(); i++) {
			JSONObject jb = jArray.getJSONObject(i);

			Estanteria e = new Estanteria();

			// Llena datos usando el metodo toObject
			e.toObject(jb);

			// Agrega a la gestión
			estanterias.add(e);
		}
	}
	private static void agregarAlMostrador()
	{
		for(Producto p: producto)
		{
			m.agregarArticulos(p);
		}
	}
	private static void menuPersonal()
	{
		Scanner input = new Scanner(System.in);
		char continuar = 's';

		do {
			int seleccion = seleccionPersonal();
			System.out.println("Ingrese su DNI: ");
			int dni = input.nextInt();
			input.nextLine();

			Personal encontrado = null;
			for (Personal entry : personal) {
				if (entry.getDNI() == dni) {
					encontrado = entry;

					break;
				}
			}
			if (encontrado == null) {
				seleccion = 0;
			}

			switch (seleccion) {
				case 1:
					Cajero c = new Cajero();
					if (encontrado instanceof Cajero) {
						c = (Cajero) encontrado;
						System.out.println("Bienvenido " + c.getNombre() + "!");
						menuCajero(c);
					} else {
						System.out.println("Ese dni no corresponde a su area");
					}

					break;
				case 2:
					Limpiador l = new Limpiador();
					if (encontrado instanceof Limpiador) {
						l = (Limpiador) encontrado;
						System.out.println("Bienvenido " + l.getNombre() + "!");
						menuLimpiador(l);
					} else {
						System.out.println("Ese dni no corresponde a su area");
					}
					break;
				case 3:
					Repositor r = new Repositor();
					if (encontrado instanceof Repositor) {
						r = (Repositor) encontrado;
						System.out.println("Bienvenido " + r.getNombre() + "!");
						menuRepositor(r);
					} else {
						System.out.println("Ese dni no corresponde a su area");
					}
					break;
				default:
					System.out.println("No se encontro el Personal con el dni");
					break;
			}
			System.out.println("Desea intentar entrar con otro dni?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while (continuar == 's');
	}
	private static void menuCajero(Cajero c)
	{

		Scanner input = new Scanner(System.in);
		char continuar = 'n';
		do {
			switch(opcionCajero())
			{
				case 1:
					System.out.println(c.listarVentas());
					break;
				case 2:
					char miembroContinuar = 's', cliente = 'n';
					System.out.println("El cliente es miembro?(s/n)");
					cliente = input.nextLine().toLowerCase().charAt(0);

					System.out.println("recibiendo productos: ");
					while(miembroContinuar == 's')
					{
						System.out.println("Ingrese el codigo del producto");
						String codigo = input.nextLine();
						System.out.println("Ingrese la cantidad a vender del mismo");
						int cant = input.nextInt();
						input.nextLine();
						c.venderProducto(codigo, cant);
						System.out.println("Desea seguir vendiendo productos? (s/n)");
						miembroContinuar = input.nextLine().toLowerCase().charAt(0);
					}
					if(cliente == 's')
					{
						System.out.println("Ingrese el DNI del cliente miembro:");
						int dni = input.nextInt();
						input.nextLine();
						c.atenderMiembro(c.buscarMiembro(dni));
					}
					else
					{
						c.atenderCliente();
					}
					System.out.println(c.registrarCompras());
					break;
				case 3:
					System.out.println("Ingrese el nombre del nuevo cliente");
					String nombre = input.nextLine();
					System.out.println("Ingrese el dni del nuevo cliente");
					int dni = input.nextInt();
					System.out.println("Seleccione el nivel de membresía:");
					System.out.println("(1) Cobre");
					System.out.println("(2) Plata");
					System.out.println("(3) Oro");

					Membresia membresia = opcionMembresia();
					c.agregarCliente(new Cliente(nombre,dni,new TarjetaMembresia(membresia)));
					break;
			}
			System.out.println("Desea salir?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);

		}while(continuar=='n');
	}
	private static Membresia opcionMembresia()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese una opcion: ");
		System.out.println("(1)-Membresia cobre");
		System.out.println("(2)-Membresia plata");
		System.out.println("(3)-Membresia oro");
		int nivelMembresia = input.nextInt();
		input.nextLine();
		Membresia m = null;
		switch(nivelMembresia)
		{
			case 1:
				m = Membresia.cobre;
				break;
			case 2:
				m = Membresia.plata;
				break;
			case 3:
				m = Membresia.oro;
				break;
		}
		return m;
	}
	private static int opcionCajero()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese una opcion");
		System.out.println("(1)-Listar ventas");
		System.out.println("(2)-Atender cliente");
		System.out.println("(3)-Agregar miembro");
		System.out.println("(0) Salir");
		return input.nextInt();
	}
	private static void menuLimpiador(Limpiador l)
	{
		Scanner input = new Scanner(System.in);
		char continuar = 's';
		do {
			switch(opcionLimpiador())
			{
				case 1:
					System.out.println(l.listarEstanteriasSucias(almacenamiento));
					break;
				case 2:
					System.out.println("ingrese la id de la estanteria limpiada:");
					String id = input.nextLine();
					l.confirmarEstanteriaLimpiada(l.buscarEstanteria(almacenamiento, id));
					break;
				case 3:
					if(l.confirmarTiendaLimpiada(m))
					{
						System.out.println("Tienda limpia!");
					}
					else
					{
						System.out.println("La tienda no esta sucia!");
					}
					break;
				default:
					System.out.println("La opcion seleccionada no coincide con una disponible!");
					break;
			}
			System.out.println("Desea realizar alguna otra opcion?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while(continuar == 's');

	}
	private static int opcionLimpiador()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingresar una opcion");
		System.out.println("(1)-Listar estanterias sucias");
		System.out.println("(2)-Confirmar estanteria limpia");
		System.out.println("(3)-Confirmar tienda limpia");
		return input.nextInt();
	}
	private static void menuRepositor(Repositor r)
	{
		Scanner input = new Scanner(System.in);

		input.nextLine();

		char continuar = 's';
		do
		{
			switch(opcionRepositor())
			{
				case 1:
					System.out.println(r.listarEstantes(almacenamiento));
					break;
				case 2:
					System.out.println("Ingrese el codigo de la estanteria: ");
					String codigoEstanteria = input.nextLine();
					System.out.println("Ingrese el codigo del producto: ");
					String codigoProducto = input.nextLine();
					System.out.println("Ingrese la cantidad del producto a reponer: ");
					int cant = input.nextInt();
					input.nextLine();
					r.reponerProducto(m, almacenamiento.buscarEstanteriaPorID(codigoEstanteria), codigoProducto, cant);
					break;
				case 3:
					r.contarStock();
					System.out.print("Inventario hecho!");
					break;
			}
			System.out.println("Desea continuar realizando actividades?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while(continuar == 's');
	}
	private static int opcionRepositor()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Ingrese una opcion:");
		System.out.println("(1)-Listar estantes");
		System.out.println("(2)-Reponer producto");
		System.out.println("(3)-Hacer inventario");
		return input.nextInt();
	}

	private static void menuAdministrativo(){

		Scanner input = new Scanner(System.in);
		char continuar ='s';

		do {
			int seleccion = seleccionAdministrativo();
			System.out.println("Ingrese su correo: ");
			String correo = input.nextLine();
			System.out.println("Ingrese su contrasenia: ");
			String contrasenia = input.nextLine();

			Administrativo encontrado = null;
			for (Administrativo entry : administrativos) {
				if (entry.getCorreo().equalsIgnoreCase(correo) && entry.getContrasenia().equals(contrasenia)) {
					encontrado = entry;
					break;
				}
			}
			if (encontrado == null) {
				System.out.println("No se encontro el Administrativo con esa cuenta");
				seleccion = 0;
			}

			switch (seleccion) {
				case 1:
					Secretario s = new Secretario();
					if (encontrado instanceof Secretario) {
						s = (Secretario) encontrado;
						System.out.println("Bienvenido " + s.getNombre() + "!");
						menuSecretario(s);
					}
					break;
				case 2:
					RRHH r = new RRHH();
					if (encontrado instanceof RRHH) {
						r = (RRHH) encontrado;
						System.out.println("Bienvenido " + r.getNombre() + "!");
						menuRRHH(r);
					}

					break;
				default:
					break;
			}
			System.out.println("Desea intentar con otra cuenta?(s/n)");
			continuar = input.nextLine().charAt(0);
		}while(continuar=='s');
	}

	private static void menuSecretario(Secretario s) {
		Scanner input = new Scanner(System.in);
		char continuar = 's';
		do {
			switch(opcionSecretario())
			{
				case 1:
					s.agendarEntrevista(crearEntrevista());
					System.out.println("Entrevista agendada");
					break;
				case 2:
					System.out.println("Ingrese el dia:");
					int dia = input.nextInt();
					input.nextLine();
					System.out.println("Ingrese el mes:");
					int mes = input.nextInt();
					input.nextLine();
					System.out.println("Ingrese el anio:");
					int anio = input.nextInt();
					input.nextLine();
					System.out.println("Ingrese el hora:");
					int hora = input.nextInt();
					input.nextLine();
					System.out.println("Ingrese el minutos:");
					int minutos = input.nextInt();
					input.nextLine();


					if(s.darDeBajaEntrevista(dia, mes, anio, hora, minutos))
					{
						System.out.println("entrevista eliminada exitosamente!");
					}
					else
					{
						System.out.println("no se pudo dar de baja la entrevista");
					}
					break;
				case 3:
					System.out.println("Ingrese el dni para buscar el curriculum:");
					int dni = input.nextInt();
					input.nextLine();
					if(s.descartarCV(s.buscarCv(dni)))
					{
						System.out.println("El dni se borro con exito");
					}
					else
					{
						System.out.println("No se pudo descartar el cv");
					}
					break;
				case 4:
					System.out.println(s.listarEntrevistas());
					break;
				case 5:
					System.out.println(s.listarCV());
					break;
				case 6:
					System.out.println("ingrese su correo");
					String correo = input.nextLine();
					System.out.println("ingrese su contraseña");
					String contrasenia = input.nextLine();
					if(s.verificarCorreo(correo) && s.verificarContrasenia(contrasenia))
					{
						System.out.println("ingrese su ingrese su nueva contraseña");
						String nuevaContrasenia = input.nextLine();
						s.cambiarContrasenia(contrasenia, nuevaContrasenia);
					}
					else
					{
						System.out.println("correo o contraseña invalidas");
					}

					break;
				default:
					System.out.println("Ninguna opcion es una disponible");
					break;
			}
			System.out.println("Desea continuar?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while(continuar == 's');
	}

	/**
	 * Metodo que pide toda la información para crear una entrevista e intenta hacerlo, de fallar a causa de una excepcion retornará null.
	 *
	 * @return una {@link Entrevista} o null
	 */
	private static Entrevista crearEntrevista() {

			Scanner input = new Scanner(System.in);
			System.out.println("Ingrese el dia");
			int dia = input.nextInt();
			input.nextLine();
			System.out.println("Ingrese el mes");
			int mes = input.nextInt();
			input.nextLine();
			System.out.println("Ingrese el anio");
			int anio = input.nextInt();
			input.nextLine();
			System.out.println("Ingrese la hora");
			int hora = input.nextInt();
			input.nextLine();
			System.out.println("Ingrese el minuto");
			int minuto = input.nextInt();
			input.nextLine();

			CV cv = crearCurriculum();

			System.out.println("Ingrese una obvservacion");
			String informe = input.nextLine();

		try{
			return new Entrevista(dia,mes,anio,hora,minuto,cv,informe);

		}catch (InvalidDateException ide){
			ide.getMessage();
			return null;
		}
    }

	private static CV crearCurriculum()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el nombre del curriculum");
		String nombre = input.nextLine();
		System.out.println("Ingrese el apellido");
		String apellido = input.nextLine();
		System.out.println("Ingrese la edad");
		int edad = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese el dni");
		int dni = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese el correo");
		String correo = input.nextLine();
		System.out.println("Ingrese el genero");
		char genero = input.nextLine().toLowerCase().charAt(0);
		System.out.println("Ingrese el telefono");
		String telefono = input.nextLine();

		return new CV(nombre,apellido,dni,edad,telefono,correo,genero);
	}

	private static int opcionSecretario() {
		Scanner input = new Scanner(System.in);

		System.out.println("Ingrese una opcion");
		System.out.println("(1)-Agendar entrevista");
		System.out.println("(2)-Dar de baja una entrevista");
		System.out.println("(3)-Dar de baja curriculum");
		System.out.println("(4)-Listar entrevistas");
		System.out.println("(5)-Listar curriculums");
		System.out.println("(6)-cambiar contraseña");

		return input.nextInt();
	}

	private static void menuRRHH(RRHH r) {
		Scanner input = new Scanner(System.in);
		switch(opcionesRRHH()) {
			case 1:
				r.asignarEntrevistas();
				System.out.println("Las entrevistas han sido actualizadas");
				break;
			case 2:
				Empleado e = buscarEmpleado();
				if(e != null)
				{
					System.out.println("Ingresar el monto a aumentar");
					int monto = input.nextInt();
					r.darAumento(e, monto);
				}
				else
				{
					System.out.println("no se encontro el empleado");
				}
				break;
			case 3:
				Empleado em = buscarEmpleado();
				if(em != null)
				{
					System.out.println("Ingresar el porcentaje a aumentar");
					int porcentaje = input.nextInt();
					r.darAumentoPorcentaje(em, 0);
				}
				else
				{
					System.out.println("no se encontro el empleado");
				}

				break;
			case 4:
				System.out.println(r.listarEntrevistas());
				break;
			case 5:
				System.out.println(r.listarEntrevistasPendientes());
				break;
			case 6:
				System.out.println("ingrese su correo");
				String correo = input.nextLine();
				System.out.println("ingrese su contraseña");
				String contrasenia = input.nextLine();
				if(r.verificarCorreo(correo) && r.verificarContrasenia(contrasenia))
				{
					System.out.println("ingrese su ingrese su nueva contraseña");
					String nuevaContrasenia = input.nextLine();
					r.cambiarContrasenia(contrasenia, nuevaContrasenia);
				}
				else
				{
					System.out.println("correo o contraseña invalidas");
				}
				break;

			default:
				System.out.println("La opcion elegida no coincide con una disponible");
				break;
		}
		r.cambiarContrasenia(null, null);
	}
	private static int opcionesRRHH()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese una opcion");
		System.out.println("(1)-Actualizar entrevistas");
		System.out.println("(2)-Dar aumento fijo a empleado");
		System.out.println("(3)-Dar aumento porcentual a empleado");
		System.out.println("(4)-Listar entrevistas");
		System.out.println("(5)-Listar entrevistas pendientes");
		System.out.println("(6)-Cambiar contraseña");

		return input.nextInt();
	}
	private static Empleado buscarEmpleado()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el dni del empleado");
		int dni = input.nextInt();
		Empleado encontro = null;
		for(Empleado o : personal)
		{
			if(o.getDNI()==dni)
			{
				encontro = o;
			}
		}
		for(Empleado o : administrativos)
		{
			if(o.getDNI()==dni)
			{
				encontro = o;
			}
		}
		return encontro;
	}
	private static int opcionesGestor()
	{
		Scanner input = new Scanner(System.in);

		System.out.println("Ingrese una opcion");
		System.out.println("(1)-agregar empleado");
		System.out.println("(2)-agregar almacenamiento");
		System.out.println("(0)-salir");
		return input.nextInt();
	}
	private static void crearEmpleado() {
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el tipo de empleado");
		System.out.println("(1)-Personal");
		System.out.println("(2)-Administrativo");
		int seleccion = input.nextInt();
		input.nextLine();

		System.out.println("Ingrese el nombre");
		String nombre = input.nextLine();
		System.out.println("Ingrese el dni");
		int dni = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese el genero");
		char genero = input.nextLine().charAt(0);
		System.out.println("Ingrese el salario");
		int salario = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese el antiguedad");
		int antiguedad = input.nextInt();
		input.nextLine();

		switch (seleccion) {
			case 1:
				crearPersonal(nombre, dni, genero, salario, antiguedad);
				break;
			case 2:
				crearAdministrativo(nombre, dni, genero, salario, antiguedad);
				break;
			default:
				break;

		}
	}

	private static void crearAdministrativo(String nombre, int dni, char genero, int salario, int antiguedad){
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el tipo de empleado administrativo\n(1) - Secretario\n(2) - RRHH");

		int seleccion = input.nextInt();

		System.out.println("Ingrese el correo del empleado: ");
		String correo = input.next();
		System.out.println("Ingrese la contraseña del empleado:");
		String contraseña = input.next();

		char continuar = 's';
		do {
			switch (seleccion) {
				case 1:
					administrativos.add(new Secretario(nombre, dni, genero, salario, true, antiguedad, correo, contraseña));
					break;

				case 2:
					administrativos.add(new RRHH(nombre, dni, genero, salario, true, antiguedad, correo, contraseña));
					break;
			}
			System.out.println("Desea continuar agregando empleados administrativos? (s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);

		}while (continuar == 's');

	}

	private static void crearPersonal(String nombre,int dni,char genero,int salario,int antiguedad) {
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el tipo de Personal");
		System.out.println("(1)-Cajero");
		System.out.println("(2)-Limpiador");
		System.out.println("(3)-Repositor");
		int seleccion = input.nextInt();
		input.nextLine();
		char continuar = 's';
		do {
			switch (seleccion) {
				case 1:
					personal.add(new Cajero(m, nombre, dni, genero, salario, true, antiguedad));
					break;
				case 2:
					personal.add(new Limpiador(nombre, dni, genero, salario, true, antiguedad));
					break;
				case 3:
					personal.add(new Repositor(nombre, dni, genero, salario, true, antiguedad));
					break;
			}
			System.out.println("Desea continuar agregando personal?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while (continuar == 's');

	}
	public static void menuGestor()
	{
		switch (opcionTipoObjeto())
		{
			case 1:
				crearEmpleado();
				break;
			case 2:
				crearAlmacenamiento();
				break;

		}
	}
	private static int opcionTipoObjeto()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese una opcion");
		System.out.println("(1)-Empleado");
		System.out.println("(2)-Almacenamiento");
		return input.nextInt();
	}
	private static void crearAlmacenamiento()
	{
		Scanner input = new Scanner(System.in);

		switch (opcionesAlmacenamiento())
		{
			case 1:
				crearEstanteria();
				break;
			case 2:
				crearProducto();
				break;
		}
	}
	private static void crearProducto()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese el nombre del producto");
		String nombre = input.nextLine();
		System.out.println("Ingrese la marca del producto");
		String marca  = input.nextLine();
		ETipoMedida medida = seleccionarMedida();
		System.out.println("Ingrese el peso del producto");
		double peso = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese una descripcion del producto");
		String descripcionAdicional = input.nextLine();
		System.out.println("Ingrese el precio unitario del producto");
		double precioUnitario = input.nextInt();
		input.nextLine();
		System.out.println("Ingrese el stock del producto");
		int stock = input.nextInt();
		input.nextLine();

		producto.add(new Producto(nombre,marca,medida,peso,descripcionAdicional,precioUnitario,stock));

	}
	private static ETipoMedida seleccionarMedida()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese un tipo de medida(1-kg,2-g,3-l,4-ml)");
		int medida = input.nextInt();
		ETipoMedida  e= null;
		switch (medida)
		{
			case 1:
				e = ETipoMedida.KILOGRAMO;
				break;
			case 2:
				e =ETipoMedida.GRAMO;
				break;
			case 3:
				e = ETipoMedida.LITRO;
				break;
			case 4:
				e = ETipoMedida.MILILITRO;
				break;
		}

		return e;
	}
	private static void crearEstanteria()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("ingrese la capacidad de la estanteria");
		int capacidad = input.nextInt();
		estanterias.add(new Estanteria(capacidad));
	}
	private static int opcionesAlmacenamiento()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese una opcion");
		System.out.println("(1)-crear Estanteria");
		System.out.println("(2)-Ingresar Producto");
		return input.nextInt();
	}

	private static int bienvenida()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("------Bienvenido----");
		System.out.println("ingrese su area: ");
		System.out.println("(1)-Personal");
		System.out.println("(2)-Administrativo");
		System.out.println("(3)-Gestor");
		System.out.println("(0)-salir");

		return input.nextInt();
	}
	private static int seleccionPersonal()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Seleccione su area:");
		System.out.println("(1)-Cajero");
		System.out.println("(2)-Limpiador");
		System.out.println("(3)-Repositor");
		return input.nextInt();
	}
	private static int seleccionAdministrativo()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Seleccione su area:");
		System.out.println("(1)-Secretario");
		System.out.println("(2)-RRHH");
		System.out.println("(0)-salir");
		return input.nextInt();
	}
}
