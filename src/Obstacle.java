import java.awt.Graphics2D;

public class Obstacle extends Object2D {
    public int size;

    public Obstacle(Point spawnPosition, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        super(spawnPosition, maxSpeed, maxForce, viewRadius, viewAngle);
        size = 20;
    }

    @Override
    public void draw(Graphics2D graphic) {
        graphic.fillOval((int) position.getX(), (int) position.getY(), size, size);
    }

}
