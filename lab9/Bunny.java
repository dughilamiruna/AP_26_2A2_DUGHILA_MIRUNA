package org.example;

import java.util.List;
import java.util.Random;

public class Bunny implements Runnable {

    private String name;
    private Main game;
    private Cell currentCell;
    private Random random = new Random();

    private volatile boolean running = true;
    private volatile int sleepTime = 2000;
    private volatile boolean paused = false;

    public Bunny(String name, Main game, Cell startCell) {
        this.name = name;
        this.game = game;
        this.currentCell = startCell;
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

                List<Cell> neighbors = game.getMaze().accesibleNeighbors(currentCell);

                if (!neighbors.isEmpty()) {
                    Cell nextCell = neighbors.get(random.nextInt(neighbors.size()));

                    if (game.getMaze().visit(nextCell.row, nextCell.column, this, currentCell)) {
                        currentCell = nextCell;
                        game.winCondition(currentCell);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}