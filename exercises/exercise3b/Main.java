package exercise3b;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Shape triangle = new Triangle(3, 4, 5, 6);
        Shape rectangle = new Rectangle(4, 5);
        Shape circle = new Circle(7);
        Shape square = new Square(4);

        Shape[] shapes = {triangle, rectangle, circle, square};

        String fileName = "shapes.ser";
        SerializationUtil.serialize(shapes, fileName);

        Shape[] deserializedShapes = (Shape[]) SerializationUtil.deserialize(fileName);

        if (deserializedShapes != null) {
            for (Shape shape : deserializedShapes) {
                Shape.displayColor();
                shape.displayShapeInfo();
                System.out.println("Area: " + shape.calculateArea());
                System.out.println("Perimeter: " + shape.calculatePerimeter());
                System.out.println("-----------------------------");
            }
        }
    }
}
