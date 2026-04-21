package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JButton create, reset, exit;

    public ControlPanel(Canvas canvas)
    {
        create = new JButton("Create");
        reset = new JButton("Reset");
        exit = new JButton("Exit");

        create.setBackground(new Color(164, 154, 135));
        create.setFocusPainted(false);
        reset.setBackground(new Color(164, 154, 135));
        reset.setFocusPainted(false);
        exit.setBackground(new Color(164, 154, 135));
        exit.setFocusPainted(false);

        add(create);
        add(reset);
        add(exit);

        create.addActionListener(e-> canvas.Maze());
        reset.addActionListener(e-> canvas.resetGrid());
        exit.addActionListener(e-> System.exit(0));
    }
}
