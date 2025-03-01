package exercise1;

public class PartTimeStudent extends Student {
    public PartTimeStudent(String name) {
        super(name);
    }

    @Override
    public void printDetails() {
        System.out.println("Part-Time Student: " + name);
    }
}
