package test;

import exceptions.*;
import model.CoffeeMaker;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;


public class CoffeeMakerTest {
    private CoffeeMaker coffeeMaker;

    private void assertCorrectStateAfterBrew() {
        Assert.assertTrue(coffeeMaker.areCupsRemaining());
        Assert.assertEquals(20, coffeeMaker.getCupsRemaining());
        Assert.assertEquals(0, coffeeMaker.getTimeSinceLastBrew());
    }

    private void pourCoffeeMultipleTimes(int numberOfCupsToPour) {
        for(int i = 0; i < numberOfCupsToPour; i++) {
            try {
                coffeeMaker.pourCoffee();
            } catch(NoCupsRemainingException | StaleCoffeeException e) {
                Assert.fail("No exception should be thrown!");
            }
        }
    }

    private void brewCoffeeWithoutException() {
        try {
            coffeeMaker.brew(2.5, 15);
        } catch (BeansAmountException|WaterException e) {
            Assert.fail("No exception should be thrown!");
        }
    }

    @Before
    public void setUp(){
        coffeeMaker = new CoffeeMaker();
    }

    @Test
    public void setTimeSinceLastBrewTest() {
        brewCoffeeWithoutException();

        int timeSinceLastBrew = 5;

        coffeeMaker.setTimeSinceLastBrew(timeSinceLastBrew);

        Assert.assertEquals(timeSinceLastBrew, coffeeMaker.getTimeSinceLastBrew());
    }

    @Test
    public void brewTestNoExceptions() {
        try {
            coffeeMaker.brew(2.5, 15);
        } catch(BeansAmountException|WaterException e) {
            Assert.fail("No exception should be thrown!");
        }

        assertCorrectStateAfterBrew();
    }

    @Test (expected = NotEnoughBeansException.class)
    public void brewTestNotEnoughBeansException() throws BeansAmountException, WaterException{
        coffeeMaker.brew(2.0, 15);
    }

    @Test (expected = TooManyBeansException.class)
    public void brewTestTooManyBeansException() throws BeansAmountException, WaterException {
        coffeeMaker.brew(3.0, 15);
    }

    @Test (expected = WaterException.class)
    public void brewTestWaterException() throws BeansAmountException, WaterException {
        coffeeMaker.brew(2.5, 14);
    }

    @Test
    public void brewTestBothExceptions() {
        try {
            coffeeMaker.brew(2.0, 14);
            Assert.fail("An exception should have been thrown!");
        } catch (BeansAmountException|WaterException e) {
            // one of the exceptions should be caught
        }
    }

    @Test
    public void brewTestLowerBoundaryForBeans() {
        try {
            coffeeMaker.brew(2.4, 15);
        } catch(BeansAmountException|WaterException e) {
            Assert.fail("No exception should be thrown!");
        }

        assertCorrectStateAfterBrew();
    }

    @Test
    public void brewTestUpperBoundaryForBeans() {
        try {
            coffeeMaker.brew(2.6, 15);
        } catch(BeansAmountException|WaterException e) {
            Assert.fail("No exception should be thrown!");
        }

        assertCorrectStateAfterBrew();
    }

    @Test (expected = WaterException.class)
    public void brewTestBoundaryForWater() throws BeansAmountException, WaterException{
        coffeeMaker.brew(2.5, 14.75);
    }

    @Test
    public void pourCoffeeTestNoException() {
        brewCoffeeWithoutException();

        try {
            coffeeMaker.pourCoffee();
            coffeeMaker.pourCoffee();
            coffeeMaker.pourCoffee();
        } catch (NoCupsRemainingException|StaleCoffeeException e) {
            Assert.fail("No exception should be thrown!");
        }
    }

    @Test (expected = NoCupsRemainingException.class)
    public void pourCoffeeTestNoCupsRemainingException() throws NoCupsRemainingException, StaleCoffeeException{
        brewCoffeeWithoutException();
        pourCoffeeMultipleTimes(20);

        try {
            coffeeMaker.pourCoffee();
        } finally {
            Assert.assertFalse("No cups should be remaining!", coffeeMaker.areCupsRemaining());
        }
    }

    @Test (expected = StaleCoffeeException.class)
    public void pourCoffeeTestStaleCoffeeException() throws NoCupsRemainingException, StaleCoffeeException{
        brewCoffeeWithoutException();
        coffeeMaker.setTimeSinceLastBrew(70);

        coffeeMaker.pourCoffee();
    }

    @Test (expected = StaleCoffeeException.class)
    public void pourCoffeeTestStaleCoffeeExceptionBoundaryValue() throws NoCupsRemainingException, StaleCoffeeException{
        brewCoffeeWithoutException();
        coffeeMaker.setTimeSinceLastBrew(60);

        coffeeMaker.pourCoffee();
    }

    @Test
    public void pourCoffeeTestBothExceptions() {
        brewCoffeeWithoutException();
        pourCoffeeMultipleTimes(20);
        coffeeMaker.setTimeSinceLastBrew(70);

        try {
            coffeeMaker.pourCoffee();
            Assert.fail("An exception should have been thrown!");
        } catch (NoCupsRemainingException|StaleCoffeeException e) {
            // one of the exceptions should be caught
        }
    }

    @Test
    public void getCupsRemainingTest() {
        brewCoffeeWithoutException();

        // assert number of cups is 20 before any coffee is poured
        Assert.assertEquals("Problem with upper boundary for number of cups!",
                            20, coffeeMaker.getCupsRemaining());

        // pour 5 cups and assert 15 cups are left
        pourCoffeeMultipleTimes(5);
        Assert.assertEquals(15, coffeeMaker.getCupsRemaining());

        // pour 15 more cups and assert 0 cups are left
        pourCoffeeMultipleTimes(15);
        Assert.assertEquals("Problem with lower boundary for number of cups!",
                0, coffeeMaker.getCupsRemaining());
    }

    @Test
    public void areCupsRemainingTest() {
        brewCoffeeWithoutException();

        // assert there are cups left before any coffee is poured
        Assert.assertTrue("There should be maximum cups left, but there aren't!",
                           coffeeMaker.areCupsRemaining());

        // pour 5 cups and assert there are cups left
        pourCoffeeMultipleTimes(5);
        Assert.assertTrue("There should be cups left, but there aren't!",
                           coffeeMaker.areCupsRemaining());

        // pour 15 more cups and assert there are non cups left
        pourCoffeeMultipleTimes(15);
        Assert.assertFalse("There should be no cups left!", coffeeMaker.areCupsRemaining());
    }
}
