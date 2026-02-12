import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ListSum {
    public static void main(String[] args) {
        int threads = 6;
        int totalSum = 0;
        List<Integer> numbers = new ArrayList<>();
        for(int i = 0; i<101; i++){
            numbers.add(i);
        }
        try(ExecutorService executorService = Executors.newFixedThreadPool(threads)) {
            int part = numbers.size() / threads;
            List<Callable<Integer>> callables = new ArrayList<>();
            for (int i = 0; i < threads; i++) {
                int starting = i * part;
                int end = (i == threads - 1) ? numbers.size() : starting + part;
                Callable<Integer> callable = () -> {
                    int sum = 0;
                    for(int x: numbers.subList(starting,end)){
                        sum+=x;
                    }
                    return sum;
                };
                callables.add(callable);
            }
            List<Future<Integer>> promises = executorService.invokeAll(callables);
            for(Future<Integer> promise : promises){
                totalSum+=promise.get();
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(totalSum);
    }
 }