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
            System.out.println("Connected to the server! Enter a command: ");
            while(true)
            {
                String command= scanner.nextLine();
                if(command.equalsIgnoreCase("exit"))
                {
                    System.out.println("Client is closing...");
                    break;
                }

                out.println(command);
                String response=in.readLine();
                System.out.println(response);

                if (command.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        }
        catch (UnknownHostException exception)
        {
            System.err.println("Unknown Host: " + exception.getMessage());
        }
        catch (IOException exception)
        {
            System.err.println("I/O error: " + exception.getMessage());
        }
    }
}
