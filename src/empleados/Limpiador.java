package empleados;

import almacenamiento.Almacenamiento;
import almacenamiento.Estanteria;
import almacenamiento.Mostrador;
import interfaces.IRendimiento;
import interfaces.ISalario;

public class Limpiador extends Personal implements ISalario, IRendimiento{

	//los limpiadores tendran una variable estatica que les permita ver si el mostrador esta limpio
	private static boolean tiendaLimpia = false;
	//cada uno tendra una variable para sumarle o restarle valor al rendimiento actual que tengan
	private int rendimientoActual;



	public Limpiador(String nombre, int DNI, char genero) {
		super(nombre, DNI, genero);
		// TODO Auto-generated constructor stub
	}

	public Limpiador(String nombre, int DNI, char genero, int salario, boolean activo, int antiguedad) {
		super(nombre, DNI, genero, salario, activo, antiguedad);
		// TODO Auto-generated constructor stub
	}

	public int getRendimientoActual() {
		return rendimientoActual;
	}

	public static boolean isTiendaLimpia() {
		return tiendaLimpia;
	}

	public static void setTiendaLimpia(boolean tiendaLimpia) {
		Limpiador.tiendaLimpia = tiendaLimpia;
	}

	// TODO: aca modifique la variable seLimpio para que sea un retorno directo, fijate si te parece y lo cambio tmb abajo, creo que hace mas legible el codigo.
	/**
	 * Se obtiene un mostrador por parametro y se verifica su suciedad, si es mayor a 50% se limpia y setea la suciedad del mostrador a 0.
	 *
	 * @param mostrador el mostrador seleccionado para confirmar.
	 * @return {@code true} si se limpió la tienda {@code false} de lo contrario.
	 */
	public boolean confirmarTiendaLimpiada(Mostrador mostrador)
	{
		if(mostrador.getSuciedad()>50) {
			Limpiador.setTiendaLimpia(true);
			this.rendimientoActual+=18;
			mostrador.setSuciedad(0);
			return true;
		}
		return false;
	}

	// TODO: creo que la variable seLimpio se puede cambiar por un retorno directo si se limpio o no.
	/**
	 * Selecciona una estanteria y la limpia si se puede.
	 *
	 * @param estanteria la estanteria seleccionada de la tienda.
	 * @return {@code true} si se limpio la estanteria, {@code false} si no.
	 */
	public boolean confirmarEstanteriaLimpiada(Estanteria estanteria)
	{
		boolean seLimpio=false;

		if(estanteria.verificarSuciedad())
		{
			this.rendimientoActual+=4;
			seLimpio = true;
			estanteria.setSuciedad(0);
		}

		return seLimpio;
	}

	/**
	 * Recibe un almacenamiento y lista las estanterías sucias
	 *
	 * @param almacenamiento el almacenamiento de la tienda
	 * @return String lista de estanterias sucias.
	 */
	public String listarEstanteriasSucias(Almacenamiento almacenamiento)
	{
		StringBuilder lista = new StringBuilder();

		for(Estanteria estanteria : almacenamiento.getEstanterias())
		{
			if(estanteria.verificarSuciedad())
			{
				lista.append(estanteria).append("\n");
			}
		}

		return lista.toString();
	}

	// modifique algunas cosas redundantes (variable instanciada con valor e inmediatamente sobreescrito
	// y que la variable solo se retorna, por lo que solo use la cuenta de la variable en el retorno)
	@Override
	public int calcularSalario() {
		int salarioBase = 100000;
		return (int)(salarioBase * calcularRendimiento());
	}

	// porque un porcentaje? (me explicas despues)
	//devuelve un porcentaje que sera el indice del rendimiento que tenga
	// removi el "=0" de rendimiento total porque modifica su valor inmediatamente y agregue casteo a flotante en el retorno
	public float calcularRendimiento() {
		int rendimientoTotal = IRendimiento.rendimientoBase + rendimientoActual;

		if(rendimientoTotal>=100) {
			rendimientoTotal=100;
		}

		return (float)(rendimientoTotal/100)+1;
	}

	@Override
	public Integer getIdentificador() {
		return 0;
	}
}