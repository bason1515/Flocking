import java.awt.Graphics2D;

public abstract class Object2D {
    public Point position, velocity, acceleration;
    public double maxForce, maxSpeed, viewAngle, viewRadius, size;

    public Object2D(Point spawnPosition, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        this.acceleration = new Point();
        this.velocity = new Point();
        this.size = 0;
        this.position = spawnPosition;
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        this.viewRadius = viewRadius;
        this.viewAngle = viewAngle;
    }

    public abstract void draw(Graphics2D graphic);

    public double angle(Point target) {
        Point posAndVelo = Point.add(position, velocity);
        double a = velocity.magnitude();
        double b = Point.distance(posAndVelo, target);
        double c = Point.distance(target, position);
        double angle = Math.toDegrees(Math.acos((Math.pow(a, 2) + Math.pow(b, 2) - Math.pow(c, 2)) / (2 * a * b)));
        return Math.abs(angle - 180);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getVelocity() {
        return velocity;
    }

    public void setVelocity(Point velocity) {
        this.velocity = velocity;
    }

    public Point getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Point acceleration) {
        this.acceleration = acceleration;
    }

    public double getMaxForce() {
        return maxForce;
    }

    public void setMaxForce(double maxForce) {
        this.maxForce = maxForce;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getViewAngle() {
        return viewAngle;
    }

    public void setViewAngle(double viewAngle) {
        this.viewAngle = viewAngle;
    }

    public double getViewRadius() {
        return viewRadius;
    }

    public void setViewRadius(double viewRadius) {
        this.viewRadius = viewRadius;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

}
