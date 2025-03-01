package exercise3b;

import java.io.Serializable;

abstract class Shape implements Serializable{
    private static final long serialVersionUID = 1L;
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