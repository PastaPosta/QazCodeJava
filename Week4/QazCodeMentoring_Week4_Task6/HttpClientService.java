package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpClientService {
    Logger logger = Logger.getLogger("HttpClienService");
    HttpClient httpClient;
    public HttpClientService(HttpClient httpClient) {
        this.httpClient = httpClient;

    }
    public RequestResult checkURL(String url) {
        logger.log(Level.INFO, "Start request to "+url);
        long start = System.currentTimeMillis();
        try{
            HttpResponse<Void> response = httpClient.send(HttpRequest.newBuilder().GET().uri(new URI(url)).build(), HttpResponse.BodyHandlers.discarding());
            long elapsed = System.currentTimeMillis() - start;
            boolean error = true;
            if (response.statusCode() == 200) {
                error = false;
            }
            logger.log(Level.INFO, "Finish request to "+url);
            return new RequestResult(response.statusCode(), error, elapsed);
        }
        catch(Exception e){
            long elapsed = System.currentTimeMillis() - start;
            logger.log(Level.SEVERE, "Failed to send request"+e);
            boolean error = true;
            return new RequestResult(500,error,elapsed);
        }
    }

}
