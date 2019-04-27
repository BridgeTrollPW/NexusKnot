package bt.nexusknot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class Config
{
    private static Logger logger = Logger.getLogger(Config.class.getSimpleName());

    public static Properties get(String filename)
    {
        Properties p = new Properties();
        logger.info("Loading configuration for Startup.");
        try
        {
            URL url = Config.class.getClassLoader().getResource(filename);
            if (url == null)
            {
                throw new IOException("Error finding config file");
            }
            p.load(new FileInputStream(new File(url.getPath())));
        }
        catch (IOException e)
        {
            logger.severe("Error loading config " + filename + " :: " + e.getMessage());
        }
        return p;
    }
}
