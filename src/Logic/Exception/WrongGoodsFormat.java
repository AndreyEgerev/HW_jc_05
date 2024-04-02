package Logic.Exception;

public class WrongGoodsFormat extends RuntimeException{
    public WrongGoodsFormat (String message){
        super("Неправильный формат данных. " + message);
    }
}
