package exercise3b;

class Triangle extends Shape{
    private static final long serialVersionUID = 1L;

    private double base, height, side1, side2;

    public Triangle(double base, double height, double side1, double side2){
        this.base = base;
        this.height = height;
        this.side1 = side1;
        this.side2 = side2;
    }

    @Override
    double calculateArea() {
        return 0.5 * base * height;
    }

    @Override
    double calculatePerimeter() {
        return base + side1 + side2;
    }
}
