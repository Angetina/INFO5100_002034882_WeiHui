package exercise3b;

class Circle extends Shape {
    private static final long serialVersionUID = 1L;
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea(){
        return Math.PI * radius * radius;
    }

    @Override
    double calculatePerimeter(){
        return 2 * Math.PI * radius;
    }
}
