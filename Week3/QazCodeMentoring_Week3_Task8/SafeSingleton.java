package QazCodeMentoring_Week3_Task8;

import java.io.*;
import java.lang.reflect.Constructor;

public class SafeSingleton implements Serializable {

    private static boolean isInstanceCreated = false;

    private SafeSingleton() {
        if (isInstanceCreated) {
            throw new RuntimeException("no reflection today nah!");
        }
        isInstanceCreated = true;
        System.out.println("иициализировано");
    }

    private static class SingletonHolder {
        private static final SafeSingleton INSTANCE = new SafeSingleton();
    }

    public static SafeSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Serial
    private Object readResolve() {
        return getInstance();
    }

    public void printConfig() {
        System.out.println("Lock'n Load");
    }

}
