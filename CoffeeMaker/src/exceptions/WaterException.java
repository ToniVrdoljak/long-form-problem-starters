package exceptions;

public class WaterException extends Exception {
    private double waterAmount;

    public WaterException(double waterAmount) {
        super(String.format("Amount of water insufficient: %f", waterAmount));
        this.waterAmount = waterAmount;
    }
}
