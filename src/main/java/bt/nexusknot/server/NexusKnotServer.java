package bt.nexusknot.server;


import bt.nexusknot.service.tasksystem.TaskService;
import bt.nexusknot.util.Config;

import java.io.IOException;
import java.net.ServerSocket;

import java.util.Properties;
import java.util.logging.Logger;

public class NexusKnotServer
{

    private static final Logger logger = Logger.getLogger(NexusKnotServer.class.getName());
    public static final Properties logMessages = Config.get("configuration/log_messages.properties");


    public static void run()
    {
        logger.info(logMessages.getProperty("server_start"));

        ServerSocket server = null;

        try
        {
            server = new ServerSocket(4457);
        }
        catch (IOException e1)
        {
            e1.printStackTrace();
        }

        //Actual server tasks
        TaskService taskService = new TaskService();
        taskService.run();
    }
}