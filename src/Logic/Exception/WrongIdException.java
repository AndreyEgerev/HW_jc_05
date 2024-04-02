package Logic.Exception;

public class WrongIdException extends RuntimeException{
    public WrongIdException(String message){
        super("Некорректный Id " + message);
    }

    public WrongIdException(int idOrder) {
        super("Некорректный Id " + idOrder);
    }
}
