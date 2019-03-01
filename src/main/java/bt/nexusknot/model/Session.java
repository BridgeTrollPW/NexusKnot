package bt.nexusknot.model;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.UUID;

public class Session extends Thread{
    private Client client;
    private String sessionId;

    public Session (Client client)
    {

        this.client = client;
        this.sessionId = UUID.randomUUID().toString();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try(DataInputStream clientInput = new DataInputStream(this.client.getSocket().getInputStream()))
        {
            for(;;) {
                try {
                    String message = clientInput.readUTF();
                    System.out.println("[Client::"+this.sessionId+"]: " + message);
                }catch (EOFException endofstream)
                {
                    System.out.println("end of stream/client disconnected abruptly");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("[Client::"+this.sessionId+"] disconnected");
            try {
                this.client.getSocket().close();
            } catch (IOException e1) {
                System.out.println("[Client::"+this.sessionId+"] disconnected (unable to close socket)");
            }
        }
    }

    public String getSessionId() {
        return sessionId;
    }
}
