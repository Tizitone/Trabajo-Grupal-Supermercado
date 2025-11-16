package excepciones;

public class InvalidDNIException extends Exception {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3615805899334460450L;

	public InvalidDNIException(String message) {
        super(message);
      }

      @Override
      public String getMessage(){
      return "Ocurrio un error en la validacion del DNI ingresado: " + super.getMessage();
    }
}
