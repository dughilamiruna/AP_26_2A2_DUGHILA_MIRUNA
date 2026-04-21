package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Canvas extends JPanel {

    private int rows=0, columns=0;
    private Cell[][] grid;

    public Canvas()
    {
        setBackground(Color.white);
    }

    public void generateGrid(int rows, int columns)
    {
        this.rows=rows;
        this.columns=columns;
        grid=new Cell[rows][columns];

        for(int row=0; row<rows; row++)
            for(int column=0; column<columns; column++)
            {
                grid[row][column] = new Cell(row, column);
            }
        repaint();
    }

    public void resetGrid()
    {
        if(rows>0 && columns>0)
        {
            generateGrid(rows, columns);
        }
    }

    public void Maze()
    {
        if(grid==null) return;

        for(int row=0; row<rows; row++)
            for(int column=0; column<columns; column++)
            {
                grid[row][column].visited=false;
            }

        Stack<Cell> stack = new Stack<>();
        Cell current=grid[0][0];
        current.visited=true;
        boolean done=false;

        while(!done)
        {
            Cell next=unvisitedNeighbor(current);

            if(next!=null)
            {
                next.visited=true;
                stack.push(current);
                removeWall(current, next);
                current=next;
            }
            else if(!stack.isEmpty())
            {
                current=stack.pop();
            }
            else {
                done=true;
            }
        }

        repaint();
    }

    private Cell unvisitedNeighbor(Cell ceil) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        if (ceil.row > 0 && !grid[ceil.row - 1][ceil.column].visited) {
            neighbors.add(grid[ceil.row - 1][ceil.column]);
        }
        if (ceil.column < columns - 1 && !grid[ceil.row][ceil.column + 1].visited) {
            neighbors.add(grid[ceil.row][ceil.column + 1]);
        }
        if (ceil.row < rows - 1 && !grid[ceil.row + 1][ceil.column].visited) {
            neighbors.add(grid[ceil.row + 1][ceil.column]);
        }
        if (ceil.column > 0 && !grid[ceil.row][ceil.column - 1].visited) {
            neighbors.add(grid[ceil.row][ceil.column - 1]);
        }

        if (!neighbors.isEmpty()) {
            Collections.shuffle(neighbors);
            return neighbors.get(0);
        }
        return null;
    }

    private void removeWall(Cell cell1, Cell cell2) {
        int row1 = cell1.row - cell2.row;
        if (row1 == 1) {
            cell1.top = false;
            cell2.bottom = false;
        } else if (row1 == -1) {
            cell1.bottom = false;
            cell2.top = false;
        }

        int column1 = cell1.column - cell2.column;
        if (column1 == 1) {
            cell1.left = false;
            cell2.right = false;
        } else if (column1 == -1) {
            cell1.right = false;
            cell2.left = false;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (grid == null) return;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2));

        int cellWidth = getWidth() / columns;
        int cellHeight = getHeight() / rows;
        int cellSize = Math.min(cellWidth, cellHeight);

        int offsetX = (getWidth() - (columns * cellSize)) / 2;
        int offsetY = (getHeight() - (rows * cellSize)) / 2;

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell cell = grid[row][column];
                int x = offsetX + column * cellSize;
                int y = offsetY + row * cellSize;

                g2.setColor(new Color(187, 183, 145));
                g2.fillRect(x, y, cellSize, cellSize);

                g2.setColor(Color.black);
                if (cell.top) g2.drawLine(x, y, x + cellSize, y);
                if (cell.right) g2.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
                if (cell.bottom) g2.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
                if (cell.left) g2.drawLine(x, y, x, y + cellSize);
            }
        }
    }
}
