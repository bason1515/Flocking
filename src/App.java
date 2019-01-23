import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap.SimpleImmutableEntry;

import javax.swing.JButton;
import javax.swing.JFrame;

public class App extends JFrame {
    SimulationPanel simulationPanel;
    JButton startButton;

    public void init() {
        setTitle("Boid Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setResizable(false);

        simulationPanel = new SimulationPanel();
        getContentPane().add(simulationPanel);

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
        getContentPane().add(BorderLayout.SOUTH, startButton);

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
