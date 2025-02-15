public class Main {
    public static void main(String[] args) {
        Shape triangle = new Triangle(3, 4,5, 6);
        Shape rectangle = new Rectangle(4, 5);
        Shape circle = new Circle(7);
        Square square = new Square(4);

        Shape[] shapes = {triangle, rectangle, circle, square};

        for (Shape shape : shapes) {
            Shape.displayColor();
            shape.displayShapeInfo();
            System.out.println("Area: " + shape.calculateArea());
            System.out.println("Perimeter: " + shape.calculatePerimeter());
            System.out.println("-----------------------------");
        }
    }
}
