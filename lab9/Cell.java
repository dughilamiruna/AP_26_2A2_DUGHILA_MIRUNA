package org.example;

public class Cell implements java.io.Serializable {

    int row, column;
    boolean top=true, bottom=true, right=true, left=true;
    boolean visited=false;

    boolean robot=false;
    boolean bunny=false;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

