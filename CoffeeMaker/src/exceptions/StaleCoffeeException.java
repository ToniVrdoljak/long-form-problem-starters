package exceptions;

public class StaleCoffeeException extends Exception {
    private int timeSinceLastBrew;

    public StaleCoffeeException(int timeSinceLastBrew) {
        super(String.format("Coffee is stale! Time since last brew: %d", timeSinceLastBrew));
        this.timeSinceLastBrew = timeSinceLastBrew;

    }
}
