package exercises.exercise5.factory;

public class FactoryMain {
    public static void main(String[] args) {
        System.out.println("Testing the Factory Pattern");

        Coffee coffee1 = CoffeeFactory.createCoffee("latte");
        if (coffee1 != null) {
            coffee1.prepare();
        }

        Coffee coffee2 = CoffeeFactory.createCoffee("americano");
        if (coffee2 != null) {
            coffee2.prepare();
        }

        Coffee coffee3 = CoffeeFactory.createCoffee("cappuccino");
        if (coffee3 != null) {
            coffee3.prepare();
        }
    }
}
