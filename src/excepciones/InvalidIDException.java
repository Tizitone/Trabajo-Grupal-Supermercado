package excepciones;

public class InvalidIDException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2232430090968454327L;

	public InvalidIDException(String message) {
      super(message);
    }

    @Override
    public String getMessage(){
    return "Ocurrio un error en la validacion del DNI ingresado: " + super.getMessage();
  }

}
