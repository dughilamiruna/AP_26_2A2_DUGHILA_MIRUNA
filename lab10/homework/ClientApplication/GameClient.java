import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args)
    {
        String serverAddress = "127.0.0.1";
        int PORT = 1027;

        try( Socket socket=new Socket(serverAddress, PORT);
            PrintWriter out=new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner=new Scanner(System.in); )
        {
           Thread listenerThread = new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } 
                catch (IOException e) 
                {
                    System.out.println("Disconnected from server.");
                }
            });
            listenerThread.start();

            while (true) {
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(input);
            }
        }
        catch (UnknownHostException exception)
        {
            System.err.println("Unknown Host: " + exception.getMessage());
        }
        catch (IOException exception)
        {
            System.err.println("Connection error: " + exception.getMessage());
        }
    }
}
