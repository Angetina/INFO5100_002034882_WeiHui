package exercises.exercise5.factory;

public class Latte implements Coffee {
    @Override
    public void prepare(){
        System.out.println("Prepare a latte: espresso + milk");
    }
}
