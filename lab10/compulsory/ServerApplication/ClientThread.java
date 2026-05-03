import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private GameServer server;

    public ClientThread(Socket socket, GameServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run()
    {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true) )
        {
            String request;

            while((request=in.readLine())!=null)
            {
                System.out.println("Command from client: " + request);

                if(request.equalsIgnoreCase("stop"))
                {
                    out.println("Server stopped");
                    server.stopServer();
                    break;
                }
                else
                {
                    out.println("Server received the request: " + request);
                }
            }
        }
        catch (IOException exception)
        {
            System.out.println("Communication error");
        }
        finally {
            try {
                socket.close();
            } catch (IOException exception) {
                System.err.println("Socket closing error: " + exception.getMessage());
            }
        }
    }
}
