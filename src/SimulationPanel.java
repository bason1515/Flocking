import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

public class SimulationPanel extends JPanel {
    Flock flock;
    boolean run;

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public SimulationPanel() {
        setSize(500, 500);
        setVisible(true);
        flock = new Flock(150, getHeight(), getWidth());
        run = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic = (Graphics2D) g;
        graphic.setStroke(new BasicStroke(4));
        graphic.drawRect(0, 0, getWidth(), getHeight());
        graphic.setStroke(new BasicStroke());
        flock.run(graphic, getWidth(), getHeight());
    }
}
