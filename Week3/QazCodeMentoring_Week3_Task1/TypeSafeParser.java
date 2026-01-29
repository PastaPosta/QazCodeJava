package QazCodeMentoring_Week3_Task1;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.function.Function;

public class TypeSafeParser {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите что-либо: ");
        Object result = parseInput(scan.nextLine());
        System.out.print("Результат: "+result+"\nЕго класс: "+result.getClass().getName());
    }

    public static Object parseInput(String input){
        for(TypeSafeParser.Types type: Types.values()){
            try{
                return type.parse(input.trim());
            }
            catch(Exception e){

            }
        }
        return input;
    }
    enum Types{
        INT(Integer::parseInt),
        DOUBLE(Double::parseDouble),
        BOOLEAN(s -> {
            if("true".equalsIgnoreCase(s)){
                return true;
            }
            if("false".equalsIgnoreCase(s)){
                return false;
            }
            else {
                throw new RuntimeException();
            }
        }),
        LOCALDATE(LocalDate::parse),
        STRING(s -> s);

        private final Function<String, ?> parser;

        Types(Function<String, ?> parser) {
            this.parser = parser;
        }

        public Object parse(String input){
            return parser.apply(input);
        }
    }

}