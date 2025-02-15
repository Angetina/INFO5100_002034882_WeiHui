abstract class Shape {
    static String color = "Blue";

    abstract double calculateArea();
    abstract double calculatePerimeter();

    public static void displayColor(){
        System.out.println("shape color: " + color);
    }

    public void displayShapeInfo(){
        System.out.println("Shape type: " + this.getClass().getSimpleName());
    }
}
