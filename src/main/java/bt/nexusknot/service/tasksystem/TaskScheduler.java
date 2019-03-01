package bt.nexusknot.service.tasksystem;

import bt.nexusknot.service.tasksystem.boundary.Task;
import bt.nexusknot.service.tasksystem.model.Heartbeat;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static bt.nexusknot.service.tasksystem.model.TaskStatus.*;

public class TaskScheduler extends Thread
{
    private static Logger logger = Logger.getLogger(TaskScheduler.class.getSimpleName());
    private List<Task> taskList;
    private boolean running;

    TaskScheduler()
    {
        this.taskList = new ArrayList<>();
        this.taskList.add(new Heartbeat());
    }

    @Override
    public void run()
    {
        this.running = true;
        while (this.running)
        {
            taskList.parallelStream()
                    .forEach(task ->
                    {
                        switch (task.getReport().getStatus())
                        {
                            case PREPARATION:
                                logger.info("Preparing Task " + task.getName());
                                task.prepare();
                                logger.info("Task " + task.getName() + " is now awaiting execution");
                                break;
                            case AWAIT_EXECUTION:
                                logger.info("Executing Task " + task.getName());
                                new Thread(task).start();
                                break;
                            case ERROR:
                            case SUCCESS:
                                taskList.remove(task);
                                break;
                            default:
                                logger.info("Panic! Unknown Task " + task.getName());
                                break;
                        }
                    });
        }
    }

    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
}
