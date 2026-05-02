package org.example;

import java.util.List;
import java.util.Random;

public class Robot implements Runnable {

    private String name;
    private Main game;
    private Cell currentCell;
    private SharedMemory memory;
    private Random random = new Random();

    private volatile boolean running = true;
    private volatile int sleepTime = 2000;
    private volatile boolean paused = false;

    public Robot(String name, Main game, Cell startCell, SharedMemory memory) {
        this.name = name;
        this.game = game;
        this.currentCell = startCell;
        this.memory = memory;
        game.getMaze().visit(startCell.row, startCell.column, this, null);
    }

    public void setSpeed(int speed) { this.sleepTime = speed; }
    public void pauseThread() { this.paused = true; }
    public void resumeThread() { this.paused = false; }
    public void stopThread() { this.running = false; }

    @Override
    public void run() {
        while (running && !game.isGameOver()) {
            try {
                while (paused && running) {
                    Thread.sleep(100);
                }

                Thread.sleep(sleepTime);

                if (paused) continue;

                memory.markExplored(currentCell.row, currentCell.column);

                List<Cell> neighbors = game.getMaze().accesibleNeighbors(currentCell);
                Cell nextCell = null;

                for (Cell n : neighbors) {
                    if (!memory.isExplored(n.row, n.column)) {
                        nextCell = n;
                        break;
                    }
                }

                if (nextCell == null && !neighbors.isEmpty()) {
                    nextCell = neighbors.get(random.nextInt(neighbors.size()));
                }

                if (nextCell != null) {
                    if (game.getMaze().visit(nextCell.row, nextCell.column, this, currentCell)) {
                        currentCell = nextCell;
                        game.winCondition(currentCell);
                    }
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}