import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleImmutableEntry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class App extends JFrame {
    SimulationPanel simulationPanel;
    JButton startButton;
    JSlider maxSpeed, maxForce, viewRadius, viewAngle;

    public void init() {
        setTitle("Boid Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setResizable(false);

        maxSpeed = new JSlider(1, 200);
        maxSpeed.setValue(70);
        maxSpeed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulationPanel.flock.setMaxSpeed(maxSpeed.getValue() * 0.01);
            }
        });
        maxForce = new JSlider(1, 100);
        maxForce.setValue(8);
        maxForce.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulationPanel.flock.setMaxForce(maxForce.getValue() * 0.0005);
            }
        });
        viewRadius = new JSlider(0, 400);
        viewRadius.setValue(100);
        viewRadius.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulationPanel.flock.setViewRadius(viewRadius.getValue());
            }
        });
        viewAngle = new JSlider(0, 179);
        viewAngle.setValue(100);
        viewAngle.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                simulationPanel.flock.setViewAngle(viewAngle.getValue());
            }
        });
        GridLayout grid = new GridLayout(0, 2);
        JPanel editPanel = new JPanel(grid);
        editPanel.add(new JLabel("Max Speed"));
        editPanel.add(maxSpeed);
        editPanel.add(new JLabel("Max Force"));
        editPanel.add(maxForce);
        editPanel.add(new JLabel("View Range"));
        editPanel.add(viewRadius);
        editPanel.add(new JLabel("View Angle"));
        editPanel.add(viewAngle);

        startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (simulationPanel.isRun()) {
                    simulationPanel.setRun(false);
                    startButton.setText("Start");
                } else {
                    simulationPanel.setRun(true);
                    startButton.setText("Stop");
                }
            }
        });
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simulationPanel.flock = new Flock(100, simulationPanel.getHeight(), simulationPanel.getWidth(),
                        maxSpeed.getValue() * 0.01, maxForce.getValue() * 0.001, viewRadius.getValue(),
                        viewAngle.getValue());
                simulationPanel.obstacles = new Obstacles(0, simulationPanel.getWidth(), simulationPanel.getHeight(),
                        maxSpeed.getValue() * 0.01, maxForce.getValue() * 0.001, viewRadius.getValue(),
                        viewAngle.getValue());
                simulationPanel.setRun(false);
                startButton.setText("Start");
            }
        });
        editPanel.add(startButton);
        editPanel.add(resetButton);
        getContentPane().add(BorderLayout.SOUTH, editPanel);

        simulationPanel = new SimulationPanel(100, maxSpeed.getValue() * 0.01, maxForce.getValue() * 0.001,
                viewRadius.getValue(), viewAngle.getValue());
        getContentPane().add(simulationPanel);

        setVisible(true);
        while (true) {
            try {
                Thread.sleep(5);
                if (simulationPanel.isRun()) {
                    repaint();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
