package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main
{
    private final Maze maze;
    private Bunny bunny;
    private final List<Robot> robots=new ArrayList<>();
    private final SharedMemory memory;

    private volatile boolean gameOver=false;
    private final long startTime;
    private final long timeLimit=30000;
    public volatile boolean isPaused = false;

    public Main(int size)
    {
        maze=new Maze(size,size);
        memory=new SharedMemory(size, size);
        startTime= System.currentTimeMillis();
    }

    public void setBunny(Bunny bun) { this.bunny = bun; }
    public void addRobot(Robot rob) { this.robots.add(rob); }
    public Maze getMaze() { return maze; }
    public boolean isGameOver() { return gameOver; }

    public synchronized void winCondition(Cell cell)
    {
        if(cell.robot && cell.bunny)
        {
            System.out.println("GAME OVER!");
            stopGame();
        }
    }

    public void stopGame()
    {
        this.gameOver=true;
        if(bunny!=null) bunny.stopThread();
        for(Robot r:robots) r.stopThread();
    }

    public void start()
    {
        int initialSpeed = 2000;
        bunny.setSpeed(initialSpeed);
        for (Robot r : robots) r.setSpeed(initialSpeed);

        new Thread(bunny).start();
        for (Robot robot : robots) {
            new Thread(robot).start();
        }

        Thread daemon = new Thread(() -> {
            while (!gameOver) {
                if (!isPaused) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    if (elapsed > timeLimit) {
                        System.out.println("TIMEOUT!");
                        stopGame();
                        break;
                    }
                    System.out.println("[Running time: " + (elapsed / 1000) + "s]");
                    System.out.println(maze.toString());
                }

                try { Thread.sleep(2000); }
                catch (InterruptedException e) { break; }
            }
        });
        daemon.setDaemon(true);
        daemon.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Commands: p = pause | r = resume | - = slow down | + = speed up | x = stop");

        int currentSpeed = initialSpeed;

        while (!gameOver) {
            if (scanner.hasNextLine()) {
                String cmd = scanner.nextLine().trim().toLowerCase();

                if (cmd.equals("x") || cmd.equals("stop")) {
                    stopGame();
                } else if (cmd.equals("p") || cmd.equals("pause all")) {
                    isPaused = true;
                    bunny.pauseThread();
                    for (Robot r : robots) r.pauseThread();
                    System.out.println("Game paused");
                } else if (cmd.equals("r") || cmd.equals("resume all")) {
                    isPaused = false;
                    bunny.resumeThread();
                    for (Robot r : robots) r.resumeThread();
                    System.out.println("Game resumed");
                } else if (cmd.equals("-") || cmd.equals("slow down")) {
                    currentSpeed += 500;
                    bunny.setSpeed(currentSpeed);
                    for (Robot r : robots) r.setSpeed(currentSpeed);
                    System.out.println("Slowed down");
                } else if (cmd.equals("+") || cmd.equals("speed up")) {
                    currentSpeed = Math.max(100, currentSpeed - 500);
                    bunny.setSpeed(currentSpeed);
                    for (Robot r : robots) r.setSpeed(currentSpeed);
                    System.out.println("Speeded up");
                }
            }
        }
        System.exit(0);
    }

    public static void main(String[] args)
    {
        int size = 6;
        var game = new Main(size);
        Random random = new Random();

        int bRow = random.nextInt(size);
        int bCol = random.nextInt(size);
        game.setBunny(new Bunny("Bugs Bunny", game, game.getMaze().getCell(bRow, bCol)));

        int numRobots = 2;

        for (int index = 1; index <= numRobots; index++) {
            int rRow, rCol;

            do {
                rRow = random.nextInt(size);
                rCol = random.nextInt(size);
            } while (game.getMaze().getCell(rRow, rCol).bunny || game.getMaze().getCell(rRow, rCol).robot);

            game.addRobot(new Robot("R" + index, game, game.getMaze().getCell(rRow, rCol), game.memory));
        }

        game.start();
    }
