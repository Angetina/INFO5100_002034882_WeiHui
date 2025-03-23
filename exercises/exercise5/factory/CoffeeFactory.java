package exercises.exercise5.factory;

public class CoffeeFactory {
    public static Coffee createCoffee(String type){
        switch (type.toLowerCase()){
            case "latte":
                return new Latte();
            case "americano":
                return new Americano();
            case "cappuccino":
                return new Cappuccino();
            default:
                System.out.println("Unknown coffee type: " + type);
                return null;
        }
    }
}
