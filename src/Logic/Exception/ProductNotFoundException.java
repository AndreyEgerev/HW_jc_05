package Logic.Exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super("Продукт " + message + " не найден");
    }
}
