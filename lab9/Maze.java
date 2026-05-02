package org.example;

import java.util.*;
import java.util.List;

public class Maze {

    private int rows, columns;
    private Cell[][] grid;
    public volatile boolean gameOver=false;

    public Maze(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                grid[row][column] = new Cell(row, column);
            }
        }
        generateMaze();
    }

    public void generateMaze()
    {
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
    }

    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    public List<Cell> accesibleNeighbors(Cell cell)
    {
        List<Cell> neighbors =new ArrayList<>();

        if(!cell.top && cell.row>0)
        {
            neighbors.add(grid[cell.row-1][cell.column]);
        }
        if (!cell.bottom && cell.row < rows - 1)
        {
            neighbors.add(grid[cell.row + 1][cell.column]);
        }
        if (!cell.left && cell.column > 0)
        {
            neighbors.add(grid[cell.row][cell.column - 1]);
        }
        if (!cell.right && cell.column < columns - 1)
        {
            neighbors.add(grid[cell.row][cell.column + 1]);
        }
        return neighbors;
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

    public boolean visit(int row, int column, Object entity, Cell currentCell) {
        synchronized (grid[row][column]) {
            Cell next = grid[row][column];

            if (entity instanceof Robot) {
                if (next.robot) return false;

                if (currentCell != null) currentCell.robot = false;
                next.robot = true;
                return true;

            } else if (entity instanceof Bunny) {
                if (currentCell != null) currentCell.bunny = false;
                next.bunny = true;
                return true;
            }
            return false;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MAZE\n");

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                sb.append("+");
                sb.append(grid[row][column].top ? "---" : "   ");
            }
            sb.append("+\n");

            for (int column = 0; column < columns; column++) {
                sb.append(grid[row][column].left ? "|" : " ");

                if (grid[row][column].bunny) sb.append(" B ");
                else if (grid[row][column].robot) sb.append(" R ");
                else sb.append("   ");
            }
            sb.append(grid[row][columns - 1].right ? "|\n" : " \n");
        }

        for (int c = 0; c < columns; c++) {
            sb.append("+");
            sb.append(grid[rows - 1][c].bottom ? "---" : "   ");
        }
        sb.append("+\n");

        return sb.toString();
    }
}

