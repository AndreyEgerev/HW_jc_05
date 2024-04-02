package Logic.Exception;

public class WrongGenderFormat extends RuntimeException{
    public WrongGenderFormat(String message){
        super("Неправильный формат указания пола " + message + " Требуется m или f");
    }
}
