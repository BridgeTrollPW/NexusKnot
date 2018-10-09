package NexusKnotServer;

import NexusKnotServer.ClientHandler.SessionWorker;
import NexusKnotServer.Models.Client;
import NexusKnotServer.Models.Session;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class NexusKnotServer {

    public NexusKnotServer() {
        SessionWorker sessionWorker = new SessionWorker();
        ServerSocket server = null;
        Client pendingClient = null;
        try {
            server = new ServerSocket(4457);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        while (true) {

            try {
                pendingClient = new Client(server.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Session clientSessionThread = new Session(pendingClient);
            System.out.println("[SERVER]: New Client connected with session: " + clientSessionThread.getSessionId());
            sessionWorker.open(clientSessionThread);

            new Thread(() -> {
                Scanner serverSideInput = new Scanner(System.in);
                String input = "";
                while (serverSideInput.hasNextLine()) {
                    input = serverSideInput.nextLine();
                    if (input.equalsIgnoreCase("/list")) {
                        System.out.println(sessionWorker.getList());
                    } else {
                        System.out.println("[SERVER]: Unknow command: " + input);
                    }
                }
            }).start();

        }
    }
}