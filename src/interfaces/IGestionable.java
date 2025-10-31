package interfaces;

public interface IGestionable <T>{ // intefaz usada en Almacenamiento, Administrativo y Personal, usada para filtrar las clases que se puedan gestionar
	public T getIdentificador(); // de las clases mencionadas, pueden devolver un string o un integer (UUID o dni)
}
