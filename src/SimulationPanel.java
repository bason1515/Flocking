import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel implements MouseListener {
    Flock flock;
    Obstacles obstacles;
    boolean run;

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public SimulationPanel(int size, double maxSpeed, double maxForce, double viewRadius, double viewAngle) {
        setSize(500, 500);
        setVisible(true);
        flock = new Flock(size, getWidth(), getHeight(), maxSpeed, maxForce, viewRadius, viewAngle);
        obstacles = new Obstacles(0, getHeight(), getWidth(), maxSpeed, maxForce, viewRadius, viewAngle);
        run = false;
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic = (Graphics2D) g;
        graphic.setStroke(new BasicStroke(4));
        graphic.drawRect(0, 0, getWidth(), getHeight());
        graphic.setStroke(new BasicStroke());
        flock.run(graphic, getWidth(), getHeight(), obstacles.obstacles);
        obstacles.run(graphic, getWidth(), getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        double mouseX = MouseInfo.getPointerInfo().getLocation().x - getLocationOnScreen().getX();
        double mouseY = MouseInfo.getPointerInfo().getLocation().y - getLocationOnScreen().getY();
        Point mousePosition = new Point(mouseX, mouseY);
        if (e.getButton() == MouseEvent.BUTTON1) {
            flock.add(new Boid(mousePosition, flock.getMaxSpeed(), flock.getMaxForce(), flock.getViewRadius(),
                    flock.getViewAngle()));
            System.out.println("Adding Boid: " + (int) mousePosition.getX() + " " + (int) mousePosition.getY()
                    + " Size:" + flock.flock.size());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            obstacles.add(new Obstacle(mousePosition, flock.getMaxSpeed(), flock.getMaxForce(), flock.getViewRadius(),
                    flock.getViewAngle()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }
}
