package main;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import enumerators.ETipoMedida;
import excepciones.InvalidDNIException;
import excepciones.InvalidDateException;

import almacenamiento.*;
import clientes.*;
import empleados.*;
import enumerators.Membresia;
import excepciones.InvalidLengthException;
import gestion.Gestion;
import gestion.JsonGestor;
import registros.CV;
import registros.Entrevista;

public class Main {

	// Arreglos Estaticos
	static Almacenamiento almacen = new Almacenamiento(50);
	static Gestion<Administrativo> administrativo = new Gestion<>();
	static Gestion<Personal> personal = new Gestion<>();
	static Gestion<Almacenamiento> almacenamiento = new Gestion<>();

	private static Mostrador m = new Mostrador();

	private final static String archivoPersonal = "personal.json";
	private final static String archivoAdministrativo = "administrativo.json";
	private final static String archivoAlmacenamiento = "almacenamiento.json";
	private final static String archivoMostrador = "mostrador.json";

	// Menu del sistema.
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		m.toObject(archivoMostrador);
		personal.getListaGestora().addAll(JsonGestor.cargarListaJSON(archivoPersonal));
		administrativo.getListaGestora().addAll(JsonGestor.cargarListaJSON(archivoAdministrativo));
		almacenamiento.getListaGestora().addAll(JsonGestor.cargarListaJSON(archivoAlmacenamiento));

		char continuar;
		do {
			System.out.println("------Bienvenido----\ningrese su area:\n(1)-Personal\n(2)-Administrativo\n(3)-Gestor\n(0)-salir");
			switch (validarInt(0,3)) {
				case 1:
					menuPersonal();
					break;

				case 2:
					menuAdministrativo();
					break;

				case 3:
					menuGestor();
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

	private static void menuPersonal()
	{
		Scanner input = new Scanner(System.in);
		char continuar;

		do {
			System.out.println("Seleccione su area:\n(1)-Cajero\n(2)-Limpiador\n(3)-Repositor");
			int seleccion = validarInt(3);

			System.out.println("Ingrese su DNI: ");
			int dni = validarDNI();

			Personal encontrado = null;
			for (Personal entry : personal.getListaGestora()) {
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
					if (encontrado instanceof Cajero c) {
						System.out.println("Bienvenido/a " + c.getNombre() + "!");
						menuCajero(c);
					} else
						System.out.println("Ese dni no corresponde a su area");
					break;

				case 2:
					if (encontrado instanceof Limpiador l) {
						System.out.println("Bienvenido/a " + l.getNombre() + "!");
						menuLimpiador(l);
					} else
						System.out.println("Ese dni no corresponde a su area");
					break;

				case 3:
					if (encontrado instanceof Repositor r) {
						System.out.println("Bienvenido/a " + r.getNombre() + "!");
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

	private static void menuCajero(Cajero c) {

		Scanner input = new Scanner(System.in);
		char continuar;
		do {
			System.out.println("Ingrese una opcion\n(1)-Listar ventas\n(2)-Atender cliente\n(3)-Agregar miembro\n(0) Salir");
			switch(validarInt(3)) {

				case 1:
					System.out.println(c.listarVentas());
					break;

				case 2:
					char continuarAtendiendo, cliente;
					System.out.println("El cliente es miembro?(s/n)");
					cliente = input.nextLine().toLowerCase().charAt(0);

					System.out.println("recibiendo productos: ");
					do{// Ingreso de los productos a vender

						System.out.println("Ingrese el codigo del producto");
						String codigo = validarString(36, 36);


						System.out.println("Ingrese la cantidad a vender del mismo");
						int cant = validarInt(Mostrador.buscarProducto(codigo).getCantEnVenta());

						c.venderProducto(codigo, cant);

						System.out.println("Desea seguir vendiendo productos? (s/n)");
						continuarAtendiendo	 = input.nextLine().toLowerCase().charAt(0);
						m.toJson(archivoMostrador);
					} while(continuarAtendiendo == 's');

					if(cliente == 's') {
						System.out.println("Ingrese el DNI del cliente miembro:");
						int dni = validarDNI();
						c.atenderMiembro(c.buscarMiembro(dni));

					} else c.atenderCliente();

					System.out.println(c.registrarCompras());
					break;

				case 3:
					System.out.println("Ingrese el nombre del nuevo cliente");
					String nombre = validarString(3,12);
					System.out.println("Ingrese el dni del nuevo cliente");
					int dni = validarDNI();
					Membresia membresia = opcionMembresia();

					c.agregarCliente(new Cliente(nombre,dni,new TarjetaMembresia(membresia)));
					break;
			}

			System.out.println("Desea salir?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while(continuar=='n');
	}

	private static Membresia opcionMembresia() {
		System.out.println("""
                            Seleccione el nivel de membresía:
                            (1) Cobre
                            (2) Plata
                            (3) Oro""");

		switch(validarInt(1,3)) {
			case 1:
				return Membresia.cobre;

			case 2:
				return Membresia.plata;

			case 3:
				return Membresia.oro;

			default:
				return null;
		}
	}

	private static void menuLimpiador(Limpiador l) {
		Scanner input = new Scanner(System.in);
		char continuar;
		do {
			System.out.println("""
                Ingresar una opcion
                (1)-Listar estanterias sucias
                (2)-Confirmar estanteria limpia
                (3)-Confirmar tienda limpia""");
			switch(validarInt(1,3)) {

				case 1:
					System.out.println(l.listarEstanteriasSucias(almacenamiento.getListaGestora().getFirst()));
					break;

				case 2:
					System.out.println("ingrese la id de la estanteria limpiada:");
					String id = validarString(36,36);
					l.confirmarEstanteriaLimpiada(l.buscarEstanteria(almacenamiento.getListaGestora().getFirst(), id));
					break;

				case 3:
					if(l.confirmarTiendaLimpiada(m))
						System.out.println("Tienda limpia!");
					else
						System.out.println("La tienda no esta sucia!");
					break;

				default:
					System.out.println("La opcion seleccionada no coincide con una disponible!");
					break;
			}

			System.out.println("Desea realizar alguna otra opcion?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while(continuar == 's');
	}

	private static void menuRepositor(Repositor r) {
		Scanner input = new Scanner(System.in);

		char continuar;
		do {
			System.out.println("""
                Ingrese una opcion:
                (1)-Listar estantes
                (2)-Reponer producto
                (3)-Hacer inventario""");
			switch(validarInt(1,3)) {

				case 1:
					System.out.println(r.listarEstantes(almacenamiento.getListaGestora().getFirst()));
					break;

				case 2:
					System.out.println("Ingrese el codigo de la estanteria: ");
					String codigoEstanteria = validarString(36,36);

					System.out.println("Ingrese el codigo del producto: ");
					String codigoProducto = validarString(36,36);

					System.out.println("Ingrese la cantidad del producto a reponer: ");
					int cant = validarInt();

					r.reponerProducto(m, almacenamiento.getListaGestora().getFirst().buscarEstanteriaPorID(codigoEstanteria), codigoProducto, cant);
					m.toJson(archivoMostrador);
					JsonGestor.guardarListaJSON(almacenamiento.getListaGestora(), archivoAlmacenamiento);
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

	private static void menuAdministrativo(){

		Scanner input = new Scanner(System.in);
		char continuar;

		do {
			System.out.println("""
                    Seleccione su area:
                    (1)-Secretario
                    (2)-RRHH
                    (0)-salir""");
			int seleccion = validarInt(0,2);
			System.out.println("Ingrese su correo: ");
			String correo = validarCorreo();
			System.out.println("Ingrese su contrasenia: ");
			String contrasenia = validarString(6,24);

			Administrativo encontrado = null;
			for (Administrativo entry : administrativo.getListaGestora()) {
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
					Secretario s;
					if (encontrado instanceof Secretario) {
						s = (Secretario) encontrado;
						System.out.println("Bienvenido " + s.getNombre() + "!");
						menuSecretario(s);
					}
					break;

				case 2:
					RRHH r;
					if (encontrado instanceof RRHH) {
						r = (RRHH) encontrado;
						System.out.println("Bienvenido " + r.getNombre() + "!");
						menuRRHH(r);
					}
					break;
			}
			System.out.println("Desea intentar con otra cuenta?(s/n)");
			continuar = input.nextLine().charAt(0);
		}while(continuar=='s');
	}

	private static void menuSecretario(Secretario s) {
		Scanner input = new Scanner(System.in);
		char continuar;
		do {
			System.out.println("""
                    Ingrese una opcion
                    (1)-Agendar entrevista
                    (2)-Dar de baja una entrevista
                    (3)-Dar de baja curriculum
                    (4)-Listar entrevistas
                    (5)-Listar curriculums
                    (6)-cambiar contraseña""");
			switch(validarInt(1,6))
			{
				case 1:
					s.agendarEntrevista(crearEntrevista());
					System.out.println("Entrevista agendada");
					break;
				case 2:
					System.out.println("Ingrese el dia:");
					int dia = validarInt(1,30);

					System.out.println("Ingrese el mes:");
					int mes = validarInt(1,12);

					System.out.println("Ingrese el anio:");
					int anio = validarInt(1950, LocalDate.now().getYear());

					System.out.println("Ingrese el hora:");
					int hora = validarInt(23);

					System.out.println("Ingrese el minutos:");
					int minuto = validarInt(59);

					if(s.darDeBajaEntrevista(dia, mes, anio, hora, minuto))
						System.out.println("Entrevista eliminada exitosamente!");
					else
						System.out.println("No se pudo dar de baja la entrevista");
					break;

				case 3:
					System.out.println("Ingrese el dni para buscar el curriculum:");
					int dni = validarDNI();

					if(s.descartarCV(s.buscarCv(dni))) {
						System.out.println("El dni se borro con exito");
					} else {
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
					String correo = validarCorreo();
					System.out.println("ingrese su contraseña");
					String contrasenia = input.nextLine();
					if(s.verificarCorreo(correo) && s.verificarContrasenia(contrasenia)) {
						System.out.println("ingrese su ingrese su nueva contraseña");
						String nuevaContrasenia = input.nextLine();
						s.cambiarContrasenia(contrasenia, nuevaContrasenia);
					} else {
						System.out.println("Correo o contraseña invalidas");
					}

					break;

				default:
					System.out.println("No se selecciono una opcion dentro del rango");
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

		System.out.println("Ingrese el dia");
		int dia = validarInt(1,30);

		System.out.println("Ingrese el mes");
		int mes = validarInt(1,12);

		System.out.println("Ingrese el anio");
		int anio = validarInt(1950,LocalDate.now().getYear());

		System.out.println("Ingrese la hora");
		int hora = validarInt(23);

		System.out.println("Ingrese el minuto");
		int minuto = validarInt(59);

		CV cv = crearCurriculum();

		System.out.println("Ingrese una obvservacion");
		String informe = validarString(1,250);

		try{
			return new Entrevista(dia,mes,anio,hora,minuto,cv,informe);

		}catch (InvalidDateException ide){
			System.out.println("Ocurrio un error insperado al intentar crear su entrevista, por favor intente de nuevo.\nError de fecha: " + ide.getMessage());
			return crearEntrevista();
		}
	}

	private static CV crearCurriculum()
	{
		System.out.println("Ingrese el nombre del curriculum");
		String nombre = validarString(3,12);

		System.out.println("Ingrese el apellido");
		String apellido = validarString(3,12);

		System.out.println("Ingrese la edad");
		int edad = validarInt(16,118);

		System.out.println("Ingrese el dni");
		int dni = validarDNI();

		System.out.println("Ingrese el correo");
		String correo = validarCorreo();

		System.out.println("Ingrese el genero");
		char genero = validarGenero();

		System.out.println("Ingrese el telefono");
		String telefono = validarString(10,15);

		return new CV(nombre,apellido,dni,edad,telefono,correo,genero);
	}

	private static void menuRRHH(RRHH r) {
		Scanner input = new Scanner(System.in);
		System.out.println("""
                Ingrese una opcion
                (1)-Actualizar entrevistas
                (2)-Dar aumento fijo a empleado
                (3)-Dar aumento porcentual a empleado
                (4)-Listar entrevistas
                (5)-Listar entrevistas pendientes
                (6)-Cambiar contraseña""");
		switch(validarInt(1,6)) {

			case 1:
				r.asignarEntrevistas();
				System.out.println("Las entrevistas han sido actualizadas");
				break;

			case 2:
				Empleado e = buscarEmpleado();
				if(e != null) {
					System.out.println("Ingresar el monto a aumentar");
					int monto = input.nextInt();
					r.darAumento(e, monto);
				} else {
					System.out.println("no se encontro el empleado");
				}
				break;

			case 3:
				Empleado em = buscarEmpleado();
				if(em != null) {
					System.out.println("Ingresar el porcentaje a aumentar");
					r.darAumentoPorcentaje(em, 0);
				} else {
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
				System.out.println("Ingrese su correo");
				String correo = validarCorreo();
				System.out.println("Ingrese su contraseña");
				String contrasenia = validarString(6,24);
				if(r.verificarCorreo(correo) && r.verificarContrasenia(contrasenia)) {
					System.out.println("Ingrese su nueva contraseña");
					String nuevaContrasenia = input.nextLine();
					r.cambiarContrasenia(contrasenia, nuevaContrasenia);
				} else {
					System.out.println("Correo o contraseña invalidas");
				}
				break;

			default:
				System.out.println("La opcion elegida esta fuera de rango.");
				break;
		}
	}

	private static Empleado buscarEmpleado() {
		System.out.println("Ingrese el dni del empleado");
		int dni = validarDNI();
		Empleado encontrado = null;

		for(Empleado o : personal.getListaGestora()) {
			if(o.getDNI() == dni) {
				encontrado = o;
			}
		}
		for(Empleado o : administrativo.getListaGestora()) {
			if(o.getDNI() == dni) {
				encontrado = o;
			}
		}
		return encontrado;
	}

	private static void crearEmpleado() {
		System.out.println("""
                Ingrese el tipo de empleado
                (1)-Personal
                (2)-Administrativo""");
		int seleccion = validarInt(1,2);

		System.out.println("Ingrese el nombre");
		String nombre = validarString(3,12);

		System.out.println("Ingrese el dni");
		int dni = validarDNI();

		System.out.println("Ingrese el genero(h/m/o)");
		char genero = validarGenero();

		System.out.println("Ingrese el salario");
		int salario = validarInt();

		System.out.println("Ingrese el antiguedad");
		int antiguedad = validarInt(0,60);

		switch (seleccion) {

			case 1:
				crearPersonal(nombre, dni, genero, salario, antiguedad);
				break;

			case 2:
				crearAdministrativo(nombre, dni, genero, salario, antiguedad);
				break;
		}
	}

	private static void crearAdministrativo(String nombre, int dni, char genero, int salario, int antiguedad) {
		Scanner input = new Scanner(System.in);
		System.out.println("""
                Ingrese el tipo de empleado administrativo
                (1) - Secretario
                (2) - RRHH""");

		int seleccion = validarInt(1,2);

		System.out.println("Ingrese el correo del empleado: ");
		String correo = validarCorreo();
		System.out.println("Ingrese la contraseña del empleado:");
		String contrasenia = validarString(8,24);

		char continuar;
		do {
			switch (seleccion) {
				case 1:
					administrativo.getListaGestora().add(new Secretario(nombre, dni, genero, salario, true, antiguedad, correo, contrasenia));
					break;

				case 2:
					administrativo.getListaGestora().add(new RRHH(nombre, dni, genero, salario, true, antiguedad, correo, contrasenia));
					break;
			}
			System.out.println("Desea continuar agregando empleados administrativos? (s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while (continuar == 's');
	}

	private static void crearPersonal(String nombre,int dni,char genero,int salario,int antiguedad) {
		Scanner input = new Scanner(System.in);
		System.out.println("""
                Ingrese el tipo de Personal
                (1)-Cajero
                (2)-Limpiador
                (3)-Repositor""");
		char continuar;
		do {
			switch (validarInt(1,3)) {

				case 1:
					personal.getListaGestora().add(new Cajero(m, nombre, dni, genero, salario, true, antiguedad));
					break;

				case 2:
					personal.getListaGestora().add(new Limpiador(nombre, dni, genero, salario, true, antiguedad));
					break;

				case 3:
					personal.getListaGestora().add(new Repositor(nombre, dni, genero, salario, true, antiguedad));
					break;
			}
			System.out.println("Desea continuar agregando personal?(s/n)");
			continuar = input.nextLine().toLowerCase().charAt(0);
		}while (continuar == 's');
	}

	public static void menuGestor() {
		System.out.println("Ingrese su cuenta/correo");
		String correo = validarString(1, 24);

		System.out.println("Ingrese su contraseña");
		String contrasenia = validarString(1,24);

		if(Gestion.validadCuenta(correo, contrasenia)) {
			System.out.println("""
                    Bienvenido admin.
                    Ingrese una opcion:
                    (1)-Agregar
                    (2)-Eliminar
                    (3)-Listar""");
			switch (validarInt(1,3)) {
				case 1:
					menuGestorAgregar();
					break;

				case 2:
					menuGestorEliminar();
					break;

				case 3:
					menuGestorListar();
					break;
			}
			JsonGestor.guardarListaJSON(personal.getListaGestora(),archivoPersonal);
			JsonGestor.guardarListaJSON(administrativo.getListaGestora(),archivoAdministrativo);
			JsonGestor.guardarListaJSON(almacenamiento.getListaGestora(), archivoAlmacenamiento);
		} else
			System.out.println("La cuenta o contraseña no son correctas");
	}

	public static void menuGestorListar() {
		System.out.println("""
                Ingrese que lista desea ver:
                (1)-Personal
                (2)-Administrativo
                (3)-Almacenamiento
                (4)-Mostrador""");
		switch(validarInt(1,4)) {
			case 1:
				System.out.println(personal.listar());
				break;

			case 2:
				System.out.println(administrativo.listar());
				break;

			case 3:
				System.out.println(almacenamiento.listar());
				break;

			case 4:
				System.out.println(Mostrador.listarArticulos());
				break;
		}
	}

	public static void menuGestorAgregar() {
		System.out.println("""
                Ingrese una opcion
                (1)-Empleado
                (2)-Almacenamiento""");
		switch (validarInt(1,2)) {
			case 1:
				crearEmpleado();
				break;

			case 2:
				crearAlmacenamiento();
				break;
		}
	}
	public static void menuGestorEliminar() {
		System.out.println("""
                Ingrese una opcion
                (1)-Empleado
                (2)-Almacenamiento""");
		switch (validarInt(1,2)) {
			case 1:
				eliminarEmpleado();
				break;

			case 2:
				eliminarAlmacenamiento();
				break;
		}
	}

	public static void eliminarEmpleado() {
		System.out.println("Ingrese el dni del Empleado a remover");
		int dni = validarDNI();
		System.out.println("""
                Ingrese el tipo de empleado
                (1)-Personal
                (2)-Administrativo""");
		switch(validarInt(1,2)) {

			case 1:

				if(personal.removerPorIdentificador(Integer.toString(dni))) {
					System.out.println("Personal removido con exito!");
				} else {
					System.out.println("No se encontro al personal con ese dni");
				}
				break;

			case 2:
				if(administrativo.removerPorIdentificador(Integer.toString(dni)))
					System.out.println("Administrativo removido con exito!");
				else
					System.out.println("No se encontro al administrativo con ese dni");
				break;
		}
	}

	public static void eliminarAlmacenamiento() {
		System.out.println("Ingrese el id del producto/estanteria a remover");
		String idSeleccion = validarString(36,36);
		System.out.println("""
                Ingrese el tipo de empleado
                (1)-Personal
                (2)-Administrativo""");
		switch(validarInt(1,2)) {

			case 1:
				if(almacenamiento.getListaGestora().getFirst().removerEstanteria(idSeleccion))
					System.out.println("Personal removido con exito!");
				else
					System.out.println("No se encontro al personal con ese dni");
				break;

			case 2:
				System.out.println("Ingrese la id donde se encuentra el producto: ");
				String estanteria = validarString(36,36);
				if(almacenamiento.getListaGestora().getFirst().buscarEstanteriaPorID(estanteria).eliminarProducto(idSeleccion))
					System.out.println("Producto removido con exito!");
				else
					System.out.println("No se encontro un producto con ese dni");
				break;
		}
	}

	private static void crearAlmacenamiento() {
		System.out.println("""
                Ingrese una opcion
                (1)-crear Estanteria
                (2)-Ingresar Producto""");
		switch (validarInt(1,2))
		{
			case 1:
				crearEstanteria();
				break;

			case 2:
				crearProducto();
				break;
		}
	}
	private static void crearProducto() {
		Scanner input = new Scanner(System.in);
		System.out.println("Ingrese la id de la estanteria donde se va a guardar el producto");
		String id = input.nextLine();

		if(almacenamiento.getListaGestora().getFirst().buscarEstanteriaPorID(id)!= null) {
			System.out.println("Ingrese el nombre del producto");
			String nombre = validarString(3,12);

			System.out.println("Ingrese la marca del producto");
			String marca  = validarString(3,35);
			ETipoMedida medida = seleccionarMedida();

			System.out.println("Ingrese el peso del producto");
			double peso = validardouble();

			System.out.println("Ingrese una descripcion del producto");
			String descripcionAdicional = validarString(5,40);

			System.out.println("Ingrese el precio unitario del producto");
			double precioUnitario = validardouble();

			System.out.println("Ingrese el stock del producto");
			int stock = validarInt();

			almacenamiento.getListaGestora().getFirst().buscarEstanteriaPorID(id).agregarProducto(new Producto(nombre,marca,medida,peso,descripcionAdicional,precioUnitario,stock));
		} else {
			System.out.println("No se encontro una estanteria con esa id");
		}
	}

	private static ETipoMedida seleccionarMedida() {
		System.out.println("Ingrese un tipo de medida(1-kg,2-g,3-l,4-ml)");
		switch (validarInt(1,4)) {
			case 1:
				return ETipoMedida.KILOGRAMO;

			case 2:
				return ETipoMedida.GRAMO;

			case 3:
				return ETipoMedida.LITRO;

			case 4:
				return ETipoMedida.MILILITRO;
		}
		return null;
	}

	private static void crearEstanteria() {
		System.out.println("ingrese la capacidad de la estanteria");
		int capacidad = validarInt();
		almacenamiento.getListaGestora().getFirst().agregarEstanteria(new Estanteria(capacidad));
	}

	/**
	 * Metodo que verifica que el valor ingresado sea un entero positivo teniendo en cuenta un limite inferior y superior.
	 * @return entero positivo ingresado por el usuario.
	 */
	private static int validarInt(int base, int tope) {
		try{
			int entero = validarInt();

			if (entero > tope)
				throw new InputMismatchException("usted supero el limite superior");
			if (entero < base)
				throw new InputMismatchException("usted no supero el limite inferior");
			return entero;

		}catch(InputMismatchException ime){
			System.out.println("Opcion invalida, ingrese una opcion dentro del rango (debe ser entre " + base + " y " + tope + "):" + ime.getMessage());
			return validarInt(base, tope);
		}
	}

	/**
	 * Metodo que verifica que el valor ingresado sea un entero positivo teniendo en cuenta un limite.
	 * @return entero positivo ingresado por el usuario.
	 */
	private static int validarInt(int limite) {
		try{
			int entero = validarInt();

			if (entero > limite) throw new InputMismatchException(": " + limite);
			return entero;

		}catch(InputMismatchException ime){
			System.out.println("Opcion invalida, ingrese una opcion dentro del limite del rango" + ime.getMessage());
			return validarInt(limite);
		}
	}

	/**
	 * Metodo que verifica que el valor ingresado sea un entero positivo.
	 * @return entero positivo ingresado por el usuario.
	 */
	private static int validarInt() {
		Scanner input = new Scanner(System.in);
		try{
			int entero = input.nextInt();

			if (entero < 0) throw new IndexOutOfBoundsException();
			return entero;

		}catch(InputMismatchException ime){
			System.out.println("Opcion invalida, debe ingresar un valor numerico.");
			return validarInt();
		}catch (IndexOutOfBoundsException ioobe){
			System.out.println("Opcion invalida, el valor ingresado debe ser positivo.");
			return validarInt();
		}
	}

	private static double validardouble(){
		Scanner input = new Scanner(System.in);
		try{
			double doble = input.nextDouble();

			if (doble <= 0) throw new InputMismatchException(": el numero no puede ser negativo");
			return doble;

		}catch (InputMismatchException ime){
			System.out.println("Opcion invalida, ingrese una opcion dentro del rango" + ime.getMessage());
			return validardouble();
		}
	}

	private static int validarDNI(){
		int dni = validarInt();

		try{
			if (dni >= 60000000 && dni <= 69999999) throw new InvalidDNIException("El numero del DNI coincide con los reservados para CUIT y CUIL extranjero.");
			if (dni < 10000000) throw new InvalidDNIException("El numero del DNI es demasiado pequeño como para ser de una edad real.");
		}catch (InvalidDNIException idnie){
			System.out.println(idnie.getMessage());
			return validarDNI();
		}

		return dni;
	}

	private static String validarString(int base, int tope){
		Scanner input = new Scanner(System.in);
		String validar = input.nextLine();

		try{
			if (validar.length() > tope)
				throw new InvalidLengthException("El valor ingresado excede el limite de caracteres (" + tope + ").");
			if (validar.length() < base)
				throw new InvalidLengthException("El valor ingresado es demasiado corto, debe superar los (" + base + ") caracteres.");
			return validar;

		}catch(InvalidLengthException ile){
			System.out.println(ile.getMessage());
			return validarString(base, tope);
		}
	}

	private static char validarGenero(){
		Scanner input = new Scanner(System.in);
		char validar = input.nextLine().toLowerCase().charAt(0);

		try{
			if (validar != 'h' && validar != 'm' && validar != 'o')
				throw new IllegalArgumentException("El valor ingresado no coincide a una de las opciones posibles.");
		} catch (IllegalArgumentException iae) {
			System.out.println(iae.getMessage());
			return validarGenero();
		}

		return validar;
	}

	private static String validarCorreo() {
		String validar = validarString(15, 42);

		try{
			if (!validar.contains("@gmail.com") && !validar.contains("@hotmail.com") && !validar.contains("@yahoo.com"))
				throw new IllegalArgumentException("El valor ingresado no se detecto como una direccion de correo electronico valido.");
		}catch (IllegalArgumentException iae){
			System.out.println(iae.getMessage());
			return validarCorreo();
		}

		return validar;
	}
}