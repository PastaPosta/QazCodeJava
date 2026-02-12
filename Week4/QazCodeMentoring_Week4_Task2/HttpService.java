
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;


public class HttpService{
    private final HttpClient httpClient;
    private static final Logger logger = Logger.getLogger("HttpService");
    HttpService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public String getResponseBody(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(url)).build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            logger.info("Status code: " + response.statusCode());
            return response.body();
        }
        catch(URISyntaxException | IOException | InterruptedException e){
            throw new RuntimeException("Fail", e);
        }
    }
}
