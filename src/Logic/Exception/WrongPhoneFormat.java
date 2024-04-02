package Logic.Exception;

public class WrongPhoneFormat extends RuntimeException{
    public WrongPhoneFormat(String message){
        super("Неправильный формат телефона - " + message + " Требуется ХХХххххххх где х - цифра номера");
    }
}
