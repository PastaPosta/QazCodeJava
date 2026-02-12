package org.example.Test;

import static org.mockito.Mockito.*;

import org.example.AggregatedStatistics;
import org.example.AggregatorService;
import org.example.HttpClientService;
import org.example.RequestResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AggregatorServiceTest {

    private final List<String> urls = List.of("www.lox","www.top");
    @Test
    public void testCheckURLs(){
        HttpClientService mockService = mock(HttpClientService.class);
        when(mockService.checkURL("www.lox")).thenReturn(new RequestResult(200,false,50));
        when(mockService.checkURL("www.top")).thenReturn(new RequestResult(500, true, 1000));
        AggregatorService aggregatorService = new AggregatorService(mockService,urls);

        AggregatedStatistics stats = aggregatorService.checkURLs();
        Assertions.assertEquals(1,stats.getErrorCount());
        Assertions.assertEquals(1,stats.getSuccessCount());

        verify(mockService, times(2)).checkURL(anyString());
    }
}
