package exercise3b;

class Rectangle extends Shape {
    private static final long serialVersionUID = 1L;
    public double length, width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double calculateArea(){
        return length * width;
    }

    @Override
    double calculatePerimeter(){
        return 2 * (length + width);
    }
}
