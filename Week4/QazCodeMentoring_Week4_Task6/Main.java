package org.example;

import java.net.http.HttpClient;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try(HttpClient client = HttpClient.newHttpClient()){
            HttpClientService clientService = new HttpClientService(client);
            List<String> urls = List.of(
                    "https://google.com",                // Должен быть 200 (Success)
                    "https://github.com",                // Должен быть 200 (Success)
                    "https://google.com/non-existent",   // Должен быть 404 (Error, так как != 200)
                    "https://httpbin.org/delay/2",       // Задержка 2 сек (Для проверки параллельности)
                    "https://invalid.domain.test"        // Ошибка (UnknownHostException -> попадет в catch)
            );
            AggregatorService aggregatorService = new AggregatorService(clientService,urls);
            System.out.println(aggregatorService.checkURLs().toString());
        }


    }
}