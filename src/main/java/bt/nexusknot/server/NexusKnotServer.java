package bt.nexusknot.server;


import bt.nexusknot.service.tasksystem.TaskService;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.logging.Logger;

public class NexusKnotServer {

    private static final Logger logger = Logger.getLogger(NexusKnotServer.class.getName());

    public static void run()
    {
        logger.info("server is starting");

        ServerSocket server = null;

        try {
            server = new ServerSocket(4457);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        //Actual server tasks
        TaskService taskService = new TaskService();
        taskService.run();

        logger.info("test");
    }
}