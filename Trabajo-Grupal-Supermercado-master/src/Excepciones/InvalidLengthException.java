package Gestion.Excepciones;

public class InvalidLengthException extends Exception{

    public InvalidLengthException(String message){
        super(message);
    }

    @Override
    public String getMessage(){
        return "Ocurrio un error en la validacion de la longitud del texto ingresado: " + super.getMessage();
    }
}
