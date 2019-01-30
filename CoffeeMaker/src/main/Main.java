package main;

import exceptions.BeansAmountException;
import exceptions.NoCupsRemainingException;
import exceptions.StaleCoffeeException;
import exceptions.WaterException;
import model.CoffeeMaker;

public class Main {

    public static void main(String[] args){
        // successful path
        CoffeeMaker coffeeMaker1 = new CoffeeMaker();

        try {
            coffeeMaker1.brew(2.5, 15);
        } catch (BeansAmountException|WaterException e) {
            System.out.println(e.getMessage());
        }

        try {
            coffeeMaker1.pourCoffee();
        } catch (NoCupsRemainingException|StaleCoffeeException e) {
            System.out.println(e.getMessage());
        }

        coffeeMaker1.setTimeSinceLastBrew(10);

        // unsuccessful path
        CoffeeMaker coffeeMaker2 = new CoffeeMaker();

        try {
            coffeeMaker2.brew(1.5, 10);
        } catch (BeansAmountException|WaterException e) {
            System.out.println(e.getMessage());
        }

        try {
            coffeeMaker2.brew(2.5, 15);
        } catch (BeansAmountException|WaterException e) {
            System.out.println(e.getMessage());
        }

        try {
            coffeeMaker2.pourCoffee();
        } catch (NoCupsRemainingException|StaleCoffeeException e) {
            System.out.println(e.getMessage());
        }

        coffeeMaker2.setTimeSinceLastBrew(70);

        try {
            coffeeMaker2.pourCoffee();
        } catch (NoCupsRemainingException|StaleCoffeeException e) {
            System.out.println(e.getMessage());
        }

    }

}