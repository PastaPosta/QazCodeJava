package QazCodeMentoring_Week3_Task10;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileProcessor {
    public static void main(String[] args){
        Path path = Paths.get("sample.txt");
        try(Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            int result = lines
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .mapToInt(String::length)
                    .sum();
            System.out.println("Длина всех строк документа: "+ result);
        }
        catch(IOException exception){
            System.out.println("error happened "+ exception);
        }
    }
}
