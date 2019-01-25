import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

public class Boid extends Object2D {
    private Random rand = new Random();

    public Boid(Point spawnPosition, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        super(spawnPosition, maxSpeed, maxForce, viewRadius, viewAngle);
        velocity = new Point(rand.nextDouble() - 0.5, rand.nextDouble() - 0.5);
        this.size = 5;
    }

    public Point aligment(ArrayList<Boid> flock) {
        Point avg = new Point();
        int total = 0;
        for (Boid boid : flock) {
            if (boid != this && Point.distance(this.position, boid.position) <= viewRadius
                    && angle(boid.position) < viewAngle) {
                avg.add(boid.velocity);
                total++;
            }
        }
        if (total > 0) {
            avg.div(total);
            avg.normalize(); // Normalizing vector so we can apply to it our max speed
            avg.mult(maxSpeed);
            avg.limit(maxForce);
        }
        return avg;
    }

    public Point cohesion(ArrayList<Boid> flock) {
        Point avg = new Point();
        int total = 0;
        for (Boid boid : flock) {
            if (boid != this && Point.distance(this.position, boid.position) <= viewRadius
                    && angle(boid.position) < viewAngle) {
                avg.add(boid.position);
                total++;
            }
        }
        if (total > 0) {
            avg.div(total);
            avg.sub(this.position);
            avg.normalize();
            avg.mult(maxSpeed);
            avg.limit(maxForce);
        }
        return avg;
    }

    public Point separation(ArrayList<Boid> flock) {
        Point avg = new Point();
        int total = 0;
        for (Boid boid : flock) {
            double dist = Point.distance(this.position, boid.position);
            if ((boid != this && dist <= viewRadius / 2.5 && angle(boid.position) < viewAngle)
                    || (boid != this && dist <= 8)) {
                Point diffrenc = Point.sub(position, boid.position);
                diffrenc.div(dist);
                avg.add(diffrenc);
                total++;
            }
        }
        if (total > 0) {
            avg.div(total);
            avg.normalize();
            avg.mult(maxSpeed);
            avg.limit(maxForce);
        }
        return avg;
    }

    public Point avoid(ArrayList<Obstacle> obstacles) {
        Point avg = new Point();
        int total = 0;
        for (Obstacle obstacle : obstacles) {
            double dist = Point.distance(this.position, obstacle.position);
            if (dist <= obstacle.size * 3.5) {
                Point diffrenc = Point.sub(position, obstacle.position);
                diffrenc.div(dist);
                avg.add(diffrenc);
                total++;
            }
        }
        if (total > 0) {
            avg.div(total);
            avg.normalize();
            avg.mult(maxSpeed);
            avg.limit(maxForce);
        }
        return avg;
    }

    public Point noise() {
        Point noise = new Point((rand.nextDouble() - 0.5) * 20, (rand.nextDouble() - 0.5) * 20);
        noise.normalize();
        noise.mult(maxSpeed);
        noise.limit(maxForce);
        return noise;
    }

    public void draw(Graphics2D graphic) {
        graphic.setColor(Color.BLACK);
        // graphic.fillOval((int) position.getX(), (int) position.getY(), 5, 5);
        Point heading = Point.mult(velocity.getNormalize(), 10);
        Point top = Point.add(position, heading);
        heading.div(2);
        heading.mult(-1);
        Point left = new Point(heading.getX() * 2, heading.getY());
        left.add(position);
        Point right = new Point(heading.getX(), heading.getY() * 2);
        right.add(position);
        graphic.drawPolygon(new int[] { (int) left.getX(), (int) top.getX(), (int) right.getX() },
                new int[] { (int) left.getY(), (int) top.getY(), (int) right.getY() }, 3);
    }

    public void update(int windowWidth, int windowHeight, ArrayList<Boid> flock, ArrayList<Obstacle> obstales) {
        velocity.add(acceleration);
        velocity.limit(maxSpeed);
        position.add(velocity);
        position.edge(windowWidth, windowHeight);
        acceleration = new Point();
        acceleration.add(Point.mult(separation(flock), 1.5));
        acceleration.add(Point.mult(avoid(obstales), 2.5));
        acceleration.add(aligment(flock));
        acceleration.add(cohesion(flock));
        // acceleration.add(noise()); // Noise
    }
}
