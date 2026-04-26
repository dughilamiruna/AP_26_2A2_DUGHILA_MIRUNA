package org.example;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {

    private JButton create, reset, exit;
    private JButton valid, export, save, load;

    public ControlPanel(Canvas canvas)
    {
        create = new JButton("Create");
        reset = new JButton("Reset");
        exit = new JButton("Exit");
        valid = new JButton("Validate");
        export = new JButton("Export PNG");
        save = new JButton("Save maze");
        load = new JButton("Load");

        create.setBackground(new Color(164, 154, 135));
        create.setFocusPainted(false);
        reset.setBackground(new Color(164, 154, 135));
        reset.setFocusPainted(false);
        exit.setBackground(new Color(164, 154, 135));
        exit.setFocusPainted(false);
        valid.setBackground(new Color(164, 154, 135));
        valid.setFocusPainted(false);
        export.setBackground(new Color(164, 154, 135));
        export.setFocusPainted(false);
        save.setBackground(new Color(164, 154, 135));
        save.setFocusPainted(false);
        load.setBackground(new Color(164, 154, 135));
        load.setFocusPainted(false);

        add(create);
        add(reset);
        add(exit);
        add(create);
        add(valid);
        add(export);
        add(save);
        add(load);

        create.addActionListener(e-> canvas.Maze());
        reset.addActionListener(e-> canvas.resetGrid());
        exit.addActionListener(e-> System.exit(0));
        valid.addActionListener(e-> canvas.validare());
        export.addActionListener(e-> canvas.exportPNG());
        save.addActionListener(e-> canvas.saveMaze());
        load.addActionListener(e-> canvas.loadMaze());
    }
}
