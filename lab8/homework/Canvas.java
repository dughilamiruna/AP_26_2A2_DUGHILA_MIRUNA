package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class Canvas extends JPanel {

    private int rows=0, columns=0;
    private Cell[][] grid;

    public Canvas()
    {
        setBackground(Color.white);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event)
            {
                if(grid==null) return;

                int cellWidth=getWidth()/columns;
                int cellHeight=getHeight()/rows;
                int cellSize=Math.min(cellWidth, cellHeight);
                int offsetX=(getWidth()-(columns*cellSize))/2;
                int offsetY=(getHeight()-(rows*cellSize))/2;
                int clickX=event.getX()-offsetX;
                int clickY=event.getY()-offsetY;

                if(clickX<0 || clickY<0 || clickX >= columns * cellSize
                        || clickY >= rows * cellSize) return;

                int index1=clickX/cellSize;
                int index2=clickY/cellSize;

                int distTop=clickY%cellSize;
                int distBottom=cellSize-(clickY%cellSize);
                int distRight=cellSize-(clickX%cellSize);
                int distLeft=clickX%cellSize;

                int distMin = Math.min(Math.min(distTop, distBottom), Math.min(distLeft, distRight));
                Cell cell = grid[index2][index1];

                if (distMin == distTop)
                {
                    cell.top = !cell.top;
                    if (index2 > 0) {
                        grid[index2 - 1][index1].bottom = cell.top;
                    }
                }
                else if (distMin == distBottom)
                {
                    cell.bottom = !cell.bottom;
                    if (index2 < rows - 1) {
                        grid[index2 + 1][index1].top = cell.bottom;
                    }
                }
                else if (distMin == distLeft)
                {
                    cell.left = !cell.left;
                    if (index1 > 0) {
                        grid[index2][index1 - 1].right = cell.left;
                    }
                }
                else if (distMin == distRight)
                {
                    cell.right = !cell.right;
                    if (index1 < columns - 1) {
                        grid[index2][index1 + 1].left = cell.right;
                    }
                }

                repaint();
            }
        });
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

    public void validare()
    {
        if(grid==null) return;

        boolean[][] visited =new boolean[rows][columns];
        Queue<Cell> queue =new LinkedList<>();
        queue.add(grid[0][0]);
        visited[0][0]=true;

        while(!queue.isEmpty())
        {
            Cell current=queue.poll();

            if(current.row==rows-1 && current.column==columns-1)
            {
                JOptionPane.showMessageDialog(this, "The Maze is valid.", "Success", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!current.top && current.row > 0 && !visited[current.row - 1][current.column])
            {
                visited[current.row - 1][current.column] = true;
                queue.add(grid[current.row - 1][current.column]);
            }
            if (!current.bottom && current.row < rows - 1 && !visited[current.row + 1][current.column])
            {
                visited[current.row + 1][current.column] = true;
                queue.add(grid[current.row + 1][current.column]);
            }
            if (!current.left && current.column > 0 && !visited[current.row][current.column - 1])
            {
                visited[current.row][current.column - 1] = true;
                queue.add(grid[current.row][current.column - 1]);
            }
            if (!current.right && current.column < columns - 1 && !visited[current.row][current.column + 1])
            {
                visited[current.row][current.column + 1] = true;
                queue.add(grid[current.row][current.column + 1]);
            }
        }

        JOptionPane.showMessageDialog(this, "The Maze is invalid.", "Error", JOptionPane.INFORMATION_MESSAGE);

    }

    public void exportPNG()
    {
        if(grid==null) return;

        try
        {
            BufferedImage image=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();

            paint(graphics);
            graphics.dispose();

            File file=new File("maze.png");
            ImageIO.write(image, "png", file);
            JOptionPane.showMessageDialog(this, "Image saved!");
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(this, "Error: image couldn't be saved.");
        }
    }

    public void saveMaze()
    {
        if(grid==null) return;
        try (ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("new_maze")))
        {
            out.writeObject(grid);
            JOptionPane.showMessageDialog(this, "Changes saved in new_maze.");
        }
        catch (IOException exception)
        {
            JOptionPane.showMessageDialog(this, "Error at saving: " + exception.getMessage());
        }

    }

    public void loadMaze()
    {
        try (ObjectInputStream in=new ObjectInputStream(new FileInputStream("new_maze")))
        {
            grid=(Cell[][]) in.readObject();
            rows=grid.length;
            columns=grid[0].length;
            repaint();

            JOptionPane.showMessageDialog(this, "Maze loaded.");
        }
        catch (Exception exception)
        {
            JOptionPane.showMessageDialog(this, "Couldn't load maze: file not found/corrupted.");
        }
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
