import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class JavaClientDummy {
    public static void main(String args[])
    {
        try {
            Socket client = new Socket("127.0.0.1", 4457);
            DataOutputStream input = new DataOutputStream(client.getOutputStream());
            Scanner s = new Scanner(System.in);
            while(s.hasNextLine())
            {
                input.writeUTF(s.nextLine());
                input.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
