package exceptions;

public class BeansAmountException extends Exception {
    private double beans;

    public BeansAmountException(double beans) {
        super(String.format("%f is not the right amount of beans", beans));
        this.beans = beans;
    }

    protected BeansAmountException(double beans, String message) {
        super(message + String.format(" Number of beans: %f", beans));
        this.beans = beans;
    }

    public double getBeans() {
        return beans;
    }
}
