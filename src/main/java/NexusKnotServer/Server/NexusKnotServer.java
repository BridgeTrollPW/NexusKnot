package NexusKnotServer.Server;

import NexusKnotServer.ClientHandler.SessionWorker;
import NexusKnotServer.Models.Client;
import NexusKnotServer.Models.Session;
import NexusKnotServer.TaskHandler.TaskWorker;


import java.io.IOException;
import java.net.ServerSocket;

import java.util.Scanner;
import java.util.logging.Logger;

public class NexusKnotServer {

    private static final Logger logger = Logger.getLogger(NexusKnotServer.class.getName());

    public NexusKnotServer()
    {
        logger.info("Server is starting");

        ServerSocket server = null;

        try {
            server = new ServerSocket(4457);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //Actual server tasks
        TaskWorker taskWorker = new TaskWorker();

        // Client and serverside routine
        Client pendingClient = null;
        SessionWorker sessionWorker = new SessionWorker();

        while (true) {

            try {
                pendingClient = new Client(server.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Session clientSessionThread = new Session(pendingClient);
            logger.info("New Client connected with session: " + clientSessionThread.getSessionId());
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