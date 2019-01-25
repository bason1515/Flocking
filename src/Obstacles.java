import java.awt.Graphics2D;
import java.util.ArrayList;

public class Obstacles {
    ArrayList<Obstacle> obstacles;
    double maxSpeed, maxForce, viewRadius, viewAngle;

    public Obstacles(int size, int x, int y, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        obstacles = new ArrayList<>();
        this.maxSpeed = maxSpeed;
        this.maxForce = maxForce;
        this.viewRadius = viewRadius;
        this.viewAngle = viewAngle;
        for (int i = 0; i < size; i++) {
            Point randomPosition = new Point((int) (Math.random() * x), (int) (Math.random() * y));
            obstacles.add(new Obstacle(randomPosition, maxSpeed, maxForce, viewRadius, viewAngle));
        }
    }

    public void run(Graphics2D graphic, int windowWidth, int windowHeight) {
        for (Obstacle obstacle : obstacles) {
            obstacle.draw(graphic);
        }
    }

    public void add(Obstacle obstacle) {
        obstacles.add(obstacle);
    }

}
