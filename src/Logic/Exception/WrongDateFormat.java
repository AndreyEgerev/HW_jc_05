package Logic.Exception;

public class WrongDateFormat extends RuntimeException{
    public WrongDateFormat(String message){
        super("Неправильный формат даты - " + message + " Требуется гггг-ММ-дд");
    }
}
