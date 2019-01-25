package exceptions;

public class NotEnoughBeansException extends BeansAmountException {
    public NotEnoughBeansException(double beans) {
        super(beans, "Number of beans is too low!");
    }
}
