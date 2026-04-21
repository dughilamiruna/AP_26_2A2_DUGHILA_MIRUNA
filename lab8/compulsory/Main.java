package org.example;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private ConfigurationPanel configurationPanel;
    private ControlPanel controlPanel;
    private Canvas canvas;

    public Main()
    {
        super("Maze Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvas = new Canvas();
        configurationPanel = new ConfigurationPanel(canvas);
        controlPanel = new ControlPanel(canvas);

        add(configurationPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(800,800);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}
