import java.util.List;

class Sort {
    public static void sortShapes(Shape[] array, List<Shape> shapes, List<Polygon> polygons, List<Square> squares, List<Circle> circles) {
        // write your code here
        for (Shape s : array) {
            if (s instanceof Square) {
                squares.add((Square) s);
            } else if (s instanceof Polygon) {
                polygons.add((Polygon) s);
            } else if (s instanceof Circle) {
                circles.add((Circle) s);
            } else {
                shapes.add(s);
            }
        }
    }
}

//Don't change classes below
class Shape {
}

class Polygon extends Shape {
}

class Square extends Polygon {
}

class Circle extends Shape {
}