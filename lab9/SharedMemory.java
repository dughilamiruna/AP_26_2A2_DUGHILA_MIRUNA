package org.example;

public class SharedMemory {

    private int bunnyRow=-1;
    private int bunnyColumn=-1;
    private boolean bunnyFound=false;
    private boolean[][] explored;

    public SharedMemory(int rows, int columns)
    {
        explored=new boolean[rows][columns];
    }

    public synchronized void markExplored(int row, int column)
    {
        explored[row][column]=true;
    }

    public synchronized boolean isExplored(int row, int column)
    {
        return explored[row][column];
    }

    public synchronized void bunnyLoc(int row, int column)
    {
        this.bunnyRow=row;
        this.bunnyColumn=column;
        this.bunnyFound=true;
    }

}
