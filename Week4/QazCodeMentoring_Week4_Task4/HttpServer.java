import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        try(ServerSocket socket = new ServerSocket(8080);
            ExecutorService executorService = Executors.newFixedThreadPool(6);){
            while(true){
                Socket connected = socket.accept();
                executorService.submit(() -> {
                    try{
                        handleConnection(connected);
                    }
                    catch(IOException e){
                        System.out.println(e);
                    }
                });
            }
        }
    }
    private static void handleConnection(Socket connected) throws IOException{
        try(BufferedReader readRequest = new BufferedReader(new InputStreamReader(connected.getInputStream()));
            PrintWriter writeResponse = new PrintWriter(connected.getOutputStream())){
            System.out.println(readRequest.readLine());
            writeResponse.println("HTTP/1.1 200 OK");
            writeResponse.println("Content-Type: text/html");
            writeResponse.println("");
            writeResponse.println("<h1>Hi from QazCode</h1>");
            writeResponse.flush();
        }
    }

}

