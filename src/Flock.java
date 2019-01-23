import java.awt.Graphics2D;
import java.util.ArrayList;

public class Flock {
    ArrayList<Boid> flock;

    public Flock(int size, int x, int y) {
        flock = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Point randomPosition = new Point((int) (Math.random() * x), (int) (Math.random() * y));
            flock.add(new Boid(randomPosition, 0.7, 0.003, 100));
        }
    }

    public void draw(Graphics2D graphic) {
        for (Boid boid : flock) {
            boid.draw(graphic);
        }
    }

    public void run(Graphics2D graphic, int windowWidth, int windowHeight) {
        for (Boid boid : flock) {
            boid.update(windowWidth, windowHeight, flock);
            boid.draw(graphic);
        }
    }

    public void add(Boid boid) {
        flock.add(boid);
    }
}
