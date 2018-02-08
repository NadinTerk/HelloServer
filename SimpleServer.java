package HelloServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to Server side");

        ServerSocket servers = null;
        Socket fromclient = null;

        // create server socket
        try {
            servers = new ServerSocket(4444, 0, InetAddress.getByName("192.168.0.63"));
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            fromclient = servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        BufferedReader in = new BufferedReader(new
                InputStreamReader(fromclient.getInputStream()));
        PrintWriter out = new PrintWriter(fromclient.getOutputStream(), true);
        BufferedReader readme = new
                BufferedReader(new InputStreamReader(System.in));
        String input, justread;

        System.out.println("Wait for messages");
        while ((input = in.readLine()) != null) {
            if (input.equalsIgnoreCase("exit")) break;
            System.out.println("Client ::: " + input);
            justread = readme.readLine();
            out.println("S ::: " + justread);

        }

        out.close();
        in.close();
        fromclient.close();
        servers.close();
    }
}
