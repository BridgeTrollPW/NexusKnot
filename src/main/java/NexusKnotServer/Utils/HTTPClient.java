package NexusKnotServer.Utils;

import NexusKnotServer.Models.HTTP.ErrorCode;
import NexusKnotServer.Models.HTTP.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class HTTPClient {

    public enum Protocol {
        HTTP,
        HTTPS
    }

    private String targetUrl;
    private Protocol protocol;
    private HttpURLConnection connection;
    private Map<String, String> headers;

    public HTTPClient() {
        this.headers = new HashMap<>();
        this.protocol = Protocol.HTTP;
    }

    public HTTPClient setUrl(String url) {
        this.targetUrl = url;
        return this;
    }

    public HTTPClient setProtocol(Protocol protocol) {
        this.protocol = protocol;
        return this;
    }

    public HTTPClient setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    /**
     * @param headers
     * @return
     * @deprecated NOT IMPLEMENTED
     */
    public HTTPClient setHeaders(HashMap<String, String> headers) {
        return this;
    }

    public Response get() throws IOException {
        try {
            connection = (HttpURLConnection) new URL(this.protocol.toString() + "://" + this.targetUrl).openConnection();
        } catch (MalformedURLException malformedUrl) {
            Response httpResponse = new Response();
            httpResponse.setError(ErrorCode.MalformedURL);
            return httpResponse;
        } catch (IOException e) {
            Response httpResponse = new Response();
            ErrorCode error = ErrorCode.Unknown;
            error.setMessage(e.getMessage());
            httpResponse.setError(error);
            return httpResponse;
        }

        this.headers.entrySet()
                .parallelStream()
                .peek(header -> connection.setRequestProperty(header.getKey(), header.getValue()));

        connection.setUseCaches(false);
        connection.setDoOutput(true);
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            Response httpResponse = new Response();
            httpResponse.setError(ErrorCode.ProtocolError);
            return httpResponse;
        }
        try {
            connection.connect();
        } catch (IOException e) {
            Response httpResponse = new Response();
            ErrorCode error = ErrorCode.Unknown;
            error.setMessage(e.getMessage());
            httpResponse.setError(error);
            return httpResponse;
        }

        //Get Response
        InputStream is = connection.getInputStream();

        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        Response httpResponse = new Response();
        httpResponse.setContentLength(connection.getContentLength());
        httpResponse.setBody(response.toString());
        httpResponse.setResponseCode(connection.getResponseCode());

        return httpResponse;
    }
}
