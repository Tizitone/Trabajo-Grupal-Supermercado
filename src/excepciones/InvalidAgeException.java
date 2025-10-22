package excepciones;

public class InvalidAgeException extends Exception {

    public InvalidAgeException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Ocurrio un error en la validacion de la edad: " + super.getMessage();
    }
}
