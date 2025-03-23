package exercises.exercise5.factory;

public class Americano implements Coffee{
    @Override
    public void prepare(){
        System.out.println("Prepare an Americano: Espresso + Hot Water");
    }
}
