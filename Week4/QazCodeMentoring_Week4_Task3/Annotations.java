import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.KeyPair;
import java.util.*;
import java.util.concurrent.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Cached{

}

public class Annotations {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        class Test{
            @Cached
            public int sum(int a, int b){
                System.out.println("Call without cache, args: "+a+" "+b);
                return a+b;
            }
        }
        Test test = new Test();
        invoke(test, "sum", 2,3);
        invoke(test, "sum",2,3);
        invoke(test, "sum",4,3);
    }

    static Map<List<Object>, Object> cache = new HashMap<>();

    public static Object invoke(Object target, String methodName, Object... args) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = target.getClass().getMethods();
        List<Object> argsList = Arrays.asList(args);

        if(cache.containsKey(List.of(methodName, argsList))){
            return cache.get(List.of(methodName, argsList));
        }

        Object obj = null;

        for(Method method : methods){
            if (method.getName().equals(methodName)){
                obj = method.invoke(target, args);
                if(method.isAnnotationPresent(Cached.class)) {
                    cache.put(List.of(methodName, argsList), obj);
                }
                break;
            }
        }
        return obj;
    }
}

