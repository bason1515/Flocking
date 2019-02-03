import java.util.Objects;

public class Point {
    private double x, y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public final void edge(int maxX, int maxY) {
        x %= maxX;
        y %= maxY;
        if (x <= 0)
            x += maxX;
        if (y < 0)
            y += maxY;
    }

    public final void normalize() {
        x /= magnitude();
        y /= magnitude();
    }

    public final Point getNormalize() {
        return new Point(x / magnitude(), y / magnitude());
    }

    public final double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public final void limit(double limit) {
        double magnitude = magnitude();
        if (magnitude != 0 && magnitude > limit) {
            x *= limit / magnitude;
            y *= limit / magnitude;
        }
    }

    static final double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public void setPoint(int x, int y) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public final void add(Point point) {
        this.x += point.x;
        this.y += point.y;
    }

    public final void sub(Point point) {
        this.x -= point.x;
        this.y -= point.y;
    }

    public final void div(double arg) {
        this.x = (x / arg);
        this.y = (y / arg);
    }

    public final void mult(double arg) {
        this.x *= arg;
        this.y *= arg;
    }

    static final Point add(Point point1, Point point2) {
        return new Point(point1.x + point2.x, point1.y + point2.y);
    }

    static final Point sub(Point point1, Point point2) {
        return new Point(point1.x - point2.x, point1.y - point2.y);
    }

    static final Point div(Point point1, double arg) {
        return new Point(point1.x / arg, point1.y / arg);
    }

    static final Point mult(Point point1, double arg) {
        return new Point(point1.x * arg, point1.y * arg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object point) {
        if (!(point instanceof Point)) {
            return false;
        }
        Point p = (Point) point;
        if (p.getX() == x && p.getY() == y)
            return true;
        return false;
    }
}
