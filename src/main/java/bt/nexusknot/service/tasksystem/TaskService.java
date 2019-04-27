package bt.nexusknot.service.tasksystem;

import bt.nexusknot.server.NexusKnotServer;

import java.util.logging.Logger;

public class TaskService
{
    private static Logger logger = Logger.getLogger(TaskService.class.getSimpleName());

    public TaskService()
    {
        logger.info(NexusKnotServer.logMessages.getProperty("taskservice_start"));
    }

    public void run()
    {
        TaskScheduler taskWorker = new TaskScheduler();
        taskWorker.start();
    }
}
