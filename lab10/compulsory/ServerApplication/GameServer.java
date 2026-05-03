package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static final int PORT=1027;
    private boolean running=true;
    private ServerSocket serverSocket;

    public GameServer()
    {
        try
        {
            serverSocket=new ServerSocket(PORT);
            System.out.println("Server listening on port " + PORT);

            while(running)
            {
                Socket socket =serverSocket.accept();
                System.out.println("New client connected");
                new ClientThread(socket, this).start();
            }
        } catch (IOException exception) {
            if(!running)
            {
                System.out.println("Server stopped with success");
            }
            else
            {
                System.out.println("Server starting error: " + exception.getMessage());
            }
        }
    }

    public void stopServer()
    {
        this.running=false;
        try
        {
            if(serverSocket!=null)
            {
                serverSocket.close();
            }
        }
        catch (IOException exception)
        {
            System.err.println("Server stopping error: " + exception.getMessage());
        }
    }

    public static void main(String[] args) {
        new GameServer();
    }
}
