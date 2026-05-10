import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GameServer {

    public static final int PORT=1027;
    private static volatile boolean running=true;
    public static final ExecutorService pool = Executors.newFixedThreadPool(10);

    private static Player setupPlayer(Socket socket, String defaultName) throws IOException 
    {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        output.println("Welcome! Waiting for game to start...");
        return new Player(defaultName, socket, input, output);
    }

    private static void setupGracefulShutdown()
    {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nInitiating graceful shutdown...");
            running=false;
            pool.shutdown();
            try
            {
                if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                }
            }
            catch (InterruptedException exception)
            {
                pool.shutdown();
            }
            System.out.println("Server safely stopped.");
        }));
    }

    private static List<Question> loadQuestions(String file)
    {
        List<Question> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
                if (line.trim().isEmpty()) 
                {
                    continue;
                }

                String[] parts = line.split("\\|");
                
                if (parts.length == 6) 
                {
                    String text = parts[0];
                    String[] options = {parts[1], parts[2], parts[3], parts[4]};
                    
                    try 
                    {
                        int correctOption = Integer.parseInt(parts[5].trim());
                        list.add(new Question(text, options, correctOption));
                    } 
                    catch (NumberFormatException e) 
                    {
                        System.err.println("Error: " + line);
                    }
                } 
                else 
                {
                    System.err.println("Invalid format: " + line);
                }
            }
    } 
    catch (IOException e) 
    {
        System.err.println("Error at reading file " + file + ": " + e.getMessage());
    }
    
    return list;  
    }
    
    public static void main(String[] args) {
        setupGracefulShutdown();  

        List<Question> questions = loadQuestions("questions.txt");
        System.out.println("Server started. Loaded " + questions.size() + " questions.");

        try(ServerSocket serverSocket=new ServerSocket(PORT))
        {
            while(running)
            {
                System.out.println("Waiting for players...");

                Socket socket1=serverSocket.accept();
                Player player1=setupPlayer(socket1, "Player 1");
                System.out.println("Player 1 joined. Waiting for opponent...");

                Socket socket2=serverSocket.accept();
                Player player2=setupPlayer(socket2, "Player 2");
                System.out.println("Player 2 joined. Match started!");

                pool.execute(new QuizMatch(player1, player2, questions));
            }
        }
        catch(IOException exception)
        {
            if(running) exception.printStackTrace();
        }
    }
}
