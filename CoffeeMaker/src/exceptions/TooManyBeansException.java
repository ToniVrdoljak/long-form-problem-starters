package exceptions;

public class TooManyBeansException extends BeansAmountException {
    public TooManyBeansException(double beans) {
        super(beans, "Number of beans is too high!");
    }
}
