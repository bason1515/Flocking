import java.awt.Graphics2D;
import java.util.ArrayList;

public class Flock {
    ArrayList<Boid> flock;
    double maxSpeed, maxForce, viewRadius, viewAngle;

    public Flock(int size, int x, int y, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        flock = new ArrayList<>();
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        this.viewRadius = viewRadius;
        this.viewAngle = viewAngle;
        for (int i = 0; i < size; i++) {
            Point randomPosition = new Point((int) (Math.random() * x), (int) (Math.random() * y));
            flock.add(new Boid(randomPosition, maxSpeed, maxForce, viewRadius, viewAngle));
        }
    }

    public void draw(Graphics2D graphic) {
        for (Boid boid : flock) {
            boid.draw(graphic);
        }
    }

    public void run(Graphics2D graphic, int windowWidth, int windowHeight, ArrayList<Obstacle> obstacles) {
        for (Boid boid : flock) {
            boid.update(windowWidth, windowHeight, flock, obstacles);
            boid.draw(graphic);
        }
    }

    public void add(Boid boid) {
        flock.add(boid);
    }

    public void setMaxForce(double maxForce) {
        for (Boid boid : flock) {
            this.maxForce = maxForce;
            boid.setMaxForce(maxForce);
        }
    }

    public void setMaxSpeed(double maxSpeed) {
        for (Boid boid : flock) {
            this.maxSpeed = maxSpeed;
            boid.setMaxSpeed(maxSpeed);
        }
    }

    public void setViewAngle(double viewAngle) {
        for (Boid boid : flock) {
            this.viewAngle = viewAngle;
            boid.setViewAngle(viewAngle);
        }
    }

    public void setViewRadius(double viewRadius) {
        for (Boid boid : flock) {
            this.viewRadius = viewRadius;
            boid.setViewRadius(viewRadius);
        }
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxForce() {
        return maxForce;
    }

    public double getViewRadius() {
        return viewRadius;
    }

    public double getViewAngle() {
        return viewAngle;
    }
}
