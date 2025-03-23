package exercises.exercise5.factory;

public class Cappuccino implements Coffee{
    @Override
    public void prepare(){
        System.out.println("Prepare a cappuccino: espresso + milk foam");
    }
}
