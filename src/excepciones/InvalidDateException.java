package excepciones;

public class InvalidDateException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4080764733408019409L;

	public InvalidDateException(String mensaje) {
		super(mensaje);
	}

	@Override
    public String getMessage(){
        return "Ocurrio un error en la validacion de la fecha ingresada: " + super.getMessage();
    }
	
}
