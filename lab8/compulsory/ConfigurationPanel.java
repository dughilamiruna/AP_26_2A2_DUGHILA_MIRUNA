package org.example;

import javax.swing.*;
import java.awt.*;

public class ConfigurationPanel extends JPanel {

    private JLabel label;
    private JTextField text;
    private JButton button;

    public ConfigurationPanel(Canvas canvas) {
        label = new JLabel("Grid Size (Rows & Cols):");
        text = new JTextField("", 5);
        button = new JButton("Draw Grid");

        button.setBackground(new Color(164, 154, 135));
        button.setFocusPainted(false);

        add(label);
        add(text);
        add(button);

        button.addActionListener(e-> {
            int size = Integer.parseInt(text.getText());
            canvas.generateGrid(size,size);
        });
    }
}
