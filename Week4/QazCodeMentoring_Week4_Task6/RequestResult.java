package org.example;

public class RequestResult {
    int statusCode;
    boolean error;
    long elapsed;

    public RequestResult(int statusCode, boolean error, long elapsed) {
        this.statusCode = statusCode;
        this.error = error;
        this.elapsed = elapsed;
    }
}
