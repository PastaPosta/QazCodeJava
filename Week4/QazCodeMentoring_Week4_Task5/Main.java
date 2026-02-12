
import Controller.Controller;
import Repository.Repository;
import Service.ServiceImpl;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository();
        ServiceImpl service = new ServiceImpl(repository);
        Controller controller = new Controller(scanner, service);
        controller.run();
    }
}