package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class AggregatorService {
    private final HttpClientService httpClientService;

    List<String> urls;

    public AggregatorService(HttpClientService httpClientService, List<String> urls) {
        this.httpClientService = httpClientService;
        this.urls = urls;
    }

    public AggregatedStatistics checkURLs(){
        List<Callable<RequestResult>> callables = new ArrayList<>();
        List<RequestResult> requestResults = new ArrayList<>();
        for(String url: urls){
            Callable<RequestResult> task = () -> httpClientService.checkURL(url);
            callables.add(task);
        }
        try(ExecutorService executorService = Executors.newFixedThreadPool(10)){
            List<Future<RequestResult>> promises = executorService.invokeAll(callables);
            for(Future<RequestResult> promise: promises){
                requestResults.add(promise.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return new AggregatedStatistics(requestResults);
    }
}
