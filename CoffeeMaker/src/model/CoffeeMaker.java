package model;

import exceptions.*;

/**
 * A coffee maker used to train baristas.
 *
 * Class invariant: cups remaining >= 0, time since last brew >= 0
 */

public class CoffeeMaker {
    private int timeSinceLastBrew;
    private int cupsRemaining;

    public CoffeeMaker(){
        timeSinceLastBrew = 0;
        cupsRemaining = 0;
    }

    // getters
    public int getTimeSinceLastBrew() {
        return timeSinceLastBrew;
    }
    public int getCupsRemaining() {
        return cupsRemaining;
    }

    // EFFECTS: return true if there are coffee cups remaining
    public boolean areCupsRemaining() {
        return cupsRemaining > 0;
    }

    //REQUIRES: a non-negative integer
    //EFFECTS: sets time since last brew
    public void setTimeSinceLastBrew(int time) {
        assert(time >= 0);
        timeSinceLastBrew = time;
    }

    //EFFECTS: sets cups remaining to full (20 cups) and time since last brew to 0
    //         throws NotEnoughBeansException if beans less than 2.4, TooManyBeansException
    //         if beans greater than 2.6 and WaterException if water less than or equal to 14.75
    public void brew(double beans, double water) throws BeansAmountException, WaterException {
        if (beans < 2.4) throw new NotEnoughBeansException(beans);
        if (beans > 2.6) throw new TooManyBeansException(beans);
        if (water <= 14.75) throw new WaterException(water);

        cupsRemaining = 20;
        timeSinceLastBrew = 0;
    }

    //MODIFIES: this
    //EFFECTS: subtracts one cup from cups remaining
    //         throws NoCupsRemainingException if number of cups remaining not greater than 0,
    //         StaleCoffeeException if time since last brew >= 60
    public void pourCoffee() throws NoCupsRemainingException, StaleCoffeeException {
        if (cupsRemaining == 0) throw new NoCupsRemainingException();
        if (timeSinceLastBrew >= 60) throw new StaleCoffeeException(timeSinceLastBrew);

        cupsRemaining--;
    }


}
