package excepciones;

public class InvalidAgeException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5867386226031670653L;

	public InvalidAgeException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Ocurrio un error en la validacion de la edad: " + super.getMessage();
    }
}
