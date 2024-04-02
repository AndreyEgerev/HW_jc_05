package Logic.Exception;

public class QuantityIsNegativeException extends Exception{
    public QuantityIsNegativeException(String message) {
        super("Введено неправильное количество " + message);
    }
}
