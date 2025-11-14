package excepciones;

public class InvalidLengthException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = -6816553034481247373L;

	public InvalidLengthException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Ocurrio un error en la validacion de la longitud del texto ingresado: " + super.getMessage();
    }
}
