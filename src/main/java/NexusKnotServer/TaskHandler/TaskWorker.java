package NexusKnotServer.TaskHandler;

import NexusKnotServer.Models.HTTP.Response;
import NexusKnotServer.Models.Task;
import NexusKnotServer.Utils.HTTPClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskWorker {
    private List<Task> taskList;

    public TaskWorker()
    {
        HTTPClient http = new HTTPClient();
        http.setUrl("api.photon.nexusknot.intern/server/v1/ping");
        http.setProtocol(HTTPClient.Protocol.HTTP);
        try {
            Response r = http.get();
            Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
            String[] json = gson.fromJson(r.getBody(), String[].class);

            System.out.println(json[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
