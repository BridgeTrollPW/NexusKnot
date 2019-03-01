package bt.nexusknot.service.tasksystem;

import bt.nexusknot.model.http.Response;
import bt.nexusknot.service.tasksystem.boundary.Task;
import bt.nexusknot.util.HTTPClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class TaskService
{
    private static Logger logger = Logger.getLogger(TaskService.class.getSimpleName());
    private boolean active;
    private List<Task> taskSchedule;

    public TaskService()
    {
        HTTPClient http = new HTTPClient();
        http.setUrl("api.photon.nexusknot.intern/server/v1");
        http.setProtocol(HTTPClient.Protocol.HTTP);
        try
        {
            Response r = http.get();
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            String[] json = gson.fromJson(r.getBody(), String[].class);

            logger.info(r.getBody());
        }
        catch (IOException e)
        {
            // @todo create response object still on failure, e.g. on a 404
            e.printStackTrace();
        }
    }

    public void run()
    {
        TaskScheduler taskWorker = new TaskScheduler();
        taskWorker.start();
    }
}
