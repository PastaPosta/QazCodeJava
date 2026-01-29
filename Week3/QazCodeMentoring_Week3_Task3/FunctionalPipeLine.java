package QazCodeMentoring_Week3_Task3;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionalPipeLine {
    public static Function<String, String> trim = String::trim;
    public static Predicate<String> isNotEmpty = s -> !s.isEmpty();
    public static Function<String, Optional<Integer>> parser = s -> {
        try{
            return Optional.of(Integer.parseInt(s));
        }
        catch(NumberFormatException numberFormatException){
            return Optional.empty();
        }
    };
    public static Predicate<Integer> isEven = i -> i%2 == 0;


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        System.out.println("Напишите число для добавления в список на очередь обработки. Напишите qwerty для остановки цикла");
        while(true){
            String in = scan.nextLine();
            if(in.equalsIgnoreCase("qwerty")){
                break;
            }
            input.add(in);
            System.out.println("Напишите следующее: ");
        }
        //List<Integer>
        int result = input.stream()
                .map(trim)
                .filter(isNotEmpty)
                .map(parser)
                .flatMap(Optional::stream)
                .filter(isEven)
                //.toList();
                .mapToInt(Integer::intValue).sum();

        System.out.println("Результат обработки: "+ result);
    }

}
