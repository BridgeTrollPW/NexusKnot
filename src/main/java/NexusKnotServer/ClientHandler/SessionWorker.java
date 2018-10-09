package NexusKnotServer.ClientHandler;

import NexusKnotServer.Models.Client;
import NexusKnotServer.Models.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionWorker {
    private List<Session> openSessions;

    public SessionWorker()
    {
        this.openSessions = new ArrayList<>();
    }

    public void open(Session s)
    {
        s.start();
        this.openSessions.add(s);
    }

    public String getList()
    {
        String output = "";
        for(Session s : this.openSessions)
        {
            output += s.getSessionId() + ", ";
        }
        return output;
    }
}
