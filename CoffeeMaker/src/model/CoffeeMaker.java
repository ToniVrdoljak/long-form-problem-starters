package model;

import exceptions.BeansAmountException;
import exceptions.NotEnoughBeansException;
import exceptions.TooManyBeansException;
import exceptions.WaterException;

/**
 * A coffee maker used to train baristas.
 *
 * Class invariant: cups remaining >= 0, time since last brew >= 0
 */

public class CoffeeMaker {

    public CoffeeMaker(){
        // TODO: complete the implementation of this method
    }

    // getters
    public int getTimeSinceLastBrew() {
        // TODO: complete the implementation of this method
        return 0;
    }
    public int getCupsRemaining() {
        // TODO: complete the implementation of this method
        return 0;
    }

    // EFFECTS: return true if there are coffee cups remaining
    public boolean areCupsRemaining() {
        // TODO: complete the implementation of this method
        return false;
    }

    //REQUIRES: a non-negative integer
    //EFFECTS: sets time since last brew
    public void setTimeSinceLastBrew(int time) {
        // TODO: complete the implementation of this method
    }
    
    //EFFECTS: sets cups remaining to full (20 cups) and time since last brew to 0
    //         throws NotEnoughBeansException if beans less than 2.4, TooManyBeansException
    //         if beans greater than 2.6 and WaterException if water less than or equal to 14.75
    public void brew(double beans, double water) throws BeansAmountException, WaterException {
        // TODO: complete the implementation of this method
    }

    ///REQUIRES: cups remaining > 0, time since last brew < 60
    //MODIFIES: this
    //EFFECTS: subtracts one cup from cups remaining
    public void pourCoffee() {
        // TODO: complete the implementation of this method
    }


}
