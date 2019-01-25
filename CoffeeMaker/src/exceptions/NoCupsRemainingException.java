package exceptions;

public class NoCupsRemainingException extends Exception {
    public NoCupsRemainingException() {
        super("There are no cup remaining!");
    }
}
