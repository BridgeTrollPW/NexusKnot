package NexusKnotServer.Utils;

import NexusKnotServer.Models.HTTP.Response;

import java.util.HashMap;
import java.util.Map;

public class HTTPClient
{
    private String targetUrl;
    private Map<String, String> headers;

    public HTTPClient()
    {
        this.headers = new HashMap<>();
    }

    public HTTPClient setUrl(String url)
    {
        this.targetUrl = url;
        return this;
    }

    public HTTPClient setHeader(String key, String value)
    {
        this.headers.put(key, value);
        return this;
    }

    /**
     * @deprecated NOT IMPLEMENTED
     * @param headers
     * @return
     */
    public HTTPClient setHeaders(HashMap<String, String> headers)
    {
        return this;
    }

    public Response get()
    {

    }
}
