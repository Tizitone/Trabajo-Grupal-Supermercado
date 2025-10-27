package excepciones;

public class InvalidIDException extends RuntimeException {

    public InvalidIDException(String message) {
      super(message);
    }

    @Override
    public String getMessage(){
    return "Ocurrio un error en la validacion del DNI ingresado: " + super.getMessage();
  }

}
