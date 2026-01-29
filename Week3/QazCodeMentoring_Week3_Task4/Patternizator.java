package QazCodeMentoring_Week3_Task4;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patternizator {
    public static void main(String[] args){
        Map<String, Object> db = Map.of("name","Ivan","balance",123.456);
        StringBuilder stringBuilder = new StringBuilder();

        String sample = "Привет, ${name}! Твой баланс: ${balance:%.2f} руб.";
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
        Matcher matcher = pattern.matcher(sample);
        while(matcher.find()){
            String placeholder = matcher.group(1);
            String key;
            String format = null;

            if (placeholder.contains(":")){
                String[] parts = placeholder.split(":");
                key = parts[0];
                format = parts[1];
            }
            else key = placeholder;

            Object value = db.get(key);
            if (value == null){
                matcher.appendReplacement(stringBuilder, "undefined");
                continue;
            }

            String replace;
            if (format !=null){
                replace = String.format(format, value);
            }
            else{
                replace = value.toString();
            }

            matcher.appendReplacement(stringBuilder,replace);
        }
        matcher.appendTail(stringBuilder);
        System.out.print("Строка после обработки: \n"+stringBuilder.toString());
    }
}
