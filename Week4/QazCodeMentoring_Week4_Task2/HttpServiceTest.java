import org.junit.jupiter.api.*;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import static org.mockito.Mockito.*;

public class HttpServiceTest{

    @Test
    public void testGetResponseBody() throws IOException, InterruptedException{
        HttpClient mockClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("Fake Body");

        when(mockClient.send(any(), any())).thenReturn((HttpResponse) mockResponse);

        HttpService httpService = new HttpService(mockClient);
        String result = httpService.getResponseBody("http://owa.beeline.kz");

        Assertions.assertEquals("Fake Body", result);
        verify(mockClient,times(1)).send(any(),any());
    }

    @Test
    public void testGetResponseBodyThrows() throws IOException, InterruptedException {
        HttpClient mockClient = mock(HttpClient.class);
        HttpResponse<String> mockResponse = mock(HttpResponse.class);

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn("Fake Body");

        when(mockClient.send(any(),any())).thenThrow(new IOException());
        HttpService httpService = new HttpService(mockClient);

        Assertions.assertThrows(RuntimeException.class, () -> {
            httpService.getResponseBody("http://owa.beeline.kz");
        });

    }
}
