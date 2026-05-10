import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {

    private final String name;
    public final BufferedReader input;
    public final PrintWriter output;
    public final Socket socket;
    private int score=0;
    private long responseTime=0;

    public Player(String name, Socket socket, BufferedReader input, PrintWriter output) {
        this.name = name;
        this.socket = socket;
        this.input = input;
        this.output = output;
    }

    public void addScore()
    {
        this.score++;
    }
    
    public void addTime(long timeMs)
    {
        this.responseTime+=timeMs;
    }

    public int getScore()
    {
        return score;
    }

    public long getResponseTime()
    {
        return responseTime;
    }

    public String getName()
    {
        return name;
    }
}
