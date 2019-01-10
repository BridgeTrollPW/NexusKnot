package NexusKnotServer.TaskHandler;

import NexusKnotServer.Models.HTTP.Response;
import NexusKnotServer.Models.Task;
import NexusKnotServer.Utils.HTTPClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TaskWorker {
    private List<Task> taskList;
    private static Logger logger = Logger.getLogger(TaskWorker.class.getSimpleName());

    public TaskWorker()
    {
        HTTPClient http = new HTTPClient();
        http.setUrl("api.photon.nexusknot.intern/server/v1");
        http.setProtocol(HTTPClient.Protocol.HTTP);
        try {
            Response r = http.get();
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            String[] json = gson.fromJson(r.getBody(), String[].class);

            logger.info(r.getBody());
        } catch (IOException e) {
            // @todo create response object still on failure, e.g. on a 404
            e.printStackTrace();
        }
    }
}
