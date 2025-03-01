package exercise2;

abstract class Shape {
    static String color = "blue";

    abstract double calculateArea();
    abstract double calculatePerimeter();

    public static void displayColor(){
        System.out.println("Shape color: " + color);
    }
    public void displayShapeInfo(){
        System.out.println("Shape: Type: " + this.getClass().getName());
    }
}