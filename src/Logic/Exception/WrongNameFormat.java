package Logic.Exception;

public class WrongNameFormat extends RuntimeException{
    public WrongNameFormat(String message){
        super("Неправильный формат имени " + message);
    }
}
